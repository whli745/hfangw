package lucene;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import pt.wzgl.nrgl.pojo.InfoTcontent;
import pt.wzgl.nrgl.service.IInfoTcontentService;
import util.BaseParameter;
import util.Common;

/**
 * @Description 全文检索引擎
 * @author Liangmh
 * @createDate 2016-1-5 上午11:42:30 
 */
public class LuceneUtil {
	
	/** 新闻发布Service */
	private IInfoTcontentService infoTcontentService;
	
	/**
	 * 生成新闻发布检索索引
	 * @createDate 2016-1-5 上午11:55:19 
	 * @author Liangmh
	 */
	public void generateWebNewsInfoIndex(HttpServletRequest request){
		IndexWriter indexWriter = null;
		try {
			List<InfoTcontent> tcontents = infoTcontentService.getAllInfoTcontents();
			// 1. 创建 Directory (索引存放位置)
			Directory dir = FSDirectory.open(new File(getIndexPosition(request) + BaseParameter.LUCENE_INDEX_INFOTCONTENT));
			// 2. 创建分词器
			Analyzer analyzer = new PaodingAnalyzer();
			// 3. 创建IndexWriter 写索引
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_36, analyzer);
			iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			indexWriter = new IndexWriter(dir, iwc);
			// 4. 创建Document 对象 field
			Document document = null;
			for(InfoTcontent t : tcontents){
				document = new Document();
				// 5. 为Documen添加field
				document.add(new Field("contentMainTitle", t.getContentMainTitle(),Field.Store.YES, Field.Index.NOT_ANALYZED));
				if(!Common.isNullOrSpace(t.getContentSubTitle())){
					document.add(new Field("contentSubTitle", t.getContentSubTitle(),Field.Store.YES, Field.Index.ANALYZED));
				}
				document.add(new Field("content", t.getContent(),Field.Store.YES, Field.Index.ANALYZED));
				
				// 6. 通过IndexWriter 添加文档到索引中
				indexWriter.addDocument(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (indexWriter != null) {
				try {
					indexWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获取索引文件路径
	 * @return
	 * @createDate 2016-1-5 下午2:09:52 
	 * @author Liangmh
	 */
	@SuppressWarnings("deprecation")
	public static String getIndexPosition(HttpServletRequest request) {
		String indexPosition = BaseParameter.LUCENE_INDEX_POS;
		if (Common.isNullOrSpace(indexPosition)) {
			throw new RuntimeException("lucene 索引位置为空！");
		} else if (indexPosition.contains(":")) {
			return indexPosition;
		} else {
			return request.getRealPath("/") + indexPosition;
		}
	}

	/**
	 * 获取  infoTcontentService
	 * @return the infoTcontentService
	 */
	public IInfoTcontentService getInfoTcontentService() {
		return infoTcontentService;
	}

	/**
	 * 设置  infoTcontentService
	 * @param infoTcontentService the infoTcontentService to set
	 */
	public void setInfoTcontentService(IInfoTcontentService infoTcontentService) {
		this.infoTcontentService = infoTcontentService;
	}
}

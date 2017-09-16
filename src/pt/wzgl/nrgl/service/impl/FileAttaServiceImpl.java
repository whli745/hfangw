package pt.wzgl.nrgl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import pt.wzgl.nrgl.dao.IFileAttaDao;
import pt.wzgl.nrgl.pojo.FileAtta;
import pt.wzgl.nrgl.service.IFileAttaService;
import util.BaseParameter;
import util.UploadFileTools;

public class FileAttaServiceImpl implements IFileAttaService{
	
	private IFileAttaDao fileAttaDao;
	
	public IFileAttaDao getFileAttaDao() {
		return fileAttaDao;
	}
	public void setFileAttaDao(IFileAttaDao fileAttaDao) {
		this.fileAttaDao = fileAttaDao;
	}
	
	/**
	 * 取文件大小
	 * @author shimh
	 * @date 2013-9-25
	 * @param fileAtta
	 * @return
	 * @throws Exception
	 */
	public long getFileSize(File fileAtta) throws Exception{
		long s = 0;
		FileInputStream fis = null;
		try {
			if(fileAtta.exists()){
				fis = new FileInputStream(fileAtta);
				s = fis.available();
			}
		} catch (Exception e) {
		} finally {
			if(fis != null) fis.close();
		}
		return s;
	}
	
	@Override
	public FileAtta uploadFile(File atta, String attaName, String attaPath) throws Exception {
		FileAtta item = new FileAtta();
		// 有上传附件
		if (atta != null && atta.exists()) {
			String excelPath = UploadFileTools.uploadFile(attaPath, attaName, atta);
//			String itemPath = excelPath.substring(excelPath.lastIndexOf("/"));
//			itemPath = attaPath + itemPath;
			item.setAttaName(attaName);// 附件名称
			item.setAttaPath(excelPath);// 附件路径
			item.setAttaItemPath(excelPath);//附件项目路径，在附件为图片时使用
			
			item.setAttaType(attaName.substring(attaName.lastIndexOf(".") + 1));// 附件类型
			item.setAttaSize((int) getFileSize(atta));// 附件大小

			item.setUploadTime(new Date());// 上传时间
			item.setDelFlag(BaseParameter.NO);// 是否删除 0-未删除 1-删除
			item.setAttaDesc("");// 上传
			fileAttaDao.saveFileAtta(item);
		}
		return item;
	}
	
	public static void main(String[] args) {
		String aa = "D:/workspace/zwdt_1.0_sql_standard/WebRoot/zwdt/contentManager/upload/content/1417588702755865.jpg";
		System.out.println(aa.substring(aa.lastIndexOf("/") + 1));
	}
	
	@Override
	public FileAtta getFileAtta(String attaId) throws Exception {
		return fileAttaDao.getFileAtta(attaId);
	}
	
	@Override
	public void deleteFileAtta(String attaId) throws Exception {
		fileAttaDao.deleteFileAtta(attaId);
	}
	
	@Override
	public void deleteFileAtta(FileAtta fileAtta) throws Exception {
		fileAttaDao.deleteFileAtta(fileAtta);
	}
}

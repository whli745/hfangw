package pt.xtgl.jcgl.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import pt.xtgl.jcgl.dao.ISysNoticeDao;
import pt.xtgl.jcgl.pojo.SysNotice;
import pt.xtgl.jcgl.service.ISysNoticeService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.UploadFileTools;

public class SysNoticeServiceImpl implements ISysNoticeService {
	@Autowired
	private ISysNoticeDao sysNoticeDao;

	@Override
	public void deleteSysNotice(String noticeId) throws Exception {

		SysNotice sysNotice = sysNoticeDao.querySysNotice(noticeId);
		sysNoticeDao.deleteSysNotice(sysNotice);
	}

	@Override
	public SysNotice querySysNotice(String noticeId) throws Exception {

		SysNotice sysNotice = sysNoticeDao.querySysNotice(noticeId);
		return sysNotice;
	}
	
	@Override
	public ResultPage queryNoticeList(String noticeTitle, String areaId,
			String serviceType, int page, int pageRows) throws Exception {
		return sysNoticeDao.queryNoticeList(noticeTitle, areaId, serviceType, page, pageRows);
	}

	@Override
	public void saveSysNotice(SysNotice sysNotice) throws Exception {

		sysNoticeDao.saveSysNotice(sysNotice);
	}

	@Override
	public void updateSysNotice(SysNotice sysNotice) throws Exception {

		sysNoticeDao.updateSysNotice(sysNotice);

	}

	public ISysNoticeDao getSysNoticeDao() {
		return sysNoticeDao;
	}

	public void setSysNoticeDao(ISysNoticeDao sysNoticeDao) {
		this.sysNoticeDao = sysNoticeDao;
	}

	@Override
	public ResultPage querySysNotice(String noticeTitle, String noticeType,
			String areaId, String serviceType, int page, int pageRows)
			throws Exception {
		ResultPage sysNoticeList = sysNoticeDao.querySysNoticeList(noticeTitle,
				noticeType, areaId, serviceType, page, pageRows);
		return sysNoticeList;
	}

	@Override
	public ResultPage querySysNotice(String noticeTitle, String areaId,
			int page, int pageRows) throws Exception {
		return sysNoticeDao.querySysNoticeList(noticeTitle, areaId, page,
				pageRows);
	}

	@Override
	public void saveOrUpdateSysNotice(SysNotice sysNotice,
			String noticefileFileName, File noticefile) throws Exception {
		String path = BaseParameter.NOTICE_FILE_ATTA;
		if (Common.isNullOrSpace(sysNotice.getNoticeId())) {
			sysNotice.setNoticeId(null);
			sysNotice.setDelFlag("0");
			sysNotice.setInsertDate(new Date());
		}
		if (!Common.isNullOrSpace(noticefileFileName)) {// 有上传附件
			String fileUrl = UploadFileTools.uploadFile(path,
					noticefileFileName, noticefile);
			sysNotice.setNoticeAtta(fileUrl);
		}
		sysNoticeDao.saveOrUpdateSysNotice(sysNotice);
	}


	@Override
	public ResultPage queryJsNoticeList(String noticeTitle, String areaId,
			String serviceType, int page, int pageRows) throws Exception {
		return sysNoticeDao.queryJsNoticeList(noticeTitle, areaId, serviceType, page, pageRows);
	}

	

}

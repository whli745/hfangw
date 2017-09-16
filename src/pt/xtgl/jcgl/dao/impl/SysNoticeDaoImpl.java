package pt.xtgl.jcgl.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysNoticeDao;
import pt.xtgl.jcgl.pojo.SysNotice;
import util.BaseParameter;
import util.Common;
import util.DateTools;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysNoticeDaoImpl extends BaseDaoImpl<SysNotice> implements
		ISysNoticeDao {

	@Override
	public void deleteSysNotice(SysNotice sysNotice) throws Exception {
		sysNotice.setDelFlag(BaseParameter.DELETE_YES);
		update(sysNotice);
	}

	@Override
	public SysNotice querySysNotice(String noticeId) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysNotice where noticeId=? and delFlag=?";
		return super.getObjByHql(hql, new Object[] { noticeId,
				BaseParameter.DELETE_NO });
	}

	@Override
	public void saveSysNotice(SysNotice sysNotice) throws Exception {
		save(sysNotice);
	}

	@Override
	public void updateSysNotice(SysNotice sysNotice) throws Exception {
		update(sysNotice);
	}

	@Override
	public ResultPage querySysNoticeList(String noticeTitle, String noticeType,
			String areaId, String serviceType, int page, int pageRows)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysNotice n where n.delFlag=?");
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(noticeTitle)) {
			sb.append(" and n.noticeTitle like ?");
			parmaList.add("%" + noticeTitle.trim() + "%");
		}
		if (!Common.isNullOrSpace(noticeType)) {
			sb.append(" and n.noticeType = ?");
			parmaList.add(noticeType);
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and (n.areaId=? or n.sysArea.areaPath like ?)");
			parmaList.add(areaId);
			parmaList.add("%" + areaId + "%");
		}
		if (!Common.isNullOrSpace(serviceType)) {
			sb.append(" and n.ywType = ?");
			parmaList.add(serviceType);
		}
		sb.append(" order by n.sysArea.areaSort,n.insertDate desc ");
		return super.getResultPage(sb.toString(), parmaList, page, pageRows);
	}

	@Override
	public ResultPage querySysNoticeList(String noticeTitle, String areaId,
			int page, int pageRows) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysNotice n where n.delFlag=?");
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.DELETE_NO);
		sb.append(" and (n.noticeEndDate is null or n.noticeEndDate>=?)");
		parmaList.add(DateTools.getDate("yyyy-MM-dd"));
		if (!Common.isNullOrSpace(noticeTitle)) {
			sb.append(" and n.noticeTitle like ?");
			parmaList.add("%" + noticeTitle + "%");
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and n.areaId = ?");
			parmaList.add(areaId);
		}
		sb.append(" order by n.insertDate desc ");
		return super.getResultPage(sb.toString(), parmaList, page, pageRows);
	}

	public ResultPage queryNoticeList(String noticeTitle, String areaId,
			String serviceType, int page, int pageRows) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysNotice n where n.delFlag=?");
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(noticeTitle)) {
			sb.append(" and n.noticeTitle like ?");
			parmaList.add("%" + noticeTitle + "%");
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and n.areaId = ?");
			parmaList.add(areaId);
		}
		sb.append(" and n.noticeType in (2,3) and   noticeEndDate is null or   convert(varchar(24), noticeEndDate,120) > "
				+ "'" + df.format(new Date()) + "'" + " ");
		sb.append(" order by n.insertDate desc ");
		return super.getResultPage(sb.toString(), parmaList, page, pageRows);
	}
	
	
	
	public ResultPage queryJsNoticeList(String noticeTitle, String areaId,
			String serviceType, int page, int pageRows) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysNotice n where n.delFlag=?");
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(noticeTitle)) {
			sb.append(" and n.noticeTitle like ?");
			parmaList.add("%" + noticeTitle + "%");
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and n.areaId = ?");
			parmaList.add(areaId);
		}
		sb.append(" and n.noticeType in (2,3) and   noticeEndDate is null or   convert(varchar(24), noticeEndDate,120) > "
				+ "'" + df.format(new Date()) + "'" + " ");
		sb.append(" order by n.insertDate desc ");
		return super.getResultPage(sb.toString(), parmaList, page, pageRows);
	}

	@Override
	public void saveOrUpdateSysNotice(SysNotice sysNotice) throws Exception {
		saveOrUpdate(sysNotice);
	}

}

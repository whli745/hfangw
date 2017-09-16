package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.dao.ILogDao;
import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.pojo.Loginfo;
import util.Common;
import util.DateTools;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class LogDaoImpl extends BaseDaoImpl<Loginfo> implements ILogDao {
	@Override
	public ResultPage getLogActionDescPages(String actionUrl,
			String actionDesc, int page, int pageRows) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.LogActionDesc a where 1=1";
		List<Object> paramList = new ArrayList<Object>();
		if (actionUrl != null && !actionUrl.equals("")) {
			hql += " and a.actionUrl like ?";
			paramList.add("%" + actionUrl + "%");
		}
		if (actionDesc != null && !actionDesc.equals("")) {
			hql += " and a.actionDesc like ?";
			paramList.add("%" + actionDesc + "%");
		}
		return super.getResultPage(hql, paramList, page, pageRows);
	}

	@Override
	public LogActionDesc getLogActionDescByUrl(String actionUrl)
			throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.LogActionDesc a where a.actionUrl = ?";
		Object o = super.getObjByHql(hql, new Object[] { actionUrl });
		return (LogActionDesc) o;
	}

	@Override
	public LogActionDesc getLogActionDescByOid(String oid) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.LogActionDesc a where a.oid = ?";
		Object o = super.getObjByHql(hql, new Object[] { oid });
		return (LogActionDesc) o;
	}

	@Override
	public ResultPage getLogInfoList(String discode, Integer type,
			String userName, String actText, String actTimeFrom,
			String actTimeTo, String ip, int page, int pagerows)
			throws Exception {
		StringBuffer hql = new StringBuffer("from pt.xtgl.jcgl.pojo.LogLoginfo a");
		hql.append(" where a.userOid = u.oid ");
		List<Object> paramList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(discode)) {
			hql.append(" and o.quhuacode = ?");
			paramList.add(discode);
		}
		if (type != null && type != -1) {
			hql.append(" and a.type = ?");
			paramList.add(type);
		}
		if (!Common.isNullOrSpace(userName)) {
			hql.append(" and a.userName like ? ");
			paramList.add("'%" + userName.trim() + "%");
		}
		if (!Common.isNullOrSpace(actText)) {
			hql.append(" and a.actText like ? ");
			paramList.add("%" + actText.trim() + "%");
		}
		if (!Common.isNullOrSpace(actTimeFrom)) {
			hql.append(" and a.actTime >= ? ");
			paramList.add(actTimeFrom + " 00:00:00");
		}
		if (!Common.isNullOrSpace(actTimeTo)) {
			hql.append(" and a.actTime <= ? ");
			paramList.add(actTimeFrom + " 23:59:59");
		}
		if (!Common.isNullOrSpace(ip)) {
			hql.append(" and a.ip like ? ");
			paramList.add("%" + ip.trim() + "%");
		}
		hql.append(" order by a.actTime desc");
		return super.getResultPage(hql.toString(), paramList.toArray(), page,
				pagerows);
	}

	@Override
	public ResultPage queryLogInfoList(String areaId, 
			String roleIds, String userName, Date startDate, Date endDate,
			int page, int pageRows) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.Loginfo l where l.type='0' ");
		List<Object> params = new ArrayList<Object>();
		if (!Common.isNullOrSpace(userName)) {
			sb.append(" and l.userName like ?");
			params.add("%" + userName + "%");
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and (l.areaId=? or l.sysArea.areaPath like ?)");
			params.add(areaId);
			params.add("%" + areaId + "%");
		}
		if (!Common.isNullOrSpace(roleIds)) {
			sb.append(" and l.roleIds like ?");
			params.add("%" + roleIds + "%");
		}
		if (null != startDate) {
			sb.append(" and l.actTime >= ?");
			params.add(startDate);
		}
		if (null != endDate) {
			endDate = DateTools.addDay(endDate, 1);
			sb.append(" and l.actTime < ?");
			params.add(endDate);
		}
		sb.append(" order by l.sysArea.areaSort,l.actTime desc");
		return super.getResultPage(sb.toString(), params, page, pageRows);
	}

	public void clearLog(String areaId,  String roleId,
			String userName, Date startDate, Date endDate) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from pt.xtgl.jcgl.pojo.Loginfo l where l.type='0' ");
		List<Object> params = new ArrayList<Object>();
		if (!Common.isNullOrSpace(userName)) {
			sb.append(" and l.userName like ?");
			params.add("%" + userName + "%");
		}
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and l.areaId = ?");
			params.add(areaId);
		}
		if (!Common.isNullOrSpace(roleId)) {
			sb.append(" and l.roleId like ?");
			params.add(roleId);
		}
		if (null != startDate) {
			sb.append(" and l.actTime >= ?");
			params.add(startDate);
		}
		if (null != endDate) {
			endDate = DateTools.addDay(endDate, 1);
			sb.append(" and l.actTime < ?");
			params.add(endDate);
		}
		super.batchEntityByHQL(sb.toString(), params);
	}

	@Override
	public void insertObject(Loginfo loginfo) throws Exception {
		save(loginfo);
	}

	@Override
	public void deleteLogInfo(Loginfo loginfo) throws Exception {
		delete(loginfo);
	}

	@Override
	public Loginfo queryLoginfoByOid(String oid) throws Exception {
		return super.onGet(oid);
	}

}

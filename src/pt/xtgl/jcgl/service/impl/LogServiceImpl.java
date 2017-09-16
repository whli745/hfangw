package pt.xtgl.jcgl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pt.xtgl.jcgl.dao.ILogDao;
import pt.xtgl.jcgl.pojo.LogActionDesc;
import pt.xtgl.jcgl.pojo.Loginfo;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.service.ILogService;
import pt.xtgl.jcgl.service.ISysRoleService;
import util.Common;
import util.ResultPage;
import util.base.service.BaseServiceImpl;

public class LogServiceImpl extends BaseServiceImpl<Loginfo> implements
		ILogService {

	public LogServiceImpl(ILogDao logDao, ISysRoleService sysRoleService) {
		super(logDao);
		this.logDao = logDao;
		this.sysRoleService = sysRoleService;
	}

	@Autowired
	private ILogDao logDao;
	@Autowired
	private ISysRoleService sysRoleService;

	@Override
	public ResultPage getLogActionDescPages(String actionUrl,
			String actionDesc, int page, int pageRows) throws Exception {
		return logDao.getLogActionDescPages(actionUrl, actionDesc, page,
				pageRows);
	}

	@Override
	public LogActionDesc getLogActionDescByUrl(String actionUrl)
			throws Exception {
		return logDao.getLogActionDescByUrl(actionUrl);
	}

	@Override
	public LogActionDesc getLogActionDescByOid(String oid) throws Exception {
		return logDao.getLogActionDescByOid(oid);
	}

	@Override
	public ResultPage getLogInfoList(String discode, Integer type,
			String userName, String actText, String actTimeFrom,
			String actTimeTo, String ip, int page, int pagerows)
			throws Exception {
		return logDao.getLogInfoList(discode, type, userName, actText,
				actTimeFrom, actTimeTo, ip, page, pagerows);
	}

	@Override
	public void insertObject(Loginfo loginfo) throws Exception {
		logDao.insertObject(loginfo);
	}

	@Override
	public void deleteLogInfo(Loginfo loginfo) throws Exception {
		logDao.deleteLogInfo(loginfo);
	}

	@Override
	public Loginfo queryLoginfoByOid(String oid) throws Exception {
		Loginfo info = logDao.queryLoginfoByOid(oid);
		if (!Common.isNullOrSpace(info.getRoleIds())) {
			List<SysRole> rList = sysRoleService.querySysRoleByIdsList(info
					.getRoleIds());
			info.setSysRoleList(rList);
		}
		return info;
	}

	@Override
	public ResultPage queryLogInfoList(String areaId, String roleId,
			String userName, Date startDate, Date endDate, int page,
			int pageRows) throws Exception {
		return logDao.queryLogInfoList(areaId, roleId, userName, startDate,
				endDate, page, pageRows);
	}

	public void clearLog(String areaId, String roleId, String userName,
			Date startDate, Date endDate) throws Exception {
		logDao.clearLog(areaId, roleId, userName, startDate, endDate);
	}

}

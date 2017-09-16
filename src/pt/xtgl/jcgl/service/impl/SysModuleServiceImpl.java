package pt.xtgl.jcgl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysModuleDao;
import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysModuleService;
import util.base.service.BaseServiceImpl;

public class SysModuleServiceImpl extends BaseServiceImpl<SysModule> implements
		ISysModuleService {

	public SysModuleServiceImpl(ISysModuleDao sysModuleDao) {
		super(sysModuleDao);
		this.sysModuleDao = sysModuleDao;
	}

	private ISysModuleDao sysModuleDao;

	@Override
	public List<SysModule> queryFirstSysModuleList() throws Exception {
		return sysModuleDao.queryFirstSysModuleList();
	}

	@Override
	public SysModule getSysModuleByOid(String oid) throws Exception {
		return sysModuleDao.getSysModuleByOid(oid);
	}

	@Override
	public void saveOrUpdateSysModule(SysModule sysModule) throws Exception {
		if ("".equals(sysModule.getModuleId())) {
			sysModule.setModuleId(null);
			sysModule.setModuleDate(new Date());
		}
		sysModule.setDelFlag(util.BaseParameter.DELETE_NO);
		sysModuleDao.saveOrUpdateSysModule(sysModule);
	}

	@Override
	public void delSysModule(String moduleId) throws Exception {
		SysModule sysModule = sysModuleDao.getSysModuleByOid(moduleId);
		sysModule.setDelFlag(util.BaseParameter.DELETE_YES);
		sysModuleDao.saveOrUpdateSysModule(sysModule);
	}

	@Override
	public List<SysModule> getTopModulesByUser(SysUser user) throws Exception {
		String userId = null;
		if (!user.getUserId().trim().equals(util.BaseParameter.ADMIN)) {
			userId = user.getUserId();
		}
		List<SysModule> list = sysModuleDao.querySysModuleList(userId, null, 1,
				null, null, null);
		List<SysModule> finalMList = new ArrayList<SysModule>();
		for (int i = 0; i < list.size(); i++) {
			SysModule m = list.get(i);
			boolean showFlag = sysModuleDao.ifHaveLev3Module(userId, null,
					null, null, m.getModuleId(), null);
			if (showFlag)
				finalMList.add(m);
		}
		return finalMList;
	}

	@Override
	public List<List> getLeftModulesByUser(SysUser user, String modulePid)
			throws Exception {
		String userId = null;
		if (!user.getUserId().trim().equals(util.BaseParameter.ADMIN)) {
			// 用户id
			userId = user.getUserId();
		}
		// 查询所有2菜单
		List lev2MList = sysModuleDao.querySysModuleList(userId, modulePid, 2,
				"1", null, null);
		List<SysModule> lev3MList = null;
		List<SysModule> ml = null;
		List<List> modules = new ArrayList<List>();
		for (int i = 0; i < lev2MList.size(); i++) {
			SysModule lev2M = (SysModule) lev2MList.get(i);
			// 查询所有三级菜单
			lev3MList = sysModuleDao.querySysModuleList(userId,
					lev2M.getModuleId(), 3, "1", null, null);
			// 有对应的三级菜单才会显示二级菜单
			if (lev3MList != null && lev3MList.size() > 0) {
				ml = new ArrayList<SysModule>();
				ml.add(lev2M);// 二级菜单
				ml.addAll(lev3MList);// 三级菜单
				modules.add(ml);
			}
		}
		return modules;
	}

	public boolean isCurrentModuleHaveChildModules(String modulePid)
			throws Exception {
		List list = sysModuleDao.querySysModuleListByPid(modulePid);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getModuleTreeByPid(String modulePid) throws Exception {
		String sysModulesJson;
		if (modulePid == null) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			sysModulesJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.SYSMODULE_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return sysModulesJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<SysModule> moduleList = (List<SysModule>) sysModuleDao
				.querySysModuleListByPid(modulePid);
		if (moduleList == null) {
			sysModulesJson = "[]";
		} else {
			tmp.append("[");
			for (SysModule sysModule : moduleList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysModule
								.getModuleId());
				tmp.append("{id:'")
						.append(sysModule.getModuleId())
						.append("', name:'")
						.append(sysModule.getModuleName())
						.append("', pId:'")
						.append((sysModule.getParentModuleId() == null ? ""
								: sysModule.getParentModuleId()))
						.append("', isParent: " + isChildNode + "},");
			}
			sysModulesJson = tmp.toString();
			if (moduleList.size() > 0)
				sysModulesJson = sysModulesJson.substring(0,
						sysModulesJson.length() - 1);
			sysModulesJson += "]";
		}
		return sysModulesJson;
	}

	@Override
	public List<SysModule> querySysButtonModuleListByPid(SysUser user,
			String modulePid) throws Exception {
		String userId = null;
		String anIdentity = null;
		if (user != null
				&& !user.getUserId().trim().equals(util.BaseParameter.ADMIN)) {
			// 用户id
			userId = user.getUserId();
		}
		return sysModuleDao.querySysModuleList(userId, modulePid, null, "2",
				null, null);
	}

	@Override
	public List<SysModule> queryAllModuleByTypeList() throws Exception {
		return sysModuleDao.queryAllModuleByTypeList();
	}

	@Override
	public List<SysModule> queryAllSysModuleList() throws Exception {
		return sysModuleDao.queryAllSysModuleList();
	}

	@Override
	public List<SysModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception {
		return sysModuleDao.queryTradeIndustryBureauMenuList(moduleUrl);
	}

}

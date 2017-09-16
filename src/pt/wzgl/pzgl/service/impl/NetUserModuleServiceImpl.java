package pt.wzgl.pzgl.service.impl;

import java.util.Date;
import java.util.List;

import pt.wzgl.pzgl.dao.INetUserModuleDao;
import pt.wzgl.pzgl.pojo.NetUserModule;
import pt.wzgl.pzgl.service.INetUserModuleService;
import util.BaseParameter;
import util.Common;

public class NetUserModuleServiceImpl implements INetUserModuleService {

	private INetUserModuleDao netUserModuleDao;
	public INetUserModuleDao getNetUserModuleDao() {
		return netUserModuleDao;
	}
	public void setNetUserModuleDao(INetUserModuleDao netUserModuleDao) {
		this.netUserModuleDao = netUserModuleDao;
	}

	@Override
	public List<NetUserModule> queryFirstNetUserModuleList() throws Exception {
		return netUserModuleDao.queryFirstNetUserModuleList();
	}

	@Override
	public NetUserModule getNetUserModuleByOid(String oid) throws Exception {
		return netUserModuleDao.getNetUserModuleByOid(oid);
	}

	@Override
	public void saveOrUpdateNetUserModule(NetUserModule netUserModule) throws Exception {
		if (Common.isNullOrSpace(netUserModule.getModuleId())) {
			netUserModule.setModuleId(null);
			netUserModule.setModuleDate(new Date());
		}
		netUserModule.setDelFlag(BaseParameter.DELETE_NO);
		netUserModuleDao.saveOrUpdateNetUserModule(netUserModule);
		NetUserModule parentNetUserModule = netUserModuleDao.getNetUserModuleByOid(netUserModule.getParentId());
		if (parentNetUserModule != null) {
			netUserModule.setModulePath(parentNetUserModule.getModulePath()
					+ netUserModule.getModuleId() + ".");
		} else {
			netUserModule.setModulePath("-1.");
		}
	}

	@Override
	public void delNetUserModule(String moduleId) throws Exception {
		NetUserModule netUserModule = netUserModuleDao.getNetUserModuleByOid(moduleId);
		netUserModule.setDelFlag(util.BaseParameter.DELETE_YES);
		netUserModuleDao.saveOrUpdateNetUserModule(netUserModule);
	}

	public boolean isCurrentModuleHaveChildModules(String modulePid)
			throws Exception {
		List<NetUserModule> list = netUserModuleDao.queryNetUserModuleListByPid(modulePid);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getModuleTreeByPid(String modulePid) throws Exception {
		String netUserModulesJson;
		if (Common.isNullOrSpace(modulePid)) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			netUserModulesJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.NET_USERMODULE_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return netUserModulesJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<NetUserModule> moduleList = (List<NetUserModule>) netUserModuleDao
				.queryNetUserModuleListByPid(modulePid);
		if (moduleList == null) {
			netUserModulesJson = "[]";
		} else {
			tmp.append("[");
			for (NetUserModule netUserModule : moduleList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(netUserModule
								.getModuleId());
				tmp.append("{id:'")
						.append(netUserModule.getModuleId())
						.append("', name:'")
						.append(netUserModule.getModuleName())
						.append("', pId:'")
						.append((netUserModule.getParentId() == null ? ""
								: netUserModule.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			netUserModulesJson = tmp.toString();
			if (moduleList.size() > 0)
				netUserModulesJson = netUserModulesJson.substring(0,
						netUserModulesJson.length() - 1);
			netUserModulesJson += "]";
		}
		return netUserModulesJson;
	}

	@Override
	public List<NetUserModule> queryAllModuleByTypeList() throws Exception {
		return netUserModuleDao.queryAllModuleByTypeList();
	}

	@Override
	public List<NetUserModule> queryAllNetUserModuleList() throws Exception {
		return netUserModuleDao.queryAllNetUserModuleList();
	}

	@Override
	public List<NetUserModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception {
		return netUserModuleDao.queryTradeIndustryBureauMenuList(moduleUrl);
	}
}

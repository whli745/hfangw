package pt.wzgl.pzgl.dao;

import java.util.List;

import pt.wzgl.pzgl.pojo.NetUserModule;
import util.base.dao.IBaseDao;

public interface INetUserModuleDao extends IBaseDao<NetUserModule>{
	
	public List<NetUserModule> queryAllNetUserModuleList() throws Exception;

	public List<NetUserModule> queryAllModuleByTypeList() throws Exception;

	public NetUserModule getNetUserModuleByOid(String oid) throws Exception;

	public void saveOrUpdateNetUserModule(NetUserModule netUserModule) throws Exception;

	public List<NetUserModule> queryNetUserModuleListByPid(String modulePid)
			throws Exception;

	public List<NetUserModule> queryFirstNetUserModuleList() throws Exception;

	public List<NetUserModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception;

}

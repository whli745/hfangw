package pt.wzgl.pzgl.service;

import java.util.List;

import pt.wzgl.pzgl.pojo.NetUserModule;

public interface INetUserModuleService {
	/**
	 * 通过oid查询对象
	 */
	public NetUserModule getNetUserModuleByOid(String oid) throws Exception;

	/**
	 * 保存、修改菜单
	 */
	public void saveOrUpdateNetUserModule(NetUserModule netUserModule) throws Exception;

	/**
	 * 根据oid删除菜单 逻辑删除
	 */
	public void delNetUserModule(String moduleId) throws Exception;

	/**
	 * 异步加载树，由前台zTree调用
	 * 
	 * @author zhujj
	 * @param modulePid
	 *            父id
	 * @date 2013-04
	 */
	public String getModuleTreeByPid(String modulePid) throws Exception;

	/**
	 * 查询所有启用且未删除的菜单列表
	 * 
	 * @param status
	 *            0 启用 1 未启用
	 */
	public List<NetUserModule> queryAllNetUserModuleList() throws Exception;

	/**
	 * 查询所有的action请求菜单
	 * 
	 * @author meidj
	 * @date 2013-05-16
	 */
	public List<NetUserModule> queryAllModuleByTypeList() throws Exception;

	/**
	 * 
	 * 
	 * 功能：获取一级菜单列表
	 * 
	 * @author:xuhw
	 * @Email: xuhaiwei@zhuofansoft.com
	 * @date: 2013-6-28
	 * @return
	 * @modify：
	 * @return
	 * @throws Exception
	 */
	public List<NetUserModule> queryFirstNetUserModuleList() throws Exception;

	/**
	 * 功能 ：
	 * 
	 * @author chj
	 * @date 2013-8-22上午11:29:16
	 * @param moduleUrl
	 *            主菜单url
	 */
	public List<NetUserModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception;

}

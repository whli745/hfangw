package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysUser;
import util.base.service.IBaseService;

public interface ISysModuleService extends IBaseService<SysModule> {
	/**
	 * 通过oid查询对象
	 */
	public SysModule getSysModuleByOid(String oid) throws Exception;

	/**
	 * 保存、修改菜单
	 */
	public void saveOrUpdateSysModule(SysModule sysModule) throws Exception;

	/**
	 * 根据oid删除菜单 逻辑删除
	 */
	public void delSysModule(String moduleId) throws Exception;

	/**
	 * 根据userId查询对应的一级菜单
	 * 
	 */
	public List<SysModule> getTopModulesByUser(SysUser user) throws Exception;

	/**
	 * 根据userId和一级菜单查询对应所有子菜单
	 * 
	 * @param modulePid一级菜单的oid
	 */
	public List<List> getLeftModulesByUser(SysUser user, String modulePid)
			throws Exception;

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
	 * 
	 * 根据登录用户读取当前模块下的所有功能模块
	 * 
	 * @param user和member都为null时
	 *            查询modulePid下所有功能菜单
	 * @param modulePid
	 *            模块ID
	 * 
	 */
	public List<SysModule> querySysButtonModuleListByPid(SysUser user,
			String modulePid) throws Exception;

	/**
	 * 查询所有启用且未删除的菜单列表
	 * 
	 * @param status
	 *            0 启用 1 未启用
	 */
	public List<SysModule> queryAllSysModuleList() throws Exception;

	/**
	 * 查询所有的action请求菜单
	 * 
	 * @author meidj
	 * @date 2013-05-16
	 */
	public List<SysModule> queryAllModuleByTypeList() throws Exception;

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
	public List<SysModule> queryFirstSysModuleList() throws Exception;

	/**
	 * 功能 ：
	 * 
	 * @author chj
	 * @date 2013-8-22上午11:29:16
	 * @param moduleUrl
	 *            主菜单url
	 */
	public List<SysModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception;

}

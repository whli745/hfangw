package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysModule;
import util.base.dao.IBaseDao;

public interface ISysModuleDao extends IBaseDao<SysModule>{
	/**
	 * 查询所有的菜单
	 * 
	 * @param stutas
	 *            是否启用
	 * @author zhujj
	 * @date 2013-04
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
	 * 根据oid查询菜单对象
	 * 
	 * @author zhujj
	 * @date 2013-04
	 */
	public SysModule getSysModuleByOid(String oid) throws Exception;

	/**
	 * 保存修改菜单对象
	 * 
	 * @author zhujj
	 * @date 2013-04
	 */
	public void saveOrUpdateSysModule(SysModule sysModule) throws Exception;

	/**
	 * 根据oid删除菜单对象
	 * 
	 * @author zhujj
	 * @date 2013-04
	 */
	public void delSysModule(String moduleId) throws Exception;

	/**
	 * 查询登录用户所有权限
	 * 
	 * @author zhujj
	 * @date 2013-04
	 * @param userId
	 *            sysUser的oid
	 * @param modulePid
	 *            菜单父级ip
	 * @param moduleLevel
	 *            菜单级别
	 * @param moduleType
	 *            模块类别 普通模块，功能模块
	 * @param modulePidsReg
	 *            菜单的所有父id
	 * @areaId 地区id
	 */
	public List<SysModule> querySysModuleList(String userId, 
			String modulePid, Integer moduleLevel, 
			String moduleType, String modulePidsReg, String areaId)
			throws Exception;

	/**
	 * 根据pid查询菜单
	 * 
	 * @param modulePid
	 * @author zhujj
	 * @date 2013-04
	 */
	public List<SysModule> querySysModuleListByPid(String modulePid)
			throws Exception;

	/**
	 * 判断一级菜单是否有对应的三级菜单，控制是否显示
	 * 
	 * @param userId
	 *            SysUser 的 oid
	 * @param anIdentity
	 *            会员的所属身份
	 * @param moduleApp
	 *            所属平台
	 * @param moduleType
	 *            模块类别
	 * @param modulePidsReg
	 * @return 返回 modulePidsReg 是否有对应三级菜单
	 * @author zhujj
	 * @date 2013-04
	 */
	public boolean ifHaveLev3Module(String userId, String anIdentity,
			String moduleApp, String moduleType, String modulePidsReg,
			String areaId) throws Exception;

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
	 * @date 2013-8-22上午11:32:46
	 * @param moduleUrl
	 *            moduleUrl 主菜单url
	 */
	public List<SysModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception;

}

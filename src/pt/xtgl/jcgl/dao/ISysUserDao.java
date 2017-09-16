package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysUser;
import util.ResultPage;
import util.base.dao.IBaseDao;

public interface ISysUserDao extends IBaseDao<SysUser> {

	/***************************************************************************
	 * 根据用户输入的登录名code判断数据库中是否存在该用户
	 * 
	 * @param code
	 *            登录code
	 * @return 如果用户不存在，将返回空
	 */
	public SysUser findUserByCodeAndPass(String code) throws Exception;

	/**
	 * 保存用户信息 add by wanghw date 2013-04-02
	 * 
	 * @param sysUser
	 *            用户对象
	 * @throws Exception
	 */
	public void saveSysUser(SysUser sysUser) throws Exception;

	/**
	 * 删除用户信息 add by wanghw date 2013-04-02
	 * 
	 * @param sysUser
	 *            用户对象
	 * @throws Exception
	 */
	public void deleteSysUser(SysUser sysUser) throws Exception;

	/**
	 * 修改用户信息 add by wanghw date 2013-04-02
	 * 
	 * @param sysUser
	 *            用户对象
	 * @throws Exception
	 */
	public void updateSysUser(SysUser sysUser) throws Exception;

	/**
	 * 通过userId查找用户信息 add by wanghw date 2013-04-02
	 * 
	 * @param userId
	 *            用户Id
	 * @return 如果用户不存在，将返回空
	 * @throws Exception
	 */
	public SysUser querySysUser(String userId) throws Exception;

	/**
	 * 查询用户的信息列表 add by wanghw date 2013-04-07
	 * 
	 * @param areaId
	 *            所属区划
	 * @param userName
	 *            用户名称
	 * @param organId
	 *            组织机构Id
	 * @param page
	 *            列表的页数
	 * @param pageRows
	 *            显示列表的行数
	 * @return
	 * @throws Exception
	 */
	public ResultPage querySysUserList(String areaId, String userName,
			String organId, String serviceType, int page, int pageRows)
			throws Exception;

	/**
	 * 保存或者修改用户信息
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 * @param sysUser
	 *            用户对象信息
	 * @throws Exception
	 */
	public void saveOrUpdateSysUser(SysUser sysUser) throws Exception;

	/**
	 * 根据多个岗位id以及用户id读取用户列表
	 * 
	 * @param postIds
	 *            多个岗位id，之间使用分号分割
	 * @param uersIds
	 *            多个用户id，之间使用分号分割
	 * @return
	 * @throws Exception
	 */
	public List<SysUser> getUsersByPostIdsAndUserIds(String postIds,
			String uersIds, String areaId) throws Exception;
}

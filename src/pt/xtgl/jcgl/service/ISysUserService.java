package pt.xtgl.jcgl.service;

import pt.xtgl.jcgl.pojo.SysUser;
import util.ResultPage;
import util.base.service.IBaseService;
import dwr.vo.SysMyUser;

/**
 * 功能：用户管理service
 * 
 * @author wanghw
 * @date 2013-04-02
 * @modify：
 */
public interface ISysUserService extends IBaseService<SysUser> {

	/***************************************************************************
	 * 根据用户输入的登录名code判断数据库中是否存在该用户
	 * 
	 * @param code
	 *            登录code
	 * @return 如果用户不存在，将返回空
	 */

	public SysUser findUserByCodeAndPass(String code) throws Exception;

	/**
	 * 保存用户方法
	 * 
	 * @param sysUser
	 *            用户对象
	 * @throws Exception
	 */
	public void saveSysUser(SysUser sysUser) throws Exception;

	/**
	 * 删除用户方法
	 * 
	 * @param userId
	 *            用户编号
	 * @throws Exception
	 */
	public void deleteSysUser(String userId) throws Exception;

	/**
	 * 更新或修改用户信息
	 * 
	 * @param sysUser
	 *            用户对象
	 * @throws Exception
	 */
	public void updateSysUser(SysUser sysUser) throws Exception;

	/**
	 * TOP修改个人信息
	 * 
	 * @param sysUser
	 * @throws Exception
	 */
	public void updateMyUserInfo(SysMyUser sysUser) throws Exception;

	/**
	 * 通过userId查找用户信息
	 * 
	 * @param userId
	 * @return 如果用户不存在，将返回空
	 * @throws Exception
	 */
	public SysUser querySysUser(String userId) throws Exception;

	/**
	 * 查询用户信息列表
	 * 
	 * @param areaId
	 * @param userName
	 * @param organId
	 * @param page
	 * @param pageRows
	 * @return
	 */
	public ResultPage querySysUserList(String areaId, String userName,
			String organId,String serviceType, int page, int pageRows) throws Exception;

	/**
	 * 系统登录验证
	 * 
	 * @param yzm
	 *            验证码
	 * @return 验证结果
	 * @author glh zhujj
	 * @date 2013-04-07
	 */
	public String checkLogin(String sysyzm, String yzm, SysUser user,
			String password) throws Exception;
	/**
	 * 系统登录验证
	 * 
	 * @param yzm
	 *            验证码
	 * @return 验证结果
	 * @author glh zhujj
	 * @date 2013-04-07
	 */
	public String checkLogin(SysUser user,String password) throws Exception;

	/**
	 * 保存或者修改用户信息
	 * 
	 * @author wanghw
	 * @date 2013-4-16
	 * @param sysUser
	 * @param websiteIds 
	 * @throws Exception
	 */
	public void saveOrUpdateSysUser(SysUser sysUser, String websiteIds) throws Exception;
}

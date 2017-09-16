package pt.xtgl.jcgl.service;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysUserRole;
import util.base.service.IBaseService;

/**
 * 功能：用户角色service
 * 
 * @author meidj
 * @date 2013-05-15
 * @modify：
 */
public interface ISysUserRoleService extends IBaseService<SysUserRole> {
	/**
	 * 根据用户主键ID查询用户角色记录
	 * 
	 * @author meidj
	 * @param userId
	 *            用户主键ID
	 * @return
	 * @throws Exception
	 */
	public List<SysUserRole> queryUserRoleList(String userId) throws Exception;

}

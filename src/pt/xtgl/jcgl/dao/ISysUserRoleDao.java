package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysUserRole;
import util.base.dao.IBaseDao;

/**
 * 功能：用户角色dao
 * 
 * @author meidj
 * @date 2013-05-15
 * @modify：
 */
public interface ISysUserRoleDao extends IBaseDao<SysUserRole> {

	/**
	 * 根据用户ID删除用用户分配的角色
	 * 
	 * @author meidj
	 * @param userId
	 *            用户主键ID
	 * 
	 */
	public void deleteRoleByUserId(String userId) throws Exception;

	/**
	 * 保存用户分配的角色
	 * 
	 * @author userRole 实体角色类
	 * @param userId
	 * @throws Exception
	 */
	public void saveUserRole(SysUserRole userRole) throws Exception;

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

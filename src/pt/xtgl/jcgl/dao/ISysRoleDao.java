package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysRole;
import util.ResultPage;
import util.base.dao.IBaseDao;

public interface ISysRoleDao extends IBaseDao<SysRole> {
	/**
	 * 查询角色权限列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResultPage querySysRoleList(String roleName, String optId,
			String areaId, int page, int pageRows) throws Exception;

	/**
	 * 查询角色权限列表（区划、业务类型下）
	 * 
	 * @param areaId
	 *            区划主键ID
	 * @param serviceType
	 *            业务类型
	 * @return List<SysRole>
	 * @throws Exception
	 */

	public List<SysRole> querySysRoleList(String areaId, String serviceType)
			throws Exception;

	/**
	 * 查询角色权限列表（区划下）
	 * 
	 * @param areaId
	 *            区划主键ID
	 * @return List<SysRole>
	 * @throws Exception
	 */

	public List<SysRole> querySysRoleList(String areaId) throws Exception;

	/**
	 * 保存修改角色权限
	 */
	public void saveOrUpdateSysRole(SysRole sysRole) throws Exception;

	/**
	 * 通过roleIds查询角色列表
	 */
	public List<SysRole> querySysRoleByIdsList(String roleIds) throws Exception;

	/**
	 * roleId查询SysRole
	 */
	public SysRole getSysRoleById(String id) throws Exception;

	/**
	 * 
	 * @功能：查找所有的角色
	 * @date 2013-5-8下午04:49:16
	 * @return
	 */
	public List<SysRole> querySysRoleAll() throws Exception;

	/**
	 * 
	 * 
	 * 功能：根据当前用户的区划id,所属身份id,所属业务类型查询角色列表
	 * 
	 * @author:xuhw
	 * @Email: xuhaiwei@zhuofansoft.com
	 * @date: 2013-6-27
	 * @return
	 * @modify：
	 * @param areaId
	 *            区划id
	 * @param anIdentity
	 *            所属身份id
	 * @param optId
	 *            所属业务类型
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> queryRoleList(String areaId, String anIdentity,
			String optId) throws Exception;

	/**
	 * 根据区划id取得本级区划的角色配置
	 * 
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<SysRole> querySysRoleAllByAreaId(String areaId)
			throws Exception;
}

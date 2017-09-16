package pt.xtgl.jcgl.dao;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysRoleModule;
import util.base.dao.IBaseDao;

public interface ISysRoleModuleDao extends IBaseDao<SysRoleModule> {

	/**
	 * 查询角色对应权限列表
	 */
	public List<SysRoleModule> querySysModuleRoleList(String roleId)
			throws Exception;

	/**
	 * 根据oid删除对象
	 */
	public void delSysRoleModule(String oid) throws Exception;

	/**
	 * 保存
	 */
	public void saveSysRoleModule(SysRoleModule sysRoleModule) throws Exception;
}

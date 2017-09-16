package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysRoleModuleDao;
import pt.xtgl.jcgl.pojo.SysRoleModule;
import util.base.dao.BaseDaoImpl;

public class SysRoleModuleDaoImpl extends BaseDaoImpl<SysRoleModule> implements ISysRoleModuleDao{
	List<Object> objs=null;
	public List<SysRoleModule> querySysModuleRoleList(String roleId) throws Exception {
		String hql="from pt.xtgl.jcgl.pojo.SysRoleModule sm where sm.roleId= ? ";
		objs=new ArrayList<Object>();
		objs.add(roleId);
		return super.queryByHql(hql,objs);
	}
	public void delSysRoleModule(String oid) throws Exception{
		super.delete(oid);
	}
	public void saveSysRoleModule(SysRoleModule sysRoleModule) throws Exception{
		super.save(sysRoleModule);
	}

}

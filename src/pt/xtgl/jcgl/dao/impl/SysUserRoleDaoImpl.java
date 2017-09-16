package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysUserRoleDao;
import pt.xtgl.jcgl.pojo.SysUserRole;
import util.base.dao.BaseDaoImpl;

public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole> implements
		ISysUserRoleDao {

	@Override
	public void deleteRoleByUserId(String userId) throws Exception {
		String hql = "delete from pt.xtgl.jcgl.pojo.SysUserRole ur where ur.userId=?";
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(userId);
		super.batchEntityByHQL(hql, parmaList);
	}

	@Override
	public void saveUserRole(SysUserRole userRole) throws Exception {
		super.save(userRole);
	}

	@Override
	public List<SysUserRole> queryUserRoleList(String userId) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysUserRole ur where ur.userId=?";
		List<String> parmaList = new ArrayList<String>();
		parmaList.add(userId);
		return super.queryByHql(hql, parmaList);
	}

}

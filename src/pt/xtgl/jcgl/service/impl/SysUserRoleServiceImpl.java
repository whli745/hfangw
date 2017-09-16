package pt.xtgl.jcgl.service.impl;

import java.util.List;

import pt.xtgl.jcgl.dao.ISysUserRoleDao;
import pt.xtgl.jcgl.pojo.SysUserRole;
import pt.xtgl.jcgl.service.ISysUserRoleService;
import util.base.service.BaseServiceImpl;

public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements ISysUserRoleService {
	
	public SysUserRoleServiceImpl(ISysUserRoleDao sysUserRoleDao) {
		super(sysUserRoleDao);
		this.sysUserRoleDao = sysUserRoleDao;
	}
	private ISysUserRoleDao sysUserRoleDao;

	@Override
	public List<SysUserRole> queryUserRoleList(String userId) throws Exception {
		return sysUserRoleDao.queryUserRoleList(userId);
	}

}

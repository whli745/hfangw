package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysUserDao;
import pt.xtgl.jcgl.pojo.SysUser;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements ISysUserDao {

	@Override
	public SysUser findUserByCodeAndPass(String code) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysUser user where user.userCode=?";
		return super.getObjByHql(hql, new Object[] { code });
	}

	@Override
	public void deleteSysUser(SysUser sysUser) throws Exception {
		sysUser.setDelFlag(BaseParameter.DELETE_YES);
		update(sysUser);
	}

	@Override
	public SysUser querySysUser(String userId) throws Exception {
		return super.onGet(userId);
	}

	@Override
	public void saveSysUser(SysUser sysUser) throws Exception {
		super.save(sysUser);
	}

	@Override
	public void updateSysUser(SysUser sysUser) throws Exception {
		super.update(sysUser);
	}

	@Override
	public ResultPage querySysUserList(String areaId, String userName,
			String organId,String serviceType, int page, int pageRows) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysUser s where s.userId<>? and s.delFlag=?");
		ArrayList<Object> parmaList = new ArrayList<Object>();
		parmaList.add(BaseParameter.ADMIN);
		parmaList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and (s.areaId=? or s.sysArea.areaPath like ?)");
			parmaList.add(areaId);
			parmaList.add("%" + areaId + "%");
		}
		if (!Common.isNullOrSpace(userName)) {
			sb.append(" and s.userName like?");
			parmaList.add("%" + userName.trim() + "%");
		}
		if (!Common.isNullOrSpace(serviceType)&& !serviceType.equals("0")) {
			sb.append(" and s.serviceType=?");
			parmaList.add(serviceType);
		}
		
		if (!Common.isNullOrSpace(organId) && !organId.equals("0")) {
			sb.append(" and s.organId=?");
			parmaList.add(organId);
		}
		sb.append(" order by s.sysArea.areaSort");
		return getResultPage(sb.toString(), parmaList, page, pageRows);
	}

	@Override
	public void saveOrUpdateSysUser(SysUser sysUser) throws Exception {
		super.saveOrUpdate(sysUser);
	}

	@Override
	public List<SysUser> getUsersByPostIdsAndUserIds(String postIds,
			String uersIds, String areaId) throws Exception {
		String posts = "'" + postIds + "'";
		posts = posts.replaceAll(",", "','");
		String users = "'" + uersIds + "'";
		users = users.replaceAll(",", "','");
		String hql = "from pt.xtgl.jcgl.pojo.SysUser u where (u.userId in (" + users
				+ ") or u.userId in (select sur.userId from pt.xtgl.jcgl.pojo.SysUserRole sur where sur.roleId in ("
				+ posts + "))) and u.delFlag=? and u.areaId=?";
		return super.queryByHql(hql, new Object[]{BaseParameter.DELETE_NO, areaId});
	}
}

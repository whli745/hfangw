package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysRoleDao;
import pt.xtgl.jcgl.pojo.SysRole;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements ISysRoleDao {
	private List<Object> objs = null;

	@Override
	public ResultPage querySysRoleList(String roleName, String optId,
			String areaId, int page, int pageRows) throws Exception {
		objs = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag=?");
		objs.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(roleName)) {
			sb.append(" and r.roleName like ?");
			objs.add("%" + roleName.trim() + "%");
		}

		if (!Common.isNullOrSpace(optId)) {
			sb.append(" and r.optId = ?");
			objs.add(optId.trim());
		}

		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and (r.areaId=? or r.sysArea.areaPath like ?)");
			objs.add(areaId.trim());
			objs.add("%" + areaId.trim() + "%");
		}
		sb.append(" order by r.sysArea.areaSort");
		return super.getResultPage(sb.toString(), objs, page, pageRows);
	}

	@Override
	public List<SysRole> querySysRoleList(String areaId, String serviceType)
			throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag = ? and r.optId=? and r.areaId = ? ";
		objs = new ArrayList<Object>();
		objs.add(BaseParameter.DELETE_NO);
		objs.add(serviceType);
		objs.add(areaId);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<SysRole> querySysRoleList(String areaId) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag = ? and r.areaId = ? ";
		objs = new ArrayList<Object>();
		objs.add(BaseParameter.DELETE_NO);
		objs.add(areaId);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<SysRole> queryRoleList(String areaId, String anIdentity,
			String optId) throws Exception {
		StringBuffer hql = new StringBuffer(
				"from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag = ?");
		List<Object> list = new ArrayList<Object>();
		list.add(BaseParameter.DELETE_NO);
		hql.append(" and r.areaId = ? ");
		list.add(areaId);
		hql.append(" and r.anIdentity = ?  ");
		list.add(anIdentity);
		hql.append(" and r.optId in ( " + optId + ")  ");
		return super.queryByHql(hql.toString(), list);
	}

	@Override
	public List<SysRole> querySysRoleByIdsList(String roleIds) throws Exception {
		StringBuffer hql = new StringBuffer(
				"from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag = ?");
		List<Object> list = new ArrayList<Object>();
		list.add(BaseParameter.DELETE_NO);
		hql.append(" and r.roleId in ( " + roleIds + ")");
		return super.queryByHql(hql.toString(), list);
	}

	@Override
	public void saveOrUpdateSysRole(SysRole sysRole) throws Exception {
		super.saveOrUpdate(sysRole);
	}

	@Override
	public SysRole getSysRoleById(String id) throws Exception {
		return super.onGet(id);
	}

	@Override
	public List<SysRole> querySysRoleAll() throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag=? ";
		List<String> params = new ArrayList<String>();
		params.add(BaseParameter.DELETE_NO);
		return super.queryByHql(hql, params);
	}
	@Override
	public List<SysRole> querySysRoleAllByAreaId(String areaId) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysRole r where r.delFlag=? ";
		List<String> params = new ArrayList<String>();
		params.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(areaId)) {
			hql += " and r.areaId = ? ";
			params.add(areaId.trim());
		}
		return super.queryByHql(hql, params);
	}
}

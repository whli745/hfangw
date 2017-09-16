package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysAreaDao;
import pt.xtgl.jcgl.pojo.SysArea;
import util.BaseParameter;
import util.base.dao.BaseDaoImpl;

public class SysAreaDaoImpl extends BaseDaoImpl<SysArea> implements ISysAreaDao {

	@Override
	public List<SysArea> querySysAreaByAreaPath(String areaId) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysArea a where a.delFlag='");
		sb.append(BaseParameter.DELETE_NO);
		sb.append("'");
		if (null != areaId && !"".equals(areaId.trim())) {
			sb.append(" and a.areaId<>? and a.areaPath like ?");
			list.add(areaId);
			list.add("%" + areaId + "_");
		}
		sb.append(" order by a.areaSort");
		return super.queryByHql(sb.toString(), list);
	}

	@Override
	public List<SysArea> querySysAreaByPid(String areaId) throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysArea a where a.delFlag='");
		sb.append(BaseParameter.DELETE_NO);
		sb.append("'");
		if (null != areaId && !"".equals(areaId.trim())) {
			sb.append(" and a.topId = ?");
			list.add(areaId);
		}
		sb.append(" order by a.areaSort");
		return super.queryByHql(sb.toString(), list);
	}

	@Override
	public SysArea getSysAreaBytopId(String areaId) throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysArea t where t.areaId=?";
		Object obj = getObjByHql(hql, new Object[] { areaId });
		return (SysArea) obj;
	}

	@Override
	public void saveSysArea(SysArea sysArea) throws Exception {
		save(sysArea);
	}

	@Override
	public void updateSysArea(SysArea sysArea) throws Exception {
		update(sysArea);

	}

	@Override
	public SysArea getSysAreaTop() throws Exception {
		String hql = "from pt.xtgl.jcgl.pojo.SysArea t where t.topId is null or t.topId='' or t.topId='-1' ";
		Object obj = getObjByHql(hql, new Object[] {});
		return (SysArea) obj;
	}

}

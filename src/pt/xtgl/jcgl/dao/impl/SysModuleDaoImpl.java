package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysModuleDao;
import pt.xtgl.jcgl.pojo.SysModule;
import util.BaseParameter;
import util.Common;
import util.base.dao.BaseDaoImpl;

public class SysModuleDaoImpl extends BaseDaoImpl<SysModule> implements
		ISysModuleDao {
	List<Object> objs = null;

	@Override
	public List<SysModule> queryAllSysModuleList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from pt.xtgl.jcgl.pojo.SysModule m where m.moduleStatus = ? "
				+ "and m.delFlag= ? order by m.moduleOrder";
		objs.add(util.BaseParameter.STATUS_ENABLE);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<SysModule> queryFirstSysModuleList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from pt.xtgl.jcgl.pojo.SysModule m where m.moduleLevel = ? "
				+ "and m.delFlag= ? order by m.moduleOrder";
		objs.add(1);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);

	}

	@Override
	public SysModule getSysModuleByOid(String oid) throws Exception {
		return (SysModule) super.onGet(oid);
	}

	@Override
	public void saveOrUpdateSysModule(SysModule sysModule) throws Exception {
		super.saveOrUpdate(sysModule);
	}

	@Override
	public void delSysModule(String moduleId) throws Exception {
		super.delete(moduleId);
	}

	@Override
	public List<SysModule> querySysModuleList(String userId, 
			String modulePid, Integer moduleLevel,
			String moduleType, String modulePidsReg, String areaId)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct m from SysModule m ");
		objs = new ArrayList<Object>();
		if (!Common.isNullOrSpace(userId)) {// 平台权限
			hql.append(",SysRoleModule rm where m.moduleId=rm.moduleId and rm.roleId ");
			hql.append("in (select ur.roleId  from SysUserRole ur where ur.sysRole.roleStatus = ?  and ur.userId = ? and ur.sysRole.delFlag = ?) ");
			objs.add(util.BaseParameter.STATUS_ENABLE);
			objs.add(userId);
			objs.add(util.BaseParameter.DELETE_NO);
		} else {
			hql.append("where 1 = 1");
		}
		if (!Common.isNullOrSpace(modulePid)) {
			hql.append(" and m.parentModuleId = ? ");
			objs.add(modulePid);
		}
		if (moduleLevel != null) {
			hql.append(" and m.moduleLevel = ? ");
			objs.add(moduleLevel);
		}
		if (!Common.isNullOrSpace(moduleType)) {
			hql.append(" and m.moduleType = ? ");
			objs.add(moduleType);
		}
		if (!Common.isNullOrSpace(modulePidsReg)) {
			hql.append(" and m.modulePids like ? ");
			objs.add("%" + modulePidsReg + "%");
		}
		hql.append(" and m.moduleStatus = ? and m.delFlag = ? order by m.moduleOrder asc");
		objs.add(util.BaseParameter.STATUS_ENABLE);
		objs.add(util.BaseParameter.DELETE_NO);

		return super.queryByHql(hql.toString(), objs);
	}

	@Override
	public List<SysModule> querySysModuleListByPid(String modulePid)
			throws Exception {
		String hql = "from SysModule m where m.parentModuleId= ? and m.moduleStatus = ? and m.delFlag = ? order by m.moduleOrder asc ";
		return super.queryByHql(hql,
				new Object[] { modulePid, util.BaseParameter.STATUS_ENABLE,
						util.BaseParameter.DELETE_NO });
	}

	@Override
	public boolean ifHaveLev3Module(String userId, String anIdentity,
			String moduleApp, String moduleType, String modulePidsReg,
			String areaId) throws Exception {
		return this.querySysModuleList(userId,  null, 3, 
				moduleType, modulePidsReg, areaId).size() > 0 ? true : false;
	}

	@Override
	public List<SysModule> queryAllModuleByTypeList() throws Exception {
		objs = new ArrayList<Object>();
		String hql = "from pt.xtgl.jcgl.pojo.SysModule m where m.moduleStatus = ? "
				+ "and m.delFlag= ? and m.moduleId in (select sm.parentModuleId from pt.xtgl.jcgl.pojo.SysModule sm where sm.moduleType='2')";
		objs.add(util.BaseParameter.STATUS_ENABLE);
		objs.add(util.BaseParameter.DELETE_NO);
		return super.queryByHql(hql, objs);
	}

	@Override
	public List<SysModule> queryTradeIndustryBureauMenuList(String moduleUrl)
			throws Exception {
		String hql = "from SysModule s where s.delFlag=? and s.parentModuleId=? order by s.moduleOrder";
		return super.queryByHql(hql, new Object[] { BaseParameter.DELETE_NO,
				moduleUrl });
	}

}

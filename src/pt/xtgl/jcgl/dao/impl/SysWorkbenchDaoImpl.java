package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysWorkbenchDao;
import pt.xtgl.jcgl.pojo.SysWorkbench;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysWorkbenchDaoImpl extends BaseDaoImpl<SysWorkbench> implements
		ISysWorkbenchDao {

	/**
	 * 删除工作台
	 */
	@Override
	public void deleteSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		sysWorkbench.setWorkbenchDelFlag(BaseParameter.DELETE_YES);
		update(sysWorkbench);
	}

	/**
	 * 查找工作台
	 */
	@Override
	public SysWorkbench querySysWorkbench(String workbenchId) throws Exception {
		String hql = " from pt.xtgl.jcgl.pojo.SysWorkbench where workbenchId=?";
		return super.getObjByHql(hql, new Object[] { workbenchId });
	}

	/**
	 * 增加工作台
	 */
	@Override
	public void saveSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		save(sysWorkbench);
	}

	/**
	 * 修改工作台
	 */
	@Override
	public void updateSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		update(sysWorkbench);
	}

	/**
	 * 新增或者修改工作台信息
	 */
	@Override
	public void editeSysWorkbench(SysWorkbench sysWorkbench) throws Exception {
		saveOrUpdate(sysWorkbench);
	}

	@Override
	public ResultPage querySysWorkbenchList(SysWorkbench sysWorkbench,
			String delFlag, int page, int pageRows) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb
				.append("from pt.xtgl.jcgl.pojo.SysWorkbench w where w.workbenchDelFlag=?");
		List<Object> parmaList = new ArrayList<Object>();
		parmaList.add(delFlag);
		if (sysWorkbench != null) {
			if (!Common.isNullOrSpace(sysWorkbench.getAreaId())) {
				sb.append(" and (w.areaId=? or w.sysArea.areaId like ?)");
				parmaList.add(sysWorkbench.getAreaId());
				parmaList.add("%" + sysWorkbench.getAreaId() + "%");
			}
			if (!Common.isNullOrSpace(sysWorkbench.getWorkbenchSystem())) {
				sb.append(" and w.workbenchSystem like ?");
				parmaList.add("%" + sysWorkbench.getWorkbenchSystem() + "%");
			}
			if (!Common.isNullOrSpace(sysWorkbench.getWorkbenchName())) {
				sb.append(" and w.workbenchName like ?");
				parmaList.add("%" + sysWorkbench.getWorkbenchName().trim() + "%");
			}
			if (!Common.isNullOrSpace(sysWorkbench.getWorkbenchServiceType())) {
				sb.append(" and w.workbenchServiceType = ?");
				parmaList.add(sysWorkbench.getWorkbenchServiceType());
			}
		}
		sb.append(" order by w.sysArea.areaSort");
		return super.getResultPage(sb.toString(), parmaList, page, pageRows);
	}

	public List queryWorkbenchList(String workbenchSystem, String modulePid)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysWorkbench w");
		List<Object> params = new ArrayList<Object>();
		sb.append(" where w.workbenchDelFlag=? and w.workbenchDefault=? and w.workbenchUseFlag=?");
		params.add(BaseParameter.DELETE_NO);
		params.add(BaseParameter.WORK_BENCH_DEFAULT);
		params.add(BaseParameter.STATUS_ENABLE);
		if (!Common.isNullOrSpace(workbenchSystem)) {
			String tmpStr = "";
			String[] roles = workbenchSystem.split(",");
			for (String role : roles) {
				tmpStr += " w.workbenchSystem like ? or";
				params.add("%" + role.trim() + "%");
			}
			tmpStr = tmpStr.substring(0, tmpStr.lastIndexOf("or"));
			sb.append(" and (" + tmpStr + ")");
		}
		if (!Common.isNullOrSpace(modulePid)) {
			sb.append(" and w.workbenchModule = ?");
			params.add(modulePid);
		}
		sb.append(" order by w.orderBy");
		return super.queryByHql(sb.toString(), params);
	}

}

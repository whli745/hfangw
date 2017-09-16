package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysOrganDao;
import pt.xtgl.jcgl.pojo.SysOrgan;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

public class SysOrganDaoImpl extends BaseDaoImpl<SysOrgan> implements ISysOrganDao {

	@Override
	public void saveSysOrgan(SysOrgan sysOrgan) throws Exception {
		super.save(sysOrgan);
	}

	@Override
	public void updateSysOrgan(SysOrgan sysOrgan) throws Exception {
		super.update(sysOrgan);
	}

	@Override
	public SysOrgan getSysOrganInfo(String organId) throws Exception {
		return super.onGet(organId);
	}

	@Override
	public ResultPage findSysOrganList(String organName, String areaId,
			int page, int pageRows) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag=?");
		list.add(BaseParameter.NO);
		if (!Common.isNullOrSpace(organName)) { // 判断机构名称是否输入
			sb.append(" and so.organName like ?");
			list.add("%" + organName.trim() + "%");
		}
		if (!Common.isNullOrSpace(areaId)) { // 判断区划是否存在
			sb.append(" and (so.areaId=? or so.sysArea.areaPath like ?)");
			list.add(areaId.trim());
			list.add("%" + areaId.trim() + "%");
		}
		sb.append(" order by so.sysArea.areaSort,so.organSort");
		return super.getResultPage(sb.toString(), list, page, pageRows);
	}

	@Override
	public List<SysOrgan> findSysOrganList(String areaId, String organId)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysOrgan so where so.areaId=? and so.delFlag=? and so.organFlag = ?");
		list.add(areaId);
		list.add(BaseParameter.NO);
		list.add(BaseParameter.STATUS_ENABLE);
		if (!Common.isNullOrSpace(organId)) {
			sb.append(" and so.organId<>?");
			list.add(organId);
		}
		sb.append(" order by so.organSort");
		return super.queryByHql(sb.toString(), list);
	}

	@Override
	public List<SysOrgan> queryAllParentSysOrganList() throws Exception {
		
		StringBuffer hql = new StringBuffer("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag = ? and so.organFlag = ? and so.parentId = ?");
		ArrayList<Object> list = new ArrayList<Object>();
		
		list.add(BaseParameter.NO);
		list.add(BaseParameter.STATUS_ENABLE);
		list.add("");
		return super.queryByHql(hql.toString(), list);
	}

	@Override
	public boolean queryIsChild(String organId) throws Exception {

		if (!Common.isNullOrSpace(organId)) {
			
			StringBuffer hql = new StringBuffer("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag = ? and so.organFlag = ? and so.parentId = ?");
			List<Object> list = new ArrayList<Object>();
			list.add(BaseParameter.NO);
			list.add(BaseParameter.STATUS_ENABLE);
			list.add(organId);
			
			List<SysOrgan> resList = super.queryByHql(hql.toString(), list);
			return  resList != null && resList.size() > 0;
		}
		return false;
	}

	@Override
	public List<SysOrgan> queryChildOrganById(String parentId) throws Exception {
		
		if (!Common.isNullOrSpace(parentId)) {
			
			//根据父ID查询下面的子机构
			StringBuffer hql = new StringBuffer("from SysOrgan so where so.delFlag = ? and so.organFlag = ? and so.parentId = ?");
			List<Object> params = new ArrayList<Object>();
			params.add(BaseParameter.DELETE_NO);
			params.add(BaseParameter.STATUS_ENABLE);
			params.add(parentId);
			
			return super.queryByHql(hql.toString(), params);
		}
		return null;
	}
	
	@Override
	public List<SysOrgan> findTransactOrganList(String areaId, String organId)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag=? and so.organFlag = ? ");
		list.add(BaseParameter.NO);
		list.add(BaseParameter.STATUS_ENABLE);
		if (!Common.isNullOrSpace(areaId)) {
			sb.append(" and so.areaId = ?");
			list.add(areaId);
		}
		if (!Common.isNullOrSpace(organId)) {
			sb.append(" and so.organId<>?");
			list.add(organId);
		}
		sb.append(" and (so.parentId = '' or so.parentId is null)");//过滤子单位
		sb.append(" order by so.areaId,so.organSort");
		return super.queryByHql(sb.toString(), list);
	}

	@Override
	public List<SysOrgan> queryAllSysOrganList() throws Exception {
		StringBuffer hql = new StringBuffer("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag = ? and so.organFlag = ?");
		ArrayList<Object> list = new ArrayList<Object>();
		
		list.add(BaseParameter.NO);
		list.add(BaseParameter.STATUS_ENABLE);
		return super.queryByHql(hql.toString(), list);
	}

	@Override
	public List<SysOrgan> queryAllSysOrganListByAreaId(String areaId)
			throws Exception {
		StringBuffer hql = new StringBuffer("from pt.xtgl.jcgl.pojo.SysOrgan so where so.delFlag = ? and so.organFlag = ? and so.areaId = ?");
		ArrayList<Object> list = new ArrayList<Object>();
		
		list.add(BaseParameter.NO);
		list.add(BaseParameter.STATUS_ENABLE);
		list.add(areaId);
		return super.queryByHql(hql.toString(), list);
	}
}

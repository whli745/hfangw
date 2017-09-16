package pt.wzgl.nrgl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import pt.wzgl.nrgl.dao.IinfoTcategoryDao;
import pt.wzgl.nrgl.pojo.InfoTcategory;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;
/**
 * 栏目信息管理 dao
 * @author Administrator
 *
 */
public class InfoTcategoryDaoImpl extends BaseDaoImpl<InfoTcategory> implements IinfoTcategoryDao {

	@Override
	public ResultPage queryInfoTcategoryList(InfoTcategory infoTcategory,
			int page, int pageRows) throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from pt.wzgl.nrgl.pojo.InfoTcategory t where t.delFlag = ?");
		paramList.add(BaseParameter.NO);
		if(null != infoTcategory){
			if (!Common.isNullOrSpace(infoTcategory.getColumnName())) {// 栏目名称
				hql.append(" and t.columnName like ?");
				paramList.add("%" + infoTcategory.getColumnName().trim() + "%");
			}
			if (infoTcategory.getRank() != null) {// 级别
				hql.append(" and t.rank=?");
				paramList.add(infoTcategory.getRank());
			}
			if (infoTcategory.getParentId() != null) {// 父级id
				hql.append(" and t.parentId=?");
				paramList.add(infoTcategory.getParentId().trim());
			}
		}
		hql.append(" order by t.sort,t.createDate desc");
		return super.getResultPage(hql.toString(), paramList, page, pageRows);
	}

	@Override
	public InfoTcategory initInfoTcategory(String columnId) throws Exception {
		return super.onLoad(columnId);
	}

	@Override
	public void saveInfoTcategory(InfoTcategory infoTcategory) throws Exception {
		super.saveOrUpdate(infoTcategory);
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryListByParentId(String parentId)
			throws Exception {
		StringBuffer hql = new StringBuffer(" from pt.wzgl.nrgl.pojo.InfoTcategory t where t.delFlag=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.NO);
		if (!Common.isNullOrSpace(parentId)) {// 父级id
			hql.append(" and t.parentId=?");
			paramList.add(parentId.trim());
		}
		hql.append(" order by t.sort,t.createDate desc");
		return super.queryByHql(hql.toString(), paramList);
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryList() throws Exception {
		List<Object> paramList = new ArrayList<Object>();
		String hql ="from pt.wzgl.nrgl.pojo.InfoTcategory t where t.delFlag = ? and t.usingFlag = ? order by sort";
		paramList.add(BaseParameter.NO);
		paramList.add(BaseParameter.NO);
		
		return super.queryByHql(hql, paramList);
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryLeftList(String firstColumnId)
			throws Exception {
		if (Common.isNullOrSpace(firstColumnId))
			firstColumnId = "-1";
		StringBuffer hql = new StringBuffer(
				" from pt.wzgl.nrgl.pojo.InfoTcategory w where w.delFlag=? ");
		hql.append(" and  w.parentId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.NO);
		paramList.add(firstColumnId.trim());
		hql.append(" order by w.rank,w.sort,w.createDate desc");
		return super.queryByHql(hql.toString(), paramList);
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryListByColumnPath(
			String columnId) throws Exception {
		StringBuffer hql = new StringBuffer(" from pt.wzgl.nrgl.pojo.InfoTcategory t where t.delFlag=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.NO);
		if (!Common.isNullOrSpace(columnId)) {// 父级id
			hql.append(" and t.columnPath like ?");
			paramList.add("%" + columnId + "%");
		}
		hql.append(" order by t.sort,t.createDate desc");
		return super.queryByHql(hql.toString(), paramList);
	}

	@Override
	public List<InfoTcategory> getTZGGByInfoTcategoryName(String columnName)
			throws Exception {
		String hql = " from pt.wzgl.nrgl.pojo.InfoTcategory i where i.columnName = ?";
		return super.queryByHql(hql, new Object[]{columnName});
	}
}

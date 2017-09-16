package pt.wzgl.pzgl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.wzgl.pzgl.dao.IGgfwTypeDao;
import pt.wzgl.pzgl.pojo.GgfwType;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

/**
 * 公共服务分类信息 dao类
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public class GgfwTypeDaoImpl extends BaseDaoImpl<GgfwType> implements IGgfwTypeDao {

	@Override
	public GgfwType queryGgfwType(String typeId) throws Exception {
		return super.onGet(typeId);
	}

	@Override
	public List<GgfwType> queryGgfwTypeByIdsList(String typeIds) throws Exception {
		String hql = "from GgfwType d where d.typeId in (" + typeIds + ")";
		return super.queryByHql(hql, new Object[] {});
	}

	@Override
	public GgfwType getGgfwTypeByTypeCode(String typeCode) throws Exception {
		String hql = "from GgfwType d where d.typeCode = ?";
		return super.getFirstObject(hql, new Object[]{typeCode.trim()});
	}

	@Override
	public void saveOrUpdateGgfwType(GgfwType ggfwType) throws Exception {
		ggfwType.setUpdateTime(new Date());
		if(Common.isNullOrSpace(ggfwType.getTypeId())){
			ggfwType.setCreateTime(new Date());
		}
		saveOrUpdate(ggfwType);
	}

	@Override
	public List<GgfwType> queryAllGgfwTypeList(String delFlag) throws Exception {
		String hql = " from GgfwType where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(delFlag)) {
			hql += " and delFlag=?";
			paramList.add(delFlag);
		}
		hql = hql + " order by typePath asc, typeCode asc";
		return super.queryByHql(hql, paramList);
	}

	@Override
	public List<GgfwType> queryGgfwTypeByParentId(String parentId) throws Exception {
		String hql = " from GgfwType sd where sd.delFlag=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(parentId)) {
			hql += " and sd.parentId =? ";
			paramList.add(parentId);
		}
		return super.queryByHql(hql, paramList.toArray());
	}

	@Override
	public List<GgfwType> listGgfwTypeByPCode(String typeCode) throws Exception {
		String hql = " from GgfwType s where s.parentId = (select d.typeId from GgfwType d where d.typeCode=?) and s.delFlag=? order by typeCode asc";
		return super.queryByHql(hql, new Object[] { typeCode,
				BaseParameter.DELETE_NO });
	}

	@Override
	public ResultPage queryGgfwTypeList(GgfwType ggfwType, int page,
			int pageRows) throws Exception {
		String hql = " from GgfwType sd where 1=1 ";
		List<Object> parmaList = new ArrayList<Object>();
		if(ggfwType!=null){
			if (!Common.isNullOrSpace(ggfwType.getTypeName())) {
				hql += " and sd.typeName like ?";
				parmaList.add("%" + ggfwType.getTypeName() + "%");
			}
			if (!Common.isNullOrSpace(ggfwType.getDelFlag())) {
				hql += " and sd.delFlag =?";
				parmaList.add(ggfwType.getDelFlag());
			}
		}
		return super.getResultPage(hql, parmaList, page, pageRows);
	}
	
	@Override
	public boolean chkTypeCodeUnique(String typeId, String typeCode)
			throws Exception {
		String hql = " from GgfwType sd where sd.delFlag=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.DELETE_NO);
		if(Common.isNullOrSpace(typeId)){//新增
			hql+=" and sd.typeCode=?";
			paramList.add(typeCode.trim());
		}else{
			hql+=" and sd.typeCode=? and sd.typeId != ?";
			paramList.add(typeCode.trim());
			paramList.add(typeId.trim());
		}
		Object obj=super.getFirstObj(hql, paramList.toArray());
		if(obj!=null){
			return false;
		}else{
			return true;
		}
	}
}

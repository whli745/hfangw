package pt.wzgl.pzgl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.wzgl.pzgl.dao.IZjblTypeDao;
import pt.wzgl.pzgl.pojo.ZjblType;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

/**
 * 证件办理分类信息 dao类
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public class ZjblTypeDaoImpl extends BaseDaoImpl<ZjblType> implements IZjblTypeDao {
	@Override
	public ZjblType queryZjblType(String typeId) throws Exception {
		return super.onGet(typeId);
	}

	@Override
	public List<ZjblType> queryZjblTypeByIdsList(String typeIds) throws Exception {
		String hql = "from ZjblType d where d.typeId in (" + typeIds + ")";
		return super.queryByHql(hql, new Object[] {});
	}

	@Override
	public ZjblType getZjblTypeByTypeCode(String typeCode) throws Exception {
		String hql = "from ZjblType d where d.typeCode = ?";
		return super.getFirstObject(hql, new Object[]{typeCode.trim()});
	}

	@Override
	public void saveOrUpdateZjblType(ZjblType zjblType) throws Exception {
		zjblType.setUpdateTime(new Date());
		if(Common.isNullOrSpace(zjblType.getTypeId())){
			zjblType.setCreateTime(new Date());
		}
		saveOrUpdate(zjblType);
	}

	@Override
	public List<ZjblType> queryAllZjblTypeList(String delFlag) throws Exception {
		String hql = " from ZjblType where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(delFlag)) {
			hql += " and delFlag=?";
			paramList.add(delFlag);
		}
		hql = hql + " order by typePath asc, typeCode asc";
		return super.queryByHql(hql, paramList);
	}

	@Override
	public List<ZjblType> queryZjblTypeByParentId(String parentId) throws Exception {
		String hql = " from ZjblType sd where sd.delFlag=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(parentId)) {
			hql += " and sd.parentId =? ";
			paramList.add(parentId);
		}
		return super.queryByHql(hql, paramList.toArray());
	}

	@Override
	public List<ZjblType> listZjblTypeByPCode(String typeCode) throws Exception {
		String hql = " from ZjblType s where s.parentId = (select d.typeId from ZjblType d where d.typeCode=?) and s.delFlag=? order by typeCode asc";
		return super.queryByHql(hql, new Object[] { typeCode,
				BaseParameter.DELETE_NO });
	}

	@Override
	public ResultPage queryZjblTypeList(ZjblType zjblType, int page,
			int pageRows) throws Exception {
		String hql = " from ZjblType sd where 1=1 ";
		List<Object> parmaList = new ArrayList<Object>();
		if(zjblType!=null){
			if (!Common.isNullOrSpace(zjblType.getTypeName())) {
				hql += " and sd.typeName like ?";
				parmaList.add("%" + zjblType.getTypeName() + "%");
			}
			if (!Common.isNullOrSpace(zjblType.getDelFlag())) {
				hql += " and sd.delFlag =?";
				parmaList.add(zjblType.getDelFlag());
			}
		}
		return super.getResultPage(hql, parmaList, page, pageRows);
	}
	@Override
	public boolean chkTypeCodeUnique(String typeId, String typeCode)
			throws Exception {
		String hql = " from ZjblType sd where sd.delFlag=?";
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

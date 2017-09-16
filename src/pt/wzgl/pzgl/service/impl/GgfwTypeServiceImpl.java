package pt.wzgl.pzgl.service.impl;

import java.util.List;

import pt.wzgl.pzgl.dao.IGgfwTypeDao;
import pt.wzgl.pzgl.pojo.GgfwType;
import pt.wzgl.pzgl.service.IGgfwTypeService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

/**
 * 公共服务分类信息 service类
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public class GgfwTypeServiceImpl implements IGgfwTypeService {
	
	/**
	 * 公共服务分类信息 DAO
	 */
	private IGgfwTypeDao ggfwTypeDao;

	public IGgfwTypeDao getGgfwTypeDao() {
		return ggfwTypeDao;
	}
	public void setGgfwTypeDao(IGgfwTypeDao ggfwTypeDao) {
		this.ggfwTypeDao = ggfwTypeDao;
	}
	
	@Override
	public void deleteGgfwType(GgfwType ggfwType) throws Exception {
		ggfwType.setDelFlag(BaseParameter.YES);
		ggfwTypeDao.saveOrUpdateGgfwType(ggfwType);
	}

	@Override
	public GgfwType queryGgfwType(String typeId) throws Exception {
		GgfwType ggfwType = ggfwTypeDao.queryGgfwType(typeId);
		return ggfwType;
	}

	@Override
	public GgfwType getGgfwTypeByTypeCode(String typeCode) throws Exception {
		return ggfwTypeDao.getGgfwTypeByTypeCode(typeCode);
	}

	@Override
	public List<GgfwType> queryGgfwTypeByIdsList(String typeIds) throws Exception {
		return ggfwTypeDao.queryGgfwTypeByIdsList(typeIds);
	}

	@Override
	public void saveOrUpdateGgfwType(GgfwType ggfwType) throws Exception {
		if (Common.isNullOrSpace(ggfwType.getTypeId())) {
			ggfwType.setTypeId(null);
		}
		ggfwType.setDelFlag(BaseParameter.DELETE_NO);
		ggfwTypeDao.saveOrUpdateGgfwType(ggfwType);
		GgfwType parentGgfwType = ggfwTypeDao.queryGgfwType(ggfwType.getParentId());
		if (parentGgfwType != null) {
			ggfwType.setTypePath(parentGgfwType.getTypePath()+ggfwType.getTypeId() + ".");
		} else {
			ggfwType.setTypePath("-1.");
		}
	}

	@Override
	public ResultPage queryGgfwTypeList(GgfwType ggfwType, int page, int pageRows) throws Exception {
		ResultPage rp = ggfwTypeDao.queryGgfwTypeList(ggfwType, page, pageRows);
		return rp;
	}

	@Override
	public String getGgfwTypeTreeByPid(String parentId) throws Exception {
		String ggfwTypeJson;
		if (parentId == null) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			ggfwTypeJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.GGFW_TYPE_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return ggfwTypeJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<GgfwType> ggfwTypeList = ggfwTypeDao.queryGgfwTypeByParentId(parentId);
		if (ggfwTypeList == null) {
			ggfwTypeJson = "[]";
		} else {
			tmp.append("[");
			for (GgfwType ggfwType : ggfwTypeList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(ggfwType.getTypeId());
				tmp.append("{id:'")
						.append(ggfwType.getTypeId())
						.append("', name:'")
						.append(ggfwType.getTypeName())
						.append("', pId:'")
						.append((ggfwType.getParentId() == null ? "" : ggfwType
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			ggfwTypeJson = tmp.toString();
			if (ggfwTypeList.size() > 0)
				ggfwTypeJson = ggfwTypeJson
						.substring(0, ggfwTypeJson.length() - 1);
			ggfwTypeJson += "]";
		}
		return ggfwTypeJson;
	}
	
	public boolean isCurrentModuleHaveChildModules(String parentId)
			throws Exception {
		List<GgfwType> list = ggfwTypeDao.queryGgfwTypeByParentId(parentId);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<GgfwType> queryGgfwTypeList(String typeCode) throws Exception {
		return ggfwTypeDao.listGgfwTypeByPCode(typeCode);
	}

	@Override
	public List<GgfwType> queryGgfwTypeByParentId(String parentId) throws Exception {
		return ggfwTypeDao.queryGgfwTypeByParentId(parentId);
	}
	
	@Override
	public String getGgfwTypeTreeByParentCode(String typeCode) throws Exception {
		String ggfwTypeJson;
		StringBuffer tmp = new StringBuffer();
		List<GgfwType> ggfwTypeList = ggfwTypeDao.listGgfwTypeByPCode(typeCode);
		if (ggfwTypeList == null) {
			ggfwTypeJson = "[]";
		} else {
			tmp.append("[");
			for (GgfwType ggfwType : ggfwTypeList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(ggfwType.getTypeId());
				tmp.append("{id:'")
						.append(ggfwType.getTypeId())
						.append("', name:'")
						.append(ggfwType.getTypeName())
						.append("', pId:'")
						.append((ggfwType.getParentId() == null ? "" : ggfwType
								.getParentId()))
						.append("', isParent: " + isChildNode + ",code:'"
								+ ggfwType.getTypeCode() + "'},");
			}
			ggfwTypeJson = tmp.toString();
			if (ggfwTypeList.size() > 0)
				ggfwTypeJson = ggfwTypeJson
						.substring(0, ggfwTypeJson.length() - 1);
			ggfwTypeJson += "]";
		}
		return ggfwTypeJson;
	}
	
	@Override
	public boolean chkTypeCodeUnique(String typeId, String typeCode)
			throws Exception {
		return ggfwTypeDao.chkTypeCodeUnique(typeId,typeCode);
	}
}

package pt.wzgl.pzgl.service.impl;

import java.util.Date;
import java.util.List;

import pt.wzgl.pzgl.dao.IZjblTypeDao;
import pt.wzgl.pzgl.pojo.ZjblType;
import pt.wzgl.pzgl.service.IZjblTypeService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

/**
 * 证件办理分类信息 service类
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public class ZjblTypeServiceImpl implements IZjblTypeService {
	
	/**
	 * 证件办理分类信息 DAO
	 */
	private IZjblTypeDao zjblTypeDao;
	public IZjblTypeDao getZjblTypeDao() {
		return zjblTypeDao;
	}
	public void setZjblTypeDao(IZjblTypeDao zjblTypeDao) {
		this.zjblTypeDao = zjblTypeDao;
	}
	
	@Override
	public void deleteZjblType(ZjblType zjblType) throws Exception {
		zjblType.setDelFlag(BaseParameter.YES);
		zjblTypeDao.saveOrUpdateZjblType(zjblType);
	}

	@Override
	public ZjblType queryZjblType(String typeId) throws Exception {
		ZjblType zjblType = zjblTypeDao.queryZjblType(typeId);
		return zjblType;
	}

	@Override
	public ZjblType getZjblTypeByTypeCode(String typeCode) throws Exception {
		return zjblTypeDao.getZjblTypeByTypeCode(typeCode);
	}

	@Override
	public List<ZjblType> queryZjblTypeByIdsList(String typeIds) throws Exception {
		return zjblTypeDao.queryZjblTypeByIdsList(typeIds);
	}

	@Override
	public void saveOrUpdateZjblType(ZjblType zjblType) throws Exception {
		if (Common.isNullOrSpace(zjblType.getTypeId())) {
			zjblType.setTypeId(null);
			zjblType.setCreateTime(new Date());
		}
		zjblType.setDelFlag(BaseParameter.DELETE_NO);
		zjblTypeDao.saveOrUpdateZjblType(zjblType);
		ZjblType parentZjblType = zjblTypeDao.queryZjblType(zjblType.getParentId());
		if (parentZjblType != null) {
			zjblType.setTypePath(parentZjblType.getTypePath()
					+ zjblType.getTypeId() + ".");
		} else {
			zjblType.setTypePath("-1.");
		}
	}

	@Override
	public ResultPage queryZjblTypeList(ZjblType zjblType, int page, int pageRows) throws Exception {
		ResultPage rp = zjblTypeDao.queryZjblTypeList(zjblType, page, pageRows);
		return rp;
	}

	@Override
	public String getZjblTypeTreeByPid(String parentId) throws Exception {
		String zjblTypeJson;
		if (parentId == null) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			zjblTypeJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.ZJBL_TYPE_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return zjblTypeJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<ZjblType> zjblTypeList = zjblTypeDao.queryZjblTypeByParentId(parentId);
		if (zjblTypeList == null) {
			zjblTypeJson = "[]";
		} else {
			tmp.append("[");
			for (ZjblType zjblType : zjblTypeList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(zjblType.getTypeId());
				tmp.append("{id:'")
						.append(zjblType.getTypeId())
						.append("', name:'")
						.append(zjblType.getTypeName())
						.append("', pId:'")
						.append((zjblType.getParentId() == null ? "" : zjblType
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			zjblTypeJson = tmp.toString();
			if (zjblTypeList.size() > 0)
				zjblTypeJson = zjblTypeJson
						.substring(0, zjblTypeJson.length() - 1);
			zjblTypeJson += "]";
		}
		return zjblTypeJson;
	}
	
	public boolean isCurrentModuleHaveChildModules(String parentId)
			throws Exception {
		List<ZjblType> list = zjblTypeDao.queryZjblTypeByParentId(parentId);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<ZjblType> queryZjblTypeList(String typeCode) throws Exception {
		return zjblTypeDao.listZjblTypeByPCode(typeCode);
	}

	@Override
	public List<ZjblType> queryZjblTypeByParentId(String parentId) throws Exception {
		return zjblTypeDao.queryZjblTypeByParentId(parentId);
	}
	
	@Override
	public String getZjblTypeTreeByParentCode(String typeCode) throws Exception {
		String zjblTypeJson;
		StringBuffer tmp = new StringBuffer();
		List<ZjblType> zjblTypeList = zjblTypeDao.listZjblTypeByPCode(typeCode);
		if (zjblTypeList == null) {
			zjblTypeJson = "[]";
		} else {
			tmp.append("[");
			for (ZjblType zjblType : zjblTypeList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(zjblType.getTypeId());
				tmp.append("{id:'")
						.append(zjblType.getTypeId())
						.append("', name:'")
						.append(zjblType.getTypeName())
						.append("', pId:'")
						.append((zjblType.getParentId() == null ? "" : zjblType
								.getParentId()))
						.append("', isParent: " + isChildNode + ",code:'"
								+ zjblType.getTypeCode() + "'},");
			}
			zjblTypeJson = tmp.toString();
			if (zjblTypeList.size() > 0)
				zjblTypeJson = zjblTypeJson
						.substring(0, zjblTypeJson.length() - 1);
			zjblTypeJson += "]";
		}
		return zjblTypeJson;
	}
	@Override
	public boolean chkTypeCodeUnique(String typeId, String typeCode)
			throws Exception {
		return zjblTypeDao.chkTypeCodeUnique(typeId,typeCode);
	}
}

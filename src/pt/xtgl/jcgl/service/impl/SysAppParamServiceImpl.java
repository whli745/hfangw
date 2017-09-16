package pt.xtgl.jcgl.service.impl;

import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysAppParamDao;
import pt.xtgl.jcgl.pojo.SysAppParam;
import pt.xtgl.jcgl.service.ISysAppParamService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

/**
 * 系统参数信息 service类
 * 
 * @author : yaosw
 * @date : 2016-02-19
 */
public class SysAppParamServiceImpl implements ISysAppParamService {
	
	/**
	 * 系统参数信息 DAO
	 */
	private ISysAppParamDao sysAppParamDao;
	public ISysAppParamDao getSysAppParamDao() {
		return sysAppParamDao;
	}
	public void setSysAppParamDao(ISysAppParamDao sysAppParamDao) {
		this.sysAppParamDao = sysAppParamDao;
	}
	
	@Override
	public void deleteSysAppParam(SysAppParam sysAppParam) throws Exception {
		sysAppParam.setDelFlag(BaseParameter.YES);
		sysAppParamDao.saveOrUpdateSysAppParam(sysAppParam);
	}

	@Override
	public SysAppParam querySysAppParam(String paramId) throws Exception {
		SysAppParam sysAppParam = sysAppParamDao.querySysAppParam(paramId);
		return sysAppParam;
	}

	@Override
	public SysAppParam getSysAppParamByParamCode(String paramCode) throws Exception {
		return sysAppParamDao.getSysAppParamByParamCode(paramCode);
	}

	@Override
	public List<SysAppParam> querySysAppParamByIdsList(String paramIds) throws Exception {
		return sysAppParamDao.querySysAppParamByIdsList(paramIds);
	}

	@Override
	public void saveOrUpdateSysAppParam(SysAppParam sysAppParam) throws Exception {
		if (Common.isNullOrSpace(sysAppParam.getParamId())) {
			sysAppParam.setParamId(null);
			sysAppParam.setCreateTime(new Date());
		}
		sysAppParam.setDelFlag(BaseParameter.DELETE_NO);
		sysAppParamDao.saveOrUpdateSysAppParam(sysAppParam);
		SysAppParam parentSysAppParam = sysAppParamDao.querySysAppParam(sysAppParam.getParentId());
		if (parentSysAppParam != null) {
			sysAppParam.setParamPath(parentSysAppParam.getParamPath()
					+ sysAppParam.getParamId() + ".");
		} else {
			sysAppParam.setParamPath("-1.");
		}
	}

	@Override
	public ResultPage querySysAppParamList(SysAppParam sysAppParam, int page, int pageRows) throws Exception {
		ResultPage rp = sysAppParamDao.querySysAppParamList(sysAppParam, page, pageRows);
		return rp;
	}

	@Override
	public String getSysAppParamTreeByPid(String parentId) throws Exception {
		String sysAppParamJson;
		if (parentId == null) {
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(util.BaseParameter.SYSTREE_ROOT_ID);
			sysAppParamJson = "[{id:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',name:'" + util.BaseParameter.SYSAPPPARAM_ROOT_NAME
					+ "',pId:'" + util.BaseParameter.SYSTREE_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return sysAppParamJson;
		}
		StringBuffer tmp = new StringBuffer();
		List<SysAppParam> sysAppParamList = sysAppParamDao.querySysAppParamByParentId(parentId);
		if (sysAppParamList == null) {
			sysAppParamJson = "[]";
		} else {
			tmp.append("[");
			for (SysAppParam sysAppParam : sysAppParamList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysAppParam.getParamId());
				tmp.append("{id:'")
						.append(sysAppParam.getParamId())
						.append("', name:'")
						.append(sysAppParam.getParamName())
						.append("', pId:'")
						.append((sysAppParam.getParentId() == null ? "" : sysAppParam
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			sysAppParamJson = tmp.toString();
			if (sysAppParamList.size() > 0)
				sysAppParamJson = sysAppParamJson
						.substring(0, sysAppParamJson.length() - 1);
			sysAppParamJson += "]";
		}
		return sysAppParamJson;
	}
	
	public boolean isCurrentModuleHaveChildModules(String parentId)
			throws Exception {
		List<SysAppParam> list = sysAppParamDao.querySysAppParamByParentId(parentId);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<SysAppParam> querySysAppParamList(String paramCode) throws Exception {
		return sysAppParamDao.listSysAppParamByPCode(paramCode);
	}

	@Override
	public List<SysAppParam> querySysAppParamByParentId(String parentId) throws Exception {
		return sysAppParamDao.querySysAppParamByParentId(parentId);
	}
	
	@Override
	public String getSysAppParamTreeByParentCode(String paramCode) throws Exception {
		String sysAppParamJson;
		StringBuffer tmp = new StringBuffer();
		List<SysAppParam> sysAppParamList = sysAppParamDao.listSysAppParamByPCode(paramCode);
		if (sysAppParamList == null) {
			sysAppParamJson = "[]";
		} else {
			tmp.append("[");
			for (SysAppParam sysAppParam : sysAppParamList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(sysAppParam.getParamId());
				tmp.append("{id:'")
						.append(sysAppParam.getParamId())
						.append("', name:'")
						.append(sysAppParam.getParamName())
						.append("', pId:'")
						.append((sysAppParam.getParentId() == null ? "" : sysAppParam
								.getParentId()))
						.append("', isParent: " + isChildNode + ",code:'"
								+ sysAppParam.getParamCode() + "'},");
			}
			sysAppParamJson = tmp.toString();
			if (sysAppParamList.size() > 0)
				sysAppParamJson = sysAppParamJson
						.substring(0, sysAppParamJson.length() - 1);
			sysAppParamJson += "]";
		}
		return sysAppParamJson;
	}
	@Override
	public boolean chkParamCodeUnique(String paramId, String paramCode)
			throws Exception {
		return sysAppParamDao.chkParamCodeUnique(paramId,paramCode);
	}
}

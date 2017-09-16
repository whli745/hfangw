package pt.xtgl.jcgl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.dao.ISysAppParamDao;
import pt.xtgl.jcgl.pojo.SysAppParam;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.dao.BaseDaoImpl;

/**
 * 系统参数信息 dao类
 * 
 * @author : yaosw
 * @date : 2016-02-19
 */
public class SysAppParamDaoImpl extends BaseDaoImpl<SysAppParam> implements ISysAppParamDao {
	@Override
	public SysAppParam querySysAppParam(String paramId) throws Exception {
		return super.onGet(paramId);
	}

	@Override
	public List<SysAppParam> querySysAppParamByIdsList(String paramIds) throws Exception {
		String hql = "from SysAppParam d where d.paramId in (" + paramIds + ")";
		return super.queryByHql(hql, new Object[] {});
	}

	@Override
	public SysAppParam getSysAppParamByParamCode(String paramCode) throws Exception {
		String hql = "from SysAppParam d where d.paramCode = ?";
		return super.getFirstObject(hql, new Object[]{paramCode.trim()});
	}

	@Override
	public void saveOrUpdateSysAppParam(SysAppParam sysAppParam) throws Exception {
		sysAppParam.setUpdateTime(new Date());
		if(Common.isNullOrSpace(sysAppParam.getParamId())){
			sysAppParam.setCreateTime(new Date());
		}
		saveOrUpdate(sysAppParam);
	}

	@Override
	public List<SysAppParam> queryAllSysAppParamList(String delFlag) throws Exception {
		String hql = " from SysAppParam where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		if (!Common.isNullOrSpace(delFlag)) {
			hql += " and delFlag=?";
			paramList.add(delFlag);
		}
		hql = hql + " order by paramPath asc, paramCode asc";
		return super.queryByHql(hql, paramList);
	}

	@Override
	public List<SysAppParam> querySysAppParamByParentId(String parentId) throws Exception {
		String hql = " from SysAppParam sd where sd.delFlag=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.DELETE_NO);
		if (!Common.isNullOrSpace(parentId)) {
			hql += " and sd.parentId =? ";
			paramList.add(parentId);
		}
		return super.queryByHql(hql, paramList.toArray());
	}

	@Override
	public List<SysAppParam> listSysAppParamByPCode(String paramCode) throws Exception {
		String hql = " from SysAppParam s where s.parentId = (select d.paramId from SysAppParam d where d.paramCode=?) and s.delFlag=? order by paramCode asc";
		return super.queryByHql(hql, new Object[] { paramCode,
				BaseParameter.DELETE_NO });
	}

	@Override
	public ResultPage querySysAppParamList(SysAppParam sysAppParam, int page,
			int pageRows) throws Exception {
		String hql = " from SysAppParam sd where 1=1 ";
		List<Object> parmaList = new ArrayList<Object>();
		if(sysAppParam!=null){
			if (!Common.isNullOrSpace(sysAppParam.getParamName())) {
				hql += " and sd.paramName like ?";
				parmaList.add("%" + sysAppParam.getParamName() + "%");
			}
			if (!Common.isNullOrSpace(sysAppParam.getDelFlag())) {
				hql += " and sd.delFlag =?";
				parmaList.add(sysAppParam.getDelFlag());
			}
		}
		return super.getResultPage(hql, parmaList, page, pageRows);
	}
	@Override
	public boolean chkParamCodeUnique(String paramId, String paramCode)
			throws Exception {
		String hql = " from SysAppParam sd where sd.delFlag=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(BaseParameter.DELETE_NO);
		if(Common.isNullOrSpace(paramId)){//新增
			hql+=" and sd.paramCode=?";
			paramList.add(paramCode.trim());
		}else{
			hql+=" and sd.paramCode=? and sd.paramId != ?";
			paramList.add(paramCode.trim());
			paramList.add(paramId.trim());
		}
		Object obj=super.getFirstObj(hql, paramList.toArray());
		if(obj!=null){
			return false;
		}else{
			return true;
		}
	}
}

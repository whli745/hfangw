package pt.wzgl.nrgl.service.impl;

import java.util.Date;
import java.util.List;

import pt.wzgl.nrgl.dao.IinfoTcategoryDao;
import pt.wzgl.nrgl.pojo.InfoTcategory;
import pt.wzgl.nrgl.service.IInfoTcategoryService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;

/**
 * 栏目信息管理service
 * @author Administrator
 *
 */
public class InfoTcategoryServiceImpl implements IInfoTcategoryService {
	
	private IinfoTcategoryDao infoTcategoryDao;//栏目信息管理dao

	public IinfoTcategoryDao getInfoTcategoryDao() {
		return infoTcategoryDao;
	}

	public void setInfoTcategoryDao(IinfoTcategoryDao infoTcategoryDao) {
		this.infoTcategoryDao = infoTcategoryDao;
	}

	@Override
	public ResultPage queryInfoTcategoryList(InfoTcategory infoTcategory,
			int page, int pageRows) throws Exception {
		return infoTcategoryDao.queryInfoTcategoryList(infoTcategory,page,pageRows);
	}

	@Override
	public InfoTcategory initInfoTcategory(String columnId) throws Exception {
		return infoTcategoryDao.initInfoTcategory(columnId);
	}

	@Override
	public void saveInfoTcategory(InfoTcategory infoTcategory, String userId)
			throws Exception {
		infoTcategory.setDelFlag(BaseParameter.YES);//未删除
		infoTcategory.setCreateBy(userId);//创建人
		infoTcategory.setCreateDate(new Date());//创建时间
		//父级id
		if(Common.isNullOrSpace(infoTcategory.getParentId()) || infoTcategory.getParentId().equals(BaseParameter.SYSTREE_ROOT_ID)) { 
			infoTcategory.setParentId(BaseParameter.SYSTREE_ROOT_ID);//父级id为 -1
		} else {
			String columnPath = initInfoTcategory(infoTcategory.getParentId()).getColumnPath()+infoTcategory.getParentId();
			infoTcategory.setColumnPath(columnPath);//站点id路径
		}
		//级别
		if(infoTcategory.getRank() == null) infoTcategory.setRank(1);
		//是否启用
		infoTcategory.setUsingFlag(BaseParameter.NO);//未启用
		if(Common.isNullOrSpace(infoTcategory.getColumnId())) infoTcategory.setColumnId(null);
		infoTcategoryDao.saveInfoTcategory(infoTcategory);
	}

	@Override
	public void usingInfoTcategory(String columnId, String toUsingFlag)
			throws Exception {
		//得到栏目信息管理对象
		InfoTcategory infoTcategory = this.initInfoTcategory(columnId);
		if(infoTcategory == null) return;
		//修改启用标志
		infoTcategory.setUsingFlag(toUsingFlag);
		infoTcategoryDao.saveInfoTcategory(infoTcategory);
	}

	@Override
	public void delInfoTcategory(String columnId) throws Exception {
		//得到栏目信息管理对象
		InfoTcategory infoTcategory = this.initInfoTcategory(columnId);
		if(infoTcategory == null) return;
		//修改删除标志
		infoTcategory.setDelFlag(BaseParameter.YES);
		//通过路径查询栏目list
		List<InfoTcategory> infoTcategoryList = this.queryInfoTcategoryListByColumnPath(columnId);
		for(InfoTcategory infoTcategory1: infoTcategoryList){
			infoTcategory1.setDelFlag(BaseParameter.YES);
			infoTcategoryDao.saveInfoTcategory(infoTcategory1);
		}
		infoTcategoryDao.saveInfoTcategory(infoTcategory);
		
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryListByParentId(String columnId)
			throws Exception {
		return infoTcategoryDao.queryInfoTcategoryListByParentId(columnId);
	}

	@Override
	public String getParentNameByParentId(String parentId) throws Exception {
		if(Common.isNullOrSpace(parentId) || parentId.equals("-1")) return "";
		//得到栏目信息管理对象
		InfoTcategory infoTcategory = this.initInfoTcategory(parentId);
		if(infoTcategory == null) return "";
		return infoTcategory.getColumnName();
	}

	@Override
	public String infoTcategoryJson() throws Exception {
		//查询所有启用的未删除的栏目
		List<InfoTcategory> queryInfoTcategoryList = infoTcategoryDao.queryInfoTcategoryList();
		
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		boolean disabledFlag = false;//是否可点击
		
		int len = queryInfoTcategoryList == null ?0:queryInfoTcategoryList.size();
		
		if(len==0)
			sb.append("{id:\"" +  BaseParameter.WEBCOLUMN_ROOT_ID + "\"," +
					" pId:\"" + BaseParameter.WEBCOLUMN_ROOT_ID + "\", name:\""
					+ BaseParameter.WEBCOLUMN_ROOT_NAME 
					+ "\", checked:" + ((flag) ? "true" : "false")
					+", chkDisabled:" + ((disabledFlag) ? "true" : "false")
					+ " ,open:\""+ true
					+ "\"}");
		else
			sb.append("{id:\"" +  BaseParameter.WEBCOLUMN_ROOT_ID + "\"," +
					" pId:\"" + BaseParameter.WEBCOLUMN_ROOT_ID + "\", name:\""
					+ BaseParameter.WEBCOLUMN_ROOT_NAME 
					+ "\", checked:" + ((flag) ? "true" : "false")
					+", chkDisabled:" + ((disabledFlag) ? "true" : "false")
					+ " ,open:\""+ true
					+ "\"},");
		
		for (int i = 0; i < len; i++) {
			InfoTcategory infoTcategory = (InfoTcategory) queryInfoTcategoryList.get(i);
			
			sb.append("{id:\"" + infoTcategory.getColumnId().trim() + "\"," +
					" pId:\"" + infoTcategory.getParentId().trim() + "\", name:\""
					+ infoTcategory.getColumnName().trim() 
					+ "\", checked:" + ((flag) ? "true" : "false")
					+", chkDisabled:" + ((disabledFlag) ? "true" : "false")
					+ " ,open:\""+ true
					+ "\"}");
			if (i != len - 1)
				sb.append(",");
		}
		return sb.toString();
	}

	@Override
	public String getInfoTcategoryLeftListByPid(String parentId)
			throws Exception {
		String infoTcategoryJson;
		if(null == parentId){
			boolean isChildNode = this
					.isCurrentModuleHaveChildModules(BaseParameter.WEBCOLUMN_ROOT_ID);
			infoTcategoryJson = "[{id:'" + BaseParameter.WEBCOLUMN_ROOT_ID
					+ "',name:'" + BaseParameter.WEBCOLUMN_ROOT_NAME
					+ "',pId:'" + BaseParameter.WEBCOLUMN_ROOT_ID
					+ "',isParent: " + isChildNode + "}]";
			return infoTcategoryJson;
		}
		
		StringBuffer tmp = new StringBuffer();
		List<InfoTcategory> webColumnInfoList = infoTcategoryDao.queryInfoTcategoryLeftList(parentId);
		if (webColumnInfoList == null) {
			infoTcategoryJson = "[]";
		} else {
			tmp.append("[");
			for (InfoTcategory temp : webColumnInfoList) {
				boolean isChildNode = this
						.isCurrentModuleHaveChildModules(temp.getColumnId());
				tmp.append("{id:'")
						.append(temp.getColumnId())
						.append("', name:'")
						.append(temp.getColumnName())
						.append("', pId:'")
						.append((temp.getParentId() == null ? "" : temp
								.getParentId()))
						.append("', isParent: " + isChildNode + "},");
			}
			infoTcategoryJson = tmp.toString();
			if (webColumnInfoList.size() > 0)
				infoTcategoryJson = infoTcategoryJson
						.substring(0, infoTcategoryJson.length() - 1);
			infoTcategoryJson += "]";
		}
		return infoTcategoryJson;
	}

	public boolean isCurrentModuleHaveChildModules(String parentId)
			throws Exception {
		List<InfoTcategory> list = infoTcategoryDao.queryInfoTcategoryLeftList(parentId);
		if (list == null || list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void saveOrUpdateInfoTcategory(InfoTcategory infoTcategory,
			String userId) throws Exception {
		infoTcategory.setDelFlag(BaseParameter.NO);//未删除
		infoTcategory.setCreateBy(userId);//创建人
		infoTcategory.setCreateDate(new Date());//创建时间
		//级别
		if(infoTcategory.getRank() == null) infoTcategory.setRank(1);
		//是否启用
		if(Common.isNullOrSpace(infoTcategory.getColumnId())) 
			infoTcategory.setColumnId(null);
		infoTcategoryDao.saveInfoTcategory(infoTcategory);
		
	}

	@Override
	public boolean hasNextInfoTcategory(String columnId) throws Exception {
		try {
			List<InfoTcategory> infoTcategoryList = infoTcategoryDao.queryInfoTcategoryListByParentId(columnId);
			if(infoTcategoryList == null || infoTcategoryList.size() <=0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<InfoTcategory> queryInfoTcategoryListByColumnPath(
			String columnId) throws Exception {
		return infoTcategoryDao.queryInfoTcategoryListByColumnPath(columnId);
	}

	@Override
	public List<InfoTcategory> getTZGGByInfoTcategoryName(String columnName)
			throws Exception {
		return infoTcategoryDao.getTZGGByInfoTcategoryName(columnName);
	}
}

package web.action;

import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;
import web.pojo.SecondHandHouse;
import web.service.ISecondHandHouseService;

public class SecondHandHouseManageAction extends BaseAction{

	private static final long serialVersionUID = -6390101475197323599L;
	
	private ISysAreaService sysAreaService;
	private ISysDictService sysDictService;
	private ISecondHandHouseService secondHandHouseService;
	
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private String areaId;
	private String areaName;
	private ResultPage rp;
	private SecondHandHouse queryObj;
	private SecondHandHouse secondHandHouse;
	
	/**
	 * 查询列表
	 */
	public String querySecondHandHouseList(){
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			
			if(queryObj == null){
				queryObj = new SecondHandHouse();
			}
			queryObj.setArea_id(areaId);
			
			rp = secondHandHouseService.querySecondHandHouseList(queryObj, getPage(),getPageRows());
			
			super.setPageParam(rp);
			
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增、修改
	 */
	public String initSecondHandHouseEdit(){
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			
			if(queryObj == null){
				queryObj = new SecondHandHouse();
			}
			queryObj.setArea_id(areaId);
			
			BaseParameter.esfsjDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_SJ);
			BaseParameter.esfmjDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_MJ);
			BaseParameter.esffxDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_FX);
			BaseParameter.esfjzndDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_JZND);
			BaseParameter.esflcDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_LC);
			BaseParameter.esffwlxDictList = sysDictService.querySysDictList(BaseParameter.DICT_ESF_FWLX);
			
			secondHandHouse = secondHandHouseService.getSecondHandHouseByOid(oid);
					
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 保存、修改
	 */
	public String secondHandHouseEdit(){
		try{
			secondHandHouseService.saveOrUpdateSecondHandHouse(secondHandHouse);
		}catch (Exception e) {
			
		}
		return SUCCESS;
	}
	
	public void delSecondHandHouseAjax(){
		try{
			secondHandHouseService.delSecondHandHouse(oid);
			super.returnAjaxInfo(SUCCESS);
		}catch (Exception e) {
			
		}
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public ISecondHandHouseService getSecondHandHouseService() {
		return secondHandHouseService;
	}

	public void setSecondHandHouseService(
			ISecondHandHouseService secondHandHouseService) {
		this.secondHandHouseService = secondHandHouseService;
	}

	public String getTopAreaId() {
		return topAreaId;
	}

	public void setTopAreaId(String topAreaId) {
		this.topAreaId = topAreaId;
	}

	public String getSysAreaJson() {
		return sysAreaJson;
	}

	public void setSysAreaJson(String sysAreaJson) {
		this.sysAreaJson = sysAreaJson;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public ResultPage getRp() {
		return rp;
	}

	public void setRp(ResultPage rp) {
		this.rp = rp;
	}

	public SecondHandHouse getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SecondHandHouse queryObj) {
		this.queryObj = queryObj;
	}

	public SecondHandHouse getSecondHandHouse() {
		return secondHandHouse;
	}

	public void setSecondHandHouse(SecondHandHouse secondHandHouse) {
		this.secondHandHouse = secondHandHouse;
	}
	
	
}

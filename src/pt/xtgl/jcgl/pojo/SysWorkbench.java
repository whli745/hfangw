package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

/**
 * 自定义桌面工作台管理
 * 
 * @author wanghw
 * @date 2013-04-08
 * 
 */
@SuppressWarnings("serial")
public class SysWorkbench extends BaseObject {
	private String workbenchId;// 工作台编号
	private String workbenchName;// 工作台名称
	private String workbenchUrl;// 请求地址
	private String workbenchSystem;// 工作台所属系统（角色ID集合）
	private String systemName;// 角色名称集合
	private String workbenchUseFlag;// 工作台是否启用
	private String workbenchDelFlag;// 是非被删除 0 未删除；1 已删除
	private String workbenchDefault;// 工作台是手动配置还是默认配置 0 默认；1 手动配置
	private String workbenchServiceType;// 业务类型
	private String workbenchTypesetting;// 排版方式 1代表一行一个，2代表一行两个
	private String workbenchModule;
	private String areaId;// 所属区划
	private Integer orderBy;// 排序号
	private SysDict serviceType;// 所属业务类型
	private SysArea sysArea;// 所属区划
	private SysModule sysModule;

	public SysWorkbench() {
		super();
	}

	public String getWorkbenchId() {
		return workbenchId;
	}

	public void setWorkbenchId(String workbenchId) {
		this.workbenchId = workbenchId;
	}

	public String getWorkbenchName() {
		return workbenchName;
	}

	public void setWorkbenchName(String workbenchName) {
		this.workbenchName = workbenchName;
	}

	public String getWorkbenchUrl() {
		return workbenchUrl;
	}

	public void setWorkbenchUrl(String workbenchUrl) {
		this.workbenchUrl = workbenchUrl;
	}

	public String getWorkbenchSystem() {
		return workbenchSystem;
	}

	public void setWorkbenchSystem(String workbenchSystem) {
		this.workbenchSystem = workbenchSystem;
	}

	public String getWorkbenchUseFlag() {
		return workbenchUseFlag;
	}

	public void setWorkbenchUseFlag(String workbenchUseFlag) {
		this.workbenchUseFlag = workbenchUseFlag;
	}

	public String getWorkbenchDelFlag() {
		return workbenchDelFlag;
	}

	public void setWorkbenchDelFlag(String workbenchDelFlag) {
		this.workbenchDelFlag = workbenchDelFlag;
	}

	public String getWorkbenchDefault() {
		return workbenchDefault;
	}

	public void setWorkbenchDefault(String workbenchDefault) {
		this.workbenchDefault = workbenchDefault;
	}

	public String getWorkbenchServiceType() {
		return workbenchServiceType;
	}

	public void setWorkbenchServiceType(String workbenchServiceType) {
		this.workbenchServiceType = workbenchServiceType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public SysDict getServiceType() {
		return serviceType;
	}

	public void setServiceType(SysDict serviceType) {
		this.serviceType = serviceType;
	}

	public SysArea getSysArea() {
		return sysArea;
	}

	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}

	public String getWorkbenchTypesetting() {
		return workbenchTypesetting;
	}

	public void setWorkbenchTypesetting(String workbenchTypesetting) {
		this.workbenchTypesetting = workbenchTypesetting;
	}

	public String getWorkbenchModule() {
		return workbenchModule;
	}

	public void setWorkbenchModule(String workbenchModule) {
		this.workbenchModule = workbenchModule;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public SysModule getSysModule() {
		return sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

}

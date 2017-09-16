package pt.wzgl.pzgl.pojo;

import java.util.Date;
import java.util.List;

import util.base.BaseObject;

/**
 * 用户中心菜单管理
 * @author yaosw
 * @version v1.0 2016-2-22
 */
public class NetUserModule extends BaseObject {

	private static final long serialVersionUID = 8300712135436108614L;
	
	private String moduleId;
	private String moduleName;
	private String moduleUrl;
	private String parentId;
	private String moduleApp;// 菜单所属平台
	private Date moduleDate;
	private String moduleDesc;
	private String moduleTag;	// 菜单标签
	private String moduleStatus;// 菜单状态 启用 不启用
	private String moduleType;// 菜单类型 1表示普通模块，2表示功能模块
	private String moduleControl;// 功能模块，对应id
	private String moduleImg;
	private String delFlag;// 是否删除
	private Integer moduleLevel;// 菜单级别
	private Integer moduleOrder;// 排序
	private String modulePids;// 菜单的所有父id,用于判断对应没有子权限的菜单是否显示
	// 新增以下属性
	private String controlType; // 菜单按钮类型 1、列表按钮；2、普通按钮
	private String isValidate; // 是否需要验证（弹出确认对话框）
	private String validateContent; // 是否需要验证
	private String isJsRequest; // 是否为JavaScript请求（onclick请求）
	
	private String authen; // 是否需实名认证 0 不需要，1 需要
	
	/**
	 * 表明该菜单是企业的，个人的，还是企业个人共用的。
	 */
	private String menuOwner;
	
	private List<NetUserModule> childModuleList; //当前菜单的子菜单
	
	private Date updateTime;
	private String modulePath;
	
	public String getModuleUrl_() {
		if(moduleUrl==null)return "";
		if (moduleUrl.indexOf("?") > 0)
			return moduleUrl + "&moduleOid=" + moduleId;
		else
			return moduleUrl + "?moduleOid=" + moduleId;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getModuleApp() {
		return moduleApp;
	}
	public void setModuleApp(String moduleApp) {
		this.moduleApp = moduleApp;
	}
	public Date getModuleDate() {
		return moduleDate;
	}
	public void setModuleDate(Date moduleDate) {
		this.moduleDate = moduleDate;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getModuleTag() {
		return moduleTag;
	}
	public void setModuleTag(String moduleTag) {
		this.moduleTag = moduleTag;
	}
	public String getModuleStatus() {
		return moduleStatus;
	}
	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleControl() {
		return moduleControl;
	}
	public void setModuleControl(String moduleControl) {
		this.moduleControl = moduleControl;
	}
	public String getModuleImg() {
		return moduleImg;
	}
	public void setModuleImg(String moduleImg) {
		this.moduleImg = moduleImg;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getModuleLevel() {
		return moduleLevel;
	}
	public void setModuleLevel(Integer moduleLevel) {
		this.moduleLevel = moduleLevel;
	}
	public Integer getModuleOrder() {
		return moduleOrder;
	}
	public void setModuleOrder(Integer moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	public String getModulePids() {
		return modulePids;
	}
	public void setModulePids(String modulePids) {
		this.modulePids = modulePids;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getIsValidate() {
		return isValidate;
	}
	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	public String getValidateContent() {
		return validateContent;
	}
	public void setValidateContent(String validateContent) {
		this.validateContent = validateContent;
	}
	public String getIsJsRequest() {
		return isJsRequest;
	}
	public void setIsJsRequest(String isJsRequest) {
		this.isJsRequest = isJsRequest;
	}
	public String getAuthen() {
		return authen;
	}
	public void setAuthen(String authen) {
		this.authen = authen;
	}
	public String getMenuOwner() {
		return menuOwner;
	}
	public void setMenuOwner(String menuOwner) {
		this.menuOwner = menuOwner;
	}
	public List<NetUserModule> getChildModuleList() {
		return childModuleList;
	}
	public void setChildModuleList(List<NetUserModule> childModuleList) {
		this.childModuleList = childModuleList;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getModulePath() {
		return modulePath;
	}
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	
}

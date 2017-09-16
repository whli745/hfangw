package pt.xtgl.jcgl.pojo;

import java.util.List;

import util.base.BaseObject;

public class SysRole extends BaseObject {

	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String roleStatus;
	private String delFlag;
	private String roleDesc;
	private String areaId;
	private SysArea sysArea;

	private List<SysModule> moduleList;
	private List<SysUser> userList;

	public SysRole(String roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public SysRole() {
		super();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public SysArea getSysArea() {
		return sysArea;
	}

	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}

	public List<SysModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SysModule> moduleList) {
		this.moduleList = moduleList;
	}

	public List<SysUser> getUserList() {
		return userList;
	}

	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}

}

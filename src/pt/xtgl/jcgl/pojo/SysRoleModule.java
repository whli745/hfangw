package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

public class SysRoleModule extends BaseObject{

	private static final long serialVersionUID = 1L;
	private String oid;
	private String roleId;
	private String moduleId;
	
	private SysRole sysRole;
	private SysModule sysModule;
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public SysModule getSysModule() {
		return sysModule;
	}
	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	
	
	
	
}

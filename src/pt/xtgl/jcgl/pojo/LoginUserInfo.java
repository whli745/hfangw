package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

public class LoginUserInfo extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userOid;
	private SysUser user;
	private String pwd;//为加密前的
	private String ssessionId;
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserOid() {
		return userOid;
	}
	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}
	public String getSsessionId() {
		return ssessionId;
	}
	public void setSsessionId(String ssessionId) {
		this.ssessionId = ssessionId;
	}
	
	
}

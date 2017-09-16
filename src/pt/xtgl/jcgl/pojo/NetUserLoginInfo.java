package pt.xtgl.jcgl.pojo;

import pt.wzgl.pzgl.pojo.NetUser;
import util.base.BaseObject;

/**
 * @author Administrator
 * 网站用户登录信息
 */
public class NetUserLoginInfo extends BaseObject{
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String userOid;
	private String pwd;//为加密前的
	private String ssessionId;
	private NetUser netUser;// 网站登录用户
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public NetUser getNetUser() {
		return netUser;
	}
	public void setNetUser(NetUser netUser) {
		this.netUser = netUser;
	}
	
}

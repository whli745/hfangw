package util;

import org.apache.struts2.ServletActionContext;

public class LogTool {

	public static final Integer lOG_LAYER_INFO = 0; // "一般";
	public static final Integer lOG_LAYER_ERROR = 1;// "严重";
	public static final String YYID = "402881f236e856bc0136e87cd6f10148";
	private String ipaddr;
	private String userid;
	private String usercode;
	private String username;

	protected String getIPAddress() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

}

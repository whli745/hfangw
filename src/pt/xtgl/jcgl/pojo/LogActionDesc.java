package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

/**
 * action 方法描述
 * 
 * @author dusd
 * @date 2015-12-9
 */
public class LogActionDesc extends BaseObject {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String oid;
	/**
	 * action名称
	 */
	private String actionUrl;
	/**
	 * action解释
	 */
	private String actionDesc;
	/**
	 * action执行状态描述
	 */
	private String actionKey;
	/**
	 * 是否启用 0-启用日志记录 1-不启用日志记录
	 */
	private String useFlag;

	public LogActionDesc(String actionUrl, String actionDesc, String actionKey,String useFlag) {
		super();
		this.actionUrl = actionUrl;
		this.actionDesc = actionDesc;
		this.actionKey = actionKey;
		this.useFlag = useFlag;
	}

	public LogActionDesc() {
		super();
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getActionKey() {
		return actionKey;
	}

	public void setActionKey(String actionKey) {
		this.actionKey = actionKey;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}

package pt.xtgl.jcgl.pojo;

import java.util.Date;
import java.util.List;

import util.base.BaseObject;

public class Loginfo extends BaseObject {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String oid;
	/**
	 * 0-无用户 1-平台用户 2-中介机构 3-项目单位
	 */
	private Integer userType;
	/**
	 * 0-代表操作记录，1-代表错误记录
	 */
	private Integer type;
	/**
	 * 日志记录内容
	 */
	private String actText;
	/**
	 * 操作人
	 */
	private String userName;
	/**
	 * 操作人id
	 */
	private String userOid;
	/**
	 * 机构id
	 */
	private String organId;// 业务类型Id
	/**
	 * 操作时间
	 */
	private Date actTime;
	/**
	 * 操作ip
	 */
	private String ip;
	/**
	 * 角色Id
	 */
	private String roleIds;
	/**
	 * 所属区划
	 */
	private String areaId;
	/**
	 * 访问的action url
	 */
	private String actionUrl;
	/**
	 * 角色实体list
	 */
	private List<SysRole> sysRoleList;
	/**
	 * 区划实体
	 */
	private SysArea sysArea;
	/**
	 * 业务类型实体list
	 */
	private List<SysDict> sysDictList;

	public Loginfo() {
		super();
	}

	public Loginfo(Integer userType, Integer type, String actText,
			String userName, String userOid, String organId, Date actTime,
			String ip, String roleIds, String areaId) {
		super();
		this.userType = userType;
		this.type = type;
		this.actText = actText;
		this.userName = userName;
		this.userOid = userOid;
		this.organId = organId;
		this.actTime = actTime;
		this.ip = ip;
		this.roleIds = roleIds;
		this.areaId = areaId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getActText() {
		return actText;
	}

	public void setActText(String actText) {
		this.actText = actText;
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

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public List<SysDict> getSysDictList() {
		return sysDictList;
	}

	public void setSysDictList(List<SysDict> sysDictList) {
		this.sysDictList = sysDictList;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}

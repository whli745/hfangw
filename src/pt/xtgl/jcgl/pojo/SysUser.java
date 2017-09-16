package pt.xtgl.jcgl.pojo;

import java.util.Date;

import util.base.BaseObject;

public class SysUser extends BaseObject {
	private static final long serialVersionUID = 2895027682933632191L;
	private String userId;// 用户编号Id
	private String userCode;// 用户登录账号
	private String userPassword;// 用户密码
	private String userName;// 用户名称
	private String userSex;// 性别
	private Date userBirthday;// 出生年月
	private String userPost;// 职务
	private String userPhone;// 手机号
	private String userTel;// 电话
	private String userEmail;// 邮箱
	private String userSort;// 排序号
	private String areaId;// 所属区划
	private String organId;// 所属机构
	private String delFlag;// 删除状态 默认未删除
	private String userStatus;// 启用状态 默认是启用
	private Long serialId; // 平台序列化ID，系统唯一
	private SysArea sysArea;//区划
	private SysOrgan sysOrgan; //机构
	private SysDict sysDict;//业务类型
	private SysDict sysPost; //职务
	private String roleId;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SysUser() {
		super();
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserPost() {
		return userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserSort() {
		return userSort;
	}

	public void setUserSort(String userSort) {
		this.userSort = userSort;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
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

	public Long getSerialId() {
		return serialId;
	}

	public void setSerialId(Long serialId) {
		this.serialId = serialId;
	}

	public SysOrgan getSysOrgan() {
		return sysOrgan;
	}    

	public void setSysOrgan(SysOrgan sysOrgan) {
		this.sysOrgan = sysOrgan;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public SysDict getSysPost() {
		return sysPost;
	}

	public void setSysPost(SysDict sysPost) {
		this.sysPost = sysPost;
	}

}

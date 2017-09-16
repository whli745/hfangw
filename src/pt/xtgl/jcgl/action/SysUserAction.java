package pt.xtgl.jcgl.action;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.pojo.SysUserRole;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import pt.xtgl.jcgl.service.ISysRoleService;
import pt.xtgl.jcgl.service.ISysUserRoleService;
import pt.xtgl.jcgl.service.ISysUserService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 用户处理
 * 
 * @author wanghw
 * @modify meidj 2013-06-05 新增多级区划支持
 * @modify meidj 2013-06-24 新增个人信息功能（包含密码修改）
 * 
 */
@SuppressWarnings("serial")
public class SysUserAction extends BaseAction {
	private ISysUserService sysUserService;
	private ISysDictService sysDictService;// 数据字典
	private ISysRoleService sysRoleService;// 角色
	private ISysUserRoleService sysUserRoleService;
	private ISysAreaService sysAreaService; // 区划
	private List<SysDict> sysDictList;
	private List<SysDict> serviceTypeList;
	private List<SysRole> sysRoleList;
	private List<SysUserRole> userRoleList;
	private SysUser sysUser;
	private String userId;// 用户编号Id
	private String userName;// 用户名称
	private String userPassword;// 用户密码
	private String userSex;// 性别
	private String userBirthday;// 出生年月
	private String userPost;// 职务
	private String userPhone;// 手机号
	private String userTel;// 电话
	private String userEmail;// 邮箱
	private String userSort;// 排序号
	private String organId;// 所属机构
	private String delFlag;// 删除状态 默认未删除
	private String userStatus;// 启用状态 默认是启用
	private List<SysUser> sysUserList;
	private String areaId;
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private String serviceType;
	private String websiteJson;// 站点json

	// private String websiteIds;// 站点id s

	/**
	 * 修改个人信息（包含密码修改）
	 * 
	 * @author meidj 2013-06-05
	 */
	public String editMyUserInfo() {
		SysUser user = super.getLoginUser();
		try {
			if (null == sysUser) { // 首次进去页面，查询用户详细信息
				sysUser = sysUserService.querySysUser(user.getUserId());
			}
			sysDictList = sysDictService
					.querySysDictList(BaseParameter.DUTYTYPE);
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);// 字典表中平台业务类型
			userRoleList = sysUserRoleService.queryUserRoleList(user
					.getUserId());
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：跳转到修改密码页面
	 * @date 2013-5-7下午04:20:59
	 * @return
	 */
	public String initSysUserPassword() {
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：验证用户的原密码输入是否正确
	 * @date 2013-5-7下午04:42:18
	 * @return
	 */
	public void checSysUserPassword() {
		String pwd = super.getLoginUser() == null ? null : super.getLoginUser()
				.getUserPassword();
		String msg = "1";
		if (!Common.md5(userPassword).equals(pwd)) {
			msg = "0";
		}
		try {
			super.returnAjaxInfo(msg);
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 
	 * @功能：保存修改的密码
	 * @date 2013-5-7下午04:27:30
	 * @return
	 */
	public String editSysUserPassword() {
		SysUser user = super.getLoginUser();
		try {
			if (user != null) {
				user.setUserPassword(Common.md5(userPassword));
				sysUserService.updateSysUser(user);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public String editSysUser() {
		try {
			SysUser aa = sysUserService.onGet(userId);
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			sysDictList = sysDictService
					.querySysDictList(BaseParameter.DUTYTYPE);
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);// 字典表中平台业务类型
			if (userId != null) {
				sysUser = sysUserService.querySysUser(userId);
				userRoleList = sysUserRoleService.queryUserRoleList(userId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 增加用户
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	public String initSysUser() {
		try {
			if (!Common.isNullOrSpace(userId)) {// 修改
				sysUser.setUserId(userId);
				if (sysUser.getUserPassword().length() < 32) {
					sysUser.setUserPassword(Common.md5(sysUser
							.getUserPassword()));
				}
				sysUserService.saveOrUpdateSysUser(sysUser, null);
			} else {// 新增
				sysUser.setUserPassword(Common.md5(sysUser.getUserPassword()));
				sysUser.setDelFlag(BaseParameter.DELETE_NO);
				sysUserService.saveOrUpdateSysUser(sysUser, null);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	public String deleteSysUser() {
		try {
			if (null != userId) {
				sysUserService.deleteSysUser(userId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 更新用户信息
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */

	/**
	 * 查询用户信息
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	public String querySysUser() {
		try {
			if (userId != null) {
				sysUser = sysUserService.querySysUser(userId);
				userRoleList = sysUserRoleService.queryUserRoleList(userId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询用户列表信息
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysUserList() {
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);// 字典表中平台业务类型
			ResultPage rs = sysUserService.querySysUserList(areaId, userName,
					organId, serviceType, getPage(),
					BaseParameter.getPageRowsMiddle());
			setPageParam(rs);
			sysUserList = rs.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询用户列表信息
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysUserListgzt() {
		try {
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);// 字典表中平台业务类型
			ResultPage rs = sysUserService.querySysUserList(areaId, userName,
					organId, serviceType, getPage(),
					BaseParameter.PAGE_ROWS_MIN);
			setPageParam(rs);
			sysUserList = rs.getResultList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 显示用户信息
	 * 
	 * @author wanghw
	 * @date 2013-04-02
	 * @return
	 */
	public String getSysUserInfo() {
		try {
			if (userId != null) {
				sysUser = sysUserService.querySysUser(userId);
				userRoleList = sysUserRoleService.queryUserRoleList(userId);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<SysUser> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		this.sysUserList = sysUserList;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public List<SysDict> getSysDictList() {
		return sysDictList;
	}

	public void setSysDictList(List<SysDict> sysDictList) {
		this.sysDictList = sysDictList;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public ISysUserRoleService getSysUserRoleService() {
		return sysUserRoleService;
	}

	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}

	public List<SysUserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<SysUserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getTopAreaId() {
		return topAreaId;
	}

	public void setTopAreaId(String topAreaId) {
		this.topAreaId = topAreaId;
	}

	public String getSysAreaJson() {
		return sysAreaJson;
	}

	public void setSysAreaJson(String sysAreaJson) {
		this.sysAreaJson = sysAreaJson;
	}

	public List<SysDict> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<SysDict> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getWebsiteJson() {
		return websiteJson;
	}

	public void setWebsiteJson(String websiteJson) {
		this.websiteJson = websiteJson;
	}

}

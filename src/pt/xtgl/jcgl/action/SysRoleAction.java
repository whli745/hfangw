package pt.xtgl.jcgl.action;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import pt.xtgl.jcgl.service.ISysRoleService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 角色管理
 * 
 * @author zhujj
 * @modify meidj 2013-06-04 新增多级区划支持和新增所属机构属性
 * 
 */
public class SysRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISysRoleService sysRoleService;
	private ISysDictService sysDictService;
	private ISysAreaService sysAreaService;
	private List<SysRole> roleList;
	private List<SysDict> serviceTypeList; // 业务类型，数据字典读取
	private List<SysDict> identityTypeList; // 身份类型，数据字典读取
	private SysRole sysRole;
	private String roleName;
	private String optId; // 业务类型
	private String roleId;
	private String moduleJSONStr;
	private String moduleIds;
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象

	/**
	 * 按条件查询角色列表
	 */
	@SuppressWarnings("unchecked")
	public String querySysRoleList() {
		try {
			SysUser user = super.getLoginUser();
			if (null != user && !BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				topAreaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				topAreaId = user.getAreaId();
			}
			if (Common.isNullOrSpace(areaId)) { // 默认查询用户所在区划下的机构
				areaId = topAreaId;
				areaName = user.getSysArea().getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);// 根区划树
			ResultPage rp = sysRoleService.querySysRoleList(roleName, optId,
					areaId, getPage(), getPageRows());
			roleList = rp.getResultList();
			serviceTypeList = sysDictService.querySysDictList(BaseParameter.SERVICETYPE);
			super.setPageParam(rp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 按条件查询角色列表
	 */
	@SuppressWarnings("unchecked")
	public String querySysRoleListgzt() {
		try {
			ResultPage rp = sysRoleService.querySysRoleList(roleName, optId,
					areaId, getPage(), BaseParameter.PAGE_ROWS_MIN);
			roleList = rp.getResultList();
			super.setPageParam(rp);
			serviceTypeList = sysDictService
			.querySysDictList(BaseParameter.SERVICETYPE);
			identityTypeList = sysDictService
			.querySysDictList(BaseParameter.IDENTITYTYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转修改角色页面
	 */
	public String initSysRole() {
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
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId); // 根区划树
			if (!Common.isNullOrSpace(roleId)) {
				sysRole = sysRoleService.getSysRoleByRoleId(roleId);
				moduleJSONStr = sysRoleService.createRoleModuleJSONString(
						 roleId,  "false");
			} else {
				moduleJSONStr = sysRoleService
						.createModuleJSONString();
			}
			serviceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
			identityTypeList = sysDictService
					.querySysDictList(BaseParameter.IDENTITYTYPE);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 保存修改角色
	 */
	public String editSysRole() {
		try {
			sysRoleService.saveOrUpdateSysRole(sysRole, moduleIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除角色
	 */
	public String delSysRole() {
		try {
			sysRoleService.delSysRole(roleId);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 跳转到查看页面
	 */
	public String getSysRoleDetail() {
		try {
			if (!Common.isNullOrSpace(roleId)) {
				sysRole = sysRoleService.getSysRoleByRoleId(roleId);
				moduleJSONStr = sysRoleService.createRoleModuleJSONString(
						 roleId, "true");
				serviceTypeList = sysDictService
						.querySysDictList(BaseParameter.SERVICETYPE);
				identityTypeList = sysDictService
						.querySysDictList(BaseParameter.IDENTITYTYPE);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getModuleJSONStr() {
		return moduleJSONStr;
	}

	public void setModuleJSONStr(String moduleJSONStr) {
		this.moduleJSONStr = moduleJSONStr;
	}

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getOptId() {
		return optId;
	}

	public void setOptId(String optId) {
		this.optId = optId;
	}

	public List<SysDict> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<SysDict> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public List<SysDict> getIdentityTypeList() {
		return identityTypeList;
	}

	public void setIdentityTypeList(List<SysDict> identityTypeList) {
		this.identityTypeList = identityTypeList;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

}

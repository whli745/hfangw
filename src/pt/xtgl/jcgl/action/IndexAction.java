package pt.xtgl.jcgl.action;

import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.pojo.SysUserRole;
import pt.xtgl.jcgl.service.ISysModuleService;
import pt.xtgl.jcgl.service.ISysRoleService;
import pt.xtgl.jcgl.service.ISysUserRoleService;
import util.base.action.BaseAction;

public class IndexAction extends BaseAction {
	/**
	 * 登录成功 返回系统首页类
	 */
	private static final long serialVersionUID = 1L;
	private ISysModuleService sysModuleService;
	private ISysUserRoleService sysUserRoleService;
	private ISysRoleService sysRoleService; 
	private List<SysModule> moduleList;
	private List<SysUserRole> userRoleList;
	private String currentDate;
	private String modulePid;
	private List<List> modules;
	private SysModule sysModule;
	private String loginfo;
	private List<SysRole> roleList;
	private String instantMessagingUrl;// 及时通信url

	/**
	 * 根据系统登录用户查询一级权限菜单列表
	 * 
	 * @author zhujj
	 * @date 2013-04-16
	 */
	public String top() {
		try {
			moduleList = sysModuleService.getTopModulesByUser(super
					.getLoginUser());
			// 平台日期显示
			currentDate = util.DateTools.formatDate(new Date(), "yyyy年MM月dd日")
					+ " " + util.DateTools.getWeekOfDate(new Date());
			// 平台登录用户显示
			loginfo = super.getLoginUser().getUserName();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 根据系统登录用户和一级菜单查询所有子权限菜单列表
	 * 
	 * @author zhujj
	 * @date 2013-04-16
	 */
	public String left() {
		try {
			// 一级菜单，取main区域URL
			sysModule = sysModuleService.getSysModuleByOid(modulePid);
			// 加载系统左侧菜单
			modules = sysModuleService.getLeftModulesByUser(
					super.getLoginUser(), modulePid);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public ISysModuleService getSysModuleService() {
		return sysModuleService;
	}

	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public List<SysModule> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SysModule> moduleList) {
		this.moduleList = moduleList;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getModulePid() {
		return modulePid;
	}

	public void setModulePid(String modulePid) {
		this.modulePid = modulePid;
	}

	public List<List> getModules() {
		return modules;
	}

	public void setModules(List<List> modules) {
		this.modules = modules;
	}

	public SysModule getSysModule() {
		return sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public String getLoginfo() {
		return loginfo;
	}

	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public String getInstantMessagingUrl() {
		return instantMessagingUrl;
	}

	public void setInstantMessagingUrl(String instantMessagingUrl) {
		this.instantMessagingUrl = instantMessagingUrl;
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

}

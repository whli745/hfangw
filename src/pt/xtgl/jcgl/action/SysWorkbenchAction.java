package pt.xtgl.jcgl.action;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysDict;
import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.pojo.SysWorkbench;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysDictService;
import pt.xtgl.jcgl.service.ISysModuleService;
import pt.xtgl.jcgl.service.ISysRoleService;
import pt.xtgl.jcgl.service.ISysWorkbenchService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 工作台管理
 * 
 * @author wanghw
 * @modify meidj 2013-06-07 新增多级区划支持
 * 
 */
@SuppressWarnings("serial")
public class SysWorkbenchAction extends BaseAction {
	private ISysWorkbenchService sysWorkbenchService;
	private ISysModuleService sysModuleService;//
	private ISysDictService sysDictService;// 数据字典service
	private ISysRoleService sysRoleService;// 角色service
	private ISysAreaService sysAreaService;
	private SysWorkbench sysWorkbench;
	private List<SysWorkbench> sysWorkbenchList;
	private String workbenchId;// 工作台编号
	private List<SysDict> workbenchServiceTypeList;// 业务类型list
	private String workbenchServiceType;// 业务类型
	private List<SysRole> workbenchSystemList;// 所属系统
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象
	private List<SysModule> moudleList;

	/**
	 * 增加或者修改用户
	 * 
	 * @author wanghw
	 * @date 2013-04-09
	 * @return
	 */
	public String editSysWorkbench() {
		try {
			sysWorkbenchService.editeSysWorkbench(sysWorkbench);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询工作台列表
	 * 
	 * @author wanghw
	 * @date 2013-04-09
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysWorkbenchList() {
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
			if (null == sysWorkbench) {// 设置区划查询条件
				sysWorkbench = new SysWorkbench();
				sysWorkbench.setAreaId(areaId);
			} else {
				sysWorkbench.setAreaId(areaId);
			}
			ResultPage rp = sysWorkbenchService.querySysWorkbenchList(
					sysWorkbench, BaseParameter.DELETE_NO, getPage(),
					getPageRows());
			setPageParam(rp);
			sysWorkbenchList = rp.getResultList();
			workbenchServiceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	/**
	 * 查询工作台列表
	 * 
	 * @author wanghw
	 * @date 2013-04-09
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String querySysWorkbenchListgzt() {
		try {
			if (null == sysWorkbench) {// 设置区划查询条件
				sysWorkbench = new SysWorkbench();
				sysWorkbench.setAreaId(areaId);
			} else {
				sysWorkbench.setAreaId(areaId);
			}
			ResultPage rp = sysWorkbenchService.querySysWorkbenchList(
					sysWorkbench, BaseParameter.DELETE_NO, getPage(),
					BaseParameter.PAGE_ROWS_MIN);
			setPageParam(rp);
			sysWorkbenchList = rp.getResultList();
			workbenchServiceTypeList = sysDictService
			.querySysDictList(BaseParameter.SERVICETYPE);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除工作台
	 * 
	 * @author wanghw
	 * @date 2013-04-09
	 * @return
	 */
	public String deleteSysWorkbench() {
		try {
			if (workbenchId != null) {
				sysWorkbench = sysWorkbenchService
						.querySysWorkbench(workbenchId);
				sysWorkbenchService.deleteSysWorkbench(sysWorkbench);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @author wanghw
	 * @date 2013-04-09
	 * @return
	 */
	public String initSysWorkbench() {
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
			if (workbenchId != null) {
				sysWorkbench = sysWorkbenchService
						.querySysWorkbench(workbenchId);
			}
			workbenchServiceTypeList = sysDictService
					.querySysDictList(BaseParameter.SERVICETYPE);
			moudleList = sysModuleService.queryFirstSysModuleList();
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public ISysWorkbenchService getSysWorkbenchService() {
		return sysWorkbenchService;
	}

	public void setSysWorkbenchService(ISysWorkbenchService sysWorkbenchService) {
		this.sysWorkbenchService = sysWorkbenchService;
	}

	public SysWorkbench getSysWorkbench() {
		return sysWorkbench;
	}

	public void setSysWorkbench(SysWorkbench sysWorkbench) {
		this.sysWorkbench = sysWorkbench;
	}

	public String getWorkbenchId() {
		return workbenchId;
	}

	public void setWorkbenchId(String workbenchId) {
		this.workbenchId = workbenchId;
	}

	public List<SysWorkbench> getSysWorkbenchList() {
		return sysWorkbenchList;
	}

	public void setSysWorkbenchList(List<SysWorkbench> sysWorkbenchList) {
		this.sysWorkbenchList = sysWorkbenchList;
	}

	public ISysDictService getSysDictService() {
		return sysDictService;
	}

	public void setSysDictService(ISysDictService sysDictService) {
		this.sysDictService = sysDictService;
	}

	public List<SysDict> getWorkbenchServiceTypeList() {
		return workbenchServiceTypeList;
	}

	public void setWorkbenchServiceTypeList(
			List<SysDict> workbenchServiceTypeList) {
		this.workbenchServiceTypeList = workbenchServiceTypeList;
	}

	public String getWorkbenchServiceType() {
		return workbenchServiceType;
	}

	public void setWorkbenchServiceType(String workbenchServiceType) {
		this.workbenchServiceType = workbenchServiceType;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public List<SysRole> getWorkbenchSystemList() {
		return workbenchSystemList;
	}

	public void setWorkbenchSystemList(List<SysRole> workbenchSystemList) {
		this.workbenchSystemList = workbenchSystemList;
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

	public ISysModuleService getSysModuleService() {
		return sysModuleService;
	}

	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}

	public List<SysModule> getMoudleList() {
		return moudleList;
	}

	public void setMoudleList(List<SysModule> moudleList) {
		this.moudleList = moudleList;
	}
	
}

package pt.xtgl.jcgl.action;

import java.util.List;

import net.sf.json.JSONObject;
import pt.xtgl.jcgl.pojo.SysModule;
import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.service.ISysModuleService;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 模块管理
 */
public class SysModuleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ISysModuleService sysModuleService;
	private ISysOrganService sysOrganService;
	private String moduleId;
	private SysModule sysModule;
	private List moduleList;
	private String modulePid;
	private List<SysOrgan> sysOrganList;

	public String initSysModule() {
		try {
			/*
			 * sysOrganList =
			 * sysOrganService.findSysOrganList(util.BaseParameter
			 * .SYSAREAID,null);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ajax添加修改系统菜单
	 * 
	 * @author zhujj
	 * @date 2013-04-07
	 */
	public void editSysModuleAjax() {
		try {
			if (sysModule != null) {
				// sysModule.setModuleName(new
				// String(sysModule.getModuleName().getBytes(),"utf-8"));
				// sysModule.setModuleDesc(new
				// String(sysModule.getModuleDesc().getBytes(),"utf-8"));
				// System.out.println(sysModule.getModuleName());
				// System.out.println(sysModule.getModuleDesc());
				sysModuleService.saveOrUpdateSysModule(sysModule);
				super.returnAjaxInfo(sysModule.getModuleId());// 树节点的id
			}
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax查询系统菜单并转换成JSON对象传输到前台
	 * 
	 * @author zhujj
	 * @date 2013-04-07
	 */
	public void getJSONSysModule() {
		SysModule module = null;
		try {
			module = sysModuleService.getSysModuleByOid(moduleId);
			// 将javaBean转换成JSON对象
			JSONObject jModule = JsonUtil.parse(module);
			super.returnAjaxInfo(jModule.toString());
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * ajax删除菜单
	 * 
	 * @author zhujj
	 * @date 2013-04-09
	 */
	public void delSysModuleAjax() {
		try {
			sysModuleService.delSysModule(moduleId);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 异步加载树
	 */
	public void getModuleTreeAjax() {
		try {
			String JSONStr = sysModuleService.getModuleTreeByPid(modulePid);
			super.returnAjaxInfo(JSONStr);

		} catch (Exception e) {
			error(null, e);
		}
	}

	public ISysModuleService getSysModuleService() {
		return sysModuleService;
	}

	public void setSysModuleService(ISysModuleService sysModuleService) {
		this.sysModuleService = sysModuleService;
	}

	public SysModule getSysModule() {
		return sysModule;
	}

	public void setSysModule(SysModule sysModule) {
		this.sysModule = sysModule;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public List getModuleList() {
		return moduleList;
	}

	public void setModuleList(List moduleList) {
		this.moduleList = moduleList;
	}

	public String getModulePid() {
		return modulePid;
	}

	public void setModulePid(String modulePid) {
		this.modulePid = modulePid;
	}

	public ISysOrganService getSysOrganService() {
		return sysOrganService;
	}

	public void setSysOrganService(ISysOrganService sysOrganService) {
		this.sysOrganService = sysOrganService;
	}

	public List<SysOrgan> getSysOrganList() {
		return sysOrganList;
	}

	public void setSysOrganList(List<SysOrgan> sysOrganList) {
		this.sysOrganList = sysOrganList;
	}

}

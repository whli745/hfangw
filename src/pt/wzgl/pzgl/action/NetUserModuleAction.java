package pt.wzgl.pzgl.action;

import net.sf.json.JSONObject;
import pt.wzgl.pzgl.pojo.NetUserModule;
import pt.wzgl.pzgl.service.INetUserModuleService;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.base.action.BaseAction;
import util.json.JsonUtil;

/**
 * 模块管理
 */
public class NetUserModuleAction extends BaseAction {
	private static final long serialVersionUID = -2578838666816146880L;
	
	private INetUserModuleService netUserModuleService;
	private String moduleId;
	private NetUserModule netUserModule;
	private String parentId;

	public String initNetUserModule() {
		return SUCCESS;
	}

	/**
	 * ajax添加修改系统菜单
	 * 
	 * @author zhujj
	 * @date 2013-04-07
	 */
	public void editNetUserModuleAjax() {
		try {
			if (netUserModule != null) {
				netUserModuleService.saveOrUpdateNetUserModule(netUserModule);
				super.returnAjaxInfo(netUserModule.getModuleId());// 树节点的id
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
	public void getJSONNetUserModule() {
		NetUserModule module = null;
		try {
			module = netUserModuleService.getNetUserModuleByOid(moduleId);
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
	public void delNetUserModuleAjax() {
		try {
			netUserModuleService.delNetUserModule(moduleId);
			super.returnAjaxInfo("success");
		} catch (Exception e) {
			error(null, e);
		}
	}

	/**
	 * 异步加载树
	 */
	public void getNetUserModuleTreeAjax() {
		try {
			String JSONStr = netUserModuleService.getModuleTreeByPid(parentId);
			super.returnAjaxInfo(JSONStr);
		} catch (Exception e) {
			error(null, e);
		}
	}

	public INetUserModuleService getNetUserModuleService() {
		return netUserModuleService;
	}
	public void setNetUserModuleService(INetUserModuleService netUserModuleService) {
		this.netUserModuleService = netUserModuleService;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public NetUserModule getNetUserModule() {
		return netUserModule;
	}
	public void setNetUserModule(NetUserModule netUserModule) {
		this.netUserModule = netUserModule;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}

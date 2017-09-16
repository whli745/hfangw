package pt.xtgl.jcgl.action;

import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import util.BaseParameter;
import util.base.action.BaseAction;

/**
 * 功能：区划管理
 * 
 * @author xuhw
 * @date 2013-04-02
 * @modify：@modify：meidj 2013-05-22（修改成树的方式）
 */
public class SysAreaAction extends BaseAction {
	private static final long serialVersionUID = 1427602865659518408L;

	private ISysAreaService sysAreaService;

	private String sysAreaJson;

	/**
	 * 
	 * 进入区划页面
	 * 
	 * @author:xuhw
	 * @date: 2013-4-14
	 * @return
	 * @modify：meidj 2013-05-22
	 */
	public String initSysArea() {
		String areaId = null;
		try {
			SysUser user = super.getLoginUser();
			if (null != user && BaseParameter.ADMIN.equals(user.getUserId())) { // 超级管理员登录系统
				areaId = sysAreaService.getSysAreaTop().getAreaId();
			} else { // 普通用户或区划管理员登录
				areaId = user.getAreaId();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(areaId);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getSysAreaJson() {
		return sysAreaJson;
	}

	public void setSysAreaJson(String sysAreaJson) {
		this.sysAreaJson = sysAreaJson;
	}

}

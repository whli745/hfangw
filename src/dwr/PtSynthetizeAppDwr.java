package dwr;

import java.util.ArrayList;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.pojo.SysRole;
import pt.xtgl.jcgl.service.ISysOrganService;
import pt.xtgl.jcgl.service.ISysRoleService;
import pt.xtgl.jcgl.service.ISysUserService;
import util.BaseParameter;
import util.Common;
import util.base.action.BaseAction;
import dwr.vo.SysMyUser;
import dwr.vo.SysRoleVo;

/**
 * 其他综合使用DWR处理类，如某些页面使用到DWR来处理数据，有关的DWR方法全部放到该类里面
 * 
 * @author meidj
 * @date 2013-05-31
 * 
 */
@SuppressWarnings("serial")
public class PtSynthetizeAppDwr extends BaseAction {
	private ISysOrganService sysOrganService;
	private ISysRoleService sysRoleService;
	private ISysUserService sysUserService;


	/**
	 * TOP保存修改个人信息
	 * 
	 * @param sysUser
	 *            待修改对象
	 * @return 0 失败 1 成功
	 */
	public String saveMyUserInfo(SysMyUser sysUser) {
		try {
			if (null != sysUser && !"".equals(sysUser.getUserId())) {
				if (sysUser.getUserPassword().length() < 32) {
					sysUser.setUserPassword(Common.md5(sysUser
							.getUserPassword()));
				}
				sysUserService.updateMyUserInfo(sysUser);
			} else {
				return BaseParameter.NO;
			}
		} catch (Exception e) {
			error(null, e);
			return BaseParameter.NO;
		}
		return BaseParameter.YES;
	}

	/**
	 * 根据区划与业务类型查询角色列表
	 * 
	 * @param areaId
	 *            所属区划
	 * @param serviceType
	 *            业务类型
	 * @return List<SysRole>
	 */
	public List<SysRoleVo> queryRoleByAreaId(String areaId, String serviceType) {
		try {
			List<SysRole> roleList = sysRoleService.querySysRoleList(areaId,
					serviceType);
			List<SysRoleVo> roleVoList = new ArrayList<SysRoleVo>();
			for (SysRole role : roleList) {
				SysRoleVo roleVo = new SysRoleVo();
				roleVo.setRoleId(role.getRoleId());
				roleVo.setRoleName(role.getRoleName());
				roleVo.setAreaId(role.getAreaId());
				roleVoList.add(roleVo);
			}
			return roleVoList;
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * 根据区划查询角色列表
	 * 
	 * @param areaId
	 *            所属区划
	 * @return List<SysRole>
	 */
	public List<SysRoleVo> queryRoleByAreaId(String areaId) {
		try {
			List<SysRole> roleList = sysRoleService.querySysRoleList(areaId);
			List<SysRoleVo> roleVoList = new ArrayList<SysRoleVo>();
			for (SysRole role : roleList) {
				SysRoleVo roleVo = new SysRoleVo();
				roleVo.setRoleId(role.getRoleId());
				roleVo.setRoleName(role.getRoleName());
				roleVo.setAreaId(role.getAreaId());
				roleVoList.add(roleVo);
			}
			return roleVoList;
		} catch (Exception e) {
			error(null, e);
		}
		return null;
	}

	/**
	 * 根据区划查询该区划下所有机构列表
	 * 
	 * @param areaId
	 *            区划主键ID
	 * @return List<SysOrgan> 机构列表
	 */
	public List<SysOrgan> queryOrganByAreaId(String areaId, String organId) {
		if (!Common.isNullOrSpace(areaId)) {
			try {
				return sysOrganService.findSysOrganList(areaId, organId);
			} catch (Exception e) {
				error(null, e);
			}
		}
		return null;
	}

	public ISysOrganService getSysOrganService() {
		return sysOrganService;
	}

	public void setSysOrganService(ISysOrganService sysOrganService) {
		this.sysOrganService = sysOrganService;
	}

	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

}

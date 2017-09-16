package pt.xtgl.jcgl.action;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysOrgan;
import pt.xtgl.jcgl.pojo.SysUser;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysOrganService;
import util.BaseParameter;
import util.Common;
import util.ResultPage;
import util.base.action.BaseAction;

/**
 * 机构管理
 * 
 * @author meidj
 * @date 2013-05-22
 * 
 */
@SuppressWarnings( { "serial", "unchecked" })
public class SysOrganAction extends BaseAction {
	private ISysOrganService sysOrganService;
	private ISysAreaService sysAreaService;
	private List<SysOrgan> sysOrganList; // 机构列表
	private SysOrgan sysOrgan; // 机构实体类
	private SysOrgan sysParentOrgan; // 父级机构对象（查看机构详细信息使用）
	private String organId; // 机构主键ID
	private String organName; // 机构名称（查询条件）
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示
	private String topAreaId;// 当前登录用户的所属区划（及顶级区划）
	private String sysAreaJson;// 顶级区划的Json对象

	/**
	 * 根据机构主键ID，删除机构
	 * 
	 * @author meidj
	 * @date 2013-05-31
	 */
	public String delSysOrgan() {
		try {
			sysOrganService.delSysOrgan(organId);
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 根据机构主键ID查询机构对象信息
	 * 
	 * @author meidj
	 * @date 2013-05-31
	 * @return 机构对象(SysOrgan)
	 */
	public String getSysOrganDetail() {
		try {
			sysOrgan = sysOrganService.getSysOrganInfo(organId);
			if (!Common.isNullOrSpace(sysOrgan.getParentId())) {
				sysParentOrgan = sysOrganService.getSysOrganInfo(sysOrgan
						.getParentId());
			}
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 保存或修改机构（机构organId为空，则为新增机构）
	 * 
	 * @author meidj
	 * @date 2013-05-31
	 */
	public String saveOrUpdateOrgan() {
		try {
			//sysOrganService.saveOrUpdateOrgan(sysOrgan);
			sysOrgan.setDelFlag(BaseParameter.NO);
			sysOrganService.save(sysOrgan);
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 进入新增或修改机构页面
	 * 
	 * @author meidj
	 * @date 2013-05-30
	 */
	public String initSysOrgan() {
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
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			if (!Common.isNullOrSpace(organId)) { // 请求为修改机构，查询机构信息
				sysOrgan = sysOrganService.getSysOrganInfo(organId);
			}
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 查询返回机构列表
	 * 
	 * @author meidj
	 * @date 2013-05-22
	 */
	public String querySysOrganList() {
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
			} else {
				areaName = sysAreaService.getSysAreaBytopId(areaId)
						.getAreaName();
			}
			sysAreaJson = sysAreaService.querySysAreaJSONByAreaId(topAreaId);
			ResultPage resultPage = sysOrganService.findSysOrganList(organName,
					areaId, getPage(), getPageRows());
			if (null != resultPage) {
				super.setPageParam(resultPage);
				sysOrganList = resultPage.getResultList();
			}
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
	}
	/**
	 * 查询返回机构列表
	 * 
	 * @author meidj
	 * @date 2013-05-22
	 */
	public String querySysOrganListgzt() {
		try {
			ResultPage resultPage = sysOrganService.findSysOrganList(organName,
					areaId, getPage(), BaseParameter.PAGE_ROWS_MIN);
			if (null != resultPage) {
				super.setPageParam(resultPage);
				sysOrganList = resultPage.getResultList();
			}
		} catch (Exception e) {
			super.error(null, e);
		}
		return SUCCESS;
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

	public SysOrgan getSysOrgan() {
		return sysOrgan;
	}

	public void setSysOrgan(SysOrgan sysOrgan) {
		this.sysOrgan = sysOrgan;
	}

	public ISysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(ISysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public SysOrgan getSysParentOrgan() {
		return sysParentOrgan;
	}

	public void setSysParentOrgan(SysOrgan sysParentOrgan) {
		this.sysParentOrgan = sysParentOrgan;
	}

}

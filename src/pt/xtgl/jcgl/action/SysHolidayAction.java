package pt.xtgl.jcgl.action;

import java.util.List;

import pt.xtgl.jcgl.pojo.SysHoliday;
import pt.xtgl.jcgl.service.ISysAreaService;
import pt.xtgl.jcgl.service.ISysHolidayService;
import util.ResultPage;
import util.base.action.BaseAction;

public class SysHolidayAction extends BaseAction {
	private static final long serialVersionUID = -6642916428386131334L;
	private ISysHolidayService sysHolidayService;
	private ISysAreaService sysAreaService;

	private SysHoliday holiday;
	private String oid;
	private List<SysHoliday> holidayList;
	private String areaId; // 所属区划（查询条件）
	private String areaName; // 区划名称显示

	/**
	 * 查询节假日列表
	 * @return
	 */
	public String querySysHolidayList() {
		try {
			ResultPage rp = sysHolidayService.querySysHoliday(holiday,getPage(),
					getPageRows());
			holidayList = rp.getResultList();
			super.setPageParam(rp);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 *  新增节假日页面
	 * @return
	 */
	public String initSysHoliday(){
		try {
			if (null != oid) {
				holiday = sysHolidayService.querySysHoliday(oid);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	/**
	 *  查看节假日页面
	 * @return
	 */
	public String getSysHoliday(){
		try {
			if (null != oid) {
				holiday = sysHolidayService.querySysHoliday(oid);
			}
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}
	
	/**
	 * 新增节假日
	 * @return
	 */
	public String saveSysHoliday() {
		try {
			sysHolidayService.saveSysHoliday(holiday);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	/**
	 * 删除节假日
	 * @return
	 */
	public String deleteSysHoliday() {
		try {
			sysHolidayService.deleteSysHoliday(oid);
		} catch (Exception e) {
			error(null, e);
		}
		return SUCCESS;
	}

	public ISysHolidayService getSysHolidayService() {
		return sysHolidayService;
	}

	public void setSysHolidayService(ISysHolidayService sysHolidayService) {
		this.sysHolidayService = sysHolidayService;
	}

	public SysHoliday getHoliday() {
		return holiday;
	}

	public void setHoliday(SysHoliday holiday) {
		this.holiday = holiday;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public List<SysHoliday> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<SysHoliday> holidayList) {
		this.holidayList = holidayList;
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
}

package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

/**
 * 功能：区划信息
 * 
 * @author xuhw
 * @date 2013-04-02
 * @modify：
 */
@SuppressWarnings("serial")
public class SysArea extends BaseObject {

	private String areaId;// 区划id
	private String topId;// 顶级区划id
	private String areaCode;// 区划编号
	private String areaName;// 区划名称
	private String areaLevel;// 区划等级、级别、默认2
	private String parentCode;// 父级区划
	private String delFlag;// 删除标志，默认0,0代表未删除，1代表已删除
	private Integer areaSort;// 排序号
	private String areaPath;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getTopId() {
		return topId;
	}

	public void setTopId(String topId) {
		this.topId = topId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}


	public String getAreaPath() {
		return areaPath;
	}

	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}

	public Integer getAreaSort() {
		return areaSort;
	}

	public void setAreaSort(Integer areaSort) {
		this.areaSort = areaSort;
	}

}

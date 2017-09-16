package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

/**
 * 机构类
 * 
 * @author meidj
 * @date 2013-05-21
 * 
 */
public class SysOrgan extends BaseObject {
	private static final long serialVersionUID = 1L;
	private String organId;// 主键ID
	private String parentId; // 父级机构
	private String organCode;// 机构代码
	private String organName;// 机构名称
	private String organFullName;// 机构全称
	private String organSimpCode;// 机构简码
	private Long organSort;// 机构排序号
	private String organAddr;// 机构地址
	private String organTel;// 机构电话
	private String areaId;// 所属区划
	private String organFlag;// 是否启用，默认启用 统一调用
	private String delFlag;// 删除状态，默认未删除 统一调用
	private String organPath;
	private SysArea sysArea;

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganFullName() {
		return organFullName;
	}

	public void setOrganFullName(String organFullName) {
		this.organFullName = organFullName;
	}

	public String getOrganSimpCode() {
		return organSimpCode;
	}

	public void setOrganSimpCode(String organSimpCode) {
		this.organSimpCode = organSimpCode;
	}

	public Long getOrganSort() {
		return organSort;
	}

	public void setOrganSort(Long organSort) {
		this.organSort = organSort;
	}

	public String getOrganAddr() {
		return organAddr;
	}

	public void setOrganAddr(String organAddr) {
		this.organAddr = organAddr;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getOrganFlag() {
		return organFlag;
	}

	public void setOrganFlag(String organFlag) {
		this.organFlag = organFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public SysArea getSysArea() {
		return sysArea;
	}

	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOrganPath() {
		return organPath;
	}

	public void setOrganPath(String organPath) {
		this.organPath = organPath;
	}

	public String getOrganTel() {
		return organTel;
	}

	public void setOrganTel(String organTel) {
		this.organTel = organTel;
	}

}

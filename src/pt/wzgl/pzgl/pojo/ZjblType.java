package pt.wzgl.pzgl.pojo;

import java.util.Date;

import util.Common;
import util.base.BaseObject;

/**
 * @证件办理分类信息
 * @TABLE_NAME ：T_ZJBL_TYPE
 * 
 * @author : yaosw
 * @date : 2016-02-14
 */
public class ZjblType extends BaseObject {
	private static final long serialVersionUID = 1L;
	
	private String typeId;
	private String parentId;
	private String typePath;//分类ID全路径
	private String typeName;//分类名称
	private String typeCode;//分类编码（唯一）
	private String typeSort;//分类排序
	private String typeMemo;//分类描述
	private String typeLogo;//分类图标
	private String typeUrl;//分类链接
	private String delFlag;//删除标识： 1-删除，0-未删除
	private String usingFlag;//禁用标识： 1-删除，0-未删除
	private Date updateTime;//更新时间
	private Date createTime;//创建时间，系统只插入、不更新

	public void setTypeId(String typeId) {
		if(Common.isNullOrSpace(typeId)) {
			this.typeId = null;
		} else {
			this.typeId = typeId;
		}
	}
	public String getTypeId() {
		return typeId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeMemo() {
		return typeMemo;
	}
	public void setTypeMemo(String typeMemo) {
		this.typeMemo = typeMemo;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getUsingFlag() {
		return usingFlag;
	}
	public void setUsingFlag(String usingFlag) {
		this.usingFlag = usingFlag;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTypePath() {
		return typePath;
	}
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}
	public String getTypeSort() {
		return typeSort;
	}
	public void setTypeSort(String typeSort) {
		this.typeSort = typeSort;
	}
	public String getTypeLogo() {
		return typeLogo;
	}
	public void setTypeLogo(String typeLogo) {
		this.typeLogo = typeLogo;
	}
	public String getTypeUrl() {
		return typeUrl;
	}
	public void setTypeUrl(String typeUrl) {
		this.typeUrl = typeUrl;
	}
	
}

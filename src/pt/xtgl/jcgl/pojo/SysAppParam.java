package pt.xtgl.jcgl.pojo;

import java.util.Date;

import util.base.BaseObject;
/**
 * 系统参数实体
 * @author Administrator
 *
 */
public class SysAppParam extends BaseObject{
	private static final long serialVersionUID = 439200195396861805L;
	
	private String paramId;//主键
	private String paramCode;//参数编码
	private String paramVal;//参数值
	private String paramName;//参数名
	private String paramDesc;//描述
	private String paramPath;//递归路径
	private String paramSort;//排序
	private String parentId;//父级id
	private String delFlag;//删除标志（0:未删除 1：删除）
	private String usingFlag;//禁用标识（0:启用 1：禁用）
	private Date updateTime;//更新时间
	private Date createTime;//创建时间，系统只插入、不更新
	
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamVal() {
		return paramVal;
	}
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	public String getParamPath() {
		return paramPath;
	}
	public void setParamPath(String paramPath) {
		this.paramPath = paramPath;
	}
	
	public String getParamSort() {
		return paramSort;
	}
	public void setParamSort(String paramSort) {
		this.paramSort = paramSort;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	
}

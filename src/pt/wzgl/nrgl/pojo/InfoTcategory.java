package pt.wzgl.nrgl.pojo;

import java.util.Date;

import util.base.BaseObject;
/**
 * 栏目管理实体
 * @author Administrator
 *
 */
public class InfoTcategory extends BaseObject{

	private static final long serialVersionUID = 1L;

	private String columnId;// 主键
	private String parentId;// 父级id
	private Integer rank;// 级别
	private String columnName;// 栏目名称
	private String columnDescribe;// 栏目描述
	private Integer sort;// 排序
	private String createBy;// 创建人
	private Date createDate;// 创建时间
	private String delFlag;// 是否删除 0-删除 1-未删除
	private String usingFlag;// 是否启用 0-未启用 1-启用
	private String columnPath;// 栏目id路径
	private String loginFlag;// 是否登录  0-登录 1-不登录
	private String columnUrl;//栏目地址
	private String isMapService;//是否是地图服务
	
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnDescribe() {
		return columnDescribe;
	}
	public void setColumnDescribe(String columnDescribe) {
		this.columnDescribe = columnDescribe;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getColumnPath() {
		return columnPath;
	}
	public void setColumnPath(String columnPath) {
		this.columnPath = columnPath;
	}
	public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public String getColumnUrl() {
		return columnUrl;
	}
	public void setColumnUrl(String columnUrl) {
		this.columnUrl = columnUrl;
	}
	public String getIsMapService() {
		return isMapService;
	}
	public void setIsMapService(String isMapService) {
		this.isMapService = isMapService;
	}
}

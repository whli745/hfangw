package pt.xtgl.jcgl.pojo;

import util.base.BaseObject;

/**
 * @地区
 * @TABLE_NAME ：T_SYS_LOCAL
 * 
 * @author : dusd
 * @date : 2015-11-13
 */
public class SysLocal extends BaseObject {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @COLUMN_EXPLAIN : 主键
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_ID
	 */
	private java.lang.String localId;
	/**
	 * @COLUMN_EXPLAIN : 父ID
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : TOP_ID
	 */
	private java.lang.String topId;
	/**
	 * @COLUMN_EXPLAIN : 地区代码
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_CODE
	 */
	private java.lang.String localCode;
	/**
	 * @COLUMN_EXPLAIN : 地区名字
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_NAME
	 */
	private java.lang.String localName;
	/**
	 * @COLUMN_EXPLAIN : 父代码
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : TOP_CODE
	 */
	private java.lang.String topCode;
	/**
	 * @COLUMN_EXPLAIN : 地区等级
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_LEVEL
	 */
	private java.lang.String localLevel;
	/**
	 * @COLUMN_EXPLAIN : 是否删除(0-未删除 1-已删除)
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : DEL_FLAG
	 */
	private java.lang.String delFlag;
	/**
	 * @COLUMN_EXPLAIN : 排序号
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_SORT
	 */
	private java.lang.String localSort;
	/**
	 * @COLUMN_EXPLAIN : 关系路径
	 * @TABLE_COLUMN_TYPE : varchar
	 * @TABLE_COLUMN_NAME : LOCAL_PATH
	 */
	private java.lang.String localPath;

	/**
	 * @param localId the localId to set
	 */
	public void setLocalId(java.lang.String localId) {
		if(util.Common.isNullOrSpace(localId)) {
			this.localId = null;
		} else {
			this.localId = localId;
		}
	}
	/**
	 * @return the localId
	 */
	public java.lang.String getLocalId() {
		return localId;
	}
	/**
	 * @param topId the topId to set
	 */
	public void setTopId(java.lang.String topId) {
		this.topId = topId;
	}
	/**
	 * @return the topId
	 */
	public java.lang.String getTopId() {
		return topId;
	}
	/**
	 * @param localCode the localCode to set
	 */
	public void setLocalCode(java.lang.String localCode) {
		this.localCode = localCode;
	}
	/**
	 * @return the localCode
	 */
	public java.lang.String getLocalCode() {
		return localCode;
	}
	/**
	 * @param localName the localName to set
	 */
	public void setLocalName(java.lang.String localName) {
		this.localName = localName;
	}
	/**
	 * @return the localName
	 */
	public java.lang.String getLocalName() {
		return localName;
	}
	/**
	 * @param topCode the topCode to set
	 */
	public void setTopCode(java.lang.String topCode) {
		this.topCode = topCode;
	}
	/**
	 * @return the topCode
	 */
	public java.lang.String getTopCode() {
		return topCode;
	}
	/**
	 * @param localLevel the localLevel to set
	 */
	public void setLocalLevel(java.lang.String localLevel) {
		this.localLevel = localLevel;
	}
	/**
	 * @return the localLevel
	 */
	public java.lang.String getLocalLevel() {
		return localLevel;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(java.lang.String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * @return the delFlag
	 */
	public java.lang.String getDelFlag() {
		return delFlag;
	}
	/**
	 * @param localSort the localSort to set
	 */
	public void setLocalSort(java.lang.String localSort) {
		this.localSort = localSort;
	}
	/**
	 * @return the localSort
	 */
	public java.lang.String getLocalSort() {
		return localSort;
	}
	/**
	 * @param localPath the localPath to set
	 */
	public void setLocalPath(java.lang.String localPath) {
		this.localPath = localPath;
	}
	/**
	 * @return the localPath
	 */
	public java.lang.String getLocalPath() {
		return localPath;
	}
	
}

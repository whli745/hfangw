package pt.wzgl.nrgl.pojo;

import java.util.Date;

import util.base.BaseObject;

/**
 * 上传附件实体
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class FileAtta extends BaseObject {
	// primary key
	private java.lang.String attaId;	// 主键
	// fields
	private java.lang.String attaName;	// 附件名称
	private java.lang.String attaPath;	// 附件路径
	private java.lang.String attaItemPath;	// 附件项目路径
	private java.lang.String attaType;	// 附件类型
	private java.lang.Integer attaSize;	// 附件大小
	private java.util.Date uploadTime;	// 上传时间
	private java.lang.String delFlag;	// 是否删除
	private java.lang.String attaDesc;	// 附件描述
	

	public FileAtta() {
		super();
	}

	public FileAtta(String attaId, String attaName, String attaPath,
			String attaType, Integer attaSize, Date uploadTime, String delFlag,
			String attaDesc) {
		super();
		this.attaId = attaId;
		this.attaName = attaName;
		this.attaPath = attaPath;
		this.attaType = attaType;
		this.attaSize = attaSize;
		this.uploadTime = uploadTime;
		this.delFlag = delFlag;
		this.attaDesc = attaDesc;
	}
	

	public java.lang.String getAttaId() {
		return attaId;
	}

	public void setAttaId(java.lang.String attaId) {
		this.attaId = attaId;
	}

	public java.lang.String getAttaName() {
		return attaName;
	}

	public void setAttaName(java.lang.String attaName) {
		this.attaName = attaName;
	}

	public java.lang.String getAttaPath() {
		return attaPath;
	}

	public void setAttaPath(java.lang.String attaPath) {
		this.attaPath = attaPath;
	}

	public java.lang.String getAttaType() {
		return attaType;
	}

	public void setAttaType(java.lang.String attaType) {
		this.attaType = attaType;
	}

	public java.lang.Integer getAttaSize() {
		return attaSize;
	}

	public void setAttaSize(java.lang.Integer attaSize) {
		this.attaSize = attaSize;
	}

	public java.util.Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public java.lang.String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(java.lang.String delFlag) {
		this.delFlag = delFlag;
	}

	public java.lang.String getAttaDesc() {
		return attaDesc;
	}

	public void setAttaDesc(java.lang.String attaDesc) {
		this.attaDesc = attaDesc;
	}

	/**
	 * 获取attaItemPath  
	 * @return attaItemPath attaItemPath  
	 */
	public java.lang.String getAttaItemPath() {
		return attaItemPath;
	}

	/**
	 * 设置attaItemPath
	 * @param attaItemPath attaItemPath  
	 */
	public void setAttaItemPath(java.lang.String attaItemPath) {
		this.attaItemPath = attaItemPath;
	}
}
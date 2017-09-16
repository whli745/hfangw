package pt.xtgl.jcgl.pojo;

import java.util.Date;

import util.base.BaseObject;

@SuppressWarnings("serial")
public class SysNotice extends BaseObject {

	private String noticeId;// 公告编号Id
	private String noticeTitle;// 公告标题
	private String noticeMemo;// 公告内容
	private String noticeAtta;// 公告附件
	private String noticeDateType;// 公告时间类型 1指有时间限制 2指无时间限制 默认为1
	private Date noticeEndDate;// 公告失效日期
	private String noticeType;// 公告类型 内外网
	private String delFlag;// 删除状态 默认未删除
	private String userId;// 发布公告人的用户Id
	private Date insertDate;// 发布公告的时间
	private String ywType;// 业务类型
	private String areaId; // 所属区划
	private SysArea sysArea;// 区划
	private SysDict serviceType;// 业务类型

	public SysNotice() {
		super();
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeMemo() {
		return noticeMemo;
	}

	public void setNoticeMemo(String noticeMemo) {
		this.noticeMemo = noticeMemo;
	}

	public String getNoticeAtta() {
		return noticeAtta;
	}

	public void setNoticeAtta(String noticeAtta) {
		this.noticeAtta = noticeAtta;
	}

	public String getNoticeDateType() {
		return noticeDateType;
	}

	public void setNoticeDateType(String noticeDateType) {
		this.noticeDateType = noticeDateType;
	}

	public Date getNoticeEndDate() {
		return noticeEndDate;
	}

	public void setNoticeEndDate(Date noticeEndDate) {
		this.noticeEndDate = noticeEndDate;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getYwType() {
		return ywType;
	}

	public void setYwType(String ywType) {
		this.ywType = ywType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public SysArea getSysArea() {
		return sysArea;
	}

	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}

	public SysDict getServiceType() {
		return serviceType;
	}

	public void setServiceType(SysDict serviceType) {
		this.serviceType = serviceType;
	}

}

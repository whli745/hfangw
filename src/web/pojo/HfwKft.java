package web.pojo;

import java.util.Date;

import util.base.BaseObject;

public class HfwKft extends BaseObject {
	private static final long serialVersionUID = -5393299439233856292L;
	private String oid;
	private Date bm_end_time;//报名截止时间
	private String kf_time;//看房时间
	private String jhdd;//集合地点
	private String housing_proj_id;
	
	private HousingProject housingProject;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getJhdd() {
		return jhdd;
	}
	public void setJhdd(String jhdd) {
		this.jhdd = jhdd;
	}
	public String getHousing_proj_id() {
		return housing_proj_id;
	}
	public void setHousing_proj_id(String housing_proj_id) {
		this.housing_proj_id = housing_proj_id;
	}
	public Date getBm_end_time() {
		return bm_end_time;
	}
	public void setBm_end_time(Date bm_end_time) {
		this.bm_end_time = bm_end_time;
	}
	public String getKf_time() {
		return kf_time;
	}
	public void setKf_time(String kf_time) {
		this.kf_time = kf_time;
	}
	public HousingProject getHousingProject() {
		return housingProject;
	}
	public void setHousingProject(HousingProject housingProject) {
		this.housingProject = housingProject;
	}
	
	
}

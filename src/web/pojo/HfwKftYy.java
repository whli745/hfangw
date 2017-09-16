package web.pojo;

import java.util.Date;

import pt.xtgl.jcgl.pojo.SysArea;

import util.base.BaseObject;

public class HfwKftYy extends BaseObject {
	private static final long serialVersionUID = -4308682627706266400L;
	private String oid;
	private String lxxm;//联系人姓名
	private String phone;//联系电话
	private String kft_id;//看房id
	private Date yysj;
	private String kf_date;//看房时间
	private String budget;//预算
	private String proj_name;//项目
	private String address;//地址
	private String pickup_flag;//是否班车接
	private String handle_flag;//是否已处理
	
	private Date q_s_date;//开始时间
	private Date q_e_date;//结束时间
	private Integer yy_type;//预约类型 
	
	private String area_id;
	
	private HfwKft hfwKft;
	private SysArea sysArea;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getLxxm() {
		return lxxm;
	}
	public void setLxxm(String lxxm) {
		this.lxxm = lxxm;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getKft_id() {
		return kft_id;
	}
	public void setKft_id(String kft_id) {
		this.kft_id = kft_id;
	}
	public Date getYysj() {
		return yysj;
	}
	public void setYysj(Date yysj) {
		this.yysj = yysj;
	}
	public String getKf_date() {
		return kf_date;
	}
	public void setKf_date(String kf_date) {
		this.kf_date = kf_date;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPickup_flag() {
		return pickup_flag;
	}
	public void setPickup_flag(String pickup_flag) {
		this.pickup_flag = pickup_flag;
	}
	public HfwKft getHfwKft() {
		return hfwKft;
	}
	public void setHfwKft(HfwKft hfwKft) {
		this.hfwKft = hfwKft;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public SysArea getSysArea() {
		return sysArea;
	}
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}
	public Date getQ_s_date() {
		return q_s_date;
	}
	public void setQ_s_date(Date q_s_date) {
		this.q_s_date = q_s_date;
	}
	public Date getQ_e_date() {
		return q_e_date;
	}
	public void setQ_e_date(Date q_e_date) {
		this.q_e_date = q_e_date;
	}
	public String getHandle_flag() {
		return handle_flag;
	}
	public void setHandle_flag(String handle_flag) {
		this.handle_flag = handle_flag;
	}
	public Integer getYy_type() {
		return yy_type;
	}
	public void setYy_type(Integer yy_type) {
		this.yy_type = yy_type;
	}
	
	 
}

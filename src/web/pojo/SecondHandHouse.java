package web.pojo;

import java.util.Date;

import pt.xtgl.jcgl.pojo.SysArea;
import util.base.BaseObject;

public class SecondHandHouse extends BaseObject{ 
	private static final long serialVersionUID = -6084006418776785922L;
	private String oid;//主键
	private String area_id;//所属区划
	private String house_name;//房源名称
	private String acreage_dict_id;//面积
	private String hous_type_dict_id;//房型
	private String building_age_dict_id;//建造年代
	private String floor_dict_id;//楼层
	private String building_type_dict_id;//房屋类型
	private String selling_price_dict_id;//售价
	private String selling_price;//售价
	private String refe_down_payment;//参考首付
	private String refe_month_for;//参考月供 //房源编号
	private String unit_price;//单价
	private String hous_type;//房型
	private String acreage;//面积
	private String orientations;//朝向
	private String floor;//楼层
	private String deco_standard;//装修
	private String address;//位置
	private String village;//所在小区
	private String lng_lat;//经纬度
	private String building_age;//建造年代
	private String pics_1;//室内图
	private String pics_2;//效果图
	private String labels;//标签
	private String detail_info;//房源详细信息
	private Date latest_edit_date;//最后编辑时间
	private String del_flag;//是否删除
	
	private SysArea sysArea;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getAcreage_dict_id() {
		return acreage_dict_id;
	}
	public void setAcreage_dict_id(String acreage_dict_id) {
		this.acreage_dict_id = acreage_dict_id;
	}
	public String getHous_type_dict_id() {
		return hous_type_dict_id;
	}
	public void setHous_type_dict_id(String hous_type_dict_id) {
		this.hous_type_dict_id = hous_type_dict_id;
	}
	public String getBuilding_age_dict_id() {
		return building_age_dict_id;
	}
	public void setBuilding_age_dict_id(String building_age_dict_id) {
		this.building_age_dict_id = building_age_dict_id;
	}
	public String getFloor_dict_id() {
		return floor_dict_id;
	}
	public void setFloor_dict_id(String floor_dict_id) {
		this.floor_dict_id = floor_dict_id;
	}
	public String getBuilding_type_dict_id() {
		return building_type_dict_id;
	}
	public void setBuilding_type_dict_id(String building_type_dict_id) {
		this.building_type_dict_id = building_type_dict_id;
	}
	public String getSelling_price_dict_id() {
		return selling_price_dict_id;
	}
	public void setSelling_price_dict_id(String selling_price_dict_id) {
		this.selling_price_dict_id = selling_price_dict_id;
	}
	public String getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(String selling_price) {
		this.selling_price = selling_price;
	}
	public String getRefe_down_payment() {
		return refe_down_payment;
	}
	public void setRefe_down_payment(String refe_down_payment) {
		this.refe_down_payment = refe_down_payment;
	}
	public String getRefe_month_for() {
		return refe_month_for;
	}
	public void setRefe_month_for(String refe_month_for) {
		this.refe_month_for = refe_month_for;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getHous_type() {
		return hous_type;
	}
	public void setHous_type(String hous_type) {
		this.hous_type = hous_type;
	}
	public String getAcreage() {
		return acreage;
	}
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	public String getOrientations() {
		return orientations;
	}
	public void setOrientations(String orientations) {
		this.orientations = orientations;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDeco_standard() {
		return deco_standard;
	}
	public void setDeco_standard(String deco_standard) {
		this.deco_standard = deco_standard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getLng_lat() {
		return lng_lat;
	}
	public void setLng_lat(String lng_lat) {
		this.lng_lat = lng_lat;
	}
	public String getBuilding_age() {
		return building_age;
	}
	public void setBuilding_age(String building_age) {
		this.building_age = building_age;
	}
	public String getPics_1() {
		return pics_1;
	}
	public void setPics_1(String pics_1) {
		this.pics_1 = pics_1;
	}
	public String getPics_2() {
		return pics_2;
	}
	public void setPics_2(String pics_2) {
		this.pics_2 = pics_2;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(String detail_info) {
		this.detail_info = detail_info;
	}
	public Date getLatest_edit_date() {
		return latest_edit_date;
	}
	public void setLatest_edit_date(Date latest_edit_date) {
		this.latest_edit_date = latest_edit_date;
	}
	public SysArea getSysArea() {
		return sysArea;
	}
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}
	public String getHouse_name() {
		return house_name;
	}
	public void setHouse_name(String house_name) {
		this.house_name = house_name;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
 
}

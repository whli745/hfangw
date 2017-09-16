package web.pojo;

import java.util.Date;
import java.util.List;

import pt.xtgl.jcgl.pojo.SysArea;
import pt.xtgl.jcgl.pojo.SysDict;
import util.base.BaseObject;

public class HousingProject extends BaseObject{
	private static final long serialVersionUID = -4984323465650233152L;
	private String oid;
	private String area_id;//所属区划
	private String address;//详细地
	private String sale_hous_status;//售楼状态
	private String hous_type;//楼盘户型
	private String building_type;//建筑类型
	private String hous_acreage;//面积
	private String launch_time;//交房时间
	private String sales_line;//售楼专线
	private String discount;//优惠折扣
	private String latest_opening;//最新开盘
	private String deco_standard;//装修标准
	private String period_right;//产权年限
	private String search_unit_price;//搜索单价
	private String proj_type;//楼盘类型：1-最新楼盘，2-团购楼盘
	private String hot_sell_label;//热卖标签
	private String proj_name;//楼盘名称
	private String pics;//图片
	private String pics_desc;//图片描述
	private Date lrDate;//录入时间
	private String del_flag;//是否删除
	private Integer attention;//关注度
	private String lnglat;//经纬度
	
	private SysArea sysArea;
	private HfwKft hfwKft;
	private SysDict shs_dict;
	
	private List<HousingProjectPrice> priceList;
	private List<RedPacket> packetList;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHous_type() {
		return hous_type;
	}
	public void setHous_type(String hous_type) {
		this.hous_type = hous_type;
	}
	public String getBuilding_type() {
		return building_type;
	}
	public void setBuilding_type(String building_type) {
		this.building_type = building_type;
	}
	public String getHous_acreage() {
		return hous_acreage;
	}
	public void setHous_acreage(String hous_acreage) {
		this.hous_acreage = hous_acreage;
	}
	public String getLaunch_time() {
		return launch_time;
	}
	public void setLaunch_time(String launch_time) {
		this.launch_time = launch_time;
	}
	public String getSales_line() {
		return sales_line;
	}
	public void setSales_line(String sales_line) {
		this.sales_line = sales_line;
	}
	public String getUnit_price() {
		if(priceList != null&&priceList.size() > 0){
			HousingProjectPrice p = priceList.get(0);
			return p.getVal_1() + " " + p.getVal_3() + " " + p.getVal_4();
		}
		return "";
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getLatest_opening() {
		return latest_opening;
	}
	public void setLatest_opening(String latest_opening) {
		this.latest_opening = latest_opening;
	}
	public String getDeco_standard() {
		return deco_standard;
	}
	public void setDeco_standard(String deco_standard) {
		this.deco_standard = deco_standard;
	}
	public String getPeriod_right() {
		return period_right;
	}
	public void setPeriod_right(String period_right) {
		this.period_right = period_right;
	}
	public String getSearch_unit_price() {
		return search_unit_price;
	}
	public void setSearch_unit_price(String search_unit_price) {
		this.search_unit_price = search_unit_price;
	}
	public String getProj_type() {
		return proj_type;
	}
	public void setProj_type(String proj_type) {
		this.proj_type = proj_type;
	}
	public String getHot_sell_label() {
		return hot_sell_label;
	}
	public void setHot_sell_label(String hot_sell_label) {
		this.hot_sell_label = hot_sell_label;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Date getLrDate() {
		return lrDate;
	}
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}
	public SysArea getSysArea() {
		return sysArea;
	}
	public void setSysArea(SysArea sysArea) {
		this.sysArea = sysArea;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public HfwKft getHfwKft() {
		return hfwKft;
	}
	public void setHfwKft(HfwKft hfwKft) {
		this.hfwKft = hfwKft;
	}
	public List<HousingProjectPrice> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<HousingProjectPrice> priceList) {
		this.priceList = priceList;
	}
	public Integer getAttention() {
		return attention;
	}
	public void setAttention(Integer attention) {
		this.attention = attention;
	}
	public String getPics_desc() {
		return pics_desc;
	}
	public void setPics_desc(String pics_desc) {
		this.pics_desc = pics_desc;
	}
	public String getLnglat() {
		return lnglat;
	}
	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}
	public String getSale_hous_status() {
		return sale_hous_status;
	}
	public void setSale_hous_status(String sale_hous_status) {
		this.sale_hous_status = sale_hous_status;
	}
	public SysDict getShs_dict() {
		return shs_dict;
	}
	public void setShs_dict(SysDict shs_dict) {
		this.shs_dict = shs_dict;
	}

	public List<RedPacket> getPacketList() {
		return packetList;
	}

	public void setPacketList(List<RedPacket> packetList) {
		this.packetList = packetList;
	}
}

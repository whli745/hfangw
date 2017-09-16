package web.pojo;

import util.base.BaseObject;

public class HousingProjectPrice extends BaseObject{
	private static final long serialVersionUID = -4984323465650233152L;
	private String oid;
	private String val_1;//住宅
	private String val_2;//均价
	private String val_3;//19000
	private String val_4;//元/m2
	private Integer val_5;//排序
	private String housing_proj_id;
	
	private HousingProject housingProject;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getVal_1() {
		return val_1;
	}
	public void setVal_1(String val_1) {
		this.val_1 = val_1;
	}
	public String getVal_2() {
		return val_2;
	}
	public void setVal_2(String val_2) {
		this.val_2 = val_2;
	}
	public String getVal_3() {
		return val_3;
	}
	public void setVal_3(String val_3) {
		this.val_3 = val_3;
	}
	public String getVal_4() {
		return val_4;
	}
	public void setVal_4(String val_4) {
		this.val_4 = val_4;
	}
	public String getHousing_proj_id() {
		return housing_proj_id;
	}
	public void setHousing_proj_id(String housing_proj_id) {
		this.housing_proj_id = housing_proj_id;
	}
	public Integer getVal_5() {
		return val_5;
	}
	public void setVal_5(Integer val_5) {
		this.val_5 = val_5;
	}
	public HousingProject getHousingProject() {
		return housingProject;
	}
	public void setHousingProject(HousingProject housingProject) {
		this.housingProject = housingProject;
	}
	
	
}

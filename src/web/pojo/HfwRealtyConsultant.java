package web.pojo;

import util.base.BaseObject;

public class HfwRealtyConsultant extends BaseObject {

	private static final long serialVersionUID = 6534489659073243812L;
	private String oid;
	private String cons_name;
	private String motto;
	private String pic;
	private int sort;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getCons_name() {
		return cons_name;
	}
	public void setCons_name(String cons_name) {
		this.cons_name = cons_name;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}

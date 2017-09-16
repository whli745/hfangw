package pt.xtgl.jcgl.pojo;

import java.util.Date;

import util.base.BaseObject;

public class SysHoliday extends BaseObject {
	private static final long serialVersionUID = -4147112929327122653L;
	private String oid;
	private Date holidayDate;
	private String holidayType;
	private String holidayMemo;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayType() {
		return holidayType;
	}

	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}

	public String getHolidayMemo() {
		return holidayMemo;
	}

	public void setHolidayMemo(String holidayMemo) {
		this.holidayMemo = holidayMemo;
	}
}

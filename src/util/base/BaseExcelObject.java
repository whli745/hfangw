package util.base;

import java.util.Date;

public class BaseExcelObject extends BaseObject {

	private String createBy;// 导入单位
	private Date createDate;// 导入时间
	private String creditCode;// 机构信用代码
	private String companyName;// 企业名称
	private String dataYear;// 数据年份
	private String dataMonth;// 数据月份

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDataYear() {
		return dataYear;
	}

	public void setDataYear(String dataYear) {
		this.dataYear = dataYear;
	}

	public String getDataMonth() {
		return dataMonth;
	}

	public void setDataMonth(String dataMonth) {
		this.dataMonth = dataMonth;
	}

}

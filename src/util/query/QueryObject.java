package util.query;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.Common;

public class QueryObject {
	/**
	 * D=日期，DB=double,N=Integer,S=字符串
	 */
	private String columnType;
	private String columnName;
	/**
	 * LT，GT，EQ，LE，GE,LK<br/>
	 * 要别代表<,>,=,<=,>=,like的条件查询
	 */
	private String columnCalc;
	private String columnValue;
	
	// 存放字段的信息
	private List<FieldObject> fieldObjectList = new ArrayList<FieldObject>();
	// 用于 hql 语句的参数值
	private List<Object> paramValueList = new ArrayList<Object>();
	// 用于在页面显示
	private Map<String, String> item = new HashMap<String, String>();

	public QueryObject() {}
	
	public QueryObject(String paramName) {
		String[] paramNames = paramName.split("[_]");
		if (paramNames[0].toUpperCase().equals("Q")) {
			this.columnName = paramNames[1];
			this.columnType = paramNames[2];
			this.columnCalc = paramNames[3];
		}
	}
	/**
	 * 功能: 将 request 中的参数封装到 QueryObject 对象的 fieldObjectList 中
	 * 
	 * @author zhangCM
	 * @param request
	 */
	public QueryObject(HttpServletRequest request) {
		Enumeration<String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			String paramName = en.nextElement();

			String paramValue = request.getParameter(paramName);
			if (!Common.isNullOrSpace(paramValue)) {
				paramValue = paramValue.trim();
				item.put(paramName, paramValue);

				if (paramName.startsWith("Q_")) {
					addField(paramName, paramValue);
				}
			}
		}
	}
	
	 /**
     * 功能：添加查询条件
     * 
     * @author zhangCM
     * 
     * @param paramName 查询参数名称
     * <p>查询参数的名称格式必须为: Q_firstName_S_EQ <br/>
     * 代表：Q_参数名称_参数类型_参数操作符
     * 
     * <p>参数类型： <br/>
     * D=日期, S=字符串, DB=Double, F=Float, N=Integer, SH=Short, L=Long <br/>
     * DL=日期类型，并且把其时分秒设置为0 <br/>
     * DG=日期类型，并且把其时分秒设置为最大，即为23:59:59 <br/>
     * 
     * <p>参数操作符：
     * EQ, NEQ, LT, GT, LE, GE, LK, NULL, NOTNULL, EMP, NOTEMP <br/>
     * 分别代表：=, <>, <, >, <=, >=, like, null, not null, empty, not empty的条件查询
     * 
     * <p>
     * @param paramValue
     */
    public void addField(String paramName, String paramValue){    	
		String[] paramNames = paramName.split("[_]");
		Object value = null;
		if (paramNames != null && paramNames.length >= 4) {
			this.columnName = paramNames[1];
			this.columnType = paramNames[2];
			this.columnCalc = paramNames[3];
			this.columnValue = paramValue;
			
			value = ParamUtil.convertObject(this.columnType, paramValue);
			if (value != null) {
				FieldObject fieldObject = new FieldObject(this.columnName, this.columnType, this.columnCalc, value, this);
				fieldObjectList.add(fieldObject);
			}
		}
    }

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCalc() {
		return columnCalc;
	}

	public void setColumnCalc(String columnCalc) {
		this.columnCalc = columnCalc;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public List<FieldObject> getFieldObjectList() {
		return fieldObjectList;
	}

	public void setFieldObjectList(List<FieldObject> fieldObjectList) {
		this.fieldObjectList = fieldObjectList;
	}

	public List<Object> getParamValueList() {
		return paramValueList;
	}

	public void setParamValueList(List<Object> paramValueList) {
		this.paramValueList = paramValueList;
	}

	public Map<String, String> getItem() {
		return item;
	}

	public void setItem(Map<String, String> item) {
		this.item = item;
	}
}

package util.query;


/**
 * 功能：字段对象，存放字段的名称、类型、操作符和值
 * 
 * @author zhangCM
 * @date 2013-9-3
 */
public class FieldObject {
	private String property;
	private String type;
	private String operation;
	private Object value;
	
	private QueryObject queryObject;
	

	public FieldObject() {
		super();
	}

	public FieldObject(String property, String type, String operation,
			Object value, QueryObject queryObject) {
		super();
		this.property = property;
		this.type = type;
		this.operation = operation;
		this.value = value;
		this.queryObject = queryObject;
	}


	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public QueryObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(QueryObject queryObject) {
		this.queryObject = queryObject;
	}

	/**
	 * 功能：操作符转换，同时拼接 属性 和 参数值
	 * 
	 * @author zhangCM
	 * @return
	 */
	public String getPartHql() {
		String partHql = "";
		if ("EQ".equals(operation)) {
			partHql += property + " = ?";
			queryObject.getParamValueList().add(value);
		} else if ("NEQ".equals(operation)) {
			partHql = property + " <> ?";
			queryObject.getParamValueList().add(value);
		} else if ("LT".equals(operation)) {
			partHql = property + " < ?";
			queryObject.getParamValueList().add(value);
		} else if ("GT".equals(operation)) {
			partHql = property + " > ?";
			queryObject.getParamValueList().add(value);
		} else if ("LE".equals(operation)) {
			partHql = property + " <= ?";
			queryObject.getParamValueList().add(value);
		} else if ("GE".equals(operation)) {
			partHql = property + " >= ?";
			queryObject.getParamValueList().add(value);
		} else if ("LK".equals(operation)) {
			partHql = property + " like ?";
			queryObject.getParamValueList().add("%" + value.toString() + "%");
		} else if ("LFK".equals(operation)) {
			partHql = property + " like ?";
			queryObject.getParamValueList().add(value.toString() + "%");
		} else if ("RHK".equals(operation)) {
			partHql = property + " like ?";
			queryObject.getParamValueList().add("%" + value.toString());
		} else if ("NULL".equals(operation)) {
			partHql = property + " is null";
		} else if ("NOTNULL".equals(operation)) {
			partHql = property + " is not null";
		} else if ("EMP".equals(operation)) {
			// TODO
		} else if ("NOTEMP".equals(operation)) {

		} else {
			partHql += property + " = ?";
			queryObject.getParamValueList().add(value);
		}

		return partHql;
	}
	
	/**
	 * 功能：操作符转换
	 * 
	 * @author zhangCM
	 * @return
	 */
	public String getOP() {
		String op = "";
		if ("EQ".equals(operation)) {
			op = " = ";
		} else if ("NEQ".equals(operation)) {
			op = " <> ";
		} else if ("LT".equals(operation)) {
			op = " < ";
		} else if ("GT".equals(operation)) {
			op = " > ";
		} else if ("LE".equals(operation)) {
			op = " <= ";
		} else if ("GE".equals(operation)) {
			op = " >= ";
		} else if ("LK".equals(operation)) {
			op = " like ";
		} else if ("NULL".equals(operation)) {
			op = " is null ";
		} else if ("NOTNULL".equals(operation)) {
			op = " is not null ";
		} else {
			op = " = ";
		}
		return op;
	}
}

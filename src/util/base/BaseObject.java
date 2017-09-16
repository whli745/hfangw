package util.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.builder.*;

@SuppressWarnings("serial")
public class BaseObject implements Serializable {

	public BaseObject() {
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode(Object o) {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	protected String showDatetime(Date pidate) {
		return getFormateDate(pidate, "yyyy-MM-dd HH:mm");
	}

	protected String showDate(Date pidate) {
		return getFormateDate(pidate, "yyyy-MM-dd");
	}

	protected String showTime(Date pidate) {
		return getFormateDate(pidate, "HH:mm");
	}

	private String getFormateDate(Date pidate, String formate) {
		if (pidate == null)
			return null;
		else
			return (new SimpleDateFormat(formate)).format(pidate);
	}
}
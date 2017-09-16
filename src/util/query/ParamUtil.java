package util.query;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.DateTools;

/**
 * 功能: 参数转换
 * 
 * @author zhangCM
 * @date 2013-9-3
 *
 */
public class ParamUtil {
	private static Log logger = LogFactory.getLog(ParamUtil.class);

	/**
	 * 功能：根据类型，参数转换
	 * @param type
	 * @param paramValue
	 * @return
	 */
	public static Object convertObject(String type, String paramValue) {
		if (StringUtils.isEmpty(paramValue))
			return null;
		Object value = null;
		try {
			if ("S".equals(type)) {// 大部的查询都是该类型，所以放至在头部
				value = paramValue;
			} else if ("L".equals(type)) {
				value = new Long(paramValue);
			} else if ("N".equals(type)) {
				value = new Integer(paramValue);
			} else if ("DB".equals(type)) {
				value = new Double(paramValue);
			} else if ("F".equals(type)) {
				value = new Float(paramValue);
			} else if ("SH".equals(type)) {
				value = new Short(paramValue);
			} else if ("D".equals(type)) {
				value = DateUtils.parseDate(paramValue, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
			} else if ("DL".equals(type)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtils.parseDate(paramValue, new String[] { "yyyy-MM-dd" }));
				value = DateTools.setStartDay(cal).getTime();
			} else if ("DG".equals(type)) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtils.parseDate(paramValue, new String[] { "yyyy-MM-dd" }));
				value = DateTools.setEndDay(cal).getTime();
			} else {
				value = paramValue;
			}
		} catch (Exception ex) {
			logger.error("the data value is not right for the query filed type:" + ex.getMessage());
		}
		return value;
	}
}

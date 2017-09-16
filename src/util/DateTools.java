package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

public class DateTools {
	/**
	 * 返回指定格式的系统当前日期时间
	 * 
	 * @param String
	 *            fomat 指定格式
	 * @return String yyyymmdd
	 * @author baoxing
	 */
	public static String getDateAndTime(String fomat) {
		SimpleDateFormat today;
		today = new SimpleDateFormat(fomat);
		return today.format(new Date());
	}

	/**
	 * 返回指定格式的系统当前日期时间
	 * 
	 * @param String
	 *            fomat 指定格式
	 * @return Date
	 * @author meidj
	 */
	public static Date getDate(String fomat) {
		SimpleDateFormat today;
		today = new SimpleDateFormat(fomat);
		return strToDate(today.format(new Date()), fomat);
	}

	/**
	 * 返回指定格式的时间
	 * 
	 * @param String
	 *            fomat 指定格式
	 * @return String yyyymmdd
	 * @author baoxing
	 */
	public static String getDateAndTime(Date date, String fomat) {
		SimpleDateFormat today;
		today = new SimpleDateFormat(fomat);
		return today.format(date);
	}

	/**
	 * 返回系统当前日期 格式为 YYYY-MM-dd
	 * 
	 * @return String YYYY-MM-dd
	 * @author baoxing
	 */
	public static String getDate() {
		return getDateAndTime("yyyy-MM-dd");
	}

	/**
	 * 按照指定的格式,格式化指定的日期
	 * 
	 * @param Date
	 *            指定的日期
	 * @param String
	 *            指定的日期格式
	 * @return String 格式化好的字符串
	 */
	public static String formatDate(Date pidate, String strFormat) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat);
		String str = simpledateformat.format(pidate);
		return str;
	}

	/**
	 * 按照指定的格式,转化字符串为日期
	 * 
	 * @param String
	 *            指定的字符串
	 * @param String
	 *            指定的日期格式
	 * @return Date 转换好的日期
	 */
	public static Date strToDate(String str, String strFormat) {
		Date reDate = null;
		SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat);
		try {
			reDate = simpledateformat.parse(str);
		} catch (Exception e) {
			// Common.log("无效的日期字符串");
		}
		return reDate;
	}

	/**
	 * 在某一日期上增加天数，返回多少天以后的日期
	 * 
	 * @param Date
	 *            指定的日期
	 * @param long 增加的天数
	 * @return Date 增加天数以后的日期
	 */
	public static Date addDay(Date date, long days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, (int) days);
		Date date1 = calendar.getTime();
		return date1;
	}
	
	/**
	 * 在某一日期上增加分钟数，返回多少分钟以后的日期
	 * 
	 * @param Date
	 *            指定的日期
	 * @param long 增加的分钟数
	 * @return Date 增加分钟数以后的日期
	 */
	public static Date addMinute(Date pidate, long minutes) {
		long adate = pidate.getTime() + minutes * 60 * 1000;
		return new Date(adate);
	}

	/**
	 * 取得两个日期之间的差值
	 * 
	 * @param date
	 *            第一个日期
	 * @param date
	 *            第二个日期
	 * @return long 两个日期之间的差值，类型是微秒
	 */
	public static long dateDiff(Date pidate1, Date pidate2) {
		return pidate1.getTime() - pidate2.getTime();
	}

	/**
	 * 取得两个日期之间的差值
	 * 
	 * @param String
	 *            差值的类型
	 * @param date
	 *            第一个日期
	 * @param date
	 *            第二个日期
	 * @return long 两个日期之间的差值，类型由第一个String参数指定
	 */
	public static long dateDiff(String style, Date fromdate, Date todate) {
		byte byte0;
		byte byte1 = 0;
		int i = 1;
		Date date2;
		Date date3;
		if (fromdate.getTime() > todate.getTime()) {
			i = -1;
			date2 = todate;
			date3 = fromdate;
		} else {
			date2 = fromdate;
			date3 = todate;
		}
		if (style.equals("yyyy"))
			byte0 = 1;
		else if (style.equals("m"))
			byte0 = 2;
		else if (style.equals("d"))
			byte0 = 5;
		else if (style.equals("y"))
			byte0 = 5;
		else if (style.equals("w"))
			byte0 = 4;
		else if (style.equals("ww"))
			byte0 = 3;
		else if (style.equals("h")) {
			byte0 = 5;
			byte1 = 11;
		} else if (style.equals("n")) {
			byte0 = 5;
			byte1 = 12;
		} else if (style.equals("s")) {
			byte0 = 5;
			byte1 = 13;
		} else {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		long l = 0;
		calendar.add(byte0, 1);
		for (Date date4 = calendar.getTime(); date4.getTime() <= date3
				.getTime();) {
			calendar.add(byte0, 1);
			date4 = calendar.getTime();
			l++;
		}
		if (byte1 == 11 || byte1 == 12 || byte1 == 13) {
			calendar.setTime(date2);
			calendar.add(byte0, (int) l);
			switch (byte1) {
			case 11:
				l *= 24;
				break;
			case 12:
				l = l * 24 * 60;
				break;
			case 13:
				l = l * 24 * 60 * 60;
				break;
			}
			calendar.add(byte1, 1);
			for (Date date6 = calendar.getTime(); date6.getTime() <= date3
					.getTime();) {
				calendar.add(byte1, 1);
				date6 = calendar.getTime();
				l++;
			}
		}
		return l * i;
	}

	/**
	 * 取得指定日期所在的星期
	 * 
	 * @param date
	 *            指定的日期
	 * @return int 所在的星期
	 */
	public static int getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(7);
	}

	/**
	 * 取得指定日期所在星期的周一和周日
	 * 
	 * @param date
	 * @return Date[] [0]为周一，[1]为周日
	 */
	public static Date[] getDateOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int index = cal.get(Calendar.DAY_OF_WEEK); // 今天是本周的第几天
		// 转成中国的习惯,如果是第一天,则转化为第七天(星期天外国为一周的第一天,而中国为每周的最后一天)
		if (index == 1)
			index = 8;

		cal.add(Calendar.DATE, -(index - 2));
		Date start = cal.getTime();

		cal.add(Calendar.DATE, 6);
		Date end = cal.getTime();

		Date[] result = new Date[] { start, end };

		return result;
	}

	/**
	 * 获得本周一的日期
	 * 
	 * @author yuandz
	 */
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 * 
	 * @author yuandz
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;// 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 取得任意两个日期之间的天数
	 * 
	 * @param toDate
	 *            结束日期字符串
	 * @param fromDate
	 *            开始日期字符串
	 * @return
	 */
	public static long getQuot(String fromDate, String toDate) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(toDate);
			Date date2 = ft.parse(fromDate);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 在某一日期上增加天数，返回多少天以后的日期字符串
	 * 
	 * @param String
	 *            指定的日期
	 * @param long 增加的天数
	 * @author yuandz
	 * @return String 增加天数以后的日期
	 */
	public static String addDay(String date, int days) {
		Date d = strToDate(date, "yyyy-MM-dd");
		SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_MONTH, +days);
		d = calendar.getTime();
		return e.format(d);
	}

	/**
	 * 获取当前日期是星期几
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获取本周的第一天与最后一天的时间段（yyyy-MM-dd）
	 * 
	 * @return
	 */
	public static String getDayToDayOfWeek(Date date) {
		Date[] weekDate = getDateOfThisWeek(date);
		String str = formatDate(weekDate[0], "yyyy-MM-dd") + "------"
				+ formatDate(weekDate[1], "yyyy-MM-dd");
		return str;
	}

	/**
	 * 取得指定日期当月的天数
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getMonthOfDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		return calendar.get(Calendar.DATE);
	}

	public static Integer getYearsOfDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int day = 0;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			day = 366;
		} else {
			day = 365;
		}
		return day;
	}

	/**
	 * 获取本周的一周的日期
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Hashtable<String, Object>> getAllWeekDays(Date date) {
		List<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
		Date monday = getDateOfThisWeek(date)[0];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(monday.getTime()));
		for (int i = 0; i < 7; i++) {
			Hashtable<String, Object> hashTable = new Hashtable<String, Object>();
			hashTable.put("day", calendar.getTime());
			calendar.add(Calendar.DATE, 1);
			list.add(hashTable);
		}
		return list;
	}
	
	/**
	 * 功能： 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	/**
	 * 功能： 设置当前时间为当天的最后时间（即23时59分59秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}
}
package com.beyondsoft.thrift.web.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;

/**
 * 日期工具类
 * 
 */
public class DateUtil {

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}

	public static Date format2(Date date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		String d = fmtDateToStr(date, pattern);
		return fmtStrToDate(d, pattern);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			// return new Date();
			pattern = "mm:ss";
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}

	/**
	 * 功能：返回当前的时间戳
	 * 
	 * @return
	 */
	public static Timestamp getToday() {

		return Timestamp
				.valueOf(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

	}

	public static String splitDate(String myDate) {

		myDate = myDate.substring(0, myDate.length() - 2);
		return myDate;
	}

	/**
	 * 将字符串转化为DATE
	 * 
	 * @param dtFormat
	 *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
	 * @param def
	 *            如果格式化失败返回null
	 * @return
	 */
	/**
	 * 将字符串转化为DATE
	 * 
	 * @param dtFormat
	 *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd或 yyyy-M-dd或 yyyy-M-d或
	 *            yyyy-MM-d或 yyyy-M-dd
	 * @param def
	 *            如果格式化失败返回null
	 * @return
	 */
	public static Date fmtStrToDate(String dtFormat) {
		if (dtFormat == null || dtFormat.trim().equals("")) {
			return null;
		}
		try {
			if (dtFormat.length() == 9 || dtFormat.length() == 8) {
				String[] dateStr = dtFormat.split("-");
				dtFormat = dateStr[0] + (dateStr[1].length() == 1 ? "-0" : "-")
						+ dateStr[1] + (dateStr[2].length() == 1 ? "-0" : "-")
						+ dateStr[2];
			}
			if (dtFormat.length() != 10 & dtFormat.length() != 19)
				return null;
			if (dtFormat.length() == 10)
				dtFormat = dtFormat + " 00:00:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(dtFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Description:格式化日期,如果格式化失败返回def
	 * 
	 * @param dtFormat
	 * @param def
	 * @return
	 */
	public static Date fmtStrToDate(String dtFormat, Date def) {
		Date d = fmtStrToDate(dtFormat);
		if (d == null)
			return def;
		return d;
	}

	/**
	 * 返回当日短日期型
	 * 
	 * @return
	 */
	public static Date getToDay() {
		return toShortDate(new Date());
	}

	/**
	 * 返回当日短日期型
	 * 
	 * @return
	 */
	public static Date getToDayL() {
		return toLongDate(new Date());
	}
	
	/**
	 * 返回当日短日期型
	 * 
	 * @return
	 */
	public static String getToDayLStr() {
		return format(toLongDate(new Date()));
	}

	/**
	 * 
	 * Description:格式化日期,String字符串转化为Date
	 * 
	 * @param date
	 * @param dtFormat
	 *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd
	 * @return
	 */
	public static String fmtDateToStr(Date date, String dtFormat) {
		if (date == null)
			return "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Description:按指定格式 格式化日期
	 * 
	 * @param date
	 * @param dtFormat
	 * @return
	 */
	public static Date fmtStrToDate(String date, String dtFormat) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
			return dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String fmtDateToYMDHM(Date date) {
		return fmtDateToStr(date, "yyyy-MM-dd HH:mm");
	}

	public static String fmtDateToYMD(Date date) {
		return fmtDateToStr(date, "yyyy-MM-dd");
	}

	public static String fmtDateToYM(Date date) {
		return fmtDateToStr(date, "yyyy-MM");
	}

	public static String fmtDateToM(Date date) {
		return fmtDateToStr(date, "MM");
	}

	/**
	 * 
	 * Description:只保留日期中的年月日
	 * 
	 * @param date
	 * @return
	 */
	public static Date toShortDate(Date date) {
		String strD = fmtDateToStr(date, "yyyy-MM-dd");
		return fmtStrToDate(strD);
	}

	/**
	 * 
	 * Description:只保留日期中的年月日 时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date toLongDate(Date date) {
		String strD = fmtDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		return fmtStrToDate(strD);
	}

	/**
	 * 
	 * Description:只保留日期中的年月日
	 * 
	 * @param date格式要求yyyy
	 *            -MM-dd……………………
	 * @return
	 */
	public static Date toShortDate(String date) {
		if (date != null && date.length() >= 10) {
			return fmtStrToDate(date.substring(0, 10));
		} else
			return fmtStrToDate(date);
	}

	/**
	 * 求对日
	 * 
	 * @param countMonth
	 *            :月份的个数(几个月)
	 * @param flag
	 *            :true 求前countMonth个月的对日:false 求下countMonth个月的对日
	 * @return
	 */
	public static Date getCounterglow(int countMonth, boolean before) {
		Calendar ca = Calendar.getInstance();
		return getCounterglow(ca.getTime(), before ? -countMonth : countMonth);
	}

	/**
	 * 
	 * Description: 求对日 加月用+ 减月用-
	 * 
	 * @param date
	 * @param countMonth
	 * @return
	 */
	public static Date getCounterglow(Date date, int num) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, num);
		return ca.getTime();
	}

	/**
	 * 
	 * Description:加一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date addDay(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_YEAR, 1);
		return cd.getTime();
	}

	/**
	 * 
	 * Description:判断一个日期是否为工作日(非周六周日)
	 * 
	 * @param date
	 */
	public static boolean isWorkDay(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek != Calendar.SUNDAY || dayOfWeek != Calendar.SATURDAY)
			return false;
		return true;
	}

	/**
	 * 
	 * Description:取一个月的最后一天
	 * 
	 * @param date1
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date1) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.add(Calendar.MONTH, 1);
		date.add(Calendar.DAY_OF_YEAR, -1);
		return toShortDate(date.getTime());
	}

	/**
	 * 求开始截至日期之间的天数差. 不准确不准确不准确不准确不准确不准确不准确不准确
	 * 
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            截至日期
	 * @return 返回相差天数
	 */
	public static int getDaysInterval(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return 0;
		Date[] d = new Date[2];
		d[0] = d1;
		d[1] = d2;
		Calendar[] cal = new Calendar[2];
		for (int i = 0; i < cal.length; i++) {
			cal[i] = Calendar.getInstance();
			cal[i].setTime(d[i]);
			cal[i].set(Calendar.HOUR_OF_DAY, 0);
			cal[i].set(Calendar.MINUTE, 0);
			cal[i].set(Calendar.SECOND, 0);
		}
		long m = cal[0].getTime().getTime();
		long n = cal[1].getTime().getTime();
		int ret = (int) Math.abs((m - n) / 1000 / 3600 / 24);
		return ret;
	}

	/**
	 * 求开始截至日期之间的天数差.准确
	 * 
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            截至日期
	 * @return 返回相差天数
	 */
	public static int getDaysIntervalStr(String dateStart, String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;
		int diffDaysInt = 0;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// 毫秒ms
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			diffDaysInt = new Long(diffDays).intValue();
			// System.out.print("两个时间相差：");
			// System.out.print(diffDays + " 天, ");
			// System.out.print(diffHours + " 小时, ");
			// System.out.print(diffMinutes + " 分钟, ");
			// System.out.print(diffSeconds + " 秒.");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffDaysInt;
	}

	public static String getDayOfWeek(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return "周" + toChNumber(cl.get(Calendar.DAY_OF_WEEK) - 1);
	}

	/**
	 * 将数字转为中文。 "0123456789"->"〇一二三四五六七八九"
	 * 
	 * @param num
	 *            长度为1,'0'-'9'的字符串
	 * @return
	 */
	private static String toChNumber(int num) {
		final String str = "〇一二三四五六七八九";
		return str.substring(num, num + 1);
	}

	/**
	 * 
	 * Description:指定日期加或减分钟
	 * 
	 * @param date1日期
	 * @param days天数
	 * @return
	 */
	public static Date addMINUTE(Date date1, int minute) {

		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.MINUTE, minute);
		return date.getTime();
	}

	/**
	 * 
	 * Description:指定日期加或减days天
	 * 
	 * @param date1日期
	 * @param days天数
	 * @return
	 */
	public static Date addDay(Date date1, int days) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.DAY_OF_YEAR, days);
		return date.getTime();
	}

	/**
	 * 
	 * Description:指定日期加或减months月
	 * 
	 * @param date1
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date1, int months) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.MONTH, months);
		return date.getTime();
	}

	/**
	 * 
	 * Description:指定日期加或减years年
	 * 
	 * @param date1
	 * @param years
	 * @return
	 */
	public static Date addYear(Date date1, int years) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.YEAR, years);
		return date.getTime();
	}

	/**
	 * 指定期间的开始日期
	 * 
	 * @param date
	 *            指定日期
	 * @param type
	 *            期间类型
	 * @param diff
	 *            与指定日期的范围
	 * @return
	 */
	public static Date getPeriodStart(Calendar date, int type, int diff) {
		date.add(type, diff * (-1));
		return date.getTime();
	}

	/**
	 * 指定期间的开始日期
	 * 
	 * @param date
	 *            指定日期
	 * @param type
	 *            期间类型
	 * @param diff
	 *            与指定日期的范围
	 * @return
	 */
	public static Date getPeriodStart(Date date, int type, int diff) {
		return getPeriodStart(dateToCalendar(date), type, diff);
	}

	/**
	 * 指定期间的结束日期
	 * 
	 * @param date
	 *            指定日期
	 * @param type
	 *            期间类型
	 * @param diff
	 *            与指定日期的范围
	 * @return
	 */
	public static Date getPeriodEnd(Calendar date, int type, int diff) {
		date.add(type, diff);
		return date.getTime();
	}

	/**
	 * 指定期间的结束日期
	 * 
	 * @param date
	 *            指定日期
	 * @param type
	 *            期间类型
	 * @param diff
	 *            与指定日期的范围
	 * @return
	 */
	public static Date getPeriodEnd(Date date, int type, int diff) {
		return getPeriodEnd(dateToCalendar(date), type, diff);
	}

	/**
	 * 指定日期所在星期的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		cdate.set(Calendar.DAY_OF_WEEK, 2);
		return cdate.getTime();
	}

	/**
	 * 将java.util.Date类型转换成java.util.Calendar类型
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cdate = Calendar.getInstance();
		cdate.setTime(date);
		return cdate;
	}

	/**
	 * 指定日期所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		cdate.set(Calendar.DAY_OF_MONTH, 1);
		return toShortDate(cdate.getTime());
	}

	/**
	 * 指定日期所在上月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		cdate.set(Calendar.DAY_OF_MONTH, 1);
		cdate.add(Calendar.MONTH, -1);
		return toShortDate(cdate.getTime());
	}

	/**
	 * 指定日期所在旬的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTenDaysStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		int day = cdate.get(Calendar.DAY_OF_MONTH) / 10 * 10 + 1;
		if (cdate.get(Calendar.DAY_OF_MONTH) % 10 == 0 || day == 31)
			day = day - 10;
		cdate.set(Calendar.DAY_OF_MONTH, day);
		return cdate.getTime();
	}

	/**
	 * 指定日期所在旬的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTenDaysEnd(Date date) {
		Calendar cdate = dateToCalendar(date);
		if (cdate.get(Calendar.DAY_OF_MONTH) / 10 == 2
				&& cdate.get(Calendar.DAY_OF_MONTH) != 20)
			return getLastDayOfMonth(date);
		else
			return addDay(getTenDaysStart(addDay(date, 10)), -1);
	}

	/**
	 * 指定日期所在年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getYearStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		cdate.set(Calendar.DAY_OF_YEAR, 1);
		return cdate.getTime();
	}

	/**
	 * 指定日期所在季度的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getQuarterStart(Date date) {
		Calendar cdate = dateToCalendar(date);
		int month = (cdate.get(Calendar.MONTH) / 3) * 3;
		cdate.set(Calendar.MONTH, month);
		return getMonthStart(cdate.getTime());
	}

	/**
	 * 指定日期返回带中文的字符串（目前为年月日类型，之后补充）
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStringByChinese(String format, Date date) {
		String dateString = fmtDateToStr(date, format);
		String[] dateStringArray = dateString.split("-");
		if ("yyyy-MM-dd".equals(format)) {
			dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月"
					+ dateStringArray[2] + "日";
		} else if ("yyyy-MM".equals(format)) {
			dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月";
		}
		return dateString;
	}

	public static Date getLastDayOfYear(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String years = dateFormat.format(date);
		years += "-12-31";
		Date returnDate = fmtStrToDate(years);
		return toShortDate(returnDate);
	}

	/**
	 * 计算两个日期之间相差的月数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonths(Date date1, Date date2) {
		int iMonth = 0;
		int flag = 0;
		try {
			Calendar objCalendarDate1 = Calendar.getInstance();
			objCalendarDate1.setTime(date1);

			Calendar objCalendarDate2 = Calendar.getInstance();
			objCalendarDate2.setTime(date2);

			if (objCalendarDate2.equals(objCalendarDate1))
				return 0;
			if (objCalendarDate1.after(objCalendarDate2)) {
				Calendar temp = objCalendarDate1;
				objCalendarDate1 = objCalendarDate2;
				objCalendarDate2 = temp;
			}
			if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1
					.get(Calendar.DAY_OF_MONTH))
				flag = 1;

			if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1
					.get(Calendar.YEAR))
				iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1
						.get(Calendar.YEAR))
						* 12
						+ objCalendarDate2.get(Calendar.MONTH) - flag)
						- objCalendarDate1.get(Calendar.MONTH);
			else
				iMonth = objCalendarDate2.get(Calendar.MONTH)
						- objCalendarDate1.get(Calendar.MONTH) - flag;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return iMonth;
	}

	/**
	 * 指定日期上一个旬的第一天
	 */
	public static Date getLastTenStartDate(Date date) {
		Date returnDate = DateUtil.toShortDate(date);
		returnDate = DateUtil.getTenDaysStart(date);
		returnDate = DateUtil.addDay(returnDate, -1);
		returnDate = DateUtil.getTenDaysStart(returnDate);
		return DateUtil.toShortDate(returnDate);
	}

	/**
	 * 指定日期上一个旬的最后一天
	 */
	public static Date getLastTenEndDate(Date date) {
		Date returnDate = DateUtil.toShortDate(date);
		returnDate = DateUtil.getTenDaysStart(date);
		returnDate = DateUtil.addDay(returnDate, -1);
		return DateUtil.toShortDate(returnDate);
	}

	/**
	 * 指定日期上个月第一天
	 */
	public static Date getLastMonthStartDate(Date date) {
		Date returnDate = DateUtil.toShortDate(date);
		returnDate = DateUtil.getLastMonthStart(date);
		return DateUtil.toShortDate(returnDate);
	}

	/**
	 * 指定日期上个月最后一天
	 */
	public static Date getLastMonthEndDate(Date date) {
		Date returnDate = DateUtil.toShortDate(date);
		returnDate = DateUtil.getMonthStart(date);
		returnDate = DateUtil.addDay(returnDate, -1);
		return DateUtil.toShortDate(returnDate);
	}

	public static Date getAddDate(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static int getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return w;
	}

	// 计算两个时间段中间的日期的方法
	public static List<String> findDates(Date date1, Date date2)
			throws ParseException {
		List<String> list = Lists.newArrayList();
		date1 = format2(date1, "yyyy-MM-dd");
		date2 = format2(date2, "yyyy-MM-dd");
		int s = (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
		System.out.println(s);
		String value[] = new String[s + 1];
		if (s + 1 > 0) {
			for (int i = 0; i <= s; i++) {
				long todayDate = date1.getTime() + i * 24 * 60 * 60 * 1000;
				Date tmDate = new Date(todayDate);
				list.add(new SimpleDateFormat("yyyy-MM-dd").format(tmDate));
			}
		}
		return list;
	}

	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getMinDate(List<String> dateL) {
		String result = "";
		Collections.sort(dateL, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		result = dateL.get(0);
		return result;
	}

	public static void main(String[] args) throws ParseException {
		/*
		 * String date1="1989-12-11"; String date3="1999-11-12"; String
		 * date2="2000-11-11";
		 * 
		 * String result=""; List<String> list=Lists.newArrayList();
		 * list.add(date1); list.add(date2); list.add(date3);
		 * 
		 * System.out.println(getMinDate(list));
		 */

		// System.out.println(fmtDateToStr(addMonth(new
		// Date(),3),"yyyy-MM-dd HH:mm:ss"));

		String dateStart = "2014-09-15 00:00:00";
		String dateStop = "2014-09-18 00:00:00";
		List<String> lDate = findDates(fmtStrToDate(dateStart),
				fmtStrToDate(dateStop));
		for (String string : lDate) {
			System.out.println(string);
		}
		/*
		 * int week[]=null; StringBuffer strbuf =new StringBuffer(); String
		 * Str=""; //
		 * System.out.println(getDaysIntervalStr(dateStart,dateStop));
		 * 
		 * String noRentTime="1,3,5"; try { String[][]
		 * a=findDates(dateStart,dateStop);
		 * 
		 * //如果设置了不可租用时间，则如果 ：租客 预计借车 与 预计还车的时间>=7 肯定是不能租用的 if(a.length>=7) {
		 * System.out.println("肯定==不可租==");
		 * 
		 * } else //判断 租客 预计借车 与 预计还车的时间是否是在不可租车的时间内 根据星期几数组判断 { week=new
		 * int[a.length]; for(int i=0;i<a.length;i++) { String ab=a[i][0]; //
		 * System.out.println(ab);
		 * 
		 * if(getWeekOfDate(format(ab))==0) { //
		 * System.out.println(getWeekOfDate(format(ab))+7);
		 * week[i]=getWeekOfDate(format(ab))+7; } else {
		 * 
		 * // System.out.println(getWeekOfDate(format(ab)));
		 * week[i]=getWeekOfDate(format(ab)); }
		 * 
		 * }
		 * 
		 * Arrays.sort(week); for(int i=0;i<a.length;i++) {
		 * strbuf.append(week[i]).append(","); } Str=strbuf.toString(); //
		 * System.out.println(Str.substring(0, Str.length()-1));
		 * 
		 * boolean flag=false; String [] temp =Str.split(","); for(int
		 * i=0;i<temp.length;i++) { String result =
		 * jodd.util.StringUtil.remove(noRentTime, temp[i]);
		 * if(noRentTime.length()!=result.length()) { //
		 * System.out.println(result); flag=true; break; }
		 * 
		 * } if(flag) { System.out.println("不可租用"); } else {
		 * System.out.println("可租用"); } }
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}

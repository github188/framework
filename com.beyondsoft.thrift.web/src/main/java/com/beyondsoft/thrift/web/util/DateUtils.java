package com.beyondsoft.thrift.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
public class DateUtils {
	
	protected DateUtils(){}

	/**
	 * @param formatStr 自定义格式 例如：yyyy-MM-dd HH:mm:ss
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getDateFormat(Date date,String formatStr){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		if(StringUtils.isEmpty(formatStr)){
			 dateFormat = getDateFormat();
		}else{
			SimpleDateFormat decimal = new SimpleDateFormat(formatStr);
			 dateFormat = decimal.format(date);
		}
		return dateFormat;
	}
	/**
	 * @param formatStr 自定义格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDateFormat(String formatStr){
		String dateFormat = "";
		dateFormat = getDateFormat(null, formatStr);
		return dateFormat;
	}
	/**
	 * 返回固定格式    yyyy-MM-dd HH:mm:ss
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getDateFormat(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回固定格式    yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDateFormat(){
		Date date = null;
		String dateFormat = "";
		 dateFormat = getDateFormat(date);
		return dateFormat;
	}
	/**
	 * 返回固定格式    yyyyMMddHHmmss
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getDateFormatNum(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("yyyyMMddHHmmss");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回固定格式    yyyyMMddHHmmss
	 * @return String
	 */
	public static String getDateFormatNum(){
		String dateFormat = "";
		 dateFormat = getDateFormatNum(null);
		return dateFormat;
	}

	/**
	 * 返回固定格式    yyyy/MM/dd或yyyy-MM-dd(默认)
	 * @param date 如果为null则重新创建一个新Date
	 * @param str 值为  "/" 或 "-"
	 * @return String
	 */
	public static String getYearMonthDay(Date date,String str){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		if(!"/".equals(str)&&!"-".equals(str)){
			str = "-";
		}
		SimpleDateFormat decimal = new SimpleDateFormat("yyyy"+str+"MM"+str+"dd");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回固定格式    yyyy/MM/dd或yyyy-MM-dd(默认)
	 * @param str 值为  "/" 或 "-"
	 * @return String
	 */
	public static String getYearMonthDay(String str){
		String dateFormat = "";
		 dateFormat = getYearMonthDay(null, str);
		return dateFormat;
	}

	/**
	 * 返回固定格式   yyyy/MM或yyyy-MM(默认)
	 * @param date 如果为null则重新创建一个新Date
	 * @param str 值为  "/" 或 "-"
	 * @return String
	 */
	public static String getYearMonth(Date date,String str){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		if(!"/".equals(str)&&!"-".equals(str)){
			str = "-";
		}
		SimpleDateFormat decimal = new SimpleDateFormat("yyyy"+str+"MM");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回固定格式   yyyy/MM或yyyy-MM(默认)
	 * @param str 值为  "/" 或 "-"
	 * @return String
	 */
	public static String getYearMonth(String str){
		String dateFormat = "";
		 dateFormat = getYearMonth(null, str);
		return dateFormat;
	}

	/**
	 * 返回年   yyyy
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getYear(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("yyyy");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回年   yyyy
	 * @return String
	 */
	public static String getYear(){
		String dateFormat = "";
		 dateFormat = getYear(null);
		return dateFormat;
	}

	/**
	 * 返回月  MM
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getMonth(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("MM");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回月  MM
	 * @return String
	 */
	public static String getMonth(){
		String dateFormat = "";
		 dateFormat = getMonth(null);
		return dateFormat;
	}

	/**
	 * 返回日   dd
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getDay(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("dd");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回日   dd
	 * @return String
	 */
	public static String getDay(){
		String dateFormat = "";
		 dateFormat = getDay(null);
		return dateFormat;
	}

	/**
	 * 返回时分秒   HH:mm:ss
	 * @param date 如果为null则重新创建一个新Date
	 * @param str 值为  ":" 或 "-"
	 * @return String
	 */
	public static String getHourMinuteSecond(Date date,String str){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		if(!":".equals(str)&&!"-".equals(str)){
			str = ":";
		}
		SimpleDateFormat decimal = new SimpleDateFormat("HH"+str+"mm"+str+"ss");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回时分秒   HH:mm:ss
	 * @param str 值为  ":" 或 "-"
	 * @return String
	 */
	public static String getHourMinuteSecond(String str){
		String dateFormat = "";
		 dateFormat = getHourMinuteSecond(null, str);
		return dateFormat;
	}

	/**
	 * 返回时分   HH:mm
	 * @param date 如果为null则重新创建一个新Date
	 * @param str 值为  ":" 或 "-"
	 * @return String
	 */
	public static String getHourMinute(Date date,String str){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		if(!":".equals(str)&&!"-".equals(str)){
			str = ":";
		}
		SimpleDateFormat decimal = new SimpleDateFormat("HH"+str+"mm");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回时分   HH:mm
	 * @param str 值为  ":" 或 "-"
	 * @return String
	 */
	public static String getHourMinute(String str){
		String dateFormat = "";
		 dateFormat = getHourMinute(null, str);
		return dateFormat;
	}

	/**
	 * 返回小时   HH
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getHour(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("HH");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回小时   HH
	 * @return String
	 */
	public static String getHour(){
		String dateFormat = "";
		 dateFormat = getHour(null);
		return dateFormat;
	}

	/**
	 * 返回分   mm
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getMinute(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("mm");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回分   mm
	 * @return String
	 */
	public static String getMinute(){
		String dateFormat = "";
		 dateFormat = getMinute(null);
		return dateFormat;
	}

	/**
	 * 返回秒   ss
	 * @param date 如果为null则重新创建一个新Date
	 * @return String
	 */
	public static String getSecond(Date date){
		String dateFormat = "";
		if(date==null){
			date = new Date();
		}
		SimpleDateFormat decimal = new SimpleDateFormat("ss");
		 dateFormat = decimal.format(date);
		return dateFormat;
	}
	/**
	 * 返回秒   ss
	 * @return String
	 */
	public static String getSecond(){
		String dateFormat = "";
		 dateFormat = getSecond(null);
		return dateFormat;
	}
	
	private static long milliSecond=0;
	/**
	 * 返回毫秒   milliSecond
	 * @param date 如果为null则重新创建一个新Date
	 * @return long
	 */
	public synchronized static String getMilliSecond(Date date){
		long milliSecond=0;
		milliSecond=System.currentTimeMillis();
		while(DateUtils.milliSecond == milliSecond){
			if(date==null){
				milliSecond=System.currentTimeMillis();
			}else{
				milliSecond = date.getTime();
			}
		}
		DateUtils.milliSecond = milliSecond;
		return milliSecond+"";
	}
	/**
	 * 返回毫秒   milliSecond
	 * @return long
	 */
	public synchronized static String getMilliSecond(){
		return getMilliSecond(null);
	}
	
	/**
	 * 返回bigDate与smallDate的年份差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getYearLag(Date bigDate,Date smallDate){
		int yearLag = 0;
		int big = 0;
		big = Integer.parseInt(getYear(bigDate));
		int small =  0;
		small = Integer.parseInt(getYear(smallDate));
		yearLag = big - small;
		if(yearLag>0){
			yearLag = -yearLag;
		}
		return yearLag;
	}
	
	/**
	 * 返回bigDate与smallDate的月份差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getMonthLag(Date bigDate,Date smallDate){
		int monthLag = 0;
		int yearLag = getYearLag(bigDate, smallDate);
		if(yearLag>0){
			yearLag = yearLag-1;
		}
		int bigMonth = 0;
		int smallMonth = 0;
		bigMonth = Integer.parseInt(getMonth(bigDate));
		smallMonth = 12-Integer.parseInt(getMonth(smallDate));
		monthLag = yearLag*12 + bigMonth + smallMonth;
		return monthLag;
	}
	
	/**
	 * 返回bigDate与smallDate的天数差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getDayLag(Date bigDate,Date smallDate){
		int dayLag = 0;
		int hourLag = getHourLag(bigDate, smallDate);
		dayLag = hourLag/24;
		return dayLag;
	}
	
	/**
	 * 返回bigDate与smallDate的小时差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getHourLag(Date bigDate,Date smallDate){
		int hourLag = 0;
		int minuteLag = getMinuteLag(bigDate, smallDate);
		hourLag = minuteLag/60;
		return hourLag;
	}
	
	/**
	 * 返回bigDate与smallDate的分钟差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getMinuteLag(Date bigDate,Date smallDate){
		int minuteLag = 0;
		int secondLag = getSecondLag(bigDate, smallDate);
		minuteLag = secondLag/60;
		return minuteLag;
	}
	
	/**
	 * 返回bigDate与smallDate的秒数差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static int getSecondLag(Date bigDate,Date smallDate){
		int secondLag = 0;
		long milliSecondLag = getMilliSecondLag(bigDate, smallDate);
		secondLag = (int)milliSecondLag/1000;
		return secondLag;
	}
	
	/**
	 * 返回bigDate与smallDate的毫秒差
	 * @param bigDate  为NULL则取系统当前日期时间
	 * @param smallDate 为NULL则取系统当前日期时间
	 * @return
	 */
	public static long getMilliSecondLag(Date bigDate,Date smallDate){
		long milliSecondLag = 0;
		long big = 0;
		long small = 0;
		bigDate = checkDateIsNotNull(bigDate);
		smallDate = checkDateIsNotNull(smallDate);
		big = bigDate.getTime();
		small = smallDate.getTime();
		milliSecondLag = big - small;
		if(milliSecondLag<0){
			milliSecondLag = -milliSecondLag;
		}
		return milliSecondLag;
	}
	
	/**
	 * 判断两日期大小
	 * bigDate大于smallDate返回true，否则返回false
	 * @param bigDate
	 * @param smallDate
	 * @return
	 */
	public static boolean compareDateWithBigOrSmall(Date bigDate,Date smallDate){
		boolean flag = false;
		long big = 0;
		if(bigDate!=null){
			big = bigDate.getTime();
		}
		long small =  0;
		if(smallDate!=null){
			small = smallDate.getTime();
		}
		if(big>small){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断两日期是否相等
	 * bigDate等于smallDate返回true，否则返回false
	 * @param bigDate 
	 * @param smallDate
	 * @return
	 */
	public static boolean compareDateWithEqual(Date bigDate,Date smallDate){
		boolean flag = false;
		long big = 0;
		if(bigDate!=null){
			big = bigDate.getTime();
		}
		long small =  0;
		if(smallDate!=null){
			small = smallDate.getTime();
		}
		if(big==small){
			flag = true;
		}
//		String big = "";
//		if(bigDate!=null){
//			big = getDateFormat(bigDate); 
//		}
//	    String small = "";
//		if(smallDate!=null){
//			small = getDateFormat(smallDate); 
//		}
//	    if(big.equals(small)){
//	    	flag = true; 
//	    }
		return flag;
	}
	
	/**
	 * 检查date是否为NULL，如果是不是NULL则返回date，否则重新创建并返回
	 * @param date 
	 * return date
	 */
	private static Date checkDateIsNotNull(Date date){
		if(date==null){
			date = getDate();
		}
		return date;
	}
	
	/**
	 * return date
	 */
	public static Date getDate(){
		Date date = new Date();
		try {
			date = DateFormat.getDateTimeInstance().parse(getDateFormat());
		} catch (ParseException e) {
			System.out.println(ExceptionUtils.getErrorInfo(e));
		}
		return date;
	}
}

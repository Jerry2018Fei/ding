package com.saas.utils.date;

import com.saas.utils.string.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	private static Logger logger= LoggerFactory.getLogger(DateUtils.class);
	public static final String DEFAULT_PATTERN="yyyy-MM-dd";
	public static final String Y_M_D_PATTERN="yyyy-MM-dd";
	public static final String YMD_PATTERN="yyyyMMdd";
	public static final String MD_PATTERN="MMdd";
	public static final String Y_M_D_H_M_S_PATTERN="yyyy-MM-dd HH:mm:ss";
	public static final String YMDHMS_PATTERN="yyyyMMddHHmmss";
	public static final String H_M_S_PATTERN="HH:mm:ss";
	public static final String HMS_PATTERN="HHmmss";
	public static final Long ONE_DAY_MILLSECONDS=1000 * 3600 * 24L;

	private static String[] parsePatterns = {"yyyyMMdd","yyyyMMddHHmmss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate(DEFAULT_PATTERN);
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), StringUtils.isEmpty(pattern)?DEFAULT_PATTERN:pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd HH:mm:ss" "yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate ;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, DEFAULT_PATTERN);
		}
		return formatDate;
	}

	public static Date parse2Date(String date, String pattern) {
		SimpleDateFormat sdf=new SimpleDateFormat(pattern==null?DEFAULT_PATTERN:pattern);
		if(StringUtils.isEmpty(date)) return null;

		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
	}

	public static List<String> getBetweenDateString(String startDate, String endDate,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isEmpty(pattern)?DEFAULT_PATTERN:pattern);
		Calendar cal = Calendar.getInstance();
		Date start = sdf.parse(startDate);
		cal.setTime(start);
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(endDate));
		setDayMinTime(cal);
		long time2 = cal.getTimeInMillis();
		setDayMinTime(cal);
		long between_days = (time2 - time1) / ONE_DAY_MILLSECONDS;
		int count = Integer.parseInt(String.valueOf(between_days)) + 1;
		List<String> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(DateUtils.formatDate(DateUtils.addDays(start, i), StringUtils.isEmpty(pattern)?DEFAULT_PATTERN:pattern));
		}
		return list;
	}

	private static void setDayMinTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
	}

	private static void setDayMaxTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,59);
		cal.set(Calendar.MILLISECOND,999);
	}
	public static String getDateByAddDays(int cnt ,String pattern) {
		Calendar cal=Calendar.getInstance();
		setDayMinTime(cal);
		cal.set(Calendar.DATE,cal.get(Calendar.DATE)+cnt);
		return DateUtils.formatDate(cal.getTime(), StringUtils.isEmpty(pattern)?DEFAULT_PATTERN:pattern);
	}

	public static String getDayStart(Date date,String pattern){
		if (date==null) date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		setDayMinTime(calendar);
		return formatDate(calendar.getTime(),StringUtils.isEmpty(pattern)?Y_M_D_H_M_S_PATTERN:pattern);
	}
	public static String getDayStart(Long time,String pattern){
		if(time==null) time=System.currentTimeMillis();
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time);
		setDayMinTime(calendar);
		return formatDate(calendar.getTime(),StringUtils.isEmpty(pattern)?Y_M_D_H_M_S_PATTERN:pattern);
	}
	public static String getDayEnd(Date date,String pattern){
		if (date==null) date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		setDayMaxTime(calendar);
		return formatDate(calendar.getTime(),StringUtils.isEmpty(pattern)?Y_M_D_H_M_S_PATTERN:pattern);
	}
	public static String getDayEnd(Long time,String pattern){
		if(time==null) time=System.currentTimeMillis();
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time);
		setDayMaxTime(calendar);
		return formatDate(calendar.getTime(),StringUtils.isEmpty(pattern)?Y_M_D_H_M_S_PATTERN:pattern);
	}


    public static String getDateTime() {
        return getDate(YMD_PATTERN);
    }
	/**
	 * Auth: dingpengfei
	 * Date: 2017/10/23 16:50
	 * Title: 检查日期与当前日期 差多少天
	 * Param:
	 * Return:
	 **/
    public static Long getDayDifference(String start,String end,String pattern){
    	SimpleDateFormat sdf=new SimpleDateFormat(StringUtils.isEmpty(pattern)?DEFAULT_PATTERN:pattern);
		try {
			Calendar cal1=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			cal1.setTime(sdf.parse(start));
			setDayMinTime(cal1);
			cal2.setTime(sdf.parse(end));
			setDayMinTime(cal2);
			return (cal2.getTimeInMillis()-cal1.getTimeInMillis())/ONE_DAY_MILLSECONDS;
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage());
			return null;
		}
	}
	public static void main(String[] args) {

	}

    public static Boolean checkDateInRange(String start, String end, Long currentTime) {
    	if(com.saas.utils.string.StringUtils.isEmpty(start)||com.saas.utils.string.StringUtils.isEmpty(end)||currentTime==null){
    		return false;
		}

		try {
			Calendar calendarStart=Calendar.getInstance();
			calendarStart.setTime(DateUtils.parseDate(start,parsePatterns));

			Calendar calendarEnd=Calendar.getInstance();
			calendarEnd.setTime(DateUtils.parseDate(end,parsePatterns));

			return currentTime>=calendarStart.getTimeInMillis()&&currentTime<=calendarEnd.getTimeInMillis();
		} catch (ParseException e) {
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}


	public static boolean isValidDate(String str,String pattern) {
		boolean convertSuccess=true;
		SimpleDateFormat format = new SimpleDateFormat(StringUtils.isEmpty(pattern)?"yyyy/MM/dd HH:mm":pattern);
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess=false;
		}
		return convertSuccess;
	}

}

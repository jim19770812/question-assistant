package com.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作公共类
 * @author jim
 *
 */
public class DateUtil {
	//public static final String STRTIME = "010101";
	public final static String LONG_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String SHORT_DATE_TIME_FORMAT = "yy-MM-dd HH:mm:ss";
	public final static String FULL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	public final static String DATE_FORMAT = "yyyy-MM-dd";
	//public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String TIME_FORMAT = "HH:mm:ss";
	public final static long DAY_MILLISECOND = 24L * 60L * 60L * 1000L;
	private final static String MINI_DATE_STR = "1899-12-31 23:59:59";
	public final static Date MINI_DATE = DateUtil.getMiniDate();

	/**
	 * 根据指定格式，解释字符串，生成日期对象
	 * @param src 日期字符串
	 * @param format 格式化字符串
	 * @return Date
	 */
	public synchronized static Date parseDate(String src, String format) throws Exception {
		Date date = null;
		if (src == null || src.equals(""))
			return null;
		date = new SimpleDateFormat(format).parse(src);
		return date;
	}

	/**
	 * 将未制定格式的字符串转换成日期对象，高度自适应日期字符串格式，支持长日期/短日期和日期时间格式
	 * @param src 日期字符串
	 * @return Date
	 */
	public synchronized static Date parseDate(String src) throws Exception {
        Date result=null;
        String parse=src;
        parse=parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
        parse=parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse=parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
        DateFormat format=new SimpleDateFormat(parse);
        result=format.parse(src);
        return result;
	}

	public synchronized static Timestamp parseTimestamp(String src) throws Exception {
		Date dt = parseDate(src);
		return new Timestamp(dt.getTime());
	}

	private synchronized static Date getMiniDate() {
		try{
			return parseDate(MINI_DATE_STR, LONG_DATE_TIME_FORMAT);
		}catch(Exception e){
			return new Date();
		}
	}
	/**
	 * 根据指定格式，解释字符串，生成日期对象
	 * 
	 * @param src
	 *            日期字符串
	 * @param format 格式化字符串
	 * @return 不能转化时返回Null
	 */
	public synchronized static Timestamp parseTimestamp(String src,String format) throws Exception {
		Date date = null;
		if (src == null || src.equals(""))
			return null;
		date = new SimpleDateFormat(format).parse(src);
		return new Timestamp(date.getTime());
	}

	/**
	 * 把传入的日期，按指定格式返回其字符串形式
	 * 
	 * @param d
	 *            日期对象
	 * @param format
	 *            格式字符串
	 * @return 不能转换返回Null
	 */
	public synchronized static String dateToString(Date d, String format) {
		String str = null;
		if (d == null) {
			return null;
		}else{
			str = new SimpleDateFormat(format).format(d);
		}
		return str;
	}

	/**
	 * 把传入的日期，按指定格式返回其字符串形式
	 * 
	 * @param d
	 *            日期对象
	 * @param format
	 *            格式字符串
	 * @return 不能转换返回Null
	 */
	public synchronized static String dateToString(Timestamp d, String format) {
		String str = null;
		if (d == null) {
			return null;
		} else {
			str = new SimpleDateFormat(format).format(new Date(d.getTime()));
		}
		return str;
	}

	/**
	 * 获取当前时间yyyy-MM-dd HH:mm:ss字符串形式
	 * 
	 * @return
	 */
	public synchronized static String getCurrentDate() {
		return getCurrentDate(DateUtil.LONG_DATE_TIME_FORMAT);
	}

	/**
	 * 获取当前时间指定格式的字符串形式 格式错误按yyyy-MM-dd HH:mm:ss
	 * 
	 * @param sFormat
	 *            sFormat 日期格式字符串
	 * @return String 转换成指定格式的字符串形式
	 */
	public synchronized static String getCurrentDate(String sFormat) {
		if (sFormat == null || sFormat.equals(""))
			sFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		return formatter.format(new Date());
	}

	/**
	 * 根据模板获取指定的日期时间字符串
	 * @param dt
	 * @param format 可以传入 yyyy-MM-dd hh:mm:ss
	 * @return 字符串
	 */
	public synchronized static String getDateTimeString(Date dt, String format){
		if (dt == null){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(dt);
	}
	/**
	 * 返回年份数值
	 * 
	 * @return
	 */
	public synchronized static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 将日期转换成季度（1，2，3，4）
	 * 
	 * @param date
	 * @return
	 */
	public synchronized static  int getQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		if (month == 0 || month == 1 || month == 2)
			return 1;
		else if (month == 3 || month == 4 || month == 5)
			return 2;
		else if (month == 6 || month == 7 || month == 8)
			return 3;
		else if (month == 9 || month == 10 || month == 11)
			return 4;
		return 0;
	}

	/**
	 * 将日期转换成季度（Q1,Q2,Q3,Q4）
	 * @param date
	 * @return
	 */
	public synchronized static  String getQuarterString(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		if (month == 0 || month == 1 || month == 2)
			return "Q1";
		else if (month == 3 || month == 4 || month == 5)
			return "Q2";
		else if (month == 6 || month == 7 || month == 8)
			return "Q3";
		else if (month == 9 || month == 10 || month == 11)
			return "Q4";
		return "";
	}

	/**
	 * 返回4位年份字符串
	 * 
	 * @return
	 */
	public synchronized static String getYearString(Date date) {
		return Integer.toString(getYear(date));
	}

	/**
	 * 返回月份数值
	 * 
	 * @return int
	 */
	public synchronized static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回2位月份字符串
	 * 
	 * @return string
	 */
	public synchronized static String getMonthString(Date date) {
		return String.format("%02d", getMonth(date));
	}

	/**
	 * 返回星期数值
	 * @param date
	 * @return
	 */
	public synchronized static int getWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 返回2位星期字符串
	 * @param date
	 * @return
	 */
	public synchronized static String getWeekString(Date date){
		return String.format("%02d", getWeek(date));
	}
	/**
	 * 返回日数值
	 * 
	 * @return int
	 */
	public synchronized static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回2位日字符串
	 * 
	 * @return String
	 */
	public synchronized static String getDayString(Date date) {
		return String.format("%02d", getDay(date));
	}

	/**
	 * 返回小时值
	 * 
	 * @return int
	 */
	public synchronized static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public synchronized static Date setHour(Date date, int val) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, val);
		return cal.getTime();
	}

	/**
	 * 返回2位小时字符串
	 * 
	 * @return String
	 */
	public synchronized static String getrHourString(Date date) {
		return String.format("%02d", getHour(date));
	}

	/**
	 * 返回分钟值
	 * 
	 * @return int
	 */
	public synchronized static int getMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);
	}

	public synchronized static Date setMinute(Date date, int val) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, val);
		return cal.getTime();
	}

	/**
	 * 返回2位分钟字符串
	 * 
	 * @return String
	 */
	public synchronized static String getMinuteString(Date date) {
		return String.format("%02d", getMinute(date));
	}

	/**
	 * 返回秒值
	 * 
	 * @return int
	 */
	public synchronized static int getSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);
	}

	public synchronized static Date setSecond(Date date, int val) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, val);
		return cal.getTime();
	}

	public synchronized static Date setMillisecond(Date date, int val) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MILLISECOND, val);
		return cal.getTime();
	}

	/**
	 * 返回2位秒字符串
	 * 
	 * @return String
	 */
	public synchronized static String getSecondString(Date date) {
		return String.format("%02d", getSecond(date));
	}

	/**
	 * 返回年月日字符串格式如 1997-07-01
	 * 
	 * @return Sting
	 */
	public synchronized static String getDateStr(Date date) {
		//this.dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 返回Date实例的日期部分
	 * @param date
	 * @return
	 */
	public synchronized static Date getDatePart(Date date){
		Calendar calTemp = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calTemp.setTime(date);
		calendar.clear();
		calendar.set(calTemp.get(Calendar.YEAR), calTemp.get(Calendar.MONTH), calTemp.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 返回Date实例的时间部分
	 * @param date
	 * @return
	 */
	public synchronized static Date getTimePart(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.clear(Calendar.YEAR);
		cal.clear(Calendar.MONTH);
		cal.clear(Calendar.DAY_OF_MONTH);
		return cal.getTime();
	}
	/**
	 * 返回时分秒字符串 01:01:01
	 * 
	 * @return Sting
	 */
	public synchronized static String getTimeString(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/**
	 * 获取两个日期中较大的一个
	 * @param date1
	 * @param date2
	 * @return
	 */
	public synchronized static Date maxDate(Date date1, Date date2){
		if (date1 == null || date2 == null)
			return null;
		else if (date1 != null && date2 == null)
			return date1;
		else if (date1 == null && date2 != null)
			return date2;
		else
			return date1.compareTo(date2) >= 0 ? date1 : date2;
	}

	/**
	 * 获取两个日期中较小的一个
	 * @param date1
	 * @param date2
	 * @return
	 */
	public synchronized static Date minDate(Date date1, Date date2){
		if (date1 == null || date2 == null)
			return null;
		else if (date1 != null && date2 == null)
			return date1;
		else if (date1 == null && date2 != null)
			return date2;
		else
			return date1.compareTo(date2) <= 0 ? date1 : date2;
	}

	/**
	 * 得到两个日期间隔的天数（前一日期减去后一日期）
	 * @param date1
	 * @param date2
	 * @return long
	 */
	public static long getDateDiff(Date date1, Date date2){
		if (date1 == null || date2 == null){
			return 0;
		}
		long result = (date1.getTime() - date2.getTime()) / DAY_MILLISECOND;
		return result;
	}

	/**
	 * 获取自然日的起始时间
	 * @param date
	 * @return
	 */
	public static Date getStartDate(final Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取自然日的截止时间
	 * @param date
	 * @return
	 */
	public static Date getEndDate(final Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	public static Date incDay(final Date date, final int dayAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayAmount);
		return calendar.getTime();		
	}

	public static Date incHour(final Date date, final int hourAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hourAmount);
		return calendar.getTime();		
	}

	public static Date incMinute(final Date date, final int minuteAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteAmount);
		return calendar.getTime();		
	}

	public static Date incSecond(final Date date, final int secondAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, secondAmount);
		return calendar.getTime();		
	}

	public static Date incWeek(final Date date, final int weekAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_MONTH, weekAmount);
		return calendar.getTime();		
	}

	public static Date incMonth(final Date date, final int monthAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthAmount);
		return calendar.getTime();		
	}

	public static Date incQuarter(final Date date, final int quarterAmount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, quarterAmount * 3);
		return calendar.getTime();		
	}

	/**
	 * 只保留日期部分，去除小时，分钟，秒，毫秒等值
	 * @param date
	 * @return
	 */
	public static Date trunc(final Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

    /**
     * 日期类型yyyy-mm-dd转字符串格式日期"yyyy-mm-dd"
     * @param date
     * @return
     */
    public static String date2DString(final Date date){
        return DateUtil.dateToString(date, DateUtil.DATE_FORMAT);
    }
    /**
     * 日期类型yyyy-mm-dd hh:nn:ss转日期时间格式字符串"yyyy-mm-dd hh:nn:ss"
     * @param date
     * @return
     */
    public static String date2DTString(final Date date){
        return DateUtil.dateToString(date, DateUtil.LONG_DATE_TIME_FORMAT);
    }

    public static String date2ChineseDString(final Date date){
        return DateUtil.dateToString(date, "yyyy年MM月dd日");
    }

    public static String date2ChineseDTString(final Date date){
        return DateUtil.dateToString(date, "yyyy年MM月dd日 HH:mm:ss");
    }

    /**
     * 日期字符串"yyyymmdd"(其中日期部分可选)转换成日期类型
     * @return
     * @throws Exception 
     */
    public static Date D2Date(final String dt) throws Exception{
        byte[] bytes = dt.getBytes();
        if (bytes.length != 8)
            throw new RuntimeException("执行D2Date是出错，日期必须符合yyyyMMdd格式！");
        return DateUtil.parseDate(dt, "yyyyMMdd");
    }
    /*
     * 返回当前日期的字符串格式
     */
	public synchronized static String getStrByDate() {
		//this.dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		Date date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static void main(String[] args) {
		System.out.println(DateUtil.getStrByDate());
	}

	public static Date setYear(Date date, int year){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static Date setMonth(Date date, int month){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, month);
		return cal.getTime();
	}

	public static Date setDay(Date date, int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
}

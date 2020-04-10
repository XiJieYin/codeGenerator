package com.gx.dataI.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	public static String YYYYMMDD = "yyyyMMdd";
	public static String YYYY_MM = "yyyy-MM";
	public static String HH_MM_SS = "HH:mm:ss";
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static String YYMMDDHHMM = "yyMMddHHmm";
	
	
	public static Date string2Date(String value, String format){
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			date = df.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		return date;
	}
	
	public static Date formatDate(String value, String format) 
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.parse(value);
	}
	
	/**
	 * 获取结束时间，eg: 2011-02-28 23:59:59
	 * @param date
	 * @return 返回Date
	 */
	public static Date getEndDate4Date(Date date){
		Date endDate = string2Date(getEndDate4String(date), YYYY_MM_DD_HH_MM_SS);
		return endDate;
	}
	
	/**
	 * 获取结束时间，eg: 2011-02-28 23:59:59
	 * @param date
	 * @return 返回String
	 */
	public static String getEndDate4String(Date date){
		String endDate = date2String(date, YYYY_MM_DD);
		endDate += " 23:59:59";
		return endDate;
	}
	
	/**
	 * 获取开始时间，eg: 2011-02-28 00:00:00
	 * @param date
	 * @return 返回Date
	 */
	public static Date getBeginDate4Date(Date date){
		Date beginDate = string2Date(getBeginDate4String(date), YYYY_MM_DD_HH_MM_SS);
		return beginDate;
	}
	
	/**
	 * 获取开始时间，eg: 2011-02-28 00:00:00
	 * @param date
	 * @return 返回String
	 */
	public static String getBeginDate4String(Date date){
		String beginDate = date2String(date, YYYY_MM_DD);
		beginDate += " 00:00:00";
		return beginDate;
	}
	/**
	 * 拼时间，eg: 2011-02-28  00:00:00
	 * @author XiaoYan
	 * @param date
	 * @return 返回Date
	 */
	public static Date getDate4String(Date date1,Date date2){
		String beginDate = date2String(date1, YYYY_MM_DD);
		String endDate = date2String(date2, HH_MM_SS);
		beginDate +=" "+endDate;
		Date date = string2Date(beginDate, YYYY_MM_DD_HH_MM_SS);
		return date;
	}
	
	
	/**
	 * 根据format格式，格式化Date
	 * @param date
	 * @param format
	 * @return 返回format格式的日期字符船
	 */
	public static String date2String(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strDate = sdf.format(date);
		return strDate;
	}
	
	/**
	 * ES用
	 * @author ZhengBaohui
	 * 2015-8-31 下午5:49:38
	 *
	 * @param dataStr
	 * @return
	 */
	public static String parse2ESDateStr(String dataStr){
		dataStr = dataStr.replace(" ", "T");
		dataStr += ".000Z";
		return dataStr;
	}
	
	/**
	 * 获取当前时间毫秒数
	 * @return
	 */
	public static long getTodayMillis(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 求两个日期相差的天数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int getTimeDifferenceOfDay(Date beginDate, Date endDate){
		int diffDay = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		try {
			beginDate = sdf.parse(sdf.format(beginDate));
			endDate = sdf.parse(sdf.format(endDate));
			
			long diffMillis = endDate.getTime() - beginDate.getTime();
			
			diffDay = Integer.parseInt(String.valueOf(diffMillis/1000/60/60/24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (diffDay+1);
	}
	
	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
	 * @param date 日历 2012-10-29 15:35:35
	 * @param field 日历字段 e.g. Calendar.DAY_OF_MONTH
	 * @param amount 时间量(负数为减去，正数为添加) e.g. -5
	 * @return
	 */
	public static Date calculateDate(Date date, int field, int amount) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.add(field, amount);
		
		return calendar.getTime();
	}
	
	/**
	 * 按日切割时间段 - 历史轨迹查询用
	 * @author ZhengBaohui
	 * 2015-8-28 下午5:37:34
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static List<Date> dataSplitByDay(Date startDate, Date endDate) throws Exception{
		if(!startDate.before(endDate)) throw new Exception("开始时间应该在结束时间之后！");
		SimpleDateFormat sfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sfDay = new SimpleDateFormat("yyyy-MM-dd");
		long step = 24 * 3600 * 1000;
		
		List<Date> dateList = new ArrayList<Date>();
		dateList.add(startDate);
		for(int i=0; ; i++){
			Date newDate = sfTime.parse(sfDay.format(startDate)+" 23:59:59");
			long n = newDate.getTime() + step*i;
			if(n >= endDate.getTime()){
				break;
			}
			dateList.add(new Date(n));
			
			newDate = sfTime.parse(sfDay.format(startDate)+" 00:00:00");
			n = newDate.getTime() + step*(i+1);
			dateList.add(new Date(n));
		}
		dateList.add(endDate);
		return dateList;
	}
	
	/**
	 * 按日期切割时间段
	 * @author ZhengBaohui
	 * 2015-9-18 下午4:15:57
	 *
	 * @param sdStr
	 * @param edStr
	 * @return
	 */
	public static List<String> dateSplitByDay(String sdStr, String edStr){
		List<String> list = new ArrayList<String>();
		try {
			SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
			Date sd = sf.parse(sdStr);
			Date ed = sf.parse(edStr);
			double between = (ed.getTime()-sd.getTime())/1000;
			double day = between/(24*3600);
			for(int i=0; i<=day; i++){
				Calendar cd = Calendar.getInstance();
				cd.setTime(sd);
				cd.add(Calendar.DATE, i);
				list.add(sf.format(cd.getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 将时间跟当前时间比较，返回文字描述
	 * @author ZhengBaohui
	 * 2016-1-6 下午5:14:52
	 *
	 * @param time
	 * @return
	 */
	public static String parseDateToWord(Date time){
		Date now = new Date();
		long howlong = now.getTime() - time.getTime();
		if(howlong < 0)
			return "今日之后";
		else{
			long year = howlong/(365*24*60*60*1000);
			if(year > 0)
				return year + " 年前";
			long month = howlong/(30*24*60*60*1000);
			if(month > 0)
				return month + " 个月前";
			long day = howlong/(24*60*60*1000);
			if(day > 0)
				return day + " 天前";
			long our = howlong/(60*60*1000);
			if(our > 0)
				return our + " 小时前";
			long min = howlong/(60*1000);
			if(min > 0)
				return min + " 分钟前";
			long sec = howlong/(1000);
			if(sec > 0)
				return sec + " 秒前";
		}
		return "";
	}
	
	public synchronized static String getSyncCurrentTime(){
		return date2String(new Date(), YYYYMMDDHHMMSSSSS);
	}
	
	public synchronized static String getSyncCurrentTime(String formate){
		return date2String(new Date(), formate);
	}

	public static void main(String[] args) {
		try {
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//			Date sd = sf.parse("2015-09-25");
//			Date ed = sf.parse("2015-10-10");
//			double between = (ed.getTime()-sd.getTime())/1000;
//			double day = between/(24*3600);
//			for(int i=1; i<day; i++){
//				Calendar cd = Calendar.getInstance();
//				cd.setTime(sd);
//				cd.add(Calendar.DATE, i);
//				System.out.println(sf.format(cd.getTime()));
//			}
			System.out.println(1731958500/(24*60*60*1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
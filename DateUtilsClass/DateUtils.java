/**
 * 
 */
package com.unicom.etax.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @author 日期工具 by sukeqiang
 * 
 */
public class DateUtils {

	public static void main(String[] args)throws Exception {
//		System.out.println(getCurrentDate());
		System.out.println(getCountDays("2015-03-04 00:00:00","2015-02-25 23:59:59"));
	}

	/**
	 * 获取当前的时间的年月字符串：2013-01
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// 得到月，因为从0开始的，所以要加1
		String monthStr = month + "";
		monthStr = monthStr.length() == 1 ? monthStr = "0" + monthStr
				: monthStr;
		String dayStr = day + "";
		dayStr = dayStr.length() == 1 ? dayStr = "0" + dayStr : dayStr;
		return year + "-" + monthStr + "-" + dayStr;
	}

	public static String getCurrentMonth() {
		return getCurrentMonth(0);
	}

	/**
	 * 获取当前的时间的年月字符串：2013-01
	 * 
	 * @return
	 */
	public static String getCurrentMonth(int type) {
		Calendar cal = Calendar.getInstance();
		// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;
		// 得到月，因为从0开始的，所以要加1
		String monthStr = month + "";
		monthStr = monthStr.length() == 1 ? monthStr = "0".concat(monthStr)
				: monthStr;
		if (type == 0)
			return String.valueOf(year).concat("-").concat(monthStr);
		else
			return String.valueOf(year).concat(monthStr);
	}

	/**
	 * 获取当前的时间的上一个月的年月字符串：2013-01
	 * 
	 * @return
	 */
	public static String getPreviosMonth() {
		Calendar cal = Calendar.getInstance();
		// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH);
		// 得到月，因为从0开始的，所以要加1
		String monthStr = month + "";
		monthStr = monthStr.length() == 1 ? monthStr = "0" + monthStr
				: monthStr;
		return year + "-" + monthStr;
	}

	/**
	 * 
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}

	/**
	 * 返回2个日期的相差的天数
	 * 
	 * @param start_date
	 * @param end_date
	 * @return
	 */
	public static long DateDiff(Date start_date, Date end_date) {
		Calendar calendar = Calendar.getInstance();
		Calendar end_calendar = Calendar.getInstance();
		// 设置日期时间
		calendar.setTime(start_date == null ? new Date() : start_date);
		end_calendar.setTime(end_date == null ? new Date() : end_date);
		long mi_time = calendar.getTimeInMillis();
		long mi_end_time = end_calendar.getTimeInMillis();
		return (mi_end_time / 1000 - mi_time / 1000) * 3600 * 24;
	}

	/**
	 * 计算startDate,endDate间隔的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getCountDays(String startDate, String endDate) {
		int iRet = 0;
		if (isValidDate(startDate) && isValidDate(endDate)) {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(
					"yyyy-MM-dd");
			try {
				Date date1 = simpledateformat.parse(startDate);
				Date date2 = simpledateformat.parse(endDate);
				iRet = (int) ((date2.getTime() - date1.getTime()) / 0x5265c00L);
				iRet = Math.abs(iRet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return iRet;
	}

	/**
	 * 取参数月的前一个月 month: yyyy-MM或yyyyMM
	 */
	public static String getPreMonth(String _month) {
		String preMonth = "";
		if (_month.length() == 6) {
			_month = _month.substring(0, 4) + "-" + _month.substring(4);
		}
		if (_month.length() >= 7) {
			if (_month.substring(5, 7).equals("01")) { // 1月
				preMonth = (Integer.parseInt(_month.substring(0, 4)) - 1)
						+ "-12";
			} else {
				int im = Integer.parseInt(_month.substring(5, 7)) - 1;
				if (im < 10) {
					preMonth = _month.substring(0, 4) + "-0" + im;
				} else {
					preMonth = _month.substring(0, 4) + "-" + im;
				}
			}
		}
		return preMonth;
	}

	/**
	 * 格式化时间
	 * 
	 * @param dDate
	 * @param format
	 * @return
	 */
	public static String formatDate(Date dDate, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(dDate);
	}

	/**
	 * 获取当前日期 格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date()).toString();
	}

	/**
	 * 判断是否为闰年
	 * 
	 * @param iYear
	 * @return
	 */
	public static boolean isLeapYear(int iYear) {
		try {
			return iYear % 4 == 0 && (iYear % 400 == 0 || iYear % 100 != 0);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断日期是否合法
	 * 
	 * @param iYear
	 * @param iMonth
	 * @param iDay
	 * @return
	 */
	public static boolean isValidDate(int iYear, int iMonth, int iDay) {
		if (iDay < 1 || iMonth < 1 || iMonth > 12) {
			return false;
		}
		if (iMonth == 2) {
			if (isLeapYear(iYear)) {
				return iDay <= 29;
			} else {
				return iDay <= 28;
			}
		} else if (iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11) {
			return iDay <= 30;
		} else {
			return iDay <= 31;
		}
	}

	/**
	 * 判断日期是否合法日期格式
	 * 
	 * @param strdDate
	 *            -2009-09-09
	 * @return
	 */
	public static boolean isValidDate(String strdDate) {
		boolean bRet = false;
		try {
			String syear = strdDate.substring(0, 4);
			String smonth = strdDate.substring(5, 7);
			String sday = strdDate.substring(8, 10);
			if (isValidDate(Integer.parseInt(syear), Integer.parseInt(smonth),
					Integer.parseInt(sday))) {
				bRet = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bRet;
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new
																		// Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}
	
	//根据日期取得星期几
	public static int getWeek(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/*时间比大小*/
	public static int timeCompare(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}
	
	/*时间比大小,只到月份*/
	public static int timeCompareToMonth(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}
	/**
	 * 通过传入自定义时间返回该时间对象
	 * @param customDate 自定义时间 如：2015-01-01
	 * @param customTime 自定义时间 如：08:00:00
	 * @param format 格式化 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String getCustomTime(String customDate,String customTime,String format)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date d=formatter.parse(customDate.concat(" ").concat(customTime));
		return formatter.format(d);
	}
	
	public static String getAfterMonth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
		String time = format.format(c.getTime());
		return time;
	}
	
	/**
	 * 获取2个日期间隔月份 by sukeqiang
	 * @param minDate
	 * @param maxDate
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate)throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}
	/**
	 * 查看下个月,如输入"2015-12",得到"2016-01"
	 * @param _month
	 * @return
	 */
	public static String getNextMonth(String _month) {
		String preMonth = "";
		if (_month.length() == 6) {
			_month = _month.substring(0, 4) + "-" + _month.substring(4);
		}
		if (_month.length() >= 7) {
			if (_month.substring(5, 7).equals("12")) { // 1月
				preMonth = (Integer.parseInt(_month.substring(0, 4)) + 1)
						+ "-01";
			} else {
				int im = Integer.parseInt(_month.substring(5, 7)) + 1;
				if (im < 10) {
					preMonth = _month.substring(0, 4) + "-0" + im;
				} else {
					preMonth = _month.substring(0, 4) + "-" + im;
				}
			}
		}
		return preMonth;
	}
	//获取当前时间,格式为:yyyy-MM-dd HH:mm:ss
	public static String getCurrentTimeToSecond(){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return format.format(new Date());
	}
}

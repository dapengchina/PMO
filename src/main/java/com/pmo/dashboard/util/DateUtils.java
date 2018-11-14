package com.pmo.dashboard.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 获取date的月份的时间范围
	 * @param date
	 * @return
	 */
	public static DateRange getMonthRange(Date date) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(date);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMaxTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(date);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	/**
	 * 获取当前季度的时间范围
	 * @return current quarter
	 */
	public static DateRange getThisQuarter() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	/**
	 * 获取昨天的时间范围
	 * @return
	 */
	public static DateRange getYesterdayRange() {
		 Calendar startCalendar = Calendar.getInstance();
		 startCalendar.add(Calendar.DAY_OF_MONTH, -1);
		 setMinTime(startCalendar);
		 
		 Calendar endCalendar = Calendar.getInstance();
		 endCalendar.add(Calendar.DAY_OF_MONTH, -1);
		 setMaxTime(endCalendar);
		 
		 return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	/**
	 * 获取当前月份的时间范围
	 * @return
	 */
	public static DateRange getThisMonth(){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	/**
	 * 获取上个月的时间范围
	 * @return
	 */
	public static DateRange getLastMonth(){
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.add(Calendar.MONTH, -1);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.MONTH, -1);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	/**
	 * 获取上个季度的时间范围
	 * @return
	 */
	public static DateRange getLastQuarter() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	private static void setMinTime(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	private static void setMaxTime(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
	}
	
	
	public final static String DEFAULT_PATTERN = "yyyy-MM-dd";
	public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(date);
    }
	
	/**
	 * 获取当月是第几季度
	 * @param month
	 * @return
	 */
	public static int getQuarterByMonth(int month) {
		//int months[] = { 0, 3, 6, 9 };
		int months[] = { 1, 2, 3, 4 };
		if (month >= 0 && month <= 2) // 1-3月;0,1,2
			return months[0];
		else if (month >= 3 && month <= 5) // 4-6月;3,4,5
			return months[1];
		else if (month >= 6 && month <= 8) // 7-9月;6,7,8
			return months[2];
		else
			// 10-12月;10,11,12
			return months[3];
	}
	
	public static void main(String[] args) {
		
		DateRange currentQuarter = getThisQuarter();
		System.out.println("当前季度的时间范围： "+DateUtils.format(currentQuarter.getStart())+" - "+DateUtils.format(currentQuarter.getEnd()));
		
		
		DateRange yesterdayRange = getYesterdayRange();
		System.out.println("昨天的时间范围: "+DateUtils.format(yesterdayRange.getStart())+" - "+DateUtils.format(yesterdayRange.getEnd()));
		
		DateRange thisMonth = getThisMonth();
		System.out.println("当前月份的时间范围: "+DateUtils.format(thisMonth.getStart())+" - "+DateUtils.format(thisMonth.getEnd()));
		
		DateRange lastMonth = getLastMonth();
		System.out.println("上个月的时间范围: "+DateUtils.format(lastMonth.getStart())+" - "+DateUtils.format(lastMonth.getEnd()));
		
		DateRange lastQuarter = getLastQuarter();
		System.out.println("上个季度的时间范围: "+DateUtils.format(lastQuarter.getStart())+" - "+DateUtils.format(lastQuarter.getEnd()));
		
	}

}

package com.jiayuan.huawei.hwframeworklib.utis;

import android.annotation.SuppressLint;

import com.jiayuan.huawei.hwframeworklib.BaseApp;
import com.jiayuan.huawei.hwframeworklib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormUtils {
	private final static long MINUTE = 60 * 1000;// 1分钟
	private final static long HOUR = 60 * MINUTE;// 1小时
	private final static long DAY = 24 * HOUR;// 1天
	private final static long WEEK = 7 * DAY;// 周
	private final static long MONTH = 31 * DAY;// 月
	private final static long YEAR = 12 * MONTH;// 年

	/** hh:mm */
	public static final String TIME_FORMAT_17 = "hh:mm";
	/** yyyy年MM月dd日 */
	public static final String TIME_FORMAT_18 = "yyyy年MM月dd日";
	/** yyyy-MM-dd */
	public static final String TIME_FORMAT_19 = "yyyy-MM-dd";

	public static String formatTime(String formater) {
		return formatTime(formater, new Date());
	}


	public static String formatTime(String formater, Long time) {
		return formatTime(formater, new Date(time));
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatTime(String formater, Date date) {
		return new SimpleDateFormat(formater).format(date);
	}


	@SuppressLint("SimpleDateFormat")
	public static String formatTime(long convertime) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowtime = formatTime("yyyy-MM-dd HH:mm:ss", new Date());
		String s = "";
		try {
			long result = (d.parse(nowtime).getTime() - convertime * 1000);
			long second = result / 1000;
			long minute = result / 60000;
			long hour = result / 3600000;
			if (second < 60) {
				s = "�ո�";
				return s;
			} else if (minute <= 60 && minute >= 1) {
				s = minute + "��ǰ";
				return s;
			} else if (hour < 24 && hour >= 1) {
				s = hour + "Сʱǰ";
				return s;
			} else if (hour >= 24) {
				s = formatTime("MM��dd��", convertime * 1000);
				return s;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 显示规则：
	 * 当天的 区分凌晨上午下午
	 * 前一天 显示 昨天 凌晨 3：02的格式
	 * 前一天以后 小于7天的 显示 星期一 中午  2：04
	 * 超过一个星期的日期 显示    2015年5月6日 下午 2：06
	 *
	 * @author duyuan
	 *
	 * @param time
	 * @return
	 */
	public static String showChatTime(long time) {
		String timeStr = "";
//		//自己用来测试时间
//		SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_15);
//		Date reDate = null;
//		try {
//			reDate = format.parse("2015-12-02 05:58");
//			time = reDate.getTime();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		long todayStartTimestamp = getTodayStartTimestamp();
		long todayDiff = System.currentTimeMillis() - todayStartTimestamp;
		long yesterdayStartTimestamp = getYesterdayStartTimestamp();
		long yesterdayDiff = System.currentTimeMillis() - yesterdayStartTimestamp;

		long diff = System.currentTimeMillis() - time;
		Date date = new Date(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (diff <= todayDiff) { // 当天

		} else if (diff > todayDiff && diff <= yesterdayDiff) { // 昨天
			timeStr = " 昨天";
		} else if (diff > yesterdayDiff && diff <= WEEK) { // 周几
			timeStr = " " + getWeek(date);
		} else if (diff > WEEK) {
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_18);
			timeStr = " " + sdf.format(date);
		}

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		SimpleDateFormat ss = new SimpleDateFormat(TIME_FORMAT_17);//12小时制
		if(cal.get(Calendar.AM_PM) == 0){  // am
			if (hour <= 5) {
				timeStr = timeStr + "凌晨 " + ss.format(date);
			} else { // pm
				timeStr = timeStr + "上午 " + ss.format(date);
			}
		}else{
			timeStr = timeStr + "下午 " + ss.format(date);
		}

		return timeStr;

	}
	/**
	 * 功能描述：获取星期几
	 *
	 * @author duyuan
	 *
	 * @param date
	 * @return
	 */
	private static String getWeek(Date date) {
		String Week = "星期";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch(c.get(Calendar.DAY_OF_WEEK)){
			case 1:
				Week += "天";
				break;
			case 2:
				Week += "一";
				break;
			case 3:
				Week += "二";
				break;
			case 4:
				Week += "三";
				break;
			case 5:
				Week += "四";
				break;
			case 6:
				Week += "五";
				break;
			case 7:
				Week += "六";
				break;
			default:
				break;
		}
		return Week;
	}
	/**
	 * 获取今天起始时间的时间戳
	 * @return
	 */
	public static long getTodayStartTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 获取昨天起始时间的时间戳
	 * @return
	 */
	private static long getYesterdayStartTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
//		calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTimeInMillis();
	}

	/**
	 * 显示规则：
	 * 刚刚：一分钟以内
	 * 1分钟前到60分钟	显示具体分钟数前
	 * 1小时前到24小时	显示具体小时数前
	 * 1天前到-30天之前	显示具体天数前
	 * 1-12个月			显示具体几个月前
	 * 大约一年小于两年	显示一年前
	 * 大约2年			显示几年前
	 *
	 * @author duyuan
	 *
	 * @param time
	 * @return
	 */
	public static String showDynamicTime(long time) {
		String timeStr = "";
		long diff = System.currentTimeMillis() - time;

		if (diff < MINUTE) { // 一分钟以内
			timeStr = "刚刚";
		} else if (diff >= MINUTE && diff <= HOUR) { // 1分钟前到60分钟
			timeStr = (diff / MINUTE) + BaseApp.context.getResources().getString(
					R.string.minute_string);
		} else if (diff > HOUR && diff <= DAY) { // 1小时前到24小时
			timeStr = (diff / HOUR)
					+ BaseApp.context.getResources().getString(
					R.string.hour_string);
		} else if (diff > DAY && diff <= MONTH) { // 1天前到-30天之前
			timeStr = (diff / DAY)
					+ BaseApp.context.getResources().getString(
					R.string.day_string);
		} else if (diff > MONTH && diff <= YEAR) { // 1-12个月
			timeStr = (diff / MONTH)
					+ BaseApp.context.getResources().getString(
					R.string.month_string);
		} else if (diff > YEAR) { // 显示几年前
			timeStr = (diff / YEAR)
					+ BaseApp.context.getResources().getString(
					R.string.year_string);
		}

		return timeStr;
	}


	public static String formatDatebyTime(long time){
		Date nowTime = new Date(time);
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy年 MM月dd日 HH:mm");
		return timeFormat.format(nowTime);
	}
}

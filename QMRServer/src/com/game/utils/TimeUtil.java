package com.game.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.quartz.CronExpression;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;

public class TimeUtil {

	private static Logger log = Logger.getLogger(TimeUtil.class);
	private static String reg = "\\[([\\d|\\*]+)\\]\\[([\\d|\\*]+)\\](\\[([\\d|\\*|\\-|\\,]+)\\])?(\\[([w|W|\\d|\\-|\\,|\\*]+)\\])?(\\[(\\d+):(\\d+)-(\\d+):(\\d+)\\])";
	//private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd-HHmmss");

	/**
	 * 开服时间表达式1-6;[h0-100]
	 *
	 * @param express
	 * @return
	 */
	public static boolean isOpenServerTimer(String express) {
//		Date dateByString = getDateByString("2012-10-7 00:00:00");
//		long openServerTime=dateByString.getTime();
		String[] split = express.split(Symbol.FENHAO_REG);
		for (String string : split) {
			if (!string.contains("-")) {
				break;
			}
			String[] exp = string.split("-");
			if (exp.length != 2) {
				throw new IllegalStateException(express);
			}
			String startExp = exp[0];
			String endExp = exp[1];
			if (startExp.startsWith("h") || startExp.startsWith("H")) {
				startExp = startExp.replace("h", "");
				startExp = startExp.replace("H", "");
				int hour = getOpenAreaHour();
				int beginTime = Integer.parseInt(startExp);
				int endTime = Integer.parseInt(endExp);
				if (hour >= beginTime && hour <= endTime) {
					return true;
				}
			} else {
				int day = getOpenAreaDay();
				int beginTime = Integer.parseInt(startExp);
				int endTime = Integer.parseInt(endExp);
				if (day >= beginTime && day <= endTime) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 转译时间格式
	 *
	 * @param time 表中所填格式时间字符串
	 * @return
	 */
	public static List<String> translate(String time) {
		List<String> times = new ArrayList<String>();
		try {
			PatternCompiler complier = new Perl5Compiler();
			PatternMatcher matcher = new Perl5Matcher();
			Pattern patternForLink = complier.compile(reg,
				Perl5Compiler.CASE_INSENSITIVE_MASK);

			PatternMatcherInput input = new PatternMatcherInput(time);
			if (matcher.matches(input, patternForLink)) {
				MatchResult match = matcher.getMatch();

				StringBuffer buf = new StringBuffer();
				// 秒
				buf.append("0 ");
				// 分
				buf.append(match.group(9)).append(" ");
				// 小时
				buf.append(match.group(8)).append(" ");
				// 日
				if (match.group(4) != null) {
					if (("*").equals(match.group(4)) && match.group(6) != null
						&& !("*").equals(match.group(6))) {
						buf.append("?").append(" ");
					} else {
						buf.append(match.group(4)).append(" ");
					}
				} else {
					if (match.group(6) != null && !("*").equals(match.group(6))) {
						buf.append("?").append(" ");
					} else {
						buf.append("*").append(" ");
					}
				}
				// 月
				buf.append(match.group(2)).append(" ");
				// 星期
				if (match.group(6) != null) {
					if (("*").equals(match.group(6)) && match.group(4) != null) {
						buf.append("?").append(" ");
					} else {
						buf.append(replaceMonth(match.group(6))).append(" ");
					}
				} else {
					if (match.group(4) != null) {
						buf.append("?").append(" ");
					} else {
						buf.append("*").append(" ");
					}
				}
				// 年
				buf.append(match.group(1)).append(" ");
				// 时间开始时间
				times.add(buf.toString());

				buf = new StringBuffer();
				// 秒
				buf.append("59 ");
				// 分
				buf.append(match.group(11)).append(" ");
				// 小时
				buf.append(match.group(10)).append(" ");
				// 日
				if (match.group(4) != null) {
					if (("*").equals(match.group(4)) && match.group(6) != null
						&& !("*").equals(match.group(6))) {
						buf.append("?").append(" ");
					} else {
						buf.append(match.group(4)).append(" ");
					}
				} else {
					if (match.group(6) != null && !("*").equals(match.group(6))) {
						buf.append("?").append(" ");
					} else {
						buf.append("*").append(" ");
					}
				}
				// 月
				buf.append(match.group(2)).append(" ");
				// 星期
				if (match.group(6) != null) {
					if (("*").equals(match.group(6)) && match.group(4) != null) {
						buf.append("?").append(" ");
					} else {
						buf.append(replaceMonth(match.group(6))).append(" ");
					}
				} else {
					if (match.group(4) != null) {
						buf.append("?").append(" ");
					} else {
						buf.append("*").append(" ");
					}
				}
				// 年
				buf.append(match.group(1)).append(" ");
				// 时间开始时间
				times.add(buf.toString());
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return times;
	}

	public static Date getAfterDayTime(Date d, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 星期替换
	 *
	 * @param week
	 * @return
	 */
	private static String replaceMonth(String week) {
		// 星期一 MON 星期二 TUE 星期三 WED 星期四 THU 星期五 FRI 星期六 SAT 星期天 SUN
		return week.replaceAll("w1", "MON").replaceAll("w2", "TUE").replaceAll("w3", "WED").replaceAll("w4", "THU").replaceAll("w5", "FRI").replaceAll("w6", "SAT").replaceAll("w7", "SUN");
	}

	/**
	 * 计算时间是否在指定时间段呢
	 *
	 * @param time
	 * @param times
	 * @return
	 */
	public static boolean isSatisfiedBy(String time, long times) {
		return isSatisfiedBy(time, new Date(times));
	}

	/**
	 * 计算时间是否在指定时间段呢
	 *
	 * @param time
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isSatisfiedBy(String time, Date date) {

		try {
			PatternCompiler complier = new Perl5Compiler();
			PatternMatcher matcher = new Perl5Matcher();
			Pattern patternForLink = complier.compile(reg,
				Perl5Compiler.CASE_INSENSITIVE_MASK);

			String[] times = time.split(Symbol.FENHAO_REG);
			for (int i = 0; i < times.length; i++) {
				PatternMatcherInput input = new PatternMatcherInput(times[i]);
				if (matcher.matches(input, patternForLink)) {
					MatchResult match = matcher.getMatch();

					StringBuffer buf = new StringBuffer();
					// 秒
					buf.append("* ");
					// 分
					buf.append("* ");
					// 小时
					buf.append("* ");
					// 日
					if (match.group(4) != null) {
						if (("*").equals(match.group(4))
							&& match.group(6) != null
							&& !("*").equals(match.group(6))) {
							buf.append("?").append(" ");
						} else {
							buf.append(match.group(4)).append(" ");
						}
					} else {
						if (match.group(6) != null
							&& !("*").equals(match.group(6))) {
							buf.append("?").append(" ");
						} else {
							buf.append("*").append(" ");
						}
					}
					// 月
					buf.append(match.group(2)).append(" ");
					// 星期
					if (match.group(6) != null) {
						if (("*").equals(match.group(6))
							&& match.group(4) != null) {
							buf.append("?").append(" ");
						} else {
							buf.append(replaceMonth(match.group(6))).append(" ");
						}
					} else {
						if (match.group(4) != null) {
							buf.append("?").append(" ");
						} else {
							buf.append("*").append(" ");
						}
					}
					// 年
					buf.append(match.group(1)).append(" ");

					CronExpression exp = new CronExpression(buf.toString());
					// 年，月，日，星期检查成功
					if (exp.isSatisfiedBy(date)) {
						// 开始时间
						Calendar begin = Calendar.getInstance();
						begin.set(Calendar.HOUR_OF_DAY,
							Integer.parseInt(match.group(8)));
						begin.set(Calendar.MINUTE,
							Integer.parseInt(match.group(9)));
						begin.set(Calendar.SECOND, 0);
						begin.add(Calendar.SECOND, -1);
						// 结束时间
						Calendar end = Calendar.getInstance();
						end.set(Calendar.HOUR_OF_DAY,
							Integer.parseInt(match.group(10)));
						end.set(Calendar.MINUTE,
							Integer.parseInt(match.group(11)));
						end.set(Calendar.SECOND, 59);
						end.add(Calendar.SECOND, 1);

						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR_OF_DAY, date.getHours());
						cal.set(Calendar.MINUTE, date.getMinutes());
						cal.set(Calendar.SECOND, date.getSeconds());
						// 判断时间是否符合
						if (cal.after(begin) && cal.before(end)) {
							return true;
						}
					}
				}
			}
		} catch (ParseException e) {
			log.error(e, e);
		} catch (NumberFormatException e) {
			log.error(e, e);
		} catch (Exception e) {
			log.error(e, e);
		}

		return false;
	}

	/**
	 * 计算现在是否在指定时间段内
	 *
	 * @param time
	 * @return
	 */
	public static boolean isNowSatisfiedBy(String time) {
		return isSatisfiedBy(time, System.currentTimeMillis());
	}

	/**
	 * 获取指定时间到现在的时间数（毫秒）
	 *
	 * @param time
	 * @return
	 */
	public static long getDurationToNow(long time) {
		return System.currentTimeMillis() - time;
	}

	/**
	 * 获取指定时间到现在的时间数（秒）
	 *
	 * @param time
	 * @return 秒
	 */
	public static int getDurationToNowSec(long time) {
		return (int) (getDurationToNow(time) / 1000);
	}

	/**
	 * 判断两个时间中间所差天数
	 *
	 * @param time
	 * @param time2
	 * @return
	 */
	public static int getBetweenDays(long time1, long time2) {
		Calendar instance1 = Calendar.getInstance();
		instance1.setTimeInMillis(time1);
		instance1.set(Calendar.HOUR_OF_DAY, 0);
		instance1.set(Calendar.MINUTE, 0);
		instance1.set(Calendar.SECOND, 0);
		instance1.set(Calendar.MILLISECOND, 0);
		Calendar instance2 = Calendar.getInstance();
		instance2.setTimeInMillis(time2);
		instance2.set(Calendar.HOUR_OF_DAY, 0);
		instance2.set(Calendar.MINUTE, 0);
		instance2.set(Calendar.SECOND, 0);
		instance2.set(Calendar.MILLISECOND, 0);
		return (int) ((instance1.getTimeInMillis() - instance2.getTimeInMillis()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 判断两个时间是否在同一天
	 *
	 * @param time
	 * @param time2
	 * @return
	 */
	public static boolean isSameDay(long time, long time2) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		int d1 = instance.get(Calendar.DAY_OF_YEAR);
		int y1=instance.get(Calendar.YEAR);
		instance.setTimeInMillis(time2);
		int d2 = instance.get(Calendar.DAY_OF_YEAR);
		int y2=instance.get(Calendar.YEAR);
		return d1== d2&&y1==y2;
	}

	/**
	 * 判断是否在今天几点之后 true为大于指定时间
	 *
	 * @param hour
	 * @return
	 */
	public static boolean isAfterHourOfCurrentDay(int hour, long time) {
		long currentTimeMillis = System.currentTimeMillis();
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(currentTimeMillis);
		instance.set(Calendar.HOUR_OF_DAY, hour);
		instance.set(Calendar.MINUTE, 0);
		instance.set(Calendar.SECOND, 0);
		long timeInMillis = instance.getTimeInMillis();
		return time - timeInMillis > 0;
	}

	/**
	 * 指定时间的年份
	 *
	 * @param time
	 * @return
	 */
	public static int getYear(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.YEAR);
	}

	/**
	 * 指定时间的月份,0-11
	 *
	 * @param time
	 * @return
	 */
	public static int getMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.MONTH);
	}

	/**
	 * 获取日期
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取小时
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfHour(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取分钟
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfMin(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.MINUTE);
	}
	
	/**
	 * 获取秒
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfSecond(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.SECOND);
	}
	/**
	 * 获取指定时间 是一月内的第几周
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfWeekInMonth(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获取星期几
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfWeek(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		int i = instance.get(Calendar.DAY_OF_WEEK);
		if (i == 1) {
			return 7;
		} else {
			i -= 1;
		}
		return i;
	}

	/**
	 * 获取一年内的第几天
	 *
	 * @param time
	 * @return
	 */
	public static int getDayOfYear(long time) {
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(time);
		return instance.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取最近时间
	 *
	 * @param time
	 * @return
	 */
	public static Date getNextValidTime(String time) {
		try {
			String[] times = time.split(Symbol.FENHAO_REG);
			Date date = null;
			for (int i = 0; i < times.length; i++) {
				List<String> list = TimeUtil.translate(times[i]);
				CronExpression exp = new CronExpression(list.get(0));
				Date temp = exp.getNextValidTimeAfter(new Date());
				if (date == null || temp.before(date)) {
					date = temp;
				}
			}
			return date;
		} catch (ParseException e) {
			log.error(e);
		} catch (NumberFormatException e) {
			log.error(e);
		}
		return null;
	}

	/**
	 * 获取1970至今的天数 （计数会在在每天指定的小时+1，用来判断每天X点清数据之类的）
	 *
	 * @param hour 每天第X个小时+1
	 * @return
	 */
	public static int GetCurDay(int hour) {
		TimeZone zone = TimeZone.getDefault();	//默认时区
		long s = System.currentTimeMillis() / 1000 + hour * 3600;
		if (zone.getRawOffset() != 0) {
			s = s + zone.getRawOffset() / 1000;
		}
		s = s / 86400;
		return (int) s;
	}

	/**
	 * 获取1970至今的时间, 1获取秒，2 分钟，3小时，4天数,5周数
	 *
	 * @param x
	 * @return
	 */
	public static long GetCurTimeInMin(int x, long time) {
		TimeZone zone = TimeZone.getDefault();	//默认时区
		long s = time / 1000;
		if (zone.getRawOffset() != 0) {
			s = s + zone.getRawOffset() / 1000;
		}
		switch (x) {
			case 1:
				break;
			case 2:
				s = s / 60;
				break;
			case 3:
				s = s / 3600;
				break;
			case 4:
				s = s / 86400;
				break;
			case 5:
				s = s / 86400 + 3;// 补足天数，星期1到7算一周
				s = s / 7;
				break;
			default:
				break;
		}
		return s;
	}

	public static long GetCurTimeInMin(int x) {
		return GetCurTimeInMin(x, System.currentTimeMillis());
	}

	/**
	 * 得到开区天数
	 *
	 */
	public static int getOpenAreaDay(Player player) {
		Date time = WServer.getGameConfig().getServerTimeByPlayer(player);
		long zday = GetCurTimeInMin(4, time.getTime());
		long sday = GetCurTimeInMin(4, System.currentTimeMillis());
		int day = (int) (sday - zday) + 1;
		return day;
	}

	/**
	 * 得到当前服务器开区天数
	 *
	 */
	public static int getOpenAreaDay() {
		Date time = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
		long zday = GetCurTimeInMin(4, time.getTime());
		long sday = GetCurTimeInMin(4, System.currentTimeMillis());
		int day = (int) (sday - zday) + 1;
		return day;
	}

	/**
	 * 得到当前服务器开区时的秒数
	 *
	 */
	public static int getOpenAreaSecend(Player player) {
		Date time = WServer.getGameConfig().getServerTimeByPlayer(player);
		int zsecend = (int) (time.getTime() / 1000);
		return zsecend;
	}

	/**
	 * 获取指定时间到当前的小时数
	 *
	 * @param player
	 * @return
	 */
	public static int getOpenAreaHour() {
		Date time = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
		long now = GetCurTimeInMin(3, System.currentTimeMillis());
		long aaa = GetCurTimeInMin(3, time.getTime());
		int result = (int) (now - aaa) + 1;
		return result < 0 ? 0 : result;
	}

	/**
	 * 获取今天指定时间的UNIX时间
	 *
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static long getTheDayUnixTime(int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取格式化的时间字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getStringDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取当前格式化的时间字符串
	 *
	 * @param date
	 * @return
	 */
	public static String getNowStringDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 字符串转日期("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateByString(String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("{}日期格式有误{}" + date + "yyyy-MM-dd HH:mm:ss");
			return null;
		}
	}

	/**
	 * 字符串转日期("yyyyMMdd-HHmmss");
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateByString2(String date) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("{}日期格式有误{}" + date + "yyyyMMdd-HHmmss");
			return null;
		}
	}

	/**
	 * 判定是不是今天
	 *
	 * @author zhouyu @dateTime 2011-11-14 下午09:38:55
	 * @param time 时间毫秒串(<strong>注意:精确到毫秒</strong>)
	 * @return
	 */
	public static boolean isToday(final Long time) {
		if (null == time) {
			return false;
		}
		Calendar target = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		target.setTimeInMillis(time);
		if (target.get(Calendar.YEAR) == today.get(Calendar.YEAR) && target.get(Calendar.MONTH) == today.get(Calendar.MONTH)
			&& target.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是不是本周
	 *
	 * @author zhouyu @dateTime 2011-11-16 下午06:05:27
	 * @param time 时间毫秒串 <string>注意：精确到毫秒</strong>
	 * @return
	 */
	public static boolean isCurrentWeek(final Long time) {
		if (null == time) {
			return false;
		}
		Calendar target = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		target.setTimeInMillis(time);
		if (target.get(Calendar.YEAR) == today.get(Calendar.YEAR)
			&& target.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 传入秒 ,获得String时间格式 X天X小时X分钟X秒
	 *
	 * @param mss
	 * @return
	 */
	public static String GetTransformTime(long mss) {
		String t = "";
		long days = mss / (60 * 60 * 24);
		long hours = (mss % (60 * 60 * 24)) / (60 * 60);
		long minutes = (mss % (60 * 60)) / 60;
		long seconds = mss % 60;
		if (days > 0) {
			t = days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
		} else {
			t = hours + "小时" + minutes + "分" + seconds + "秒";
		}
		return t;
	}

	/**
	 * 根据毫秒数获取 天 时 分秒 格式
	 *
	 * @param millisecond
	 * @return
	 */
	public static String millisecondToStr(long millisecond) {
		if (millisecond < 0) {
			return millisecond + "毫秒";
		}
		if (millisecond < 1000) {
			return "1秒";
		}
		long second = millisecond / 1000;
		String result = "";
		long day = second / (3600 * 24);
		if (day > 0) {
			result += (day + "天");
		}
		second %= 3600 * 24;
		long hour = second / 3600;
		if (hour > 0) {
			result += (hour + "小时");
		}
		second %= 3600;
		long minute = second / 60;
		if (minute > 0) {
			result += (minute + "分");
		}
		second %= 60;
		if (second > 0) {
			result += (second + "秒");
		}
		return result;
	}

	/**
	 * 获得最近指定星期X 返回毫秒
	 *
	 * @param week 0=周日 ，1=周一 。。。 6=周六
	 * @return
	 */
	public static long getSoonWeek(int week) {
		return getSoonWeek(new Date(), week);
	}
	/**
	 * 获得最近指定星期X 返回毫秒
	 *
	 * @param week 0=周日 ，1=周一 。。。 6=周六
	 * @return
	 */
	public static long getSoonWeek(long ms, int week) {
		Date date = new Date(ms);
		return getSoonWeek(date, week);
	}
	/**
	 * 获得最近指定星期X 返回毫秒
	 *
	 * @param week 0=周日 ，1=周一 。。。 6=周六
	 * @return
	 */
	public static long getSoonWeek(Date date, int week) {
		if (week > 6) {
			week = week % 7;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//如果当前日期不是周6则自动往后递增日期 
		while (cal.get(Calendar.DAY_OF_WEEK) != week + 1) {
			cal.add(Calendar.DAY_OF_WEEK, 1);
		}
		//System.err.println(cal.getTime());
		return cal.getTimeInMillis();
	}

	/**
	 * 测试用组合时间
	 *
	 */
	public static void testtime() {
		List<Integer[]> datelist = new ArrayList<Integer[]>();
		//年
		Integer[] timelist1 = {2012};
		datelist.add(timelist1);
		///月
		Integer[] timelist2 = {1, 10};
		datelist.add(timelist2);
		//日
		Integer[] timelist3 = {22};
		datelist.add(timelist3);
		//星期
		Integer[] timelist4 = {-1};
		datelist.add(timelist4);
		//小时分钟
		Integer[] timelist5 = {58, 2358};
		datelist.add(timelist5);

		Integer[] timelist6 = {15};
		datelist.add(timelist6);

		String str = JSON.toJSONString(datelist, SerializerFeature.WriteClassName);
		System.err.println(str);

		System.err.println(checkRangeTime(str));

	}

	
	/**得到年月日组合格式int ， 例子20110101
	 * 用来精确控制到 某一天 的时间
	 * @return
	 */
	public static int GetSeriesDay() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int sday = year * 10000 + month * 100 + day;
		return sday;
	}
	
	
	
	/**
	 * 检查是否在指定范围内时间
	 * [[-1],[-1],[-1],[1,5],[1200,1300],[-1]];[[-1],[-1],[-1],[1,5],[1900,2000],[-1]];[[-1],[-1],[-1],[6,7],[1000,2000],[-1]]
	 * @param 年 ,月 ,日 ,周 ,时分（组合在一起）, 附加（-1=区间，-2=开区天数，>0 表示间隔分钟）
	 * @param [[2012],[1,10],[22],[-1],[101,2358],[10]]
	 * @param string
	 * @return
	 */
	public static boolean checkRangeTime(String string) {
		if (string == null || string.equals("")) {
			return false;
		}
		try {
			List<Integer[]> timelist = JSON.parseArray(string, Integer[].class);
			if (timelist.size() >= 5) {
				long millis = System.currentTimeMillis();
				long week = TimeUtil.getDayOfWeek(millis);	//星期
				if (week == 0) {
					week = 7;
				}
				long min = TimeUtil.getDayOfMin(millis);	//分钟
				long hour = TimeUtil.getDayOfHour(millis);	//小时
				long day = TimeUtil.getDayOfMonth(millis);	//天
				long month = TimeUtil.getMonth(millis) + 1;	//月
				long year = TimeUtil.getYear(millis);		//年
				//	System.err.println(year+","+month+","+day+","+hour);
				Integer[] years = timelist.get(0);
				Integer[] months = timelist.get(1);
				Integer[] days = timelist.get(2);
				Integer[] weeks = timelist.get(3);
				Integer[] times = timelist.get(4);

				boolean isyear = false;
				if (years.length == 2) {	//区间
					if (year >= years[0] && year <= years[1]) {
						isyear = true;
					}
				} else {
					for (int i = 0; i < years.length; i++) {
						int n = years[i];
						if (n == -1 || n == year) {
							isyear = true;
							break;
						}
					}
				}

				boolean ismonth = false;
				if (months.length == 2) {	//区间
					if (month >= months[0] && month <= months[1]) {
						ismonth = true;
					}
				} else {
					for (int i = 0; i < months.length; i++) {
						int n = months[i];
						if (n == -1 || n == month) {
							ismonth = true;
							break;
						}
					}
				}

				boolean isdayweek = false;
				if (days[0] > 0) {	//日期和星期互斥
					if (days.length == 2) {	//区间
						if (day >= days[0] && day <= days[1]) {
							isdayweek = true;
						}
					} else {
						for (int i = 0; i < days.length; i++) {
							int n = days[i];
							if (n == -1 || n == day) {
								isdayweek = true;
								break;
							}
						}
					}
				} else {
					if (weeks.length == 2) {	//进入区间判断
						if (week >= weeks[0] && week <= weeks[1]) {
							isdayweek = true;
						}
					} else {
						for (int i = 0; i < weeks.length; i++) {
							int n = weeks[i];
							if (n == -1 || n == week) {
								isdayweek = true;
								break;
							}
						}
					}
				}

				//支持  定点时间  ，时间范围，间隔时间
				boolean istimes = false;
				long hourmin = hour * 100 + min;
				if (timelist.size() >= 6) {
					Integer[] interval = timelist.get(5);
					if (interval[0] == -1) {	//-1表示时间区间判断
						if (times.length == 2) {	//如果有2个时间
							if (hourmin >= times[0] && hourmin <= times[1]) {
								istimes = true;
							}
						}
					} else if (interval[0] == -2) {	//开区天数
						if (times.length == 2) {
							int openday = TimeUtil.getOpenAreaDay();
							if (openday >= times[0] && openday <= times[1]) {
								istimes = true;
							}
						}
					} else {
						//时间间隔
						int xhour = times[0] / 100;
						int xmin = times[0] % 100;
						if (times.length == 2) {	//如果有2个时间
							if (hourmin >= times[0] && hourmin <= times[1]) {
								long shour = hour - xhour;
								long smin = min - xmin;
								long num = shour * 60 + smin;
								if (num % interval[0] == 0) {
									istimes = true;
								}
							}
						}
					}
				} else {
					//定点时间
					for (int i = 0; i < times.length; i++) {
						int n = times[i];
						if (n == hourmin) {
							istimes = true;
							break;
						}
					}
				}

				if (isyear && ismonth && isdayweek && istimes) {
					return true;
				}

			}
		} catch (Exception e) {
			log.error("时间解析错误：" + string + "," + e,e);
		}
		return false;
	}

	/**
	 * 得到在指定范围内时间的过去时间和剩余时间(开始时间或结束时间)
	 *
	 * @param 年 ,月 ,日 ,周 ,时分（组合在一起）, 附加（-1=区间，-2=开区天数，>0 表示间隔分钟）
	 * @param [[2012],[1,10],[22],[-1],[101,2358],[10]]
	 * @param boBeforeOrAfter 取之前还是之后的时间
	 * boStartAndEnd  取指定时间点 或者  剩余，已过时间
	 * @return
	 */
	public static long getRangeTimeBeforeOrAfter(String string, boolean boBeforeOrAfter, boolean boStartAndEnd) {
		if (string == null || string.equals("")) {
			return 0;
		}
		try {
			List<Integer[]> timelist = JSON.parseArray(string, Integer[].class);
			if (timelist.size() >= 5) {
				long millis = System.currentTimeMillis();
				long week = TimeUtil.getDayOfWeek(millis);	//星期
				if (week == 0) {
					week = 7;
				}
//				long min = TimeUtil.getDayOfMin(millis);	//分钟
//				long hour = TimeUtil.getDayOfHour(millis);	//小时
				long day = TimeUtil.getDayOfMonth(millis);	//天
				long month = TimeUtil.getMonth(millis) + 1;	//月
				long year = TimeUtil.getYear(millis);		//年
				Integer[] years = timelist.get(0);
				Integer[] months = timelist.get(1);
				Integer[] days = timelist.get(2);
//				Integer[] weeks = timelist.get(3);
				Integer[] times = timelist.get(4);
				Integer[] interval = timelist.size() >= 6 ? timelist.get(5) : new Integer[0];

				Calendar calendar = Calendar.getInstance();
				if (boBeforeOrAfter) {
					if (interval.length != 0 && interval[0] == -2) {
						Date date = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
						int btime = times.length == 0 ? 0 : times[0];
						btime = btime - 1;
						btime = btime < 0 ? 0 : btime;
						if (btime == 0) {
							calendar.setTime(date);
						} else {
							calendar.setTime(date);
							calendar.setTimeInMillis(calendar.getTimeInMillis() + btime * 24 * 3600 * 1000);
							calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
						}
					} else {
						int byear = (int) (years.length == 0 ? year : years[0]);
						int bmonth = (int) (months.length == 0 ? month : months[0]);
						int bday = (int) (days.length == 0 ? day : days[0]);
						int btime = times.length == 0 ? 0 : times[0];
						int bhour = btime / 100;
						int bmin = btime % 100;
						if (byear == -1 && bmonth == -1 && bday == -1 && bhour == 0 && bmin == 0) {
							return -1;
						}
						if (byear == -1) {
							byear = (int) year;
						}
						if (bmonth == -1) {
							bmonth = (int) month;
						}
						if (bday == -1) {
							bday = (int) day;
						}
						
						calendar.set(byear, bmonth - 1, bday, bhour, bmin, 0);
					}
					if (boStartAndEnd) {
						return calendar.getTimeInMillis();//开始时间点
					}else{
						return System.currentTimeMillis() - calendar.getTimeInMillis();  //已过时间
					}
				} else {
					if (interval.length != 0 && interval[0] == -2) {
						Date date = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
						int btime = times.length == 0 ? 0 : times[times.length - 1];
						btime = btime - 1;
						btime = btime < 0 ? 0 : btime;
						if (btime == 0) {
							calendar.setTime(date);
							calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
						} else {
							calendar.setTime(date);
							calendar.setTimeInMillis(calendar.getTimeInMillis() + btime * 24 * 3600 * 1000);
							calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
						}
					} else {
						int byear = (int) (years.length == 0 ? year : years[years.length - 1]);
						int bmonth = (int) (months.length == 0 ? month : months[months.length - 1]);
						int bday = (int) (days.length == 0 ? day : days[days.length - 1]);
						int btime = times.length == 0 ? 0 : times[times.length - 1];
						int bhour = btime / 100;
						int bmin = btime % 100;
						if (byear == -1 && bmonth == -1 && bday == -1 && bhour == 23 && bmin == 59) {
							return -1;
						}
						if (byear == -1) {
							byear = (int) year;
						}
						if (bmonth == -1) {
							bmonth = (int) month;
						}
						if (bday == -1) {
							bday = (int) day;
						}
						calendar.set(byear, bmonth - 1, bday, bhour, bmin, 59);
					}
					if (boStartAndEnd) {
						return calendar.getTimeInMillis();  //结束时间点
					}else{
						return calendar.getTimeInMillis() - System.currentTimeMillis(); //剩余时间
					}
				}
			}
		} catch (Exception e) {
			log.error("时间解析错误：" + string + "," + e,e);
		}
		return 0;
	}
//	
//	public static void main(String[] args) {
//		// long time=System.currentTimeMillis();
//		// System.out.println(getDayOfMonth(time));
//		// System.out.println(getDayOfWeek(time));
//		// System.out.println(getDayOfYear(time));
//		// System.out.println(getDayOfWeekInMonth(time));
//		// System.out.println(getMonth(time));
//		// System.out.println(getYear(time));
////		System.out.println(TimeUtil.getNextValidTime("[*][*][*][*][8:30-8:30];[*][*][*][*][12:30-12:30];[*][*][*][*][18:30-18:30];[*][*][*][*][23:00-23:00]"));
//		ArrayList<Integer> list=new ArrayList<Integer>();
//		Random random=new Random();
//		for(int i=1000;i<10000000;i+=6*1000){
//			list.add(random.nextInt(i));
//		}
//		
//		Collections.sort(list, new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				
//				return o1-o2;
//			}
//		});
//		
//
//
//
//		//
//		//
//		// List<String> list =
//		// TimeUtil.translate("[2011][11][w1,w2,w5][18:05-18:06]");
//		// list.addAll(TimeUtil.translate("[*][*][10-20][18:05-18:05]"));
//		// for (int i = 0; i < list.size(); i++) {
//		// System.out.println(list.get(i));
//		// try{
//		// CronExpression exp = new CronExpression("0 05 18 * * ? * ");
//		// System.out.println(exp.getNextValidTimeAfter(new Date(time)));
//		// }catch (Exception e) {
//		// e.printStackTrace();
//		// }
//		// }
//		// long time=System.currentTimeMillis()-1000*60*60*24;
//		// System.out.println(isAfterHourOfCurrentDay(5, time));
//
//		testtime();
//		
//		
//	}
}

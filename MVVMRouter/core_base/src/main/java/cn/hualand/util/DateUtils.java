/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cn.hualand.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Create by h4de5ing 2016/5/7 007
 */
public class DateUtils {
    private static final SimpleDateFormat DATE_FORMAT_DATETIME_Y_M_D =
            new SimpleDateFormat("yyyy年MM月dd日");
    private static final SimpleDateFormat DATE_FORMAT_DATETIME_Y_M_ =
            new SimpleDateFormat("yyyy年MM月");
    public static final SimpleDateFormat DATE_FORMAT_DATETIME_M = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DATETIME_Y_M_D_LINE =
            new SimpleDateFormat("yyyy/MM/dd");
    private static final SimpleDateFormat DATE_FORMAT_DATETIME =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat DATE_FORMAT_DATETIME_sub =
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat DATE_FORMAT_DATETIME_sub_hm =
            new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private static final SimpleDateFormat DATE_FORMAT_DATE_YYYY_MM_DD_HH =
            new SimpleDateFormat("yyyy-MM-dd-HH");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat DATE_FORMAT_DATE_MM_DD_HH_MM =
            new SimpleDateFormat("MM-dd HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DATE_HH_MM =
            new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DATE_MM_DD_HH_MM_ =
            new SimpleDateFormat("MM月dd日 HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DATE_DD_HH_MM =
            new SimpleDateFormat("dd日 HH:mm");
    private static final SimpleDateFormat DATE_FORMAT_DATE_MM =
            new SimpleDateFormat("M月");
    private static final SimpleDateFormat DATE_FORMAT_DATE_YYYY_MM = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm:ss");
    public static final String FORMAT_HTTP_DATA = "EEE, dd MMM y HH:mm:ss 'GMT'";
    public static final SimpleDateFormat formatter =
            new SimpleDateFormat(FORMAT_HTTP_DATA, Locale.US);
    public static final TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

    /**
     * 计算两个日期之间相差的天数
     */
    public static int diffDays(long Millis, long Millis2) {
        long dy = Millis - Millis2;
        long diffDays = dy / (24 * 60 * 60 * 1000l);
        return (int) Math.abs(diffDays);
    }


    /**
     * @return 0.00的时间
     */
    private static long getZeroTime(long Millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Millis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Parsing the TimeZone of time in milliseconds.
     *
     * @param gmtTime GRM Time, Format such as: {@value #FORMAT_HTTP_DATA}.
     * @return The number of milliseconds from 1970.1.1.
     * @throws ParseException if an error occurs during parsing.
     */
    public static long parseGMTToMillis(String gmtTime) throws ParseException {
        formatter.setTimeZone(GMT_TIME_ZONE);
        Date date = formatter.parse(gmtTime);
        return date.getTime();
    }

    public static long formatData(String date) {
        try {
            return DATE_FORMAT_DATETIME.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String formatDataTime(long date) {
        return DATE_FORMAT_DATETIME.format(new Date(date));
    }

    public static String formatDataStr(long date) {
        return DATE_FORMAT_DATETIME_Y_M_D.format(new Date(date));
    }

    public static String formatDataYmStr(long date) {
        return DATE_FORMAT_DATETIME_Y_M_.format(new Date(date));
    }

    public static String formatDataLineStr(long date) {
        return DATE_FORMAT_DATETIME_Y_M_D_LINE.format(new Date(date));
    }

    public static String datetime() {
        return DATE_FORMAT_DATETIME.format(new Date());
    }


    public static String formatDate(long date) {

        return DATE_FORMAT_DATE.format(new Date(date));
    }

    public static String hourAndMinute(long date) {
        return DATE_FORMAT_DATE_HH_MM.format(new Date(date));
    }

    public static String formatRedRecordDate(long date) {
        return DATE_FORMAT_DATE_MM_DD_HH_MM.format(new Date(date));
    }

    public static String formatDateYYYY_MM(long date) {
        return DATE_FORMAT_DATE_YYYY_MM.format(new Date(date));
    }

    public static String formatDateYYYY_MM_DD_HH(long date) {
        return DATE_FORMAT_DATE_YYYY_MM_DD_HH.format(new Date(date));
    }

    public static String formatDateYYYY_MM_DD(long date) {
        return DATE_FORMAT_DATE.format(new Date(date));
    }

    public static String formatDateMM_DD_HH_MM(long date) {
        return DATE_FORMAT_DATE_MM_DD_HH_MM.format(new Date(date));
    }
    public static String formatDATE_FORMAT_DATETIME_M(long date) {
        return DATE_FORMAT_DATETIME_M.format(new Date(date));
    }

    /**
     * 格式：23日 16:30
     *
     * @param date
     * @return
     */
    public static String formatDateDD(long date) {
        return DATE_FORMAT_DATE_DD_HH_MM.format(new Date(date));
    }


    /**
     * 格式：12月
     *
     * @param date
     * @return
     */
    public static String formatDateMM(long date) {
        return DATE_FORMAT_DATE_MM.format(new Date(date));
    }

    public static String formatDateMM_DD_HH_MM_(long date) {
        return DATE_FORMAT_DATE_MM_DD_HH_MM_.format(new Date(date));
    }

    public static String formatTime(long date) {
        return DATE_FORMAT_TIME.format(new Date(date));
    }

    public static String formatSubTime(long date) {
        return DATE_FORMAT_DATETIME_sub.format(new Date(date));
    }

    public static String formatSubTimeHm(long date) {
        return DATE_FORMAT_DATETIME_sub_hm.format(new Date(date));
    }


    public static String formatDateCustom(String beginDate, String format) {
        return new SimpleDateFormat(format).format(new Date(Long.parseLong(beginDate)));
    }

    public static String formatDateCustom(Date beginDate, String format) {
        return new SimpleDateFormat(format).format(beginDate);
    }

    public static Date string2Date(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if (s == null || s.length() < 6) {
            return null;
        }
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(
                Calendar.SECOND);
    }

    public static long subtractDate(Date dateStart, Date dateEnd) {
        return dateEnd.getTime() - dateStart.getTime();
    }

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }

    /**
     * 计算两个日期之间相差的天数
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        Log.e("GroupDetailActivity", "daysBetween:" + String.valueOf(between_days));
        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */

    public static String convertTimeToFormat(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);

        Date dates = null;
        try {
            dates = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = dates.getTime();
        long curTime = ts / (long) 1000;
        timeStamp = timeStamp / (long) 1000;

        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {

            return "刚刚";

        } else if (time >= 60 && time < 3600) {

            return time / 60 + "分钟前";

        } else if (time >= 3600 && time < 3600 * 24) {

            return time / 3600 + "小时前";

        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {

            return time / 3600 / 24 + "天前";

        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {

            return time / 3600 / 24 / 30 + "个月前";

        } else if (time >= 3600 * 24 * 30 * 12) {

            return time / 3600 / 24 / 30 / 12 + "年前";

        } else {

            return "刚刚";

        }

    }

    // 获得某天最大时间 2017-10-15 23:59:59
    @SuppressLint("NewApi")
    public static Date getEndOfDay(Date date) {

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());


    }

    public static String getEndOfDay(String date) {


        return date.split(" ")[0] + " 23:59";


    }

    // 获得某天最小时间 2017-10-15 00:00:00
    @SuppressLint("NewApi")
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getStartOfDay(String date) {
        return date.split(" ")[0] + " 00:00";
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();

        String result = DateUtils.DATE_FORMAT_DATETIME_M.format(today);
        Log.e("==前天", result);
        return result;
    }

    public static int countYear(int oldYear) {
        String format = DateUtils.DATE_FORMAT_DATETIME_M.format(new Date(System.currentTimeMillis()));
        int year = Integer.valueOf(format.split(" ")[0].split("-")[0]) - oldYear;
        return year;
    }

    /**
     * 返回日时分秒
     *
     * @param second
     * @return
     */
    public  static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return days + "天，" + hours + "小时，" + minutes + "分，" + second + "秒";
        } else {
            return hours + "小时，" + minutes + "分，" + second + "秒";
        }
    }

    public static String secondToTimeDir(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days) {
            return (days*24+ hours )+ ":" + (minutes<10?"0"+minutes:minutes) + ":" + ( second<10?"0"+second:second);
        } else {
            return (hours<10?"0"+hours:hours)  + ":" + (minutes<10?"0"+minutes:minutes) + ":" + ( second<10?"0"+second:second);
        }
    }
}

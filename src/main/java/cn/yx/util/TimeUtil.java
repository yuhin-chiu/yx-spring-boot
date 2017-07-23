package cn.yx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午2:17:31
 * @version 1.0
 */

public class TimeUtil {
    private static final SimpleDateFormat FORMATER_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FORMATER_2 = new SimpleDateFormat("yyyy-MM-dd");

    public static long toSeconds(long millionSeconds) {
        return millionSeconds / 1000;
    }

    public static long getCurrentTime() {
        return toSeconds(System.currentTimeMillis());
    }

    public static String seconds2TimeStr(long seconds) {
        return FORMATER_1.format(new Date(seconds * 1000));
    }

    public static String seconds2DayStr(long seconds) {
        return FORMATER_2.format(new Date(seconds * 1000));
    }

    public static long timeStr2Seconds(String timeStr) {
        if (timeStr == null) {
            return 0L;
        }
        try {
            return FORMATER_1.parse(timeStr).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long dayStr2Seconds(String timeStr) {
        if (timeStr == null) {
            return 0L;
        }
        try {
            return FORMATER_2.parse(timeStr).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long[] splitTimeRange(String timeRange) {
        try {
            String beginTimeStr = timeRange.substring(0, 10);
            String endTimeStr = timeRange.substring(timeRange.length() - 10);
            long beginTime = FORMATER_2.parse(beginTimeStr).getTime() / 1000;
            long endTime = FORMATER_2.parse(endTimeStr).getTime() / 1000;
            return new long[] { beginTime, endTime + 24 * 3600 };
        } catch (Exception e) {
        }
        return new long[] { -1, -1 };
    }

    public static String timeRange2Str(long start, long end) {
        return FORMATER_2.format(new Date(start * 1000)) + " - " + FORMATER_2.format(new Date(end * 1000));
    }

    public static String diffTime(Long time) {
        String result = new String();
        result += time / 86400 + "天";
        time = time % 86400;
        result += time / 3600 + "小时";
        time = time % 3600;
        result += time / 60 + "分钟";
        time = time % 60;
        result += time + "秒";
        return result;
    }

}

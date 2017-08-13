package cn.yx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午2:17:31
 * @version 1.0
 */

public class TimeUtil {
    
    private static final ThreadLocal<Map<String, SimpleDateFormat>> threadLocal = new ThreadLocal<Map<String, SimpleDateFormat>>(){
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();
            map.put("FORMATER_1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            map.put("FORMATER_2", new SimpleDateFormat("yyyy-MM-dd"));
            return map;
        }
    };
    
    private static SimpleDateFormat getDateFormat1() {
        return threadLocal.get().get("FORMATER_1");
    }
    private static SimpleDateFormat getDateFormat2() {
        return threadLocal.get().get("FORMATER_2");
    }
    
    public static long toSeconds(long millionSeconds){
        return millionSeconds / 1000;
    }

    public static long getCurrentTime(){
        return toSeconds(System.currentTimeMillis());
    }

    public static String time2TimeStr(long seconds) {
        return getDateFormat1().format(new Date(seconds * 1000));
    }

    public static long timeStr2Seconds(String timeStr) {
        try {
            return getDateFormat1().parse(timeStr).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static long dayStr2Seconds(String timeStr) {
        try {
            return getDateFormat2().parse(timeStr).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public synchronized static long[] splitTimeRange(String timeRange) {
        try {
            String beginTimeStr = timeRange.substring(0, 10);
            String endTimeStr = timeRange.substring(timeRange.length() - 10);
            long beginTime = getDateFormat2().parse(beginTimeStr).getTime() / 1000;
            long endTime = getDateFormat2().parse(endTimeStr).getTime() / 1000;
            return new long[]{beginTime, endTime};
        } catch (Exception e) {
        }
        return new long[]{-1, -1};
    }
    
    public static String[] splitTimeRangeToString(String timeRange) {
        try {
            String beginTimeStr = timeRange.substring(0, 10);
            String endTimeStr = timeRange.substring(timeRange.length() - 10);
            return new String[]{beginTimeStr, endTimeStr};
        } catch (Exception e) {
        }
        return new String[]{"1970-01-01", "2099-12-31"};
    }

    public static String timeRange2Str(long start, long end) {
        return getDateFormat2().format(new Date(start * 1000)) + " - " + getDateFormat2().format(new Date(end * 1000));
    }
    
    public static String time2DayStr(long time) {
        return getDateFormat2().format(new Date(time * 1000));
    }

    public static String diffTime(Long time){
        String result = new String();
        result += time/86400 +"天";
        time = time % 86400;
        result += time/3600 + "小时";
        time = time % 3600;
        result += time/60 + "分钟";
        time = time%60;
        result += time + "秒";
        return result;
    }
    
    public static List<String> splitTimeRangeIntoString(String timeRange) throws ParseException {
        if (timeRange == null || timeRange.length() < 20) {
            return null;
        }
        String beginTimeStr = timeRange.substring(0, 10);
        String endTimeStr = timeRange.substring(timeRange.length() - 10);

        // 最后时间加一天
        long endTime = getDateFormat2().parse(endTimeStr).getTime();
        endTime += 3600 * 24 * 1000;

        endTimeStr = getDateFormat2().format(new Date(endTime));
        return Arrays.asList(beginTimeStr, endTimeStr);
    }
}
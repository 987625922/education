package com.project.gelingeducation.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Data工具类
 *
 * @author LL
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmmss规则的localDataTime输出
     *
     * @param localDateTime
     * @return
     */
    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    /**
     * 按pattern规则输出localDataTime的字符串
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 判断时间是否是当天
     *
     * @param date
     * @return
     */
    public static boolean dateIsToday(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(FULL_TIME_PATTERN);
        String dateStr = format.format(date);
        String todayDateStr = format.format(date);
        return todayDateStr.equals(dateStr);
    }
}

package com.ecaray.basicres.util;

import org.joda.time.DateTime;

import java.util.Locale;

import urils.ecaray.com.ecarutils.Utils.DataFormatUtil;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * @author : Eric_Huang
 * <p>
 * 创建时间: 2016/9/21 17:55
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/21 17:55
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class TimeUtils {

    /**
     * @param millisecond 毫秒值
     * @return 转化成的时间
     */
    public static String getTotalTimeDHM(long millisecond) {
        /**
         * 获取秒数
         */
        millisecond /= 1000;
        String[] times = new String[3];

        /**
         * 天数
         */
        long dd = millisecond / (60 * 60 * 24);

        /**
         * 小时
         */
        long hh = (millisecond - 60 * 60 * 24 * dd) / (60 * 60);

        /**
         * 分钟
         */
        long mm = (millisecond / 60) % 60;

        /**
         * 秒
         */
        long ss = millisecond % 60;

        if (ss > 0) {
            mm += 1;
        }

        StringBuilder sb = new StringBuilder();
        times[0] = String.valueOf(dd);
        times[1] = (hh < 10) ? (DataFormatUtil.addText(sb, "0",
                String.valueOf(hh))) : (String.valueOf(hh));
        times[2] = (mm < 10) ? (DataFormatUtil.addText(sb, "0",
                String.valueOf(mm))) : (String.valueOf(mm));
        return dd == 0 ? DataFormatUtil.addText(sb, times[1], "小时",
                times[2], "分") : DataFormatUtil.addText(sb, times[0], "天" + times[1], "小时",
                times[2], "分");
    }

    /**
     * 毫秒转化成日时分
     */
    public static String formatTime(long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        return sb.toString();
    }


    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy年MM月dd日 HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2DateWithChinese2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("MM/dd HH:mm", Locale.CHINESE);
    }

    /**
     * 毫秒值转化为日期 /隔开
     */
    public static String TimeFormat2Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat3Date(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy/MM/dd HH:mm", Locale.CHINESE);
    }

    public static String TimeFormat2DataV2(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd");
    }

    public static String TimeFormat2DataV3(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
    }

    public static String TimeFormat2DataV4(long millisecond){
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("yyyy-MM-dd HH:mm", Locale.CHINESE);
    }

    /**
     * 转换成小时分钟
     */
    public static String TimeFormat2HM(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("HH:mm");
    }

    public static String TimeFormat2MS(long millisecond) {
        DateTime lDateTime = new DateTime(millisecond);
        return lDateTime.toString("mm:ss");
    }

}

package com.ecaray.basicres.util;

import android.util.Log;


public class LogUtils {
    public LogUtils() {
/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = BuildConfigUtils.getInstance().isDebug();
    public static final String TAG = "E-Pda";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
            FileUtils.writeLogToSDCard(tag, msg);
        }

    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
            FileUtils.writeLogToSDCard(tag, msg);
        }

    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
            FileUtils.writeLogToSDCard(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
            FileUtils.writeLogToSDCard(tag, msg);
        }

    }
}

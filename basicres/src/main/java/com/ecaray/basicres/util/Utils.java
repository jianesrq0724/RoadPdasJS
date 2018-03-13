package com.ecaray.basicres.util;

import android.content.Context;

import com.ecaray.basicres.BuildConfig;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/8
 */

public class Utils {
    private static Context sContext;

    private static BuildConfig sBuildConfig;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...baseLibray");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.sContext = context.getApplicationContext();
    }


    public static Context getContext() {
        if (sContext != null) {
            return sContext;
        }
        throw new NullPointerException("u should init first baseLibray");
    }
}

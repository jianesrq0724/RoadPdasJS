package com.ecaray.basicres.util;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2017/1/5 20:22
 */
public class NoDoubleClickUtils {

    private static long lastClickTime;
    private final static int SPACE_TIME = 1000;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }

}

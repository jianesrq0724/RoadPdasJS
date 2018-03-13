package com.ecaray.basicres.util;

import android.content.Context;
import android.widget.Toast;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/1 17:32
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/1 17:32
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ToastUtils {

    private ToastUtils() {
        /** cannot be instantiated**/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 短时间显示Toast
     */
    public static void showShort(Context context, int message) {
        if (isShow){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow){
            Toast.makeText(context, message, duration).show();
        }

    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(Context context, int message) {
        if (isShow){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        if (isShow){
            Toast.makeText(context, message, duration).show();
        }

    }

    /**
     * 显示最后的Toast
     */

    public static Toast mToast;

    public static void showLastToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}

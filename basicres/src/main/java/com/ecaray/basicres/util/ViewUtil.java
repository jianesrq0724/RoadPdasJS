package com.ecaray.basicres.util;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;

/**
 * 类描述: 布局相关的工具方法
 * @author : Shirley
 * 创建日期: 2016/11/24 15:14
 * 修改人:Shirley
 */
public class ViewUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 隐藏系统软键盘
     */
    public static  void dismissSysKeyBoard(Activity context, EditText editText) {
        //4.0以下 danielinbiti
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            context.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void hideSoftInput(Activity activity) {
        try {
            if (activity.getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(inputMethodManager.isActive()){
                    inputMethodManager.hideSoftInputFromWindow(activity
                            .getCurrentFocus().getWindowToken(), 0);
                }

            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }

    /**
     * 泊位列表的渐变动画
     * @return
     */
    public static AlphaAnimation getMonitorAlpha(AlphaAnimation alphaAnimation){
        if(alphaAnimation !=null){
            return alphaAnimation;
        }
        alphaAnimation = new AlphaAnimation(1.0f,0.5f);
        alphaAnimation.setDuration(5000);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    /**
     * 清除动画
     */
    public static void clearAnim(View view){
        if(view !=null){
            view.clearAnimation();
        }
    }
}

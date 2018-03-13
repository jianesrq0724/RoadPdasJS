package com.ecaray.basicres.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ecar.epark.evoicelib.VoiceManager;
import com.ecar.pushlib.EcarPushInterface;

import java.lang.ref.WeakReference;

/**
 * 功能：
 * 创建者：ruiqin.shen
 * 创建日期：2017/10/26
 * 版权所有：深圳市亿车科技有限公司
 */

public class AppFunctionUtil {

    public static final String ACTION_CashTieOffHistoryList = AppUtils.getAppPackageName() + ".CashTieOffHistoryListBaseActivity";
    public static final String ACTION_CashTieOffList = AppUtils.getAppPackageName() + ".CashTieOffListBaseActivity";

    private AppFunctionUtil() {
    }

    public static class SingletonHolder {
        private static AppFunctionUtil INSTANCE = new AppFunctionUtil();
    }

    public static AppFunctionUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void openActivity(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        context.startActivity(intent);
    }

    /**
     * 订单（单笔或多笔）判断并返回值：单个值（请求接口使用）或者键值对（作为二维码地址拼接参数）
     */
    public static String getMultiContent(String orderOrOrders, boolean needKey) {
        String content = "";
        if (!TextUtils.isEmpty(orderOrOrders) && (orderOrOrders.contains("_") || orderOrOrders.contains(","))) {
            if (needKey) {
                //"type:Int:缴费类型1-单笔 2-多笔",
                content = "&type=2";
            } else {
                content = "2";
            }
        } else {
            if (needKey) {
                content = "&type=1";
            } else {
                content = "1";
            }
        }
        return content;
    }

    /**
     * 获取资源（string）
     */
    public static String getResourceStr(Context mContext, int id) {
        String content = "";
        if (mContext != null) {
            content = mContext.getResources().getString(id);
        }
        return content;
    }

    /**
     * 关闭推送和语音播报
     */
    public static synchronized void stopPushAndVoice() {
        //无任务则不走stop逻辑
        if (!VoiceManager.getInstance().hasTask()) {
            return;
        }
        new Thread(() -> {
            VoiceManager.getInstance().clearTask();
            EcarPushInterface.stopPush(Utils.getContext());
        }).start();
    }

    /**
     * 获取Glide 请求管理类;使用应用上下文，处理内存泄漏
     *
     * @return
     */
    public static RequestManager getGlideReqManager() {
        return Glide.with(Utils.getContext());
    }

    /**
     * 获取弱引用包裹的上下文
     *
     * @param contextWeakReference
     * @param <T>
     * @return
     */
    public static <T extends Context> T getContext(WeakReference<T> contextWeakReference) {
        if (contextWeakReference != null && contextWeakReference.get() != null) {
            return contextWeakReference.get();
        }
        return null;
    }


    /**
     * 设置意图request标志第一个过滤位
     *
     * @param isTure true则添加标志置1，false抹去标志置0
     * @return
     */
    public static int setIntentFirstFlag(int requestCode, boolean isTure) {
        int filterFlag = 0x10000000;
        if (!isTure) {
            //抹去标志
            filterFlag = ~filterFlag;
            return filterFlag & requestCode;
        } else {
            //添加标志
            return filterFlag | requestCode;
        }
    }

    /**
     * 判断意图request的第一个标志位是否为true（value为1）
     *
     * @param requestCode
     * @return
     */
    public static boolean isHasIntentFirstFlag(int requestCode) {
        return (requestCode & 0x10000000) > 0;
    }

    public static int getOriginalIntentFlag(int requestCode) {
        //暂时只处理第一个标志位
        return requestCode & 0x01111111;

    }
}

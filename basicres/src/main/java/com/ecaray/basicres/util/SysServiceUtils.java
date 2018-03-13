package com.ecaray.basicres.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.ecaray.basicres.base.BaseApplication;

import java.util.List;

/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/5/4 10:30
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/5/4 10:30
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class SysServiceUtils {

    public static String getIMEI(){

        String IMEI = ((TelephonyManager) BaseApplication.getInstance().getSystemService(
                Context.TELEPHONY_SERVICE)).getDeviceId();
        if(IMEI != null && !TextUtils.isEmpty(IMEI)){
            return IMEI;
        }else{
            return "";
        }
    }

    public static String getDeviceName(){
        String model= android.os.Build.MODEL;
        return model.toUpperCase();
    }

    /**
     * 判断某个服务是否正在运行的方法
     * 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceExisted(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> serviceList = activityManager
                .getRunningServices(Integer.MAX_VALUE);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;

            if (serviceName.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

}

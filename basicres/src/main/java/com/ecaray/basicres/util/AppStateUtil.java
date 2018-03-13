package com.ecaray.basicres.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import java.util.List;

/**
 * Created by eric on 2015/9/7.
 * Description:程序狀態
 */
public class AppStateUtil {

    /**
     * 检测程序运行状态
     */
    public static boolean checkAppRunState(Context context, String packageName) {

        boolean isAppRunning = false;

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> list = activityManager.getRunningTasks(100);

        for (ActivityManager.RunningTaskInfo info : list) {
            if (packageName.equals(info.topActivity.getPackageName())) {
                isAppRunning = true;
                break;
            } else {
                isAppRunning = false;
            }
        }

        return isAppRunning;
    }

    /**
     *判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

}

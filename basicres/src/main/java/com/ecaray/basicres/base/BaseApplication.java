package com.ecaray.basicres.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;
import com.ecaray.basicres.util.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public abstract class BaseApplication extends Application {

    private List<Activity> mActivityList = new LinkedList<>();
    private List<Activity> mPayActivityList = new LinkedList<>();
    protected final int mTimeOut = 30 * 1000;

    protected static BaseApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Utils.init(mApp);
    }

    @Nullable
    public static BaseApplication getInstance() {
        return mApp;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 添加Activity 到容器中
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivityList.add(activity);
        }
    }

    /**
     * 移除
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 添加支付activity到容器
     */
    public void addPayActivity(Activity activity) {
        if (activity != null) {
            mPayActivityList.add(activity);
        }
    }

    /**
     * 结束Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 遍历所有Activity 并finish
     */
    public void exit() {
        //合肥督查版 清空上一次选择路段的id和name
        SPUtils.put(SPKeyUtils.S_SELECT_SECTION_ID, "");
        SPUtils.put(SPKeyUtils.S_SELECT_SECTION_NAME, "");
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quit() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
    }

    /**
     * 遍历所有Activity 并quit
     */
    public void quitPayActivity() {
        for (Activity activity : mPayActivityList) {
            activity.finish();
        }
    }


    /**
     * 获取Activity 数量
     */
    public int getActivityCount() {
        if (mActivityList != null) {
            return mActivityList.size();
        }
        return 0;
    }

    /*
     * 功能:结束指定类名的Activity。 其位置栈上面的其他activity
     */
    public void finishBeforeActivity(Class<?> cls) {
        int index = -1;
        int size = mActivityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (mActivityList.get(i).getClass().equals(cls)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = size - 1; i > index; i--) {
                finishActivity(mActivityList.get(i));
            }
        }

    }

    /*
     * 功能:找到最靠近栈顶的Activity
     */
    public Activity getTopActivity(Class<?> cls) {
        int size = mActivityList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (mActivityList.get(i).getClass().equals(cls)) {
                return mActivityList.get(i);
            }
        }
        return null;
    }


    /**
     * 功能:设置当前Activity（堆栈中最后一个压入的）
     */
    public Activity setCurrentActivity(Activity activity) {
        boolean isContains = mActivityList.contains(activity);
        if (isContains) {
            mActivityList.remove(activity);
        }
        mActivityList.add(activity);
        return activity;
    }


}

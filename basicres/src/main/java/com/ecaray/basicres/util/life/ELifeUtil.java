package com.ecaray.basicres.util.life;

import android.app.Application;
import android.os.AsyncTask;

import com.ecar.pushlib.EcarPushInterface;

/**
 * Created by happy on 2017/12/5 0005.
 */

public class ELifeUtil {

    public static void initForegroundCallbacks(final Application application){
        try {
            ForegroundCallbacks.init(application);

            ForegroundCallbacks.get().addListener(new ForegroundCallbacks.Listener() {
                @Override
                public void onBecameForeground() {
                    startPush(application);
                }

                @Override
                public void onBecameBackground() {
//                L.d("当前程序切换到后台");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 继续开启服务
     */
    private static void startPush(final Application application) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                EcarPushInterface.startPush(application);
                return null;
            }
        }.execute();
    }
}

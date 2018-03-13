package com.ecaray.e_pda_js.application;


import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecar.ecarnetwork.util.file.EFileUtil;
import com.ecar.epark.SDKMangageE;
import com.ecar.epark.evoicelib.VoiceManager;
import com.ecar.epark.greendaolib.engine.BaseGreenDaoEngin;
import com.ecaray.basicres.base.BaseApplication;
import com.ecaray.basicres.helper.CrashHandler;
import com.ecaray.basicres.util.BuildConfigUtils;
import com.ecaray.basicres.util.Utils;
import com.ecaray.basicres.util.life.ELifeUtil;
import com.ecaray.e_pda_js.BuildConfig;
import com.luojilab.component.componentlib.router.ui.UIRouter;

import org.litepal.LitePal;

import java.io.InputStream;

import bugly.ecar.com.ecarbuglylib.util.BuglyUtil;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        UIRouter.getInstance().registerUI("app");

        Utils.init(this);
        initBuildConfig();

        //避免启动慢
        new Thread(new Runnable() {
            @Override
            public void run() {
                //初始化网络库
                initNetLib();

                //初始化数据库
                LitePal.initialize(mApp);

                //全局异常捕捉
                if (BuildConfig.IS_CRASH) {
                    CrashHandler crashHandler = CrashHandler.getInstance();
                    crashHandler.init(getApplicationContext());
                }
                SDKMangageE.init(getInstance());

                initPush();
                new VoiceManager.Builder().application(mApp).build();
                if (BuildConfig.BUILD_TYPE.contains("debug") || BuildConfig.BUILD_TYPE.contains("release")) {
                    //目前控制正式环境不连接
                    return;
                }
                //监听程序处于前后台状态
                ELifeUtil.initForegroundCallbacks(mApp);
                BaseGreenDaoEngin.initDataBase(mApp);
            }

            private void initNetLib() {
                ApiBox.Builder builder = new ApiBox.Builder();
                //网络请求框架，param:1.是否需要答应log，2.传入KEY，3.传入APP_ID
                builder.application(mApp).debug(BuildConfig.DEBUG).reqKey(BuildConfig.REQUEST_KEY)
                        .readTimeOut(mTimeOut).connetTimeOut(mTimeOut);

                //加载所有证书
                InputStream[] cers = EFileUtil.getAssetsCers(mApp, "cer");
                builder.inputStreams(cers);
                builder.build();
            }

        }).start();
        //初始化腾讯bugly
        BuglyUtil.init(getInstance(), BuildConfig.BUGLY_APP_ID, BuildConfig.DEBUG, BuildConfig.VERSION_NAME);

    }

    /**
     * 初始化BuildConfig，提供给BasicRes
     */
    private void initBuildConfig() {
        BuildConfigUtils.getInstance().setAppId(BuildConfig.APP_ID);
        BuildConfigUtils.getInstance().setRequestKey(BuildConfig.REQUEST_KEY);
        BuildConfigUtils.getInstance().setBuildType(BuildConfig.BUILD_TYPE);
        BuildConfigUtils.getInstance().setDebug(BuildConfig.DEBUG);
        BuildConfigUtils.getInstance().setFlavor(BuildConfig.FLAVOR);
        BuildConfigUtils.getInstance().setUrlType(BuildConfig.URL_TYPE);
        BuildConfigUtils.getInstance().setUrlCommon(BuildConfig.Url_Common);
        BuildConfigUtils.getInstance().setUrlDwonUp(BuildConfig.Url_DwonUp);
    }

    private void initPush() {
    }


}

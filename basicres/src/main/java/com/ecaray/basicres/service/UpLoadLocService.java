package com.ecaray.basicres.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ecar.ecarnetwork.http.exception.CommonException;
import com.ecar.ecarnetwork.util.rx.RxUtils;
import com.ecaray.basicres.R;
import com.ecaray.basicres.base.ESubscriber;
import com.ecaray.basicres.entity.UpLoadLocationBean;
import com.ecaray.basicres.net.PubDataCenter;
import com.ecaray.basicres.util.LocationUtils;
import com.ecaray.basicres.util.LogUtils;
import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

/**
 * ===============================================
 * <p>
 * 类描述: 上传当前位置的信息
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/10/9 18:57
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/10/9 18:57
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class UpLoadLocService extends Service {

    //默认的当前位置上传时间间隔
    public static final long DEFAULT_UPLOAD_LOCATION_TIME = 60 * 60 * 1000L;
    private Disposable mSubscription;

    //初始化声音池
    private SoundPool mSoundPool = null;
    private HashMap<Integer, Integer> soundID = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        initSP();

        long time = (long) SPUtils.get(SPKeyUtils.s_UPLOAD_LOCATION_TIME, DEFAULT_UPLOAD_LOCATION_TIME);

        if (time < 10 * 1000) {
            time = 10 * 1000;
        }

        initOperation(time);

    }

    private void initOperation(long time) {

        Flowable.interval(time, TimeUnit.MILLISECONDS)
                .onBackpressureDrop()
                .compose(RxUtils.getScheduler(false, null))
                .subscribe(aLong -> {
                    Log.e("TAG", aLong + "位置上传");
                    upLoadLocation();
                }, Throwable::printStackTrace);
    }

    /**
     * 上传位置信息
     */
    private void upLoadLocation() {

        try {
            //重新获取地址
            LocationUtils.getInstance().getLocation(bdLocation -> {
                String lAddress = bdLocation.getAddrStr();
                double lLatitude = bdLocation.getLatitude();
                double lLongitude = bdLocation.getLongitude();
                Log.e("TAG", "位置上传 开始");

                //获取地址之后才进行上传轨迹,不需要知道是否上传成功
                PubDataCenter.getInstance().uploadTrack(lLongitude, lLatitude, lAddress)
                        .compose(RxUtils.getScheduler(false, null))
                        .onBackpressureLatest()
                        .subscribeWith(new ESubscriber<UpLoadLocationBean>(this) {
                            @Override
                            protected void onUserSuccess(UpLoadLocationBean upLoadLocationBean) {
                                Log.e("TAG", "位置上传 成功");
                                //离岗提示语音
                                if (upLoadLocationBean.departure == UpLoadLocationBean.DEPARTURE) {
                                    mSoundPool.play(soundID.get(1), 1, 1, 0, 0, 1);
                                }
                            }

                            @Override
                            protected void onUserError(CommonException ex) {
                                LogUtils.i("上传位置失败");
                            }

                        });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSP() {
        //注释代码为：默认开启制定音量
//        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        //当前系统的SDK版本大于等于21(Android 5.0)时
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder builder = new SoundPool.Builder();
            //传入音频数量
            builder.setMaxStreams(1);
            //AudioAttributes是一个封装音频各种属性的方法
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            //设置音频流的合适的属性
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            //加载一个AudioAttributes
            builder.setAudioAttributes(attrBuilder.build());
            mSoundPool = builder.build();
        }
        //当系统的SDK版本小于21时
        else {//设置最多可容纳2个音频流，音频的品质为5
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        }
        soundID.put(1, mSoundPool.load(this, R.raw.departurezone, 1));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind.....");
        IBinder result;
        result = new Binder();
        return result;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.dispose();
        }

        stopSelf();
    }
}

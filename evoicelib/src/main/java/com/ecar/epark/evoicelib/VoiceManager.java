package com.ecar.epark.evoicelib;

import android.app.Application;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoiceManager {
     static final String Ex_Have_Vehicle_In = "Ex_Have_Vehicle_In";//有车驶入
     static final String Ex_Have_Vehicle_Out = "Ex_Have_Vehicle_Out";//有车驶出
     static final String Ex_Vehicle_Out = "Ex_Vehicle_Out";//驶出
     static final String Ex_Berth = "Ex_Berth";//泊位
     static final String Ex_License_Plate = "Ex_License_Plate";//车牌
     static final String Ex_Sign_In = "Ex_Sign_In";//签到成功
     static final String Ex_Sign_Out = "Ex_Sign_Out";//签到成功
     static final String Ex_Arrears_Leave = "Ex_Arrears_Leave";//欠费驶离

    private List<AsyncTask> asyncTasks;

    private boolean isPlaying;
    private MediaPlayer mediaPlayer = null;
    private Application mApplication;

    private static VoiceManager voiceManager = null;

    private VoiceManager() {
        asyncTasks = new ArrayList<>();
    }

    public static VoiceManager getInstance() {
        return voiceManager;
    }

    public static final class Builder {
        private Application application;

        public Builder() {
        }

        public Builder application(Application application) {
            this.application = application;
            return this;
        }

        public VoiceManager build() {
            if (voiceManager == null) {
                voiceManager = new VoiceManager();
            }
            voiceManager.mApplication = this.application;
            return voiceManager;
        }
    }

    public void play(List<String> datas) {
        if (datas == null || datas.isEmpty()) {
            return;
        }
        VoiceTask voiceTask = new VoiceTask();
        addTask(voiceTask);
        voiceTask.execute(datas);
    }

    private void addTask(VoiceTask voiceTask){
        asyncTasks.add(voiceTask);
    }

    /**
     * 清空队列
     */
    public void clearTask(){
        int size = asyncTasks.size();
        Log.i("线程数量", size + "");
        //当清楚队列时，说明最后一个为已签出语音提示，不应该去除这条记录，正常播放已签出，所以size-2
        for(int i = size-2 ;i>=0 ;i--){
            AsyncTask asyncTask = asyncTasks.remove(i);
            asyncTask.cancel(true);
        }
    }

    public boolean hasTask(){
        return asyncTasks.size() >0;
    }


    private void removeTask(VoiceTask voiceTask){
        asyncTasks.remove(voiceTask);
    }


    private class VoiceTask extends AsyncTask<List<String>, Void, Void> {

        @Override
        protected void onProgressUpdate(Void... values) {
            if(isCancelled()){
                return;
            }
        }

        @Override
        protected Void doInBackground(List<String>... lists) {
            if(isCancelled()){
                return null;//如果异步任务被取消，则不再继续执行
            }
//            showDateLog();
            playSoundList(lists[0], 0);
            while (isPlay()) {//asynctask是异步的，但是异步处理完，音频播放并没有处理完，死循环等待
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            removeTask(this);
            return null;
        }

        private void playSoundList(final List<String> datas, final int soundIndex) {
            final int size = datas.size();
            setIsPlay(true);
            if (mediaPlayer == null) {
                mediaPlayer = null;
            }
            System.out.println("加载音频[" + soundIndex + "]");
            mediaPlayer = createSound(soundIndex, datas);

            if (mediaPlayer != null) {
                System.out.println("加载音频成功[" + soundIndex + "]");
            } else {
                System.out.println("加载音频失败[" + soundIndex + "]");
            }

            //播放完成触发此事件
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseToNext(mp, soundIndex, size, datas);
                }
            });
            try {
                //在播放音频资源之前，必须调用Prepare方法完成些准备工作
                if (mediaPlayer != null) {
                    mediaPlayer.prepare();
                    //开始播放音频
                    mediaPlayer.start();
                    System.out.println("播放音频[" + soundIndex + "]");
                } else {
                    releaseToNext(null, soundIndex, size, datas);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        private void releaseToNext(MediaPlayer mp, int soundindex, int size, List<String> datas) {
            if (mp != null) {
                mp.release();//释放音频资源
            }
            int newSoundIndex = soundindex + 1;
            System.out.println("释放资源[" + soundindex + "]");
            if (newSoundIndex < size) {
                playSoundList(datas, newSoundIndex);
            } else {
                setIsPlay(false);
            }
        }

        private MediaPlayer createSound(int soundIndex, List<String> datas) {
            if (soundIndex >= datas.size()) {
                return null;
            }
            MediaPlayer mp = null;
            String soundChar = datas.get(soundIndex);
            //数字、字母、自定义的文字
            if (isDigit(soundChar)) {//数字
                int identifier = getIdentifier("sound" + soundChar);
                mp = MediaPlayer.create(mApplication, identifier);
            }
            else if(isChar(soundChar)){//字母
                int identifier = getIdentifier("voice_" + soundChar.toLowerCase());
                mp = MediaPlayer.create(mApplication, identifier);
            }
            else{
                switch (soundChar) {
                    case Ex_Have_Vehicle_In://有车驶入
                        mp = MediaPlayer.create(mApplication, R.raw.e_have_vehicle_in);
                        break;
                    case Ex_Have_Vehicle_Out://有车驶出
                        mp = MediaPlayer.create(mApplication, R.raw.e_have_vehicle_out);
                        break;
                    case Ex_Vehicle_Out://驶出
                        mp = MediaPlayer.create(mApplication, R.raw.e_vehicle_out);
                        break;
                    case Ex_Berth://泊位
                        mp = MediaPlayer.create(mApplication, R.raw.e_berth);
                        break;
                    case Ex_License_Plate://车牌
                        mp = MediaPlayer.create(mApplication, R.raw.e_license_plate);
                        break;
                    case Ex_Sign_In://签到成功
                        mp = MediaPlayer.create(mApplication, R.raw.e_sign_in);
                        break;
                    case Ex_Sign_Out://签退成功
                        mp = MediaPlayer.create(mApplication, R.raw.e_sign_out);
                        break;
                    case Ex_Arrears_Leave://欠费驶离
                        mp = MediaPlayer.create(mApplication, R.raw.e_arrears_leave);
                        break;
                    case "皖"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ao_aomen);
                        break;
                    case "苏"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_su_jiangsu);
                        break;
                    case "赣"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_gan_jiangxi);
                        break;
                    case "浙"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_zhe_zhejiang);
                        break;
                    case "豫"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_yu_henan);
                        break;
                    case "鲁"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_lu_shandong);
                        break;
                    case "粤"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_yue_guagndong);
                        break;
                    case "京"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_jing_beijing);
                        break;
                    case "津"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_jing_tianjing);
                        break;
                    case "沪"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_hu_shanghai);
                        break;
                    case "渝"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_yu_chongqing);
                        break;
                    case "蒙"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_meng_neimegngu);
                        break;
                    case "新"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_xin_xinjiang);
                        break;
                    case "藏"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_zang_xizang);
                        break;
                    case "宁"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ning_ningxia);
                        break;
                    case "桂"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_gui_guangxi);
                        break;
                    case "港"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_gang_xianggang);
                        break;
                    case "澳"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ao_aomen);
                        break;
                    case "吉"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ji_jilin);
                        break;
                    case "辽"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_liao_liaoling);
                        break;
                    case "晋"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_jing_shanxi);
                        break;
                    case "冀"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ji_hebei);
                        break;
                    case "青"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_qing_qinghai);
                        break;
                    case "闽"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_ming_fujian);
                        break;
                    case "湘"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_xiang_hunan);
                        break;
                    case "鄂"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_e_hubei);
                        break;
                    case "琼"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_qiong_hainan);
                        break;
                    case "甘"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_gan_gansu);
                        break;
                    case "陕"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_shan_shanxi);
                        break;
                    case "黔"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_qian_guizhou);
                        break;
                    case "云"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_yun_yunnan);
                        break;
                    case "川"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_chuan_sichuan);
                        break;
                    case "贵"://
                        mp = MediaPlayer.create(mApplication, R.raw.e_city_gui_guiyang);
                        break;
                    default:
                        break;
                }
            }
            //下面这三句是控制语速，但是只适用于Android6.0 以上，以下的就会报错，所以这个功能下次更新时解决
//        PlaybackParams pbp = new PlaybackParams();
//        pbp.setSpeed(1.5F);
//        mp.setPlaybackParams(pbp);
            if (mp != null) {
                mp.stop();
            }
            return mp;
        }
    }

    /**
     * 获取资源
     *
     * @return
     */
    private int getIdentifier(String resName) {
        Resources res = mApplication.getResources();
        final String packageName = mApplication.getPackageName();
        int musicResId = -1;
        musicResId = res.getIdentifier(resName, "raw", packageName);
        return musicResId;
    }

    /**
     * 根据字符串取资源
     */
    /**
     * 校验是否是数字
     */
    public boolean isDigit(String str) {
        return str.matches("^[0-9]$");//str.matches("^[0-9]+$");
    }

    public boolean isChar(String str) {
        return str.matches("^[a-zA-Z]+$");//str.matches("^[a-zA-Z]$");
    }

    private void showDateLog() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        System.out.println("date = " + dateString);
    }

    /**
     * 字符串转集合，lastSize表示取最后几位
     */
    public static List<String> strToList(String content,int lastSize) {
        List<String> objects = new ArrayList<>();
        if (TextUtils.isEmpty(content)) {
            return objects;
        }
        content = content.replaceAll(" ", "");
        int length = content.length();
        if(length>=lastSize && lastSize>0){
            int i =0;
            int size = lastSize;
            while (i != size){
                objects.add(0,content.substring(length-i-1, length-i));
                i++;
            }
        }else {
            for(int i=0,size = length;i<size;i++){
                objects.add(content.substring(i,i+1));
            }
        }

        return objects;
    }

    private void setIsPlay(boolean IsPlaying) {
        this.isPlaying = IsPlaying;
    }

    private boolean isPlay() {
        return isPlaying;
    }


}

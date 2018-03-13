package com.ecar.epark.evoicelib;


import android.text.TextUtils;

import com.ecar.epark.greendaolib.entity.ResVoice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class VoiceUtils {

    /**
     * 播放泊位号 驶入驶出处理
     */
    public static void play(ResVoice resVoice) {
        if(resVoice!=null){
            List<ResVoice> mDatas =  resVoice.getMData();
            if(mDatas ==null || mDatas.isEmpty()){
                return;
            }
            for(ResVoice info:mDatas){
                handleAndPlay(info);
            }
        }
    }

    /**
     * 处理数据并播放
     */
    private static void handleAndPlay(ResVoice resVoice){
        List<String> list = null;//1驶入 2 、3 驶出 4/5欠费驶离
            if("1".equals(resVoice.getInOut())){//泊位 “10001” 有车驶入
                list = VoiceManager.strToList(resVoice.getCode(),3);
                list.add(0,VoiceManager.Ex_Berth);
                list.add(VoiceManager.Ex_Have_Vehicle_In);
            }
            else if("2".equals(resVoice.getInOut())){//车牌 “粤B 34M52” 驶出. 有车牌驶出
                String carplate = resVoice.getCarplate();
                if(TextUtils.isEmpty(carplate)){//防御车牌为空情况
                    list = getBerthOutList(resVoice);
                }else {
                    list = VoiceManager.strToList(resVoice.getCarplate(),0);
                    list.add(0,VoiceManager.Ex_License_Plate);
                    list.add(VoiceManager.Ex_Vehicle_Out);
                }
            }
            else if("3".equals(resVoice.getInOut())){//泊位 “10001” 有车驶出。无车牌驶出
                list = getBerthOutList(resVoice);
            }
            else if("4".equals(resVoice.getInOut())){//车牌 “粤B 34M52” 欠费驶离.
                String carplate = resVoice.getCarplate();
                if(TextUtils.isEmpty(carplate)){//防御车牌为空情况
                    list = getCarOutList(resVoice);
                }else {
                    list = VoiceManager.strToList(resVoice.getCarplate(),0);
                    list.add(0,VoiceManager.Ex_License_Plate);
                    list.add(VoiceManager.Ex_Arrears_Leave);
                }
            }
            else if("5".equals(resVoice.getInOut())){//泊位 “10001” 欠费驶离
                list = getCarOutList(resVoice);
            }
        VoiceManager.getInstance().play(list);
    }

    private static List<String> getBerthOutList(ResVoice resVoice){//泊位 “xxxx” 有车驶出
        List<String >list = VoiceManager.strToList(resVoice.getCode(),3);
        list.add(0,VoiceManager.Ex_Berth);
        list.add(VoiceManager.Ex_Have_Vehicle_Out);
        return list;
    }

    private static List<String> getCarOutList(ResVoice resVoice){//泊位 “xxxx” 欠费驶离
        List<String > list = VoiceManager.strToList(resVoice.getCode(),3);
        list.add(0,VoiceManager.Ex_Berth);
        list.add(VoiceManager.Ex_Arrears_Leave);
        return list;
    }

    /**
     * 播放签到、签出语音
     */
    public static void playSign(boolean isSignIn) {
        List<String> list = new ArrayList<>();
        if(isSignIn){
           list.add(VoiceManager.Ex_Sign_In);
       }else {
            list.add(VoiceManager.Ex_Sign_Out);
        }
        VoiceManager.getInstance().play(list);
    }

}

package com.ecar.epark.greendaolib.entity;


import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *
 */
@Entity
public class ResVoice extends ResBasePush<List<ResVoice>> {

    public static final String RX_PUSH_RESVOICE = "RX_PUSH_RESVOICE";

    public static final int Constant_Sign_In = 1001;//1001_签到
    public static final int Constant_Sign_Out = 1002;//1002_签出

    /**
     * Code : 436
     * InOut : 1
     */
    @Id
    private Long EVoiceId;//主键
    private String UserId;//用户id
//    bc st cn io
    @SerializedName(value = "CN",alternate = {"Carplate"})
    private String Carplate;//车牌
    @SerializedName(value = "BC",alternate = {"Code"})
    private String Code;//泊位号
    @SerializedName(value = "IO",alternate = {"InOut"})
    private String InOut;//驶入驶出标志
    @SerializedName(value = "ST",alternate = {"SendTime"})
    private long SendTime;//时间

    private String IsRead = "";//"1" 表示已读

    //签到使用
    private String SignContent;//签到签出内容

    @Generated(hash = 1415882320)
    public ResVoice(Long EVoiceId, String UserId, String Carplate, String Code, String InOut, long SendTime,
            String IsRead, String SignContent) {
        this.EVoiceId = EVoiceId;
        this.UserId = UserId;
        this.Carplate = Carplate;
        this.Code = Code;
        this.InOut = InOut;
        this.SendTime = SendTime;
        this.IsRead = IsRead;
        this.SignContent = SignContent;
    }

    @Generated(hash = 1235003931)
    public ResVoice() {
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getInOut() {
        return InOut;
    }


    public void setInOut(String inOut) {
        InOut = inOut;
    }

    public String getIsRead() {
        return IsRead;
    }

    public void setIsRead(String isRead) {
        this.IsRead = isRead;
    }

    public boolean isRead(){
        return "1".equals(IsRead);
    }

    public String getCarplate() {
        return Carplate;
    }

    public void setCarplate(String carplate) {
        Carplate = carplate;
    }

    public long getSendTime() {
        return SendTime;
    }

    public void setSendTime(long sendTime) {
        SendTime = sendTime;
    }

    public String getSignContent() {
        return SignContent;
    }

    public void setSignContent(String signContent) {
        this.SignContent = signContent;
    }


    /**
     * 是否是签到
     */
    public boolean isSignIn(){
        return !TextUtils.isEmpty(InOut) && InOut.equals(Constant_Sign_In+"");
    }

    /**
     * 是否是签出
     */
    public boolean isSignOut(){
        return !TextUtils.isEmpty(InOut) && InOut.equals(Constant_Sign_Out+"");
    }

    /**
     * 是否是泊位语音播报的类型
     */
    @Override
    public boolean isTheType() {
        String type = getBusiType();
        if (!TextUtils.isEmpty(type) && "1".equals(type)) {
            return true;
        }
        return false;
    }

    public String getBusiType() {
       return super.getBusiType();
    }

    /**
     * 创建签到签出 信息
     */
    public static String createSignBean(String content,long currTime,boolean isSignIn){
        int signType = isSignIn?Constant_Sign_In:Constant_Sign_Out;
        content = "{\"signContent\":\""+content+"\",\"SendTime\":"+currTime+",\"InOut\":\""+signType+"\"}";
        return content;
    }

    public Long getEVoiceId() {
        return this.EVoiceId;
    }

    public void setEVoiceId(Long eVoiceId) {
        this.EVoiceId = eVoiceId;
    }

    public String getUserId() {
        return this.UserId;
    }

    public void setUserId(String userId) {
        this.UserId = userId;
    }

}

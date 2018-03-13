package com.ecar.epark.greendaolib.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 签到签出信息类
 */

@Entity
public class ResSignInfo {


    private String signContent;//签到签出内容
    private String signType;//登记类型：1001_签到 2. 1002_签出
    private long addTime;//登记时间
    private String isRead = "";//是否查看 1.查看
    @Generated(hash = 174686791)
    public ResSignInfo(String signContent, String signType, long addTime,
            String isRead) {
        this.signContent = signContent;
        this.signType = signType;
        this.addTime = addTime;
        this.isRead = isRead;
    }
    @Generated(hash = 1829445932)
    public ResSignInfo() {
    }
    public String getSignContent() {
        return this.signContent;
    }
    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }
    public String getSignType() {
        return this.signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public long getAddTime() {
        return this.addTime;
    }
    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
    public String getIsRead() {
        return this.isRead;
    }
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}

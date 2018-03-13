package com.ecar.epark.greendaolib.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 推送数据基类
 */
public class ResBasePush<M> implements Serializable {


    /**
     * _extra :
     * _title : {"T":1,"Data":[{"Code":"436","InOut":1}]}
     * _id : P2017112114242653200000000000248
     * _content : {"T":1,"Data":[{"Code":"436","InOut":1}]}
     */

//    private ResBasePush _extra;
//    private String _title;
//    private String _id
    @SerializedName("_extra")
    private ResBasePush<M> extra;
    private String T;
    private M Data;
    private String exMsg;//将其他实体类信息复制进来，方便列表处理
    private String exMsgType;//区分消息类型 1001 签到 1002 签出。 空表示默认类型

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public String getExMsgType() {
        return exMsgType;
    }

    public void setExMsgType(String exMsgType) {
        this.exMsgType = exMsgType;
    }

    //    public String get_extra() {
//        return _extra;
//    }
//
//    public void set_extra(String _extra) {
//        this._extra = _extra;
//    }

//    public String get_title() {
//        return _title;
//    }
//
//    public void set_title(String _title) {
//        this._title = _title;
//    }
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }

    public ResBasePush get_extra() {
        return extra;
    }

    public void set_content(ResBasePush _content) {
        this.extra = _content;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }



    /**
     * 是否是所需要的类型。由子类重写 自定义
     *
     * @return
     */
    public boolean isTheType() {
        return false;
    }

    /**
     * 获取业务类型
     */
    public String getBusiType() {
        ResBasePush content = get_extra();
        if (content != null && !TextUtils.isEmpty(content.getT())) {
            return content.getT();
        }
        return null;
    }

    /**
     * 获取业务数据
     */
    public M getMData() {
        if (isTheType()) {
            if (get_extra() != null) {
                M data = (M) get_extra().getData();
                return data;
            }
        }
        return null;
    }

    public M getData() {
        return Data;
    }

}

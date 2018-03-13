package com.ecaray.basicres.entity.login;


import com.ecar.ecarnetwork.bean.ResBase;

import java.io.Serializable;

/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * @author : Eric_Huang
 * <p/>
 * 创建时间: 2016/5/9 15:41
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/5/9 15:41
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class VersionBean extends ResBase implements Serializable {

    /**
     * 更新状态
     * 0、不强制更新 1、强制更新
     */
    public static final int NOT_FORCE = 0;
    public static final int IS_FORCE = 1;

    /**
     * appcode : 10000
     * appname : roadpda
     * comid : 112
     * existNewVer : true
     * id : 20160509160430205848394879742231
     * info : 版本更新
     * isforce : 0
     * isrelease : 0
     * ngis : f5eb5fb62f3e4aba9d8bc7e11e3aad24
     * ts : 1463457573949
     * uploadtime : 2016-05-09 16:07:56.0
     * url : http://192.168.0.201:80/system/downClient?module=sys&service=File&method=downApp&type=app&fileName=apktext.apk
     */

    /**
     * 最新版本号
     */
    private int appcode;
    private String appname;
    private String comid;

    /**
     *是否存在新版本 (true-存在，false-否)
     */
    private String existNewVer;
    private String id;
    private String info;

    /**
     *是否强制更新 0：否 1：是
     */
    private int isforce;

    /**
     *是否替换
     */
    private int isrelease;
    private String uploadtime;

    /**
     *最新版本下载地址
     */
    private String url;


    public int getAppcode() {
        return appcode;
    }

    public void setAppcode(int appcode) {
        this.appcode = appcode;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getExistNewVer() {
        return existNewVer;
    }

    public void setExistNewVer(String existNewVer) {
        this.existNewVer = existNewVer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getIsforce() {
        return isforce;
    }

    public void setIsforce(int isforce) {
        this.isforce = isforce;
    }

    public int getIsrelease() {
        return isrelease;
    }

    public void setIsrelease(int isrelease) {
        this.isrelease = isrelease;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

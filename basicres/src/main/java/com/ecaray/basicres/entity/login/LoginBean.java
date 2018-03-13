package com.ecaray.basicres.entity.login;


import com.ecar.ecarnetwork.bean.ResBase;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * ===============================================
 * <p/>
 * 类描述: 登录信息
 * <p/>
 * @author : Eric_Huang
 * <p/>
 * 创建时间: 2016/4/28 16:26
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/4/28 16:26
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class LoginBean extends ResBase implements Serializable {

    /**
     * 签到状态
     * 1、已签到 2、已签出 3、未签到
     */
    public static final int HAS_SIGN_IN = 1;
    public static final int SIGN_OUT = 2;
    public static final int NOT_SIGN_IN = 3;

    public static final int HAS_DEFAULT_SECTION = 1;
    public static final int NOT_DEFAULT_SECTION = 2;


    /**
     * comid : 200000002
     * comname : 深圳道交中心
     * defaultsectionid : 20160504144927300872532621286564
     * isHaveDefaultSection : 1
     * ngis : 99c004dae79d8a673601c6ff7682ce6d
     * realname : 彭建威
     * sectionList : [{"areaname":"莲塘片区","canname":"罗湖区","latitude":"21","longitude":"12","sectionid":"20160506141305058453141564751267","sectionname":"宝安南路"},{"areaname":"东门片区","canname":"罗湖区","latitude":"21.62","longitude":"12.20","sectionid":"20160506142200454848394879742231","sectionname":"东门大道"}]
     * signstate : 1
     * t : 2943385021522627714890
     * ts : 1463045294338
     * u : 20160406101516396124029674060346
     * username : pjw
     * v : 20160512172814338905518382038186
     */

    private String comid;
    private String comname;
    private String defaultsectionid = "";
    private int isHaveDefaultSection;
    private String realname;
    private int signstate;
    private String username;
    private int squadronLeader;
    /**
     * 签到后的sectionid
     */
    private String sectionid = "";

    /**
     * 签到后的sectionname
     */
    private String sectionname = "";

    /**
     * PDA编号(合肥需要)
     */
    private String customcode;

    /**
     * 批次号（南昌使用）
     */
    String BatchNo;

    /**
     * 终端号（南昌使用）
     */
    String Posid;

    /**
     *最小购买时长(南昌使用)
     */
    int minpaytime;

    /**
     *管理员电话(南昌使用)
     */
    private String tel;

    /**
     * 行政区id
     */
    @SerializedName("cantonid")
    private String cantonId;

    /**
     * 片区id
     */
    @SerializedName("areaid")
    private String areaId;

    /**
     * 用户所属部门id
     */
    @SerializedName("deptid")
    private String deptId;

    @SerializedName("push")
    private PushConnBean push;

    /**
     * areaname : 莲塘片区
     * canname : 罗湖区
     * latitude : 21
     * longitude : 12
     * sectionid : 20160506141305058453141564751267
     * sectionname : 宝安南路
     */
    /**
     * 路段信息
     */
    private List<SectionListBean> sectionList;
    private List<TitleListBean> listtile;
    /**
     * 岗位信息
     */
    private List<PositionListBean> positionList;

    /**
     * 添加 良渚二维码扎帐地址 tieaccountaddr
     */
    private String tieaccountaddr;

    /**
     * 电子发票地址前缀invoiceaddr
     */
    private String invoiceaddr;

    public String getInvoiceaddr() {
        return invoiceaddr;
    }

    public void setInvoiceaddr(String invoiceaddr) {
        this.invoiceaddr = invoiceaddr;
    }

    public PushConnBean getPush() {
        return push;
    }

    public void setPush(PushConnBean push) {
        this.push = push;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getDefaultsectionid() {
        return defaultsectionid;
    }

    public void setDefaultsectionid(String defaultsectionid) {
        this.defaultsectionid = defaultsectionid;
    }

    public int getIsHaveDefaultSection() {
        return isHaveDefaultSection;
    }

    public void setIsHaveDefaultSection(int isHaveDefaultSection) {
        this.isHaveDefaultSection = isHaveDefaultSection;
    }

    public String getNgis() {
        return sign;
    }

    public void setNgis(String ngis) {
        this.sign = ngis;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getSignstate() {
        return signstate;
    }

    public void setSignstate(int signstate) {
        this.signstate = signstate;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public String getPosId() {
        return Posid;
    }

    public List<SectionListBean> getSectionList() {
        return sectionList;
    }



    public List<TitleListBean> getTitleList() {
        return listtile;
    }

    public List<PositionListBean> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<PositionListBean> positionList) {
        this.positionList = positionList;
    }

    public int getSquadronLeader() {
        return squadronLeader;
    }

    public void setSectionList(List<SectionListBean> sectionList) {
        this.sectionList = sectionList;
    }

    public String getCustomCode() {
        return customcode;
    }

    public int getMinPayTime() {
        return minpaytime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getCantonId() {
        return cantonId;
    }

    public String getTieAccountAddr() {
        return tieaccountaddr;
    }

    public String getSectionid() {
        return sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public static class SectionListBean implements Serializable {
        private String areaname;
        private String canname;
        private String latitude;
        private String longitude;
        private String sectionid;
        private String sectionname;
        /**
         * 台州引入，获取sectionid使用
         */
        private String sectioncode;

        private boolean checked;

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getCanname() {
            return canname;
        }

        public void setCanname(String canname) {
            this.canname = canname;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getSectionid() {
            return sectionid;
        }

        public void setSectionid(String sectionid) {
            this.sectionid = sectionid;
        }

        public String getSectionname() {
            return sectionname;
        }

        public void setSectionname(String sectionname) {
            this.sectionname = sectionname;
        }

        public String getSectioncode() {
            return sectioncode;
        }

        public void setSectioncode(String sectioncode) {
            this.sectioncode = sectioncode;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

    public static class TitleListBean implements Serializable {

        public TitleListBean(String paraName, String paraValue) {
            this.paraName = paraName;
            this.paraValue = paraValue;
        }

        @SerializedName(value = "paraname")
        public String paraName;
        @SerializedName(value = "paravalue")
        public String paraValue;
    }

    /**
     * 岗位信息
     */
    public static class PositionListBean implements Serializable {
        private String positionId;
        private String positionName;

        public PositionListBean(String positionId, String positionName) {
            this.positionId = positionId;
            this.positionName = positionName;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }
    }
}

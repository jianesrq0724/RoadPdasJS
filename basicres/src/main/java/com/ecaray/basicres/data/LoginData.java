package com.ecaray.basicres.data;

import android.text.TextUtils;


import com.ecaray.basicres.entity.login.LoginBean;
import com.ecaray.basicres.util.ObjUtils;
import com.ecaray.basicres.util.SysServiceUtils;

import java.util.List;

/**
 * 类描述: 登录用户数据类
 * 创建人: Eric_Huang
 * 创建时间: 2016/9/8 19:31
 * 修改人:Eric_Huang
 * 修改时间: 2016/9/8 19:31
 */
public class LoginData {

    public static LoginData sLoginData;
    public static LoginBean sLoginBean;


    public LoginData() {
    }

    public static synchronized LoginData getInstance() {
        if (sLoginBean == null) {
            sLoginBean = (LoginBean) ObjUtils.getObject(ObjUtils.OBJECT_WRITE_LOGIN);
        }
        sLoginData = new LoginData();
        return sLoginData;
    }

    public String getU() {
        if (sLoginBean != null) {
            return sLoginBean.getU();
        } else {
            return "";
        }
    }

    public String getV() {
        if (sLoginBean != null) {
            return sLoginBean.getV();
        } else {
            return "";
        }
    }

    public String getT() {
        if (sLoginBean != null) {
            return sLoginBean.getT();
        } else {
            return "";
        }
    }

    public String getComId() {
        if (sLoginBean != null) {
            return sLoginBean.getComid();
        } else {
            return "";
        }
    }

    public String getDefaultSectionId() {
        if (sLoginBean != null) {
            return sLoginBean.getDefaultsectionid();
        } else {
            return "-1";
        }
    }

    public String getRealName() {
        if (sLoginBean != null) {
            return sLoginBean.getRealname();
        } else {
            return "";
        }
    }

    public String getUserName() {
        if (sLoginBean != null) {
            return sLoginBean.getUsername();
        } else {
            return "";
        }
    }

    /**
     * 片区id
     */
    public String getAreaId() {
        if (sLoginBean != null) {
            return sLoginBean.getAreaId();
        } else {
            return "";
        }
    }

    /**
     * 行政区id
     */
    public String getCantonId() {
        if (sLoginBean != null) {
            return sLoginBean.getCantonId();
        } else {
            return "";
        }
    }

    /**
     * 扫码开发票前缀地址
     */
    public String getInvoiceaddr() {
        if (sLoginBean != null) {
            return sLoginBean.getInvoiceaddr();
        } else {
            return "";
        }
    }

    public List<LoginBean.SectionListBean> getSectionList() {
        if (sLoginBean != null) {
            return sLoginBean.getSectionList();
        } else {
            return null;
        }
    }

    /**
     * 设置sectionList的值
     *
     * @param sectionList
     */
    public void setSectionList(List<LoginBean.SectionListBean> sectionList) {
        if (sLoginBean != null) {
            sLoginBean.setSectionList(sectionList);
            //将更改的对象重新保存到文件中
            ObjUtils.saveObject(ObjUtils.OBJECT_WRITE_LOGIN, sLoginBean);
        }
    }

    public List<LoginBean.TitleListBean> getTitleList() {
        if (sLoginBean != null) {
            return sLoginBean.getTitleList();
        } else {
            return null;
        }
    }


    public String getComName() {
        if (sLoginBean != null) {
            return sLoginBean.getComname();
        } else {
            return "";
        }
    }

    /**
     * 获取设备自定义编号(合肥使用)
     *
     * @return
     */
    public String getCustomCode() {
        if (sLoginBean != null && !TextUtils.isEmpty(sLoginBean.getCustomCode())) {
            return sLoginBean.getCustomCode();
        } else {
            return SysServiceUtils.getIMEI();
        }
    }

    /**
     * 获取PODID用作一卡通刷卡(南昌使用)
     */
    public String getPosId() {
        if (sLoginBean != null) {
            return sLoginBean.getPosId();
        } else {
            return "";
        }
    }


    /**
     * 南昌一卡通刷卡使用
     */
    public String getBatchNo() {
        if (sLoginBean != null) {
            return sLoginBean.getBatchNo();
        } else {
            return "";
        }
    }

    /**
     * 是否是中队长(株洲使用)
     */
    public boolean isSquadronLeader() {
        return sLoginBean != null && sLoginBean.getSquadronLeader() == 1;
    }

    public int getMinPayTime() {
        if (sLoginBean != null) {
            return sLoginBean.getMinPayTime();
        } else {
            return 0;
        }
    }

    public String getTel() {
        if (sLoginBean != null) {
            return sLoginBean.getTel();
        } else {
            return "";
        }
    }

    public String getDeptId() {
        if (sLoginBean != null) {
            return sLoginBean.getDeptId();
        } else {
            return "";
        }
    }

    /**
     * 每次重新登录都需要充值，否则会拿到上次的数据
     */
    public void reset() {
        sLoginBean = null;
    }

    /**
     * 获取路段列表
     *
     * @return
     */
    public List<LoginBean.PositionListBean> getPositionList() {
        if (sLoginBean != null) {
            return sLoginBean.getPositionList();
        } else {
            return null;
        }
    }

    /**
     * 获取签到sectionId
     */
    public String geSectionId() {
        if (sLoginBean != null) {
            return sLoginBean.getSectionid();
        } else {
            return "-1";
        }
    }

    public String getSectionName() {
        if (sLoginBean != null) {
            return sLoginBean.getSectionname();
        } else {
            return "-1";
        }
    }


    /**
     * 是否有登录信息
     *
     * @return
     */
    public boolean isPush() {
        if (sLoginBean != null) {
            return sLoginBean.getPush() != null;
        } else {
            return false;
        }
    }
}

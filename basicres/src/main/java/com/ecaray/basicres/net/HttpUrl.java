package com.ecaray.basicres.net;


import com.ecaray.basicres.util.BuildConfigUtils;

import urils.ecaray.com.ecarutils.Utils.DataFormatUtil;

public class HttpUrl {
    /**
     * 环境
     * 0、开发环境 1、测试环境 2、正式环境
     */
    public static final int GET_DEVELOP = 0;
    public static final int GET_TEST = 1;
    public static final int GET_RELEASE = 2;

    /**
     * 一般url
     */
    public static String Url_Common;

    /**
     * 上传下载url
     */
    public static String Url_DownUp;
    public static String Base_Url;
    public static String Base_Url_upClient;

    static {
        getUrl(BuildConfigUtils.getInstance().getUrlType());
    }

    /**
     * 配置服务器请求地址
     */

    private static void getUrl(int index) {
        StringBuilder sb = new StringBuilder();
        //根据Build.gradle相关配置定义
        Url_Common = BuildConfigUtils.getInstance().getUrlCommon();
        Url_DownUp = BuildConfigUtils.getInstance().getUrlDwonUp();
        switch (index) {
            // 开发环境
            case GET_DEVELOP:
                // 测试环境
            case GET_TEST:
                Base_Url = DataFormatUtil.addText(sb, Url_Common, "/std/");
                Base_Url_upClient = DataFormatUtil.addText(sb, Url_DownUp, "/std/upClient");
                break;

            // 正式环境
            case GET_RELEASE:
                Base_Url = DataFormatUtil.addText(sb, Url_Common, "/system/");
                Base_Url_upClient = DataFormatUtil.addText(sb, Url_DownUp, "/fs/upClient");
                break;
            default:
                break;
        }
    }
}

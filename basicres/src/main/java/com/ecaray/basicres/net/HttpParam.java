package com.ecaray.basicres.net;

import android.text.TextUtils;

import com.ecar.ecarnetwork.util.major.Major;
import com.ecar.encryption.Epark.EparkEncrypUtil;
import com.ecar.factory.EncryptionUtilFactory;
import com.ecaray.basicres.data.LoginData;
import com.ecaray.basicres.util.BuildConfigUtils;
import com.ecaray.basicres.util.InvalidUtil;
import com.ecaray.basicres.util.StringUtils;
import com.ecaray.basicres.util.SysServiceUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import urils.ecaray.com.ecarutils.Utils.DataFormatUtil;

/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/8/31 14:07
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/8/31 14:07
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class HttpParam {

    private static final String PARAMS_MODULE = "module";
    private static final String PARAMS_METHOD = "method";
    private static final String PARAMS_SERVICE = "service";
    //设备唯一编号
    private static final String PARAMS_IMEI = "terminalno";

    private static final String PARAMS_U_KEY = "u";
    private static final String PARAMS_V_KEY = "v";

    public static TreeMap<String, String> getSerialParamsMap() {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_MODULE, "plo");
        lMap.put(PARAMS_METHOD, "getSerialCode");
        lMap.put(PARAMS_SERVICE, "SerialCode");
        return lMap;
    }

    /**
     * 不需要验证登录信息调用的Map
     */
    public static TreeMap<String, String> getNormalParamsMap(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_MODULE, "pda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Std");
        lMap.put(PARAMS_IMEI, SysServiceUtils.getIMEI());
        return lMap;
    }

    /**
     * 不需要验证登录信息调用的Map
     */
    public static TreeMap<String, String> getNormalParamsMap4DownLoad(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_MODULE, "pda");

        if (BuildConfigUtils.getInstance().getFlavor().equals("feixi")) {
            lMap.put("appname", "roadpdafeixi");
        } else if (BuildConfigUtils.getInstance().getFlavor().equals("feidong")) {
            lMap.put("appname", "roadpdafeidong");
        } else if (BuildConfigUtils.getInstance().getFlavor().equals("lujiang")) {
            lMap.put("appname", "roadpdalujiang");
        } else if (BuildConfigUtils.getInstance().getFlavor().equals("hefeiSupervision")) {
            lMap.put("appname", "roadpdaducha");
        } else {
            lMap.put("appname", "roadpda");
        }

        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Std");
        lMap.put(PARAMS_IMEI, SysServiceUtils.getIMEI());
        return lMap;
    }

    /**
     * 需要验证登录信息调用的Map
     */
    public static TreeMap<String, String> getNormalParamsMapWithU(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "pda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Std");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 需要验证登录信息调用的Map(南昌定制)
     */
    public static TreeMap<String, String> getNormalParamsMapWithUNC(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "ncpda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "PdaStd");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 新的service
     */
    public static TreeMap<String, String> getNormalParamsMapWith(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "trade");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "PdaStd");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 需要验证登录信息调用的Map
     */
    public static TreeMap<String, String> getServiceParamsMapWithU(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "pda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "PdaMeb");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 一卡通对接调用的Map
     */
    public static TreeMap<String, String> getParamsMapWithU4Card(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "rcomp");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "OneCard");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 新的module 西安黑卡 xianpda
     */
    public static TreeMap<String, String> getParamsMapWithU5Card(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "xianpda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "PdaStd");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /**
     * 银联支付调用
     */
    public static TreeMap<String, String> getParamsMapWith4UnionCard(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "pda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "UnionPayTrade");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    //上传图片
    public static TreeMap<String, String> getImgUploadNewParamsMap() {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "sys");
        lMap.put(PARAMS_METHOD, "upLoadPhoto");
        lMap.put(PARAMS_SERVICE, "File");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }


    //发票配置
    public static TreeMap<String, String> getInvoiceParamsMap(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "invoice");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "InvoiceManager");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    //根据泊位号搜索泊位信息
    public static TreeMap<String, String> getOrderInfoByBerthcode(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "hfcomp");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Pda");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    //根据泊位号获取路段名称及信息
    public static TreeMap<String, String> getSecionNameByBerthcode(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "pda");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Std");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    //获取该车牌停车明细
    public static TreeMap<String, String> getUserTraListToday(String method) {
        TreeMap<String, String> lMap = new TreeMap<>();
        lMap.put(PARAMS_U_KEY, LoginData.getInstance().getU());
        lMap.put(PARAMS_V_KEY, LoginData.getInstance().getV());
        lMap.put(PARAMS_MODULE, "shcomp");
        lMap.put(PARAMS_METHOD, method);
        lMap.put(PARAMS_SERVICE, "Pda");
        lMap.put(PARAMS_IMEI, LoginData.getInstance().getCustomCode());
        return lMap;
    }

    /****************************** 获取三种keyMap ****************************************/

    /**
     * 进行编码
     *
     * @param tMap 转化的map
     * @return 转化后的map
     */
    public static TreeMap<String, String> securityKeyMethodEnc(TreeMap<String, String> tMap) {
        tMap.put("appId", Major.eUtil.binstrToStr(BuildConfigUtils.getInstance().getAppId()));
        tMap.put("ve", "2");
        return Major.securityKeyMethodEnc(tMap);
    }


    /**
     * 不进行编码
     *
     * @param tMap 转化的map
     * @return 转化后的map
     */
    public static TreeMap<String, String> securityKeyMethodNoEnc(TreeMap<String, String> tMap) {
        tMap.put("appId", Major.eUtil.binstrToStr(BuildConfigUtils.getInstance().getAppId()));
        tMap.put("ve", "2");
        return Major.securityKeyMethodNoEnc(tMap);
    }

    /**
     * 重新编码(上传图片)
     *
     * @param tMap 转化的map
     */
    public static TreeMap<String, String> getSecurityMapKeys(TreeMap<String, String> tMap) {
        tMap.put("appId", Major.eUtil.binstrToStr(BuildConfigUtils.getInstance().getAppId()));
        tMap.put("ve", "2");
        return Major.securityKeyMethodEnc(tMap, false, false, true);
    }

    public static EparkEncrypUtil eUtil = EncryptionUtilFactory.getDefault(BuildConfigUtils.getInstance().isDebug()).createEpark();

    /**
     * 获取文通序列号，因为是蜜蜂的接口，加密方式不同
     */
    public static TreeMap<String, String> getSecurityMapKeys4Serial(TreeMap<String, String> tMap) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = tMap.keySet();
        Iterator<String> iterator = keys.iterator();
        String parmas = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = tMap.get(key);
            parmas = DataFormatUtil.addText(sb, parmas, key, "=", value, "&");
        }
        parmas = StringUtils.addText(sb, parmas, "requestKey=",
                eUtil.binstrToStr("1000100 110011 110000 110010 111001 1000011 110111 110011 110100 110000 110110 110010 110010 110001 1000010 110000 110010 110000 110010 110110 1000010 110110 111000 110100 1000010 1000010 110000 110000 110101 110111 111001 1000011"));
        String md5 = eUtil.getMD5Code(parmas);

        tMap.remove("requestKey");
        tMap.put("sign", md5);

        return tMap;
    }

    /**
     * 重新编码
     *
     * @param tMap     转化的map
     * @param isEnCode 是否编码
     * @param isSign   是否需要验证
     * @return 转化后的map
     */
    public static TreeMap<String, String> getSecurityMapKeys(TreeMap<String, String> tMap, boolean isEnCode, boolean isSign) {
        StringBuilder lStringBuilder = new StringBuilder();

        if (!TextUtils.isEmpty(BuildConfigUtils.getInstance().getAppId())) {
            tMap.put("ve", "2");
            if (isSign) {
                tMap.put("appId", StringUtils.BinstrToStr(BuildConfigUtils.getInstance().getAppId()));
            } else {
                tMap.put("appId", "");
            }
        }

        Set<String> keys = tMap.keySet();
        Iterator<String> iterator = keys.iterator();
        String parmas = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = tMap.get(key);
            parmas = InvalidUtil.addText(lStringBuilder, parmas, key, "=", value, "&");
        }
        parmas = DataFormatUtil.addText(lStringBuilder, parmas, "requestKey=",
                StringUtils.BinstrToStr(BuildConfigUtils.getInstance().getRequestKey()));
        String md5 = InvalidUtil.md5(parmas).toString();
        tMap.remove("requestKey");
        for (String key : keys) {
            String value = tMap.get(key);
            if (isEnCode) {
                value = InvalidUtil.getEncodedStr(value);
                tMap.put(key, value);
            }
        }
        if (isSign) {
            tMap.put("sign", md5);
        }
        return tMap;
    }

}

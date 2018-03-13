package com.ecaray.basicres.util;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * ===============================================
 * <p/>
 * 类描述: 定位对应城市，获取省份简称
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/7/14 16:58
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/7/14 16:58
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class ProvinceUtils {

    private static String[] provincesFuName = {"北京", "上海", "浙江", "苏州", "广东", "山东", "山西", "河北", "河南",
            "四川", "重庆", "辽宁", "吉林", "黑龙江", "安徽", "湖北", "湖南", "江西", "福建", "陕西", "甘肃", "宁夏",
            "内蒙古", "天津", "贵州", "云南", "广西", "海南", "青海", "新疆", "西藏"};

    private static String[] provinces = {"京", "沪", "浙", "苏", "粤", "鲁", "晋", "冀", "豫",
            "川", "渝", "辽", "吉", "黑", "皖", "鄂", "湘", "赣", "闽", "陕", "甘", "宁",
            "蒙", "津", "贵", "云", "桂", "琼", "青", "新", "藏"};

    public static String getProvince(){
        String lProvince = SPUtils.get(SPKeyUtils.s_Province,"").toString();
        List<String> listProFuName = Arrays.asList(provincesFuName);
        int indexOf = listProFuName.indexOf(lProvince);
        if (!TextUtils.isEmpty(lProvince) && indexOf != -1) {
            return provinces[indexOf];
        }
        return "";
    }
}

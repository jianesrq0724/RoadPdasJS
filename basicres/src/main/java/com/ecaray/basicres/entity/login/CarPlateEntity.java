package com.ecaray.basicres.entity.login;

import com.ecar.ecarnetwork.bean.ResBase;

import java.io.Serializable;
import java.util.List;

/**
 * /**
 * 类描述:
 * 创建人: Vlice
 * 创建时间:2017年6月1日11:14:56
 * 修改人:Vlice
 * 修改时间: 2017年6月1日11:15:07
 */

public class CarPlateEntity extends ResBase {
    /**
     * index : 1
     * name : 沪
     */
    public List<DataBean> data;
    public static class DataBean implements Serializable {
        public int index;
        public String name;
    }
}

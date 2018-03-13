package com.ecaray.basicres.entity;

import com.ecar.ecarnetwork.bean.ResBase;

/**
 * 类描述:
 * @author : Eric_Huang
 * 创建时间: 2017/5/19 11:03
 */
public class UpLoadLocationBean extends ResBase {

    /**
     * 离岗
     */
    public static final int DEPARTURE = 1;

    /**
     * 没有离岗
     */
    public static final int DEPARFALSE = 2;

    /**
     * 离岗标志（1-是，2-否）
     */
    public int departure;

}

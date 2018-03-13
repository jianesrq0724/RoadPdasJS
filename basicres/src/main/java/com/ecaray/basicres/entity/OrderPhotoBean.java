package com.ecaray.basicres.entity;

/**
 * 类描述: 订单图片实体类
 * @author : Shirley
 * 创建日期: 2016/11/23 16:10
 */
public class OrderPhotoBean {
    /**
     * 类型
     */
    public String type;

    /**
     * 地址
     */
    public String path;

    /**
     * 关联id
     */
    public String orderId;

    /**
     * 文件名字
     */
    public String fileName;

    public OrderPhotoBean() {
    }

    public OrderPhotoBean(String type, String path, String orderId, String fileName) {
        this.type = type;
        this.path = path;
        this.orderId = orderId;
        this.fileName = fileName;
    }
}

package com.ecaray.basicres.view.layout.entity;

/**
 * 类描述: 订单一项布局的实体类
 * @author : Shirley
 * 创建日期: 2017/3/28 10:41
 */
public class OrderLayoutBean {

    /**
     * 内容类型
     * 1、普通文本 2、虚线
     */
    public static final int TXT_NORMAL = 1;
    public static final int DOT_LINE = 2;

    /**
     * 内容
     */
    public String content;

    /**
     *字体样式
     */
    public int fontStyle = -1;

    /**
     *内容位置
     */
    public int gravity = -1;

    /**
     *内容类型
     */
    public int contentType = TXT_NORMAL;

    /**
     * @param contentType 内容类型
     */
    public OrderLayoutBean(int contentType) {
        this.contentType = contentType;
    }

    /**
     * @param content 内容
     * @param fontStyle 字体样式
     */
    public OrderLayoutBean(String content, int fontStyle) {
        this.content = content;
        this.fontStyle = fontStyle;
    }

    /**
     * @param content 内容
     * @param fontStyle 字体样式
     * @param contentType 内容类型
     */
    public OrderLayoutBean(String content, int fontStyle, int contentType) {
        this.content = content;
        this.fontStyle = fontStyle;
        this.contentType = contentType;
    }

    /**
     *
     * @param content 内容
     * @param fontStyle 字体样式
     * @param gravity 内容位置
     * @param contentType 内容类型
     */
    public OrderLayoutBean(String content, int fontStyle, int gravity, int contentType) {
        this.content = content;
        this.fontStyle = fontStyle;
        this.gravity = gravity;
        this.contentType = contentType;
    }
}

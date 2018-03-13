package com.ecaray.basicres.exception;

/**
 * ===============================================
 * <p>
 * 类描述: 约定异常
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/5 14:19
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/5 14:19
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class Error {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;

}

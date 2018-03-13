package com.ecaray.basicres.exception.ExceptionType;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/5 10:06
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/5 10:06
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ApiException extends Throwable {

    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

}

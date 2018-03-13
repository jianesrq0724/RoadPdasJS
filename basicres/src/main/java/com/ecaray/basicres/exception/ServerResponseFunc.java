package com.ecaray.basicres.exception;

import com.ecar.ecarnetwork.bean.ResBase;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/2 15:44
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/2 15:44
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ServerResponseFunc<T extends ResBase> {//implements Func1<T, T>{

//    @Override
//    public T call(T reponse) {
//        //对返回码进行判断，如果不是0，则证明服务器端返回错误信息了，便根据跟服务器约定好的错误码去解析异常
//        if (reponse.state != 1) {
//            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
//            throw new ServerException(reponse.code, reponse.msg);
//        }
//
//        //服务器请求数据成功，返回里面的数据实体
//        return reponse;
//    }
}

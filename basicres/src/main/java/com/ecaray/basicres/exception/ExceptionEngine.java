package com.ecaray.basicres.exception;

import com.ecaray.basicres.exception.ExceptionType.ApiException;
import com.ecaray.basicres.exception.ExceptionType.ServerException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * @author : Eric_Huang
 * <p>
 * 创建时间: 2016/9/5 10:12
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/5 10:12
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e){

        ApiException lApiException;

        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            lApiException = new ApiException(e, Error.HTTP_ERROR);
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    //均视为网络错误
                    lApiException.message = "网络错误";
                    break;
            }
            return lApiException;
            //服务器返回的错误
        } else if (e instanceof ServerException){
            ServerException resultException = (ServerException) e;
            lApiException = new ApiException(resultException, resultException.code);
            lApiException.message = resultException.message;
            return lApiException;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            lApiException = new ApiException(e, Error.PARSE_ERROR);
            //均视为解析错误
            lApiException.message = "解析错误";
            return lApiException;
        }else if(e instanceof ConnectException){
            lApiException = new ApiException(e, Error.NETWORD_ERROR);
            //均视为网络错误
            lApiException.message = "连接失败";
            return lApiException;
        }else {
            lApiException = new ApiException(e, Error.UNKNOWN);
            //未知错误
            lApiException.message = "未知错误";
            return lApiException;
        }
    }
}

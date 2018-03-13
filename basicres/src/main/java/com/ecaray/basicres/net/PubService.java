package com.ecaray.basicres.net;

import com.ecar.ecarnetwork.bean.ResBase;
import com.ecaray.basicres.entity.UpLoadLocationBean;
import com.ecaray.basicres.entity.login.VersionBean;

import java.util.TreeMap;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/1 14:09
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/1 14:09
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public interface PubService{

    String lastPath = "data";

    @GET(lastPath)
    Flowable<UpLoadLocationBean> upLoadTrack(@QueryMap TreeMap<String, String> map);

    @Multipart
    @POST("")
    Flowable<ResBase> upLoadImg(@Url String url, @QueryMap TreeMap<String, String> map, @Part MultipartBody.Part file);

    @GET(lastPath)
    Flowable<VersionBean> checkUpdate(@QueryMap TreeMap<String, String> map);

}

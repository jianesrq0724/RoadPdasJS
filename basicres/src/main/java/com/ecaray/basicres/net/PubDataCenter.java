package com.ecaray.basicres.net;

import com.ecar.ecarnetwork.bean.ResBase;
import com.ecar.ecarnetwork.http.api.ApiBox;
import com.ecaray.basicres.entity.UpLoadLocationBean;
import com.ecaray.basicres.data.LoginData;
import com.ecaray.basicres.entity.login.VersionBean;
import com.ecaray.basicres.util.LogUtils;
import com.ecaray.basicres.util.PhotoUtils;
import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;


import java.io.File;
import java.util.TreeMap;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * @author : Eric_Huang
 * <p>
 * 创建时间: 2016/9/1 14:10
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/1 14:10
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class PubDataCenter {

    public static PubDataCenter sPubDataCenter;
    private static PubService sPubService;
    private PubDataCenter() {
    }

    public static synchronized PubDataCenter getInstance() {
        if (sPubDataCenter == null) {
            sPubDataCenter = new PubDataCenter();
        }
        if(sPubService==null){
            sPubService = ApiBox.getInstance().createService(PubService.class, HttpUrl.Base_Url);
        }
        return sPubDataCenter;
    }

    /**
     * 12.轨迹上传
     * @param longitude 经度
     * @param latitude  纬度
     * @param placeName 地址名称
     * @return Flowable
     */
    public Flowable<UpLoadLocationBean> uploadTrack(double longitude, double latitude, String placeName){

        String lMETHO = "uploadTrack";
        TreeMap<String, String> lTreeMap = HttpParam.getNormalParamsMapWithU(lMETHO);
        lTreeMap.put("comid", LoginData.getInstance().getComId());
        lTreeMap.put("longitude", String.valueOf(longitude));
        lTreeMap.put("latitude", String.valueOf(latitude));
        lTreeMap.put("userid", LoginData.getInstance().getU());
        lTreeMap.put("placename",  placeName);
        lTreeMap.put("sectionid", (String) SPUtils.get(SPKeyUtils.S_LAST_SELECT_SECTION_ID, "-1"));
        TreeMap<String , String> lEncTreeMap = HttpParam.securityKeyMethodNoEnc(lTreeMap);

        return sPubService.upLoadTrack(lEncTreeMap);
    }

    /**
     * 14.上传图片接口
     * description 签到 type="pdasignin" 签出 type="pdasignout" 申请订单 type="startparking"
     *
     */
    public Flowable<ResBase> upLoadPic(String refId, String type, String fileName, File file){
        TreeMap<String, String> lTreeMap = HttpParam.getImgUploadNewParamsMap();
        //关联id
        lTreeMap.put("refid", refId);
        lTreeMap.put("type", type);
        lTreeMap.put("fileName", fileName + PhotoUtils.JPEG_FILE_SUFFIX);
        lTreeMap.put("comid", LoginData.getInstance().getComId());
        TreeMap<String, String> lEncTreeMap = HttpParam.getSecurityMapKeys(lTreeMap);
        //"这里是模拟文件的内容"
        RequestBody fileOne = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("content", fileName, fileOne);
        return sPubService.upLoadImg(HttpUrl.Base_Url_upClient, lEncTreeMap, filePart);
    }

    /**
     * 检查更新
     * @param versionCode 版本信息
     * @return Flowable
     */
    public Flowable<VersionBean> checkUpdate(int versionCode){
        String lMETHOD_CHECK_UPDATE = "getPDAVersion";
        TreeMap<String, String> lTreeMap = HttpParam.getNormalParamsMap4DownLoad(lMETHOD_CHECK_UPDATE);
        lTreeMap.put("version", String.valueOf(versionCode));
        TreeMap<String , String> lEncTreeMap = HttpParam.securityKeyMethodEnc(lTreeMap);
        LogUtils.i(lEncTreeMap.toString());
        return sPubService.checkUpdate(lEncTreeMap);
    }

}

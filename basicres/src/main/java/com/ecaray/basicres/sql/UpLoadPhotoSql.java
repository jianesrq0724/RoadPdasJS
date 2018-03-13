package com.ecaray.basicres.sql;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;


import com.ecaray.basicres.util.LogUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 类描述: 上传图片的数据库
 * @author : Eric_Huang
 * 创建时间: 2017/1/6 10:07
 */
public class UpLoadPhotoSql extends DataSupport {

    private static final String TAG = "上传图片数据库";

    /**
     * 类型
     */
    private String mType;

    /**
     * 地址
     */
    private String mPath;

    /**
     * 关联id
     */
    private String mOrderId;

    /**
     * 文件名字
     */
    private String mFileName;

    /**
     *是否添加水印
     */
    private String IsAddWaterMark;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public boolean isAddWaterMark() {
        return Boolean.parseBoolean(IsAddWaterMark);
    }

    public void setAddWaterMark(boolean addWaterMark) {
        IsAddWaterMark = String.valueOf(addWaterMark);
    }

    /**
     * 上传图片添加到数据库
     *
     * @param type     图片类型
     * @param path     路径
     * @param orderId  订单id
     * @param fileName 图片文件
     */
    public static void add2Sql(String type, String path, String orderId, String fileName) {
        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(path) || TextUtils.isEmpty(orderId) || TextUtils.isEmpty(fileName)) {
            LogUtils.i(TAG, "添加数据库失败");
            return;
        }
        UpLoadPhotoSql lUpLoadPhotoSql = new UpLoadPhotoSql();
        lUpLoadPhotoSql.setType(type);
        lUpLoadPhotoSql.setPath(path);
        lUpLoadPhotoSql.setOrderId(orderId);
        lUpLoadPhotoSql.setFileName(fileName);
        //添加数据默认为没有打水印的
        lUpLoadPhotoSql.setAddWaterMark(false);
        lUpLoadPhotoSql.save();
        Log.i(TAG, "添加数据成功");
    }

    /**
     * 更改数据库中的数据，没有打水印的状态更改为打了水印
     *
     * @param orderId        订单id
     * @param isAddWaterMark 添加水印
     */
    public static void changeData2Sql(String orderId, boolean isAddWaterMark) {
        ContentValues values = new ContentValues();
        values.put("IsAddWaterMark", String.valueOf(isAddWaterMark));
        DataSupport.updateAll(UpLoadPhotoSql.class, values, "mOrderId = ?", orderId);
        Log.i(TAG, "更改数据成功");
    }

    /**
     * 从数据库中删除图片
     *
     * @param orderId 订单id
     */
    public static void delete4SqlWithOrderId(String orderId, String type) {
        DataSupport.deleteAll(UpLoadPhotoSql.class, "mOrderId = ? and mType = ?", orderId, type);
        Log.i(TAG, "删除对应图片数据");
    }

    /**
     *  从数据库中删除图片 根据路径
     * @param mpath
     */
    public static void delete4SqlWithPath(String mpath) {
        DataSupport.deleteAll(UpLoadPhotoSql.class, "mpath = ?", mpath);
        Log.i(TAG, "删除对应图片数据");
    }

    /**
     * 查找已经添加水印图片
     *
     * @return 已添加水印列表
     */
    public static List<UpLoadPhotoSql> findHadAddWaterMarkData() {
        return DataSupport.where("IsAddWaterMark = ?", "true").find(UpLoadPhotoSql.class);
    }

    /**
     * 查找没有添加水印的图片
     *
     * @return 没添加水印列表
     */
    public static List<UpLoadPhotoSql> findNoAddWaterMarkData() {
        return DataSupport.where("IsAddWaterMark = ?", "false").find(UpLoadPhotoSql.class);
    }

}

package com.ecaray.basicres.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.ecaray.basicres.entity.OrderPhotoBean;
import com.ecaray.basicres.sql.UpLoadPhotoSql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述: 对照片进行相应处理
 * @author : Eric_Huang
 * 创建时间: 2016/9/26 16:54
 * 修改人:Eric_Huang
 * 修改时间: 2016/9/26 16:54
 */
public class PhotoUtils {

    /**
     * 签入
     */
    public static final String PHOTO_TYPE_SIGN_IN = "pdasignin";

    /**
     *签出
     */
    public static final String PHOTO_TYPE_SIGN_OUT = "pdasignout";

    /**
     *停车
     */
    public static final String PHOTO_TYPE_START_PARKING = "startparking";

    /**
     *泊位纠错
     */
    public static final String PHOTO_TYPE_BERTHCORR = "berthcorrection";

    /**
     *设备维保
     */
    public static final String PHOTO_TYPE_EQUIPMENT = "equipment";

    /**
     *停车_泊位号
     */
    public static final String PHOTO_START_PARKING_BERTHCODE = "berthcode";

    /**
     *停车_车牌
     */
    public static final String PHOTO_START_PARKING_CARPLATE = "carplate";

    /**
     *泊位纠错
     */
    public static final String PHOTO_START_PARKING_BERTHCORR = "berthcorr";

    /**
     *停车_车身
     */
    public static final String PHOTO_START_PARKING_CARS = "cars";

    public static final String JPEG_FILE_SUFFIX = ".jpg";

    /**
     * 压缩并保存图片
     *
     * @param rawBitmap 要保存的图片
     * @param mFilePath 保存的路径
     * @param quality   保存的质量
     */
    public static void compressAndSaveBitmap(Bitmap rawBitmap, String mFilePath, int quality) {
        if (rawBitmap == null) {
            return;
        }


        File saveFile = new File(mFilePath);
        if (saveFile.exists()) {
            saveFile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            LogUtils.i("saveFile=" + saveFile);

            rawBitmap = compressImage(rawBitmap);
            rawBitmap.compress(Bitmap.CompressFormat.JPEG, quality,
                    fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!rawBitmap.isRecycled()) {
                rawBitmap.recycle();
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        boolean twice = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 90;
        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        //循环判断如果压缩后图片是否大于80kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 80) {
            //重置baos即清空baos
            baos.reset();
            if (options > 10) {
                //每次都减少10
                options -= 10;
            } else {
                //这里压缩options%，把压缩后的数据存放到baos中
                image.compress(Bitmap.CompressFormat.JPEG, options,
                        baos);
                break;
            }
            //这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.JPEG, options,
                    baos);
        }
        //把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(
                baos.toByteArray());
        //把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 保存图片到文件下
     */
    public static boolean saveImageInFile(Bitmap bitmap, String path) {
        File saveFile = new File(path);

        if (saveFile.exists()) {
            saveFile.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 将data转为bitmap
     */
    public static Bitmap getBitmap(byte[] data, Camera camera) {
        if (camera == null) {
            return null;
        }


        Camera.Parameters parameters = camera.getParameters();
        int imageFormat = parameters.getPreviewFormat();
        int w = parameters.getPreviewSize().width;
        int h = parameters.getPreviewSize().height;
        Rect rect = new Rect(0, 0, w, h);
        YuvImage yuvImg = new YuvImage(data, imageFormat, w, h, null);
        Bitmap bitmap = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            yuvImg.compressToJpeg(rect, 100, outputStream);
            bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size());
            // Rotate the Bitmap
            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            // We rotate the same Bitmap
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 添加水印（时间、地址）
     *
     * @param bitmap 图片
     * @return 添加水印后的图片
     */
    public static Bitmap addWaterMark(Bitmap bitmap) {

        String datetime = TimeUtils.TimeFormat2Date(System.currentTimeMillis());
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bmpTemp;
        try {
            bmpTemp = Bitmap.createBitmap(w, h, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }

        if (bmpTemp == null) {
            return null;
        }


        Canvas canvas = new Canvas(bmpTemp);
        Paint p = new Paint();
        String fontName = "serif";
        int ts = w > h ? w : h;
        int textSize = ts / 30;
        Typeface font = Typeface.create(fontName, Typeface.NORMAL);
        p.setColor(Color.RED);
        p.setTypeface(font);
        p.setTextSize(textSize);
        canvas.drawBitmap(bitmap, 0, 0, p);
        canvas.drawText(datetime, 20, textSize, p);

        //添加地址水印
        String lAddress = (String) SPUtils.get(SPKeyUtils.s_ADDRESS, "");
        if (!TextUtils.isEmpty(lAddress)) {
            canvas.drawText(lAddress, 20, textSize * 2 + 20, p);
        } else {
            canvas.drawText("位置信息获取失败", 20, textSize * 2 + 20, p);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bmpTemp;
    }


    /**
     * 获取指定路径下的图片的路径列表
     *
     * @param photoType 指定路径文件夹名字
     */
    public static List<OrderPhotoBean> getLocalPhotoList(Context context, String photoType) {
        try {
            String lPath;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                lPath = Environment.getExternalStorageDirectory().getPath().concat("/DCIM/Camera/");
            } else {
                lPath = context.getCacheDir().getPath().concat("/Camera/");
            }
            lPath = lPath.concat(photoType).concat("/");
            List<OrderPhotoBean> paths = new ArrayList<>();
            File f = new File(lPath);
            // 列出所有文件
            File[] files = f.listFiles();
            // 将所有文件存入list中
            if (files != null) {
                // 文件个数
                int count = files.length;
                for (int i = 0; i < count; i++) {
                    File file = files[i];
                    String[] arr = file.getName().split("_");
                    String orderId = null;
                    String fileName = null;
                    if (arr.length > 1) {
                        orderId = arr[0];
                        fileName = arr[1].replace(JPEG_FILE_SUFFIX, "");
                    }
                    paths.add(new OrderPhotoBean(photoType, file.getPath(), orderId, fileName));
                }
            }
            return paths;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取新的存储路径(拍照完之后作为暂时存储的路径, 重新命名)
     *
     * @return 新的路径
     */
    public static String getNewPath(Context context, OrderPhotoBean photoBean) {

        //添加水印后的图片
        Bitmap lBitmap;
        if (BitmapFactory.decodeFile(photoBean.path) == null) {
            return "";
        }
        //加水印时间与地点
        lBitmap = PhotoUtils.addWaterMark(BitmapFactory.decodeFile(photoBean.path));
        //更换路径
        String newPicPath = getPhotoPath(context, photoBean.type)
                .concat(photoBean.orderId.concat("_").concat(photoBean.fileName).concat(".jpg"));
        Log.i("PicPath", newPicPath);
        //保存图片到对应路径中
        PhotoUtils.compressAndSaveBitmap(lBitmap, newPicPath, 10);

        //删除旧路径的图片
        FileUtils.deleteFiles(photoBean.path);
        if (lBitmap != null) {
            lBitmap.recycle();
        }
        return newPicPath;
    }

    public static boolean isAddWaterMarkSuccess(Context context, UpLoadPhotoSql photoBean) {

        Bitmap lBitmap;
        if (BitmapFactory.decodeFile(photoBean.getPath()) == null) {
            return false;
        }
        //加水印时间与地点
        lBitmap = PhotoUtils.addWaterMark(BitmapFactory.decodeFile(photoBean.getPath()));
        if (lBitmap == null) {
            LogUtils.i("添加水印失败");
            return false;
        }

        PhotoUtils.compressAndSaveBitmap(lBitmap, photoBean.getPath(), 10);

        if (!lBitmap.isRecycled()) {
            lBitmap.recycle();
        }

        return true;
    }

    /**
     * 获取新路径
     */
    public static String getPhotoPath(Context context, String photoType) {
        String lPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            lPath = Environment.getExternalStorageDirectory().getPath().concat("/DCIM/Camera/");
        } else {
            lPath = context.getCacheDir().getPath().concat("/Camera/");
        }
        return lPath.concat(photoType).concat("/");
    }

    public static String getPhotoName(String photoType) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return timeStamp.concat("_").concat(photoType).concat(JPEG_FILE_SUFFIX);
    }

    /**
     * 判断是否为正确修改过后的路径
     *
     * @param path 路径
     */
    public static boolean isRightPath(String path) {
        String lPath = path.substring(path.lastIndexOf("/") + 1);
        return lPath.length() > 32;
    }

    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (returnBm == null) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

}

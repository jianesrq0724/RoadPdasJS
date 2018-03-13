package com.ecaray.basicres.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/10/17 15:25
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/10/17 15:25
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ImageUtils {

    /**
     * 獲取二維碼
     * @throws WriterException
     */
    public static Bitmap createdCode(String string, int widthAndHeight) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        // hst配合EncodeHintType的常量名指定MultiFormatWriter编码的附加参数
        Hashtable<EncodeHintType, String> hst = new Hashtable<>();
        // Specifies what character encoding to use where applicable (type
        // String)指定字符编码使用什么样的适用（ String类型）
        // 指定UTF-8的字符编码方式
        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // Encode a barcode using the default settings.
        // contents - The contents to encode in the barcode
        // format - The barcode format to generate
        // width - The preferred width in pixels 以像素为单位的首选宽度
        // height - The preferred height in pixels
        // hints - Additional parameters to supply to the encoder提示 - 附加参数提供给编码器
        BitMatrix matrix = writer.encode(string, BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight, hst);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

//    /****************************************
//     方法描述：生成二维码图片
//     @param     content  二维码内容  size  大小
//     @return
//     ****************************************/
//    public static Bitmap createdCode(String content,int size) {
//        return QRCodeEncoder.syncEncodeQRCode(content,size) ;
//    }

}

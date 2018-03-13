package com.ecaray.basicres.util;


import android.app.Application;
import android.text.TextUtils;

import com.ecar.ecarnetwork.http.util.TagLibUtil;
import com.ecaray.basicres.base.BaseApplication;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ===============================================
 * <p>
 * 类描述: 校验类
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/7/26 0026 上午 11:08
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/7/26 0026 上午 11:08
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class InvalidUtil {

    /**
     * REQUEST_KEY
     */
    public static String REQUEST_KEY;

    /**
     * 网络请求参数
     */
    public static final String CHECKSIGN = "\"ngis\":\"[a-z0-9A-Z]{32}\"";

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };



    /**
     * 将二进制字符串转换成Unicode字符串
     * @param binStr
     * @return
     */
    public static String BinstrToStr(String binStr) {
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    /**
     * 将初始二进制字符串转换成字符串数组，以空格相隔
     * @param str
     * @return
     */
    public static String[] StrToStrArray(String str) {
        return str.split(" ");
    }

    /**
     * 将二进制字符串转换为char
     * @param binStr
     * @return
     */
    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    /**
     * 将二进制字符串转换成int数组
      */
    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    /**
     * 添加字符串
     */
    public static synchronized String addText(StringBuilder sb, String... texts) {
        sb.delete(0, sb.length());
        for (String str : texts) {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * MD5加密算法
     *
     * @param strObj
     * @return
     */
    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }


    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * request 加密
     */
    public static StringBuffer md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }

                if (i < 16){
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }

    /**
     * @功能：对String 进行encoding 操作
     * @param：
     * @return：
     * @throws Exception
     */
    public static String getEncodedStr(String str) {
        try {
            return java.net.URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}

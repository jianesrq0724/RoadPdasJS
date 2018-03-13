package com.ecaray.basicres.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static urils.ecaray.com.ecarutils.Utils.StringsUtil.BinstrToChar;
import static urils.ecaray.com.ecarutils.Utils.StringsUtil.StrToStrArray;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * @author : Eric_Huang
 * <p>
 * 创建时间: 2016/9/22 16:54
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/22 16:54
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class StringUtils {
    /**
     * 把一个字符串中的大写转为小写，小写转换为大写：思路1
     * @param str
     * @return
     */
    public static String exChange(String str) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isLowerCase(c)) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(c);
                }

            }
        }
        return sb.toString();
    }

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

    // 将初始二进制字符串转换成字符串数组，以空格相隔
//    public static String[] StrToStrArray(String str) {
//        return str.split(" ");
//    }

    // 将二进制字符串转换为char
//    public static char BinstrToChar(String binStr) {
//        int[] temp = BinstrToIntArray(binStr);
//        int sum = 0;
//        for (int i = 0; i < temp.length; i++) {
//            sum += temp[temp.length - 1 - i] << i;
//        }
//        return (char) sum;
//    }

    // 将二进制字符串转换成int数组
//    public static int[] BinstrToIntArray(String binStr) {
//        char[] temp = binStr.toCharArray();
//        int[] result = new int[temp.length];
//        for (int i = 0; i < temp.length; i++) {
//            result[i] = temp[i] - 48;
//        }
//        return result;
//    }

    /**
     * 将Bitmap转换成Base64字符串
     *
     * @param bitmap
     * @return
     */
    public static String Bitmap2StrByBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();

                //得到图片的String
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 判断一个字符串的首字符是否为字母
     */
    public static boolean isFirstChinese(String s) {
        String regEx = "[\\u4e00-\\u9fa5]";
        // System.out.println(regEx);
        // System.out.println(str);
        String first = s.substring(0, 1);
        return Pattern.matches(regEx, first);
    }


    /**
     * 将double格式化为指定小数位的String，不足小数位用0补全
     *
     * @param v     需要格式化的数字
     * @param scale 小数点后保留几位
     */
    public static String roundByScale(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The   scale   must   be   a   positive   integer   or   zero");
        }
        if (scale == 0) {
            return new DecimalFormat("0").format(v);
        }

        StringBuilder formatStr = new StringBuilder();
        formatStr.append("0.");
        for (int i = 0; i < scale; i++) {
            formatStr.append("0");
        }
        return new DecimalFormat(formatStr.toString()).format(v);
    }

    /**
     * 格式化金额数字
     *
     * @param s
     * @return
     */
    public static String formatMoneyData(String s) {
        /*
         * if(s.indexOf(".") > 0){ s = s.replaceAll("0+?$", "");//去掉多余的0 s =
		 * s.replaceAll("[.]$", "");//如最后一位是.则去掉 }
		 */
        if (s == null || s.equals("")){
            return "0.00";
        }

        if (s.indexOf(",") != -1){
            return s;
        }
        // 保留2位
        DecimalFormat df2 = new DecimalFormat("###,###,##0.00");
        return df2.format(Double.parseDouble(s));
    }

    /**
     * 添加字符串
     * @param sb
     * @param texts
     * @return
     */
    public static String addText(StringBuilder sb, String... texts) {
        sb.delete(0, sb.length());
        if (texts == null) {
            return "";
        }
        for (String text : texts) {
            if (!TextUtils.isEmpty(text)) {
                sb.append(text);
            }

        }
        return sb.toString();
    }

    /**
     * 验证车牌格式 字母不超过3个,汉字不按字母计算
     * 先取消字母不超过3个的限制
     *
     * @param plateNumber 车牌
     * @return
     */
    public static boolean checkPlateNumber(String plateNumber) {
        boolean flag = false;
        //获取字母的个数
//        int letterCount = getLowerCaseCount(plateNumber);
//        if (letterCount <= 3) {
        String e = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        try {
            Pattern regex1 = Pattern.compile(e);
            Matcher matcher1 = regex1.matcher(plateNumber);
            flag = matcher1.matches();
        } catch (Exception var5) {
            Log.e("tag", "验证车牌号错误");
            flag = false;
        }
//        }
        return flag;
    }

    /**
     * 获取大写字母的个数
     *
     * @param str
     * @return
     */
    public static int getLowerCaseCount(String str) {
        int letterCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                letterCount++;
            }
        }
        return letterCount;
    }


    /**
     * @param plateNumber 车牌
     * @return
     */
    public static boolean checkPlate(String plateNumber) {
        boolean flag = false;
        try {
            String e = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
            Pattern regex1 = Pattern.compile(e);
            Matcher matcher1 = regex1.matcher(plateNumber);
            flag = matcher1.matches();
        } catch (Exception var5) {
            Log.e("tag", "验证车牌号错误");
            flag = false;
        }
        return flag;
    }

    /**
     * 验证车牌格式 包含字母不超过3个,汉字按字母计算,所以letterCount的数量<4
     * 先取消字母不超过3个的限制
     *
     * @param plateNumber 车牌
     * @return
     */
    public static boolean checkNewEnergyPlateNumber(String plateNumber) {
        boolean flag = false;
        try {
            String e = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$";
            Pattern regex1 = Pattern.compile(e);
            Matcher matcher1 = regex1.matcher(plateNumber);
            flag = matcher1.matches();
        } catch (Exception var5) {
            Log.e("tag", "验证车牌号错误");
            flag = false;
        }
        return flag;
    }


    /**
     * 给车牌号码加上 ·
     *
     * @param carNum 车牌号
     * @return 车牌号
     */
    public static String addPoint4CarNum(String carNum) {
        if (carNum.length() <= 2){
            return "";
        }

        return carNum.substring(0, 2).concat(" · ").concat(
                carNum.substring(2));
    }

    /**
     * 检查字符串是否不是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
     * <pre>
     * StringUtil.isBlank(null)      = false
     * StringUtil.isBlank("")        = false
     * StringUtil.isBlank(" ")       = false
     * StringUtil.isBlank("bob")     = true
     * StringUtil.isBlank("  bob  ") = true
     * </pre>
     *
     * @param str 要检查的字符串
     *
     * @return 如果为空白, 则返回<code>true</code>
     *
     */
    public static boolean isNotBlank(String str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}

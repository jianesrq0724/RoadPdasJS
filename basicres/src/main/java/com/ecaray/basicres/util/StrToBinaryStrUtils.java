package com.ecaray.basicres.util;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2017/1/5 20:05
 */
public class StrToBinaryStrUtils {

    //转化二进制
    public static String strToBinarystr(String str) {
        char[] strChar = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0, len = strChar.length; i < len; i++) {
            result.append(Integer.toBinaryString(strChar[i])).append(" ");
        }
        return result.toString();
    }

}

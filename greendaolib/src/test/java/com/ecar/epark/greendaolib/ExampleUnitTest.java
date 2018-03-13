package com.ecar.epark.greendaolib;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
//        String code = "011371960100845800007e1b1cc3ac";
        String code = "011371960100835800007e1b328970";
        byte[] bytes = hexStringToBytes(code);
        byte aByte = getByte1(bytes);
//        byte aByte = getByte(bytes);
        System.out.print("aba  "+aByte);
    }

    private byte getByte(byte[] bytes){
        byte temp = 0x7f;
        for(int i=0,size=bytes.length;i<size;i++){
            byte t1= (byte) (bytes[i] &0x7f);
            byte t2= (byte) (temp & 0x7f);
            temp = (byte) (t1 ^ t2);
//            temp = (byte) (0xff & (temp ^ ((byte)( bytes[i]&0xff))));
        }
        byte hah = (byte) (temp & 0x7f);
        return hah;
    }
    private byte getByte1(byte[] bytes){
        byte temp = 0;
        for(int i=0,size=bytes.length;i<size;i++){
            byte t1= (byte) (bytes[i]);
            byte t2= (byte) (temp);
            temp = (byte) (t1 ^ t2);
//            temp = (byte) (0xff & (temp ^ ((byte)( bytes[i]&0xff))));
        }
        byte hah = (byte) (temp);
        return hah;
    }

    /**
     * BCD�ַ�ת�ֽ�
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        //����Ϊ���ţ���Ҫ��0长度
        int originalLen=hexString.length();
        //如果长度模以2是1说明是奇数位前面补0成偶数位
        if(originalLen%2==1){
            hexString="0"+hexString;
        }


        hexString = hexString.toUpperCase();//返回一个全部大写的字符串
        int length = hexString.length() / 2;//长度除以2
        char[] hexChars = hexString.toCharArray();//把返回的大写字符串也就成16进制了转成字节数组
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
package com.ecaray.basicres.helper;

import android.content.Context;
import android.text.TextUtils;

import com.ecaray.basicres.util.SPKeyUtils;
import com.ecaray.basicres.util.SPUtils;


/**
 * ===============================================
 * <p>
 * 类描述: 获取文通识别序列号
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/26 14:32
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/26 14:32
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class SerialHelper4WT {

    public static SerialHelper4WT serialHelper;
    public Context mContext;

    private SerialHelper4WT(Context context) {
        mContext =  context;
    }

    public static SerialHelper4WT getInstance(Context context) {
        if (serialHelper == null) {
            serialHelper = new SerialHelper4WT(context);
        }
        return serialHelper;
    }

    /**
     * 获取文通序列号
     * @param serial 回调函数
     */
    public void getSerialNum(SerialI serial){
        String lSerialNum = (String) SPUtils.get(SPKeyUtils.s_SERIAL_NUM,"");
        if(!TextUtils.isEmpty(lSerialNum)){
            return;
        }

        if (TextUtils.isEmpty(lSerialNum)) {
            serial.getserial();
        } else {
            SPUtils.put(SPKeyUtils.s_SERIAL_NUM, lSerialNum);
        }

    }
}

package com.ecaray.basicres.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ecaray.basicres.R;
import com.ecaray.basicres.base.BaseApplication;


/**
 * Created by Knight
 * 自定义Toast
 */

public class CustomerToastUtil {
    private Toast mToast;

    private CustomerToastUtil(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.public_layout_toast, null);
        TextView textView = (TextView) v.findViewById(R.id.pub_text_toast);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
        if (mToast != null) {
            mToast.show();
        }
    }

    private CustomerToastUtil(CharSequence text) {
        View v = LayoutInflater.from(BaseApplication.getInstance().getApplicationContext()).inflate(R.layout.public_layout_toast, null);
        TextView textView = (TextView) v.findViewById(R.id.pub_text_toast);
        textView.setText(text);
        mToast = new Toast(BaseApplication.getInstance().getApplicationContext());
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(v);
        if (mToast != null) {
            mToast.show();
        }
    }

    private CustomerToastUtil(int resId) {
        View v = LayoutInflater.from(BaseApplication.getInstance().getApplicationContext()).inflate(R.layout.public_layout_toast, null);
        TextView textView = (TextView) v.findViewById(R.id.pub_text_toast);
        textView.setText(BaseApplication.getInstance().getApplicationContext().getResources().getText(resId));
        mToast = new Toast(BaseApplication.getInstance().getApplicationContext());
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(v);
        if (mToast != null) {
            mToast.show();
        }
    }

    public static CustomerToastUtil show(Context context, CharSequence text, int duration) {
        return new CustomerToastUtil(context, text, duration);
    }

    public static CustomerToastUtil show(CharSequence text) {
        return new CustomerToastUtil(text);
    }


    public static CustomerToastUtil show(int resId) {
        return new CustomerToastUtil(resId);
    }

    /**
     * 居中显示
     *
     * @param text
     */
    public static void showCenter(CharSequence text) {
        CustomerToastUtil toast = new CustomerToastUtil(text);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}

package com.ecaray.basicres.view.load;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.ecaray.basicres.R;


/**
 * 自定义进度条
 *
 * @author Administrator
 */
public class ProgressDialogBar extends Dialog {
    private LoadingHelper loadingHelper = null;

    public ProgressDialogBar(Context context, int theme) {
        super(context, theme);
        loadingHelper = new LoadingHelper();
        this.setContentView(R.layout.pub_dialog_loading);
        this.setCanceledOnTouchOutside(false);
    }

    public ProgressDialogBar(Context context) {
        this(context,R.style.CustomProgressDialog);
    }

    @Override
    public void show() {
        loadingHelper.addCount();
        if(!isShowing()){
            super.show();
        }
    }

    @Override
    public void dismiss() {
        loadingHelper.subCount();
        if(loadingHelper.isZero()){
            super.dismiss();
        }
    }

    /**
     * setMessage 提示内容
     */
    public void setProgressMsg(String strMessage) {
        TextView tvMsg = (TextView) findViewById(R.id.text_progress);
        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }

    }
}

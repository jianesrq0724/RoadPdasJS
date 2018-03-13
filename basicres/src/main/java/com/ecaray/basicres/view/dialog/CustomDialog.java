package com.ecaray.basicres.view.dialog;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.ecaray.basicres.R;


/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/5/17 10:01
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/5/17 10:01
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public class CustomDialog extends Dialog {

    private Context mContext;
    private TextView mRightBtn;
    private TextView mLeftBtn;
    private TextView mMsg;
    private View mViewLine;

    public CustomDialog(Context context) {
        this(context, R.style.CustomProgressDialog);
    }

    public CustomDialog(Context context, int theme) {
        super(context, R.style.CustomProgressDialog);
        mContext = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_dialog_common);
        initView();
    }

    private void initView() {
        View lView = findViewById(R.id.custom_layout);
        setViewsWith(mContext,0.78,lView);
        mViewLine = findViewById(R.id.view_line);
        mRightBtn = (TextView) findViewById(R.id.right_bt);
        mLeftBtn = (TextView) findViewById(R.id.left_bt);
        mMsg = (TextView) findViewById(R.id.msg_tv);
    }

    public void setRightBtnText(String text){
        mRightBtn.setText(text);
    }

    public void setLeftBtnText(String text){
        mLeftBtn.setText(text);
    }

    public void setMsgText(String text){
        mMsg.setText(text);
    }

    public void setMsgTextGravity(int gravity){
        mMsg.setGravity(gravity);
    }

    public void setLeftBtnListener(View.OnClickListener onClickListener){
        mLeftBtn.setOnClickListener(onClickListener);
    }

    public void setRightBtnListener(View.OnClickListener onClickListener){
        mRightBtn.setOnClickListener(onClickListener);
    }

    public void hideRightBtn(){
        mRightBtn.setVisibility(View.GONE);
        mViewLine.setVisibility(View.GONE);
    }

    public void hidLeftBtn(){
        mLeftBtn.setVisibility(View.GONE);
        mViewLine.setVisibility(View.GONE);
    }

    // 设置控件的宽 参数:context 上下文 size;屏幕的倍数 view:需要调整的控件
    public static void setViewsWith(Context context, Double size, View view) {

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Service.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int sizeX = dm.widthPixels;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = (int) (size * sizeX);
    }
}

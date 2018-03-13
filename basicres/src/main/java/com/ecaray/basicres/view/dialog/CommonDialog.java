package com.ecaray.basicres.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ecaray.basicres.R;
import com.ecaray.basicres.util.ScreenUtils;


/**
 *
 */
public class CommonDialog extends Dialog {
	private Context mContext;
	private Window dialogWindow;
	private boolean isCancel = false;
	View contentView;
	private WindowManager.LayoutParams p;

	public CommonDialog(Context context, View view, boolean isCancel) {
		super(context);
		mContext = context;
		contentView = view;
		this.isCancel =isCancel;
	}

	public CommonDialog(Context context, View view, boolean isCancel, int themeId) {
		super(context, themeId);
		mContext = context;
		contentView = view;
		this.isCancel =isCancel;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(contentView);
		dialogWindow = getWindow();
		setBackgroundColor(0);
		// 获取对话框当前的参数值
		p = dialogWindow.getAttributes();
		dialogWindow.setAttributes(p);
		//由于都设置了"X"关闭标签，故设置不可点击外部，可点击返回按钮
		//设置点击屏幕消不消失 true为消失，false为不消失
		setCancelFocus(isCancel);

        hideTitleLine();
	}

    private void hideTitleLine() {
        try{
            int dividerID=mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider=findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        }catch(Exception e){
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
    }

    private void setBackgroundColor(int color) {
		if (color == 0) {
			//透明背景
			dialogWindow.getDecorView().setBackgroundResource(R.drawable.public_close_icon);
		} else {
			dialogWindow.getDecorView().setBackgroundColor(color);
		}
	}

	/**
	 * 设置占屏幕的宽高
	 * @param width
	 * @param height
	 */
	public void setWeight(float width, float height){
		if (dialogWindow == null) {
			dialogWindow = getWindow();
		}
		setBackgroundColor(R.color.white);
		// 获取对话框当前的参数值
		if (p == null) {
			p = dialogWindow.getAttributes();
		}

		p.width = (int) (ScreenUtils.getScreenWidth(mContext)*width);
		p.height = (int) (ScreenUtils.getScreenHeight(mContext)*height);
		dialogWindow.setAttributes(p);
	}

	/**
	 * 是否可以取消点击外部和返回键
	 * @param canCancelFocus
	 */
	public void setCancelFocus(boolean canCancelFocus){
		setCancelable(true);
		//设置点击边缘消不消失
		setCanceledOnTouchOutside(canCancelFocus);
	}

}

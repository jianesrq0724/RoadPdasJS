package com.ecaray.basicres.view.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecaray.basicres.R;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * @author : Eric_Huang
 * <p>
 * 创建时间: 2016/9/7 16:16
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/7 16:16
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ToolbarManager {

    private final Context mContext;
    private final Toolbar mToolbar;

    private final TextView mTitleTv;
    private final TextView mRightBtTx;

    /**
     * 泊位停车列表的title
     */
    private final LinearLayout mBMLl;
    private final TextView mBMTitleTv;
    private final CheckBox mBMTitleIc;
    private final ImageView mRightBtIv;
    private final EditText mEtFocusable;
    private TextView mRedPoint;

    /**
     * 消息+红点父布局
     */
    private final View mRightPushItem;

    /**
     * 消息文字
     */
    private final TextView mRightPushTx;

    /**
     * 消息的红点
     */
    private final TextView mRightPushRedPoint;

    public ToolbarManager(Context context, Toolbar toolbar) {
        mContext = context;
        mToolbar = toolbar;

        mTitleTv = (TextView) mToolbar.findViewById(R.id.pub_title);
        mRightBtTx = (TextView) mToolbar.findViewById(R.id.right_bt_tv);

        mBMLl = (LinearLayout) mToolbar.findViewById(R.id.berth_monitor_ll);
        mBMTitleTv = (TextView) mToolbar.findViewById(R.id.berth_monitor_title);
        mBMTitleIc = (CheckBox) mToolbar.findViewById(R.id.berth_monitor_ic);
        mRightBtIv = (ImageView) mToolbar.findViewById(R.id.right_bt_iv);
        mRedPoint = (TextView) mToolbar.findViewById(R.id.red_point);
        mEtFocusable = (EditText) mToolbar.findViewById(R.id.et_focusable);

        //消息
        mRightPushItem = mToolbar.findViewById(R.id.toolbar_right_tx_red);
        mRightPushTx = (TextView) mToolbar.findViewById(R.id.right_push_info);
        mRightPushRedPoint = (TextView) mToolbar.findViewById(R.id.right_push_info_red_point);

    }

    public ToolbarManager init() {
        renew();
        return this;
    }

    private void renew() {
        mToolbar.setNavigationIcon(R.mipmap.pub_left_arrow_ic);
        showBMTitle(false);
    }

    public ToolbarManager setTitle(int titleResId) {
        return setTitle(mContext.getString(titleResId));
    }

    public ToolbarManager setTitle(@Nullable String title) {
        if (title == null) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setVisibility(View.VISIBLE);
            mTitleTv.setText(title);
        }
        return this;
    }

    public void setOnKey() {
        mEtFocusable.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_UP == event.getAction()) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    public ToolbarManager setEtFocusableGone() {
        mEtFocusable.setVisibility(View.GONE);
        return this;
    }

    /**
     * 是否显示泊位列表的title
     */
    public ToolbarManager showBMTitle(boolean show) {
        mBMLl.setVisibility(show ? View.VISIBLE : View.GONE);
        return this;
    }

    public ToolbarManager clickBMTitle(View.OnClickListener clickListener) {
        mBMLl.setOnClickListener(clickListener);
        return this;
    }

    /**
     * 设置泊位列表标题的图标样式
     */
    public ToolbarManager setBTTitleIc(boolean isCheck) {
        mBMTitleIc.setChecked(isCheck);
        return this;
    }

    /**
     * 是否显示泊位列表标题的图标样式
     */
    public ToolbarManager showBTTitleIc(boolean show) {
        mBMTitleIc.setVisibility(show ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置泊位列表标题内容
     */
    public ToolbarManager setBMTitle(@Nullable String title) {
        if (title == null) {
            mBMTitleTv.setVisibility(View.GONE);
        } else {
            mBMTitleTv.setVisibility(View.VISIBLE);
            mBMTitleTv.setText(title);
        }
        return this;
    }

    /**
     * 推送点击事件
     */
    public ToolbarManager clickPush(View.OnClickListener clickListener) {
        mRightPushItem.setVisibility(View.VISIBLE);
        mRightPushItem.setOnClickListener(clickListener);
        return this;
    }

    /**
     * 推送设置红点
     */
    public ToolbarManager setPushRedPoint(boolean isShow, long count) {
        handleRedPoint(mRightPushRedPoint,isShow,count);
        return this;
    }

    private void handleRedPoint(TextView tx, boolean isShow, long count){
        if (isShow && count>0) {
            tx.setVisibility(View.VISIBLE);
                tx.setText(String.valueOf(count));
        } else {
            tx.setVisibility(View.GONE);
        }
    }


    /**
     * 显示红点
     */
    public ToolbarManager showRedPoint(boolean isShow, int count) {
        handleRedPoint(mRedPoint,isShow,count);
        return this;
    }

    /**
     * 不显示红点
     */
    public ToolbarManager noShowRedPoint(boolean isShow, int count) {
        mRedPoint.setVisibility(View.GONE);
        return this;
    }

    /**
     * 重写返回按钮
     */
    public ToolbarManager clickNavigation(View.OnClickListener onClickListener) {
        mToolbar.setNavigationOnClickListener(onClickListener);
        return this;
    }

    /**
     * 设置右边按钮
     */
    public ToolbarManager setRightBtIv(View.OnClickListener onClickListener, int res) {
        mRightBtIv.setOnClickListener(onClickListener);
        mRightBtIv.setImageResource(res);
        mRightBtIv.setVisibility(View.VISIBLE);
        return this;
    }


    public ToolbarManager setRightBtTv(View.OnClickListener clickListener, int res) {
        mRightBtTx.setOnClickListener(clickListener);
        mRightBtTx.setText(res);
        mRightBtTx.setVisibility(View.VISIBLE);
        return this;
    }

    public ToolbarManager setRightBtTvNoBackground(View.OnClickListener clickListener, int res) {
        mRightBtTx.setOnClickListener(clickListener);
        mRightBtTx.setText(res);
        mRightBtTx.setVisibility(View.VISIBLE);
        mRightBtTx.setBackgroundColor(mContext.getResources().getColor(R.color.main_theme));
        mRightBtTx.setTextColor(mContext.getResources().getColor(R.color.white));
        return this;
    }

    public ToolbarManager setRightBtTv(View.OnClickListener clickListener, int res, int drawableRes) {
        mRightBtTx.setOnClickListener(clickListener);
        mRightBtTx.setText(res);

        Drawable drawable = mContext.getResources().getDrawable(drawableRes);
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        mRightBtTx.setCompoundDrawables(drawable, null, null, null);
        mRightBtTx.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置显示字符
     */
    public ToolbarManager setRightBtTv(int res) {
        mRightBtTx.setText(res);
        return this;
    }

    /**
     * 设置显示红点
     */
    public ToolbarManager setRedBtTv(String res) {
        mRedPoint.setText(res);
        return this;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

}

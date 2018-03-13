package com.ecaray.basicres.view.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ecaray.basicres.R;
import com.ecaray.basicres.util.ViewUtil;
import com.ecaray.basicres.view.layout.entity.OrderLayoutBean;

import java.util.List;


/**
 * 类描述: 订单详情布局
 * @author : Shirley
 * 创建日期: 2017/3/28 10:00
 * 修改人:Shirley
 * 修改时间: 2017/3/28 10:00
 * 修改备注:
 */
public class BerthOrderDetailLayout extends ScrollView {

    private Context mContext;

    /**
     * 主布局，用来添加子view
     */
    private LinearLayout mLlMain;

    private TextView mTvLoadFail;

    public BerthOrderDetailLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BerthOrderDetailLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setPadding(ViewUtil.dip2px(mContext, 10),ViewUtil.dip2px(mContext, 10),ViewUtil.dip2px(mContext, 10),ViewUtil.dip2px(mContext, 10));
        this.setLayoutParams(params);
        this.setBackgroundResource(R.drawable.berth_rec_deep_gray_line);
        mLlMain = new LinearLayout(mContext);
        mLlMain.setOrientation(LinearLayout.VERTICAL);
        this.addView(mLlMain);
    }

    public void addView(List<OrderLayoutBean> layoutBeanList) {
        mLlMain.removeAllViews();
        if (layoutBeanList == null) {
            return;
        }
        for (int i = 0; i < layoutBeanList.size(); i++) {
            OrderLayoutBean o = layoutBeanList.get(i);
            TextView tv = new TextView(mContext);
            if (o.contentType == OrderLayoutBean.TXT_NORMAL) {
                if (!TextUtils.isEmpty(o.content)) {
                    tv.setText(o.content);
                }
                if (o.gravity != -1) {
                    tv.setGravity(o.gravity);
                }
                if (o.fontStyle != -1) {
                    tv.setTextAppearance(mContext,o.fontStyle);
                }
                tv.setPadding(0,ViewUtil.dip2px(mContext, 8),0,0);
            } else if (o.contentType == OrderLayoutBean.DOT_LINE) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewUtil.dip2px(mContext, 20));
                tv.setLayoutParams(params);
                tv.setBackgroundResource(R.drawable.berth_shape_dot_line);
            }
            mLlMain.addView(tv);
        }
    }

    public void showLoadFailView(OnClickListener onClickListener) {
        mLlMain.removeAllViews();
        if (mTvLoadFail == null) {
            mTvLoadFail = new TextView(mContext);
            mTvLoadFail.setText(R.string.common_get_fail);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewUtil.dip2px(mContext, 400));
        mTvLoadFail.setGravity(Gravity.CENTER);
        mTvLoadFail.setLayoutParams(params);
        mTvLoadFail.setTextSize(20);
        mTvLoadFail.setOnClickListener(onClickListener);
        mLlMain.addView(mTvLoadFail);
    }
}

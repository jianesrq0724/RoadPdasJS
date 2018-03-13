package com.ecaray.basicres.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecaray.basicres.base.CommonBaseAdapter;


/**
 * 类名称: ViewHolder
 * 类描述:公共的ViewHolder
 * 创建人: 金征
 * 创建时间: 2015-5-9 上午11:39:49
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private SparseArray<CommonBaseAdapter> mAdapters;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.mAdapters = new SparseArray<CommonBaseAdapter>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);
    }

    /**
     * 功能：viewHolder的入口方法
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 功能：通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 功能：设置textView的text
     * param：viewId:textView的id，text:设置的文本
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 功能：设置textView的颜色
     */
    public ViewHolder setTextColor(int viewId, String text, int color) {
        TextView tv = getView(viewId);
        tv.setText(text);
        tv.setTextColor(color);
        return this;
    }

    public ViewHolder setTextClick(int viewId, String text,
                                   OnClickListener onClickListener) {
        TextView tv = getView(viewId);
        tv.setText(text);
        tv.setOnClickListener(onClickListener);
        return this;
    }

    public ViewHolder setLinClick(int viewId, OnClickListener onClickListener) {
        LinearLayout ll = getView(viewId);
        ll.setOnClickListener(onClickListener);
        return this;
    }

    public ViewHolder setTextVisible(int viewId, boolean visible) {
        TextView tv = getView(viewId);
        tv.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public ViewHolder setCheckBoxEnabled(int viewId, boolean enabled) {
        CheckBox checkBox = getView(viewId);
        checkBox.setEnabled(enabled ? true : false);
        return this;
    }


    public ViewHolder setLinVisible(int viewId, boolean visible) {
        LinearLayout lin = getView(viewId);
        lin.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public ViewHolder setVisibleOrGone(int viewId, boolean visible) {
        View lin = getView(viewId);
        lin.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }
    /**
     *  功能：设置ImageView的图片
     *  param：viewId:imageView的id，resId:图片id
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     *  功能：设置ImageView的图片Bitmap
     *  param：viewId:imageView的id，bm:图片Bitmap
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }

    public ViewHolder setImageVisible(int viewId, boolean visible) {
        ImageView iv = getView(viewId);
        iv.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     *  功能：设置ImageView的图片url
     *  param：viewId:imageView的id，bm:图片Bitmap
     */
    public ViewHolder setImageURL(int viewId, String url) {
        ImageView iv = getView(viewId);
        return this;
    }

}

package com.ecaray.basicres.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.ecaray.basicres.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:基类adapter
 * 创建人: 金征
 * 创建时间: 2015-5-9 下午12:04:04
 */
public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected List<T> mDataList;

    protected LayoutInflater mInflater;

    protected int position;

    public void setDataList(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public CommonBaseAdapter(Context mContext, List<T> mDataList) {
        super();
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        if (mDataList == null) {
            this.mDataList = new ArrayList<>();
        } else {
            this.mDataList = mDataList;
        }
    }

    public void update(List<T> mDataList) {
        if (mDataList == null) {
            this.mDataList = new ArrayList<>();
        } else {
            this.mDataList.clear();
            this.mDataList.addAll(mDataList);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        this.position = position;
        final ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                getLayoutId(), position);
        conner(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * 功能：设置itemLayout的id
     */
    public abstract int getLayoutId();

    public abstract void conner(final ViewHolder holder, final T entity);
}

package com.ecaray.basicres.view.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ecaray.basicres.R;


/**
 * 类描述:ListView的顶层基类
 * @author : CarlLu
 * 创建时间: 2016/3/31 12:55
 */
public abstract class BaseListView extends ListView {
	protected BaseAdapter mAdapter;

	public BaseListView(Context context) {
		super(context);
	}

	public BaseListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public BaseListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 刷新adapter
	 */
	public void adapterNotify() {
		if (mAdapter != null) {
			mAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 功能：添加页脚
	 */
	public void addFooter(View fView) {
		if (this.getFooterViewsCount() > 0) {
			this.removeFooterView(fView);
		}
		this.addFooterView(fView, null, false);
	}

	/**
	 * 功能：删除页脚
	 * param：fView:页脚布局
	 */
	public void deleteFooter(View fView) {
		if (this.getFooterViewsCount() > 0) {
			this.removeFooterView(fView);
		}
	}

	public void setMyAdapter(BaseAdapter adapter) {
		this.mAdapter = adapter;
		View view= LayoutInflater.from(getContext()).inflate(R.layout.include_list_foot_view,null);
		this.addFooter(view);
		super.setAdapter(adapter);
		this.removeFooterView(view);
	}
}

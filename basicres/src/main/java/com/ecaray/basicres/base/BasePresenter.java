package com.ecaray.basicres.base;

import android.app.Activity;

import com.ecar.ecarnetwork.base.manage.RxManage;


/**
 * ===============================================
 * <p/>
 * 类描述:
 * <p/>
 * 创建人: Eric_Huang
 * <p/>
 * 创建时间: 2016/8/30 10:03
 * <p/>
 * 修改人:Eric_Huang
 * <p/>
 * 修改时间: 2016/8/30 10:03
 * <p/>
 * 修改备注:
 * <p/>
 * ===============================================
 */
public abstract class BasePresenter<T> {


    protected Activity mContext;
    protected RxManage mRxManage = new RxManage();

    protected T mView;

    /**
     * 单元测试 采用依赖参数 构造时 一起注入，方便mockito
     */
    public BasePresenter(Activity context, T view) {
        mContext = context;
        mView = view;
    }

    public void onDestroy() {
        mRxManage.clear();
        mContext = null;
        mView = null;
    }

}

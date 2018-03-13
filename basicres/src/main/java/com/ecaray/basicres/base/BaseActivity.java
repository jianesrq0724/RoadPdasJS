package com.ecaray.basicres.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ecar.ecarnetwork.interfaces.view.ILoading;
import com.ecaray.basicres.R;
import com.ecaray.basicres.util.LogUtils;
import com.ecaray.basicres.view.load.ProgressDialogBar;
import com.ecaray.basicres.view.toolbar.ToolbarManager;

import butterknife.ButterKnife;

/**
 * 类描述: 所有activity的基类
 * 创建人: Eric_Huang
 * 创建时间: 2016/8/26 11:16
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements ILoading {

    private final int CONNECT_STATE_SUCCESS = 1;
    private final int CONNECT_STATE_FAIL = 0;

    public String TAG;

    protected T mPresenter;
    protected Activity mContext;
    public ProgressDialogBar mProgressBar;
    //工具栏
    public ToolbarManager mToolBarManager;
    public Toolbar mToolbar;

    //网络接收
    protected NetWorkStateReceive mNetWorkStateReceive;

    /**
     * 注册断网广播接收者
     */
    protected void initReceiver() {
        mNetWorkStateReceive = new NetWorkStateReceive();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkStateReceive, filter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView();

        TAG = this.getClass().getSimpleName();

        BaseApplication.getInstance().addActivity(this);
        ButterKnife.bind(this);
        mContext = this;
        initToolbar();

        this.initPresenter();
        this.initLoading();
        this.initData();
        this.initView();
        this.setOnInteractListener();

        initRxbus();

    }

    public void initRxbus() {

    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.pub_tb);
        if (mToolbar != null) {
            mToolBarManager = new ToolbarManager(this, mToolbar);
            mToolbar.setNavigationOnClickListener(view -> goBack());
            mToolBarManager.setOnKey();
        }
    }

    public void goBack() {
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissLoading();
    }

    /**
     * 设置布局
     */
    public void setContentView() {
        View layoutView = getLayoutView();
        if (null != layoutView) {
            super.setContentView(layoutView);
        } else {
            super.setContentView(getLayoutId());
        }
    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //是否要一个activity 栈，移除当前activity
        BaseApplication.getInstance().removeActivity(this);
//        RxBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        if (mProgressBar != null) {
            if (mProgressBar.isShowing()) {
                mProgressBar.dismiss();
            }
            mProgressBar = null;
        }
        ButterKnife.bind(this).unbind();
        //是否需要处理progressBar 的上下文，目测不要吧
    }

    /*************************
     * loading部分
     ******************************/

    private void initLoading() {
        mProgressBar = new ProgressDialogBar(mContext);
    }


    @Override
    public void showLoading() {
        //&& !progressBar.isShowing() progrssBar 重写处理
        if (mContext != null && mProgressBar != null && !mProgressBar.isShowing()) {
            mProgressBar.show();
        }
    }

    @Override
    public void dismissLoading() {
        //&& progressBar.isShowing()  progrssBar 重写处理
        if (mContext != null && mProgressBar != null && mProgressBar.isShowing()) {
            mProgressBar.dismiss();
        }
    }

    @Override
    public void showMsg(String s) {

    }

    @Override
    public void reLogin(Context context, String s) {

    }

    /*************************抽象方法*******************************/
    /**
     * 布局layoutId
     */
    public abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 数据展示在view上
     */
    public abstract void initView();

    /**
     * 监听事件
     */
    public abstract void setOnInteractListener();

    /**
     * 简单页面无需mvp就不用管此方法即可
     */
    public abstract void initPresenter();
    /*************************抽象方法*******************************/

    /**
     * 网络变化的广播接收器
     */
    private class NetWorkStateReceive extends BroadcastReceiver {
        int mConnectState = CONNECT_STATE_FAIL;

        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (gprs.isConnected() || wifi.isConnected()) {
                if (mConnectState != CONNECT_STATE_SUCCESS) {
                    LogUtils.i(TAG, "NetWorkStateReceive");
                    //监听到网络好了则继续执行上传操作
                    connectSuccess();
                    mConnectState = CONNECT_STATE_SUCCESS;
                    LogUtils.d("连接网络成功");
                }
            } else {
                mConnectState = CONNECT_STATE_FAIL;
                LogUtils.d("连接网络失败");
            }
        }
    }

    protected void connectSuccess() {
    }

}

package com.ecaray.basicres.base;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecaray.basicres.R;
import com.ecaray.basicres.view.swipe.BaseListView;
import com.ecaray.basicres.view.swipe.SwipeToRefreshLayout;


/**
 * 类描述: 分页列表基类
 * @author : Shirley
 * 创建日期: 2016/11/30 17:01
 * 修改人:Shirley
 * 修改时间: 2016/11/30 17:01
 * 修改备注:
 */
public abstract class BaseListActivity<T extends BasePresenter> extends BaseActivity<T> implements
        SwipeRefreshLayout.OnRefreshListener, SwipeToRefreshLayout.OnLoadListener {

    public static int PAGE_SIZE = 20;

    /**
     * 第一页数据刷新完毕
     */
    public static final int REFRESH_FIRST_LIST_VIEW = 0x10001;

    /**
     * 加载更多完毕
     */
    public static final int LOAD_FOOTER_LIST_VIEW = 0x10002;

    /**
     * 加载失败
     */
    public static final int LOAD_FAIL_VIEW = 0x10003;

    /**
     * 数据为空
     */
    public static final int EMPTY_VIEW = 0x10004;

    /**
     * 不需要下拉刷新的列表数据刷新完毕
     */
    public static final int REFRESH_NORMAL_LIST_VIEW = 0x10005;

    /**
     * 停止上拉加载更多，没有下一条的数据
     */
    public static final int NOT_NEXT_DATA_VIEW = 0x10006;

    public static final int HAD_NEXT_DATA_VIEW = 0x10007;

    /**
     * listView
     */
    protected BaseListView mBaseListView;

    /**
     * adapter
     */
    protected CommonBaseAdapter mBaseAdapter;

    protected SwipeToRefreshLayout mSwipeLayout;

    /**
     * 从后台获取数据的SparseArray
     */
    protected SparseArray mDataList;

    private View mPageLoadFailIc, mPageNoDataIc;

    protected ImageView mIvNoData;
    protected TextView mTvNoData;

    /**
     * 界面头部
     */
    protected RelativeLayout mLlHeader;

    /**
     * 界面底部
     */
    protected RelativeLayout mLlFooter;

    /**
     * 是否反向 （下拉加载更多，上拉刷新）
     */
    protected boolean mIsReverse = false;

    /**
     * 设置是否可以下拉刷新 默认可以
     */
    protected boolean isEnableRefresh = true;

    public void setEnableRefresh(boolean enableRefresh) {
        this.isEnableRefresh = enableRefresh;
    }

    protected Handler mBaseHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            enableRefresh(true);
            switch (what) {
                // 不需要下拉刷新的列表数据刷新完毕
                case REFRESH_NORMAL_LIST_VIEW:
                    showNormalView();
                    if (mDataList != null) {
                        mDataList.clear();
                    }
                    if (mSwipeLayout != null) {
                        mSwipeLayout.setIsGetedFullData(false);
                    }
                    if (!mIsReverse) {
                        initListView();
                    }
                    if (mSwipeLayout != null) {
                        enableRefresh(false);
                        mSwipeLayout.setRefreshing(false);
                    }

                    break;
                // 第一页数据刷新完毕
                case REFRESH_FIRST_LIST_VIEW:
                    showNormalView();
                    if (mDataList != null) {
                        mDataList.clear();
                    }
                    if (mSwipeLayout != null) {
                        mSwipeLayout.setIsGetedFullData(false);
                    }
                    if (!mIsReverse) {
                        initListView();
                    }
                    if (mSwipeLayout != null) {
                        enableRefresh(true);
                        mSwipeLayout.setRefreshing(false);
                    }
                    break;
                // 加载更多完毕
                case LOAD_FOOTER_LIST_VIEW:
                    showNormalView();
                    if (mDataList != null) {
                        mDataList.clear();
                    }
                    if (mBaseListView != null) {
                        mBaseListView.adapterNotify();
                    }
                    if (mSwipeLayout != null) {
                        mSwipeLayout.setLoading(false);
                    }
                    break;
                // 加载失败
                case LOAD_FAIL_VIEW:
                    showLoadFailView();
                    break;
                // 数据为空
                case EMPTY_VIEW:
                    showEmptyView();
                    break;
                //没有下一页数据
                case NOT_NEXT_DATA_VIEW:
                    mSwipeLayout.setIsNotMoreData(true);
                    break;
                case HAD_NEXT_DATA_VIEW:
                    mSwipeLayout.setIsNotMoreData(false);
                    break;
                default:
                    break;
            }
        }

    };

    /**
     * 功能：设置actionbar的参数
     * param：displayHomeAsUpEnabled:控制是否允许点击标题调出左菜单 homeButtonEnabled：控制是否允许点击左箭头调出左菜单
     * displayShowTitleEnabled
     * 是否显示标题
     * displayShowCustomEnabled
     * ：允许添加控件到actionbar、
     */
    protected void initListView() {
        if (mBaseListView != null && mBaseAdapter != null) {
            mBaseListView.setMyAdapter(mBaseAdapter);
            mBaseListView.adapterNotify();
        }
        if (mSwipeLayout != null) {
            mSwipeLayout.setOnRefreshListener(this);
            mSwipeLayout.setOnLoadListener(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.pub_activity_refresh_list;
    }

    @Override
    protected void initData() {
    }

    @CallSuper
    @Override
    public void initView() {
        mPageLoadFailIc = findViewById(R.id.page_load_fail_ic);
        mPageNoDataIc = findViewById(R.id.page_no_data_ic);
        mLlHeader = (RelativeLayout) findViewById(R.id.ll_header);
        mLlFooter = (RelativeLayout) findViewById(R.id.ll_footer);
        mIvNoData = (ImageView) mPageNoDataIc.findViewById(R.id.iv_no_data);
        mTvNoData = (TextView) mPageNoDataIc.findViewById(R.id.tv_no_data);
        mBaseListView = (BaseListView) findViewById(R.id.public_lv);
    }

    protected void addHeaderView(View view) {
        mLlHeader.setVisibility(View.VISIBLE);
        mLlHeader.addView(view);
    }

    protected void addFooterView(View view) {
        mLlFooter.addView(view);
    }

    public void setLlFooterVisibility(int visibility) {
        mLlFooter.setVisibility(visibility);
    }

    @Override
    public void setOnInteractListener() {
    }

    @Override
    public void initPresenter() {
    }

    /**
     * 获取ListView初始化数据
     */
    protected abstract void getFirstData();

    /**
     * 获取ListView的下一页数据
     */
    protected abstract void getNextData();

    @Override
    public void onRefresh() {
        getFirstData();
    }

    @Override
    public void onLoad() {
        getNextData();
    }

    /**
     * 功能：设置刷新功能
     */
    protected void enableRefresh(boolean flag) {
        if (mSwipeLayout != null) {
            if (isEnableRefresh) {
                mSwipeLayout.setEnabled(flag);
            } else {
                mSwipeLayout.setEnabled(false);
            }
        }
    }

    protected void showLoadFailView() {
        if (mLlFooter.getVisibility() == View.VISIBLE){
            mLlFooter.setVisibility(View.GONE);
        }

        if (mBaseListView.getVisibility() == View.VISIBLE){
            mBaseListView.setVisibility(View.GONE);
        }

        if (mSwipeLayout != null) {
            mSwipeLayout.setLoading(false);
        }
        if (mPageLoadFailIc.getVisibility() == View.GONE){
            mPageLoadFailIc.setVisibility(View.VISIBLE);
        }

        mPageLoadFailIc.setOnClickListener(v -> getFirstData());
        if (mPageLoadFailIc.getVisibility() == View.GONE){
            mPageLoadFailIc.setVisibility(View.VISIBLE);
        }

    }

    protected void showEmptyView() {
        if (mLlFooter.getVisibility() == View.VISIBLE){
            mLlFooter.setVisibility(View.GONE);
        }

        if (mBaseListView.getVisibility() == View.VISIBLE){
            mBaseListView.setVisibility(View.GONE);
        }

        if (mSwipeLayout != null) {
            mSwipeLayout.setLoading(false);
        }
        if (mPageNoDataIc.getVisibility() == View.GONE){
            mPageNoDataIc.setVisibility(View.VISIBLE);
        }

        if (mPageLoadFailIc.getVisibility() == View.VISIBLE){
            mPageLoadFailIc.setVisibility(View.GONE);
        }

    }

    protected void showNormalView() {
        if (mBaseListView.getVisibility() == View.GONE){
            mBaseListView.setVisibility(View.VISIBLE);
        }

        if (mPageNoDataIc.getVisibility() == View.VISIBLE){
            mPageNoDataIc.setVisibility(View.GONE);
        }

        if (mPageLoadFailIc.getVisibility() == View.VISIBLE){
            mPageLoadFailIc.setVisibility(View.GONE);
        }

    }


}

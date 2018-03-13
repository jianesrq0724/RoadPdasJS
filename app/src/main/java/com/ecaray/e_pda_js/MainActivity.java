package com.ecaray.e_pda_js;

import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.util.ToastUtils;
import com.luojilab.componentlib.router.ui.UIRouter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {

        findViewById(R.id.textView).setOnClickListener(v -> {
            ToastUtils.showShort(mContext, "跳转到登录");
            UIRouter.getInstance().openUri(mContext, "DDComp://login/loginIn", null);
        });

    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public void initPresenter() {

    }
}

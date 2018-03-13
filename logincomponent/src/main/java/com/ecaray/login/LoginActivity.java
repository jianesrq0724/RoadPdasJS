package com.ecaray.login;

import com.ecaray.basicres.base.BaseActivity;
import com.luojilab.router.facade.annotation.RouteNode;
/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
@RouteNode(path = "/loginIn", desc = "登录页面")
public class LoginActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public void initPresenter() {

    }
}

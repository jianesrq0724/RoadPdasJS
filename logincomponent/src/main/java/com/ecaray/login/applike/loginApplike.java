package com.ecaray.login.applike;


import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/12
 */

public class loginApplike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("login");
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("login");
    }
}

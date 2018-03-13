package com.ecaray.e_pda_js;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.util.ToastUtils;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/11
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView).setOnClickListener(v -> {
            ToastUtils.showShort(MainActivity.this, "跳转到登录");
            UIRouter.getInstance().openUri(MainActivity.this, "DDComp://login/loginIn", null);
        });
    }

}

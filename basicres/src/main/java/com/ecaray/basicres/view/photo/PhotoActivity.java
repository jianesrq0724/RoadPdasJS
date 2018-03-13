package com.ecaray.basicres.view.photo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.bumptech.glide.request.RequestOptions;
import com.ecaray.basicres.R;
import com.ecaray.basicres.base.BaseActivity;
import com.ecaray.basicres.util.AppFunctionUtil;


/**
 * 类描述：显示单张大图页面
 * 创建人：Shirley
 */
public class PhotoActivity extends BaseActivity implements OnClickListener {

    public static final String IMG_PATH = "imgPath";

    public static final String IMG_PATH_FAIL = "imgPathFail";

    private String mPicPath;

    private int mPicPathFail;

    /**
     * @param act
     * @param imgPath 图片地址
     */
    public static void startActivity(Activity act, String imgPath) {
        Intent i = new Intent(act, PhotoActivity.class);
        i.putExtra(IMG_PATH, imgPath);
        act.startActivity(i);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mPicPath = intent.getStringExtra(IMG_PATH);
        mPicPathFail = intent.getIntExtra(IMG_PATH_FAIL, R.mipmap.pub_default_loading);
    }

    @Override
    public void initView() {
        mToolBarManager.init().setTitle("图片预览");

        ZoomImageView iv_pic = (ZoomImageView) findViewById(R.id.iv_pic);
//        ImageView iv_back_left = (ImageView) findViewById(R.id.iv_back_left);
        if (mPicPath != null) {
            RequestOptions myOptions = new RequestOptions().error(mPicPathFail);
            AppFunctionUtil.getGlideReqManager().load(mPicPath).apply(myOptions).into(iv_pic);
        }
        iv_pic.setOnClickListener(v -> finish());
//        iv_back_left.setOnClickListener(this);
    }

    @Override
    public void setOnInteractListener() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onClick(View v) {
        int vid = v.getId();
        if (vid == R.id.iv_pic) {
            finish();

        }
    }

}

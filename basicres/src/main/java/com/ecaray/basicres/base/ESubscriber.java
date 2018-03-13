package com.ecaray.basicres.base;

import android.content.Context;

import com.ecar.ecarnetwork.base.BaseSubscriber;
import com.ecar.ecarnetwork.http.exception.CommonException;
import com.ecaray.basicres.R;
import com.ecaray.basicres.util.ToastUtils;
import com.ecaray.basicres.view.dialog.ReLoginDialog;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/22 10:59
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/22 10:59
 * <p>
 * 修改备注:
 * <p>
 * ================================================
 */
public abstract class ESubscriber<T> extends BaseSubscriber<T> {

    private static ReLoginDialog sReLoginDialog;
    private final Context mContext;

    static {
        if(sReLoginDialog == null){
            sReLoginDialog = new ReLoginDialog();
        }
    }

    public ESubscriber(Context context) {
        super(context, sReLoginDialog);
        mContext = context;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
    }

    @Override
    protected void onUnifiedError(CommonException ex) {

        if(CommonException.FLAG_NET_ERROR.equals(ex.getCode()) || CommonException.FLAG_NET_TIME_OUT.equals(ex.getCode())){
            ToastUtils.showShort(mContext, R.string.common_no_network_new);
        }
    }

    @Override
    protected void onUserError(CommonException ex) {
        super.onUserError(ex);
        if(mContext == null){
            return;
        }
        if(ex.getMsg().equals(mContext.getString(R.string.not_sign))){
            ReLoginDialog.showReLoginDialog(mContext,
                    mContext.getString(R.string.had_not_sign_please_relogin), mContext.getString(R.string.relogin));
        }

    }


}

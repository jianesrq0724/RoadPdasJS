package com.ecaray.basicres.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ecar.ecarnetwork.interfaces.security.IInvalid;
import com.ecaray.basicres.R;
import com.ecaray.basicres.util.AppFunctionUtil;
import com.ecaray.basicres.util.AppUtils;
import com.ecaray.basicres.util.BuildConfigUtils;
import com.ecaray.basicres.util.Utils;


/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人: Eric_Huang
 * <p>
 * 创建时间: 2016/9/22 11:01
 * <p>
 * 修改人:Eric_Huang
 * <p>
 * 修改时间: 2016/9/22 11:01
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class ReLoginDialog implements IInvalid {

    public static CustomDialog sReloginDialog;


    @Override
    public void reLogin(Context context, String s) {
        AppFunctionUtil.stopPushAndVoice();
        showReLoginDialog(context);
    }

    /**
     * 显示异地登录弹出框
     */
    private void showReLoginDialog(Context context) {
        if (context != null) {
            if (context instanceof Activity) {
                if (sReloginDialog == null) {
                    sReloginDialog = getDialog(context, context.getString(R.string.check_user_expire),
                            context.getString(R.string.relogin), view -> {
                                sReloginDialog.dismiss();
                                Intent lIntent = new Intent();
                                lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                lIntent.setAction(AppUtils.getAppPackageName() + ".LoginActivity");
                                context.startActivity(lIntent);
                                sReloginDialog = null;
                            });
                }
            }
        }
    }

    /**
     * 显示未签到弹出框
     *
     * @param msg     提示信息
     * @param btnText 按钮信息
     */
    public static void showReLoginDialog(Context context, String msg, String btnText) {
        if (context != null) {
            if (context instanceof Activity) {
                sReloginDialog = getDialog(context, msg, btnText, view -> {
                    sReloginDialog.dismiss();
                    Intent lIntent = new Intent();
                    lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    lIntent.setAction(AppUtils.getAppPackageName() + ".LoginActivity");
                    context.startActivity(lIntent);
                    sReloginDialog = null;
                });
            }
        }
    }

    public static CustomDialog getDialog(Context context, String msg, String btnText, View.OnClickListener clickListener) {
        CustomDialog lCustomDialog = new CustomDialog(context);
        lCustomDialog.show();
        lCustomDialog.hidLeftBtn();
        lCustomDialog.setCanceledOnTouchOutside(false);
        lCustomDialog.setCancelable(false);
        lCustomDialog.setMsgText(msg);
        lCustomDialog.setRightBtnText(btnText);
        lCustomDialog.setRightBtnListener(clickListener);
        return lCustomDialog;
    }
}

package com.ecaray.basicres.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;


import com.ecaray.basicres.entity.login.VersionBean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载安装apk
 * @author Administrator
 *
 */
public class UpdateManagerUtils {
    public static final int  Download_File_Success =44 ;
    public static final int  Download_File_Failed = 45;
    public static final int  Download_File_Size = 46;
    public  static final int  Download_Process = 47;
    private static  final String DownLoad_Apk_Name = "pda.apk";
    private ProgressDialog progressDialog;
    private String url;
    private Context context;
    private int mUpdateState;
    private long mExitTime = 0 ;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case Download_File_Success:
                    progressDialog.dismiss();
                    break;
                case Download_File_Failed:
                    progressDialog.dismiss();
                    Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
                case Download_File_Size:
                    progressDialog.setMax(msg.getData().getInt("max"));
                    break;
                case Download_Process:
                    progressDialog.setProgress(msg.getData().getInt("process"));
                    break;
            }
        }

    };

    public UpdateManagerUtils(Context context, String url, int updateState){
        this.context = context;
        this.url = url;
        mUpdateState = updateState;
    }

    class DownLoadTask implements Runnable {

        @Override
        public void run() {
            try {
                File file = getFileFromServer(url,handler);
                if(file!=null){
                    handler.sendEmptyMessage(Download_File_Success);
                    installApk(context,file);
                }else{
                    handler.sendEmptyMessage(Download_File_Failed);
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("tag", e.toString());
            }
        }
    }

    /**
     * 下载更新apk
     * @param path 下载链接
     * @return  下载文件
     * @throws Exception
     */
    public File getFileFromServer(String path, Handler handler)
            throws Exception {
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("tag", "update url "+path);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn .setRequestProperty("Accept-Encoding", "identity");
            Log.i("code", conn.getResponseCode()+"");
            conn.setConnectTimeout(5000);
            Message msg1 = new Message();
            msg1.what = Download_File_Size ;
            Bundle data=new Bundle();
            data.putInt("max", conn.getContentLength() / 1024);
            msg1.setData(data);
            handler.sendMessage(msg1);
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(),
                    DownLoad_Apk_Name);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            int temp = conn.getContentLength() / 30;
            int tempstep = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                tempstep += len;
                if (tempstep >= temp) {
                    tempstep = 0;
                    Message msg = new Message();
                    msg.what = Download_Process;
                    Bundle data2=new Bundle();
                    data2.putInt("process", total/ 1024);
                    msg.setData(data2);
                    handler.sendMessage(msg);
                }
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }
    /**
     * 安装apk
     * @param file apk文件
     */
    private  void installApk(Context context, File file){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/vnd.android.package-archive";
        intent .setDataAndType(Uri.fromFile(file),type);
        context.startActivity(intent);
    }
    public void startDownloadFile(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("正在下载更新");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(onKeyListener);
        new Thread(new DownLoadTask()).start();
    }
    /*
     * 设置返回键不回退
     */
    private OnKeyListener onKeyListener = new OnKeyListener() {

        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if(keyCode== KeyEvent.KEYCODE_BACK){

                if (mUpdateState == VersionBean.NOT_FORCE){
                    if(progressDialog != null && progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }else{
                    if (System.currentTimeMillis() - mExitTime > 1500 || mExitTime == 0) {
                        ToastUtils.showShort(context,"再按一次退出路边收费终端");
                        mExitTime = System.currentTimeMillis();
                        return true;
                    }else if(System.currentTimeMillis() - mExitTime <= 1500 && System.currentTimeMillis() - mExitTime > 500){
                        ((Activity) context).finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                }
            }

            return true;
        }
    };

}


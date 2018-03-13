package com.ecaray.basicres.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 类描述: 文件管理工具
 * 创建人: Eric_Huang
 * 创建时间: 2016/9/26 16:10
 * 修改人:Eric_Huang
 * 修改时间: 2016/9/26 16:10
 */
public class FileUtils {

    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/PDA/" + AppUtils.getAppPackageName() + "/log/";
    private static final String FILE_NAME = "Log";
    /**
     * log文件的后缀名
     */
    private static final String FILE_NAME_SUFFIX = ".txt";

    /**
     * 将log日志写入sdCard
     */
    public static void writeLogToSDCard(String tag, String content) {
        //如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        //以当前时间创建log文件
        File file = new File(PATH + FILE_NAME + TimeUtils.TimeFormat2DataV2(current) + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pw.println(TimeUtils.TimeFormat3Date(current) + "/" + AppUtils.getAppPackageName() + "/" + tag);
            pw.println(content + "\n");
            pw.close();
        } catch (Exception e) {

        }
    }


    public static boolean retrieveApkFromAssets(Context context, String fileName, String path) {
        boolean bRet = false;

        try {
            InputStream e = context.getAssets().open(fileName);
            File file = new File(path);
            boolean lNewFile = file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];

            int i;
            while ((i = e.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }

            fos.close();
            e.close();
            bRet = true;
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return bRet;
    }

    public static void deleteFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        //1級文件刪除
        if (!file.isDirectory()) {
            file.delete();
        } else if (file.isDirectory()) {
            //2級文件列表
            String[] filelist = file.list();
            //獲取新的二級路徑
            for (String aFilelist : filelist) {
                File filessFile = new File(path + "\\" + aFilelist);
                if (!filessFile.isDirectory()) {
                    filessFile.delete();
                } else if (filessFile.isDirectory()) {
                    //遞歸調用
                    deleteFiles(path + "\\" + aFilelist);
                }
            }
            file.delete();
        }
    }


}

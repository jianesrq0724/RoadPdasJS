package com.ecar.epark.greendaolib.engine;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.greendaolib.helper.GreenDaoQLiteOpenHelper;
import com.ecarary.epark.greendao.DaoMaster;
import com.ecarary.epark.greendao.DaoSession;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class BaseGreenDaoEngin {

    public static DaoSession daoSession;
    public static Application mApplication;

    public static void initDataBase(Application context) {
        mApplication = context;
        GreenDaoQLiteOpenHelper openHelper = new GreenDaoQLiteOpenHelper(context, "ecar_pda_green.db", null);
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context,"ecartest1.db",null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    public boolean isSessionNull() {
        return daoSession == null;
    }


}

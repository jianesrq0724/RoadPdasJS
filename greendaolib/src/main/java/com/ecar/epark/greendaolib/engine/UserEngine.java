package com.ecar.epark.greendaolib.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ecar.epark.greendaolib.entity.UserTe;
import com.ecar.epark.greendaolib.helper.GreenDaoQLiteOpenHelper;
import com.ecarary.epark.greendao.DaoMaster;
import com.ecarary.epark.greendao.DaoSession;

import java.util.List;

/**
 * Created by Administrator on 2017/11/1 0001.
 */

public class UserEngine {

    private DaoSession daoSession;

    public static void main(String[] arsg) {

    }

    public void setupDataBase(Context context) {
        GreenDaoQLiteOpenHelper openHelper = new GreenDaoQLiteOpenHelper(context, "ecartest.db", null);
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context,"ecartest1.db",null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public void insert(UserTe user) {
        try {
            long insert = daoSession.getUserTeDao().insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(UserTe user) {
        try {
            daoSession.getUserTeDao().delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(UserTe user) {
        try {
            daoSession.getUserTeDao().update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserTe> findAll() {
        List<UserTe> users = null;
        try {
            users = daoSession.getUserTeDao().loadAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}

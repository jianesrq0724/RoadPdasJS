package com.ecar.epark.greendaolib.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ecarary.epark.greendao.DaoMaster;
import com.ecarary.epark.greendao.ResSignInfoDao;
import com.ecarary.epark.greendao.ResVoiceDao;

import org.greenrobot.greendao.database.Database;

public class GreenDaoQLiteOpenHelper extends DaoMaster.OpenHelper {
    public GreenDaoQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        MigrationHelper.migrate(db,UserDao.class,User3Dao.class);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                },ResVoiceDao.class, ResSignInfoDao.class);
    }
}
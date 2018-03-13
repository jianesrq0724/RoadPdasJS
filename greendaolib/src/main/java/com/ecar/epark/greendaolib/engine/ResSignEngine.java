package com.ecar.epark.greendaolib.engine;

import com.ecar.epark.greendaolib.entity.ResSignInfo;
import com.ecar.epark.greendaolib.util.ListConvertAdapter;
import com.ecarary.epark.greendao.ResSignInfoDao;
import com.ecarary.epark.greendao.ResVoiceDao;

import java.util.List;

/**
 * 语音播报相关推送数据
 */

public class ResSignEngine extends BaseGreenDaoEngin {

    private static ResSignEngine mEngine;

    private ResSignEngine() {
    }

    public static ResSignEngine getInstance() {
        if (mEngine == null) {
            synchronized (ResSignEngine.class) {
                if (mEngine == null) {
                    mEngine = new ResSignEngine();
                }
            }
        }
        return mEngine;
    }

    public void insert(ResSignInfo info) {
        if (isSessionNull()) {
            return;
        }
        if (info == null) {
            return;
        }
        daoSession.getResSignInfoDao().insert(info);
    }


    public void insertList(List<ResSignInfo> infos) {
        if (isSessionNull()) {
            return;
        }
        if (infos == null) {
            return;
        }

        if (infos.isEmpty()) {
            return;
        }
        //采用方案，1.删了 2.加进来  避免判断插入还是更新
        List<String> values = new ListConvertAdapter<String, ResSignInfo>(infos, "Code").getElements();
        daoSession.getResSignInfoDao().queryBuilder().where(ResVoiceDao.Properties.Code.in(values)).buildDelete().executeDeleteWithoutDetachingEntities();
        daoSession.getResSignInfoDao().insertInTx(infos);
        // // 删除指定信息
//        stuDao.queryBuilder().where(StudentDao.Properties.StuName.eq("过滤str")).buildDelete().executeDeleteWithoutDetachingEntities();
//        更新指定信息
//        Student student = stuDao.queryBuilder().where(StudentDao.Properties.StuName.eq("过滤str")).build().unique();
    }

    public List<ResSignInfo> findAll() {
        if (isSessionNull()) {
            return null;
        }
        List<ResSignInfo> infos = null;
        try {
            infos = daoSession.getResSignInfoDao().loadAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    public void deleteOldData(long date) {
        daoSession.getResSignInfoDao().queryBuilder().where(ResSignInfoDao.Properties.AddTime.lt(date)).build().unique();
    }
//
//    public List<ResSignInfo> findPage(int index,int count) {
//        if(isSessionNull()){
//            return null;
//        }
//        List<ResVoice> infos = null;
//        try {
//            infos = daoSession.getResVoiceDao().queryBuilder()
//                    .offset((index-1)*10)//偏移
//                    .limit(count)
//                    .orderDesc(ResVoiceDao.Properties.SendTime)
//                    .list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return infos;
//    }


//    public void delete(ResSignInfo info) {
//        try {
//            daoSession.getResVoiceDao().delete(info);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(ResSignInfo info) {
//        try {
//            daoSession.getResVoiceDao().update(info);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

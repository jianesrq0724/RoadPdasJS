package com.ecar.epark.greendaolib.engine;

import com.ecar.ecarnetwork.db.SettingPreferences;
import com.ecar.epark.greendaolib.entity.ResVoice;
import com.ecar.epark.greendaolib.util.ListConvertAdapter;
import com.ecarary.epark.greendao.ResVoiceDao;


import java.util.List;

/**
 * 语音播报相关推送数据
 */

public class ResVoiceEngine extends BaseGreenDaoEngin{

    private static ResVoiceEngine resVoiceEngine;

    private ResVoiceEngine() {
    }

    public static ResVoiceEngine getInstance(){
        if(resVoiceEngine == null){
            synchronized (ResVoiceEngine.class){
                if(resVoiceEngine ==null){
                    resVoiceEngine = new ResVoiceEngine();
                }
            }
        }
        return resVoiceEngine;
    }

    public void insertList(List<ResVoice> infos) {
        if(isSessionNull()){
            return;
        }
        if(infos == null){
            return;
        }

        if(infos.isEmpty()){
            return;
        }

        //采用方案，1.删了 2.加进来  避免判断插入还是更新
        List<String> values = new ListConvertAdapter<String,ResVoice>(infos, "Code").getElements();
        daoSession.getResVoiceDao().queryBuilder().where(ResVoiceDao.Properties.Code.in(values)).buildDelete().executeDeleteWithoutDetachingEntities();
        String userId = getUserId();
        for(ResVoice resVoice:infos){
            resVoice.setUserId(userId);
        }
        daoSession.getResVoiceDao().insertInTx(infos);
        // // 删除指定信息
//        stuDao.queryBuilder().where(StudentDao.Properties.StuName.eq("过滤str")).buildDelete().executeDeleteWithoutDetachingEntities();
//        更新指定信息
//        Student student = stuDao.queryBuilder().where(StudentDao.Properties.StuName.eq("过滤str")).build().unique();
    }



    public void insert(ResVoice info) {
        if(isSessionNull()){
            return;
        }
        if(info == null){
            return;
        }
        daoSession.getResVoiceDao().insert(info);
    }


    public void deleteOldData(long date){
        try{
            List<ResVoice> list = daoSession.getResVoiceDao().queryBuilder().where(ResVoiceDao.Properties.SendTime.lt(date + "")).build().list();
            if(list != null && !list.isEmpty()){
                daoSession.getResVoiceDao().deleteInTx(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void delete(ResVoice info) {
        try {
            daoSession.getResVoiceDao().delete(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ResVoice info) {
        try {
            daoSession.getResVoiceDao().update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击后表示查看全部
     */
    public void updateAll() {
        try {
            List<ResVoice> list  = daoSession.getResVoiceDao().queryBuilder().where(ResVoiceDao.Properties.IsRead.notEq("1")).build().list();
            for(ResVoice info:list){
                info.setIsRead("1");//更改为已查看
//                daoSession.getResVoiceDao().update(info);
            }
            daoSession.getResVoiceDao().updateInTx(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取纬度
     * @return
     */
    public long getUnReadCount(){
        long count = 0;
        String userId = getUserId();
        try {
            count = daoSession.getResVoiceDao().queryBuilder().where(ResVoiceDao.Properties.IsRead.notEq("1"),ResVoiceDao.Properties.UserId.eq(userId)).buildCount().count();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public List<ResVoice> findAll() {
        if(isSessionNull()){
            return null;
        }
        List<ResVoice> infos = null;
        try {
            infos = daoSession.getResVoiceDao().loadAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    public List<ResVoice> findPage(int index,int count) {
        if(isSessionNull()){
            return null;
        }
        List<ResVoice> infos = null;
        try {
            String userId = getUserId();
            infos = daoSession.getResVoiceDao().queryBuilder()
                    .where(ResVoiceDao.Properties.UserId.eq(userId))
                    .offset((index-1)*10)//偏移
                    .limit(count)
                    .orderDesc(ResVoiceDao.Properties.SendTime)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    /**
     * 用户id
     */
    private String getUserId(){
        try {
            return SettingPreferences.getDefault(mApplication).getU();
//            ResVoice unique = daoSession.getResVoiceDao().queryBuilder().orderDesc(ResVoiceDao.Properties.SendTime).limit(1).build().unique();//倒序
//            return unique.getUserId();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

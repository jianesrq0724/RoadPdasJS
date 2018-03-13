package com.ecaray.basicres.interfaces;

import com.ecar.ecarnetwork.interfaces.view.ILoading;

import java.util.List;

/**
 * 类描述: 列表页面共用
 * 创建人: Shirley
 * 创建日期: 2016/12/28 15:25
 * 修改人:Shirley
 * 修改时间: 2016/12/28 15:25
 * 修改备注:
 */
public interface IListView extends ILoading {

    void refreshListView(List list);//设置当前的listView的Adapter

    void sendMessageToBase(int what);//向baseActivity的handler发送message


    /**
     * 设置总的数量提示
     */
    void setSearchTotalCountTip(String carPalte, int totalCount);
}

package com.ecaray.basicres.view.load;

/**
 * ===============================================
 * <p>
 * 类描述:
 * <p>
 * 创建人:   happy
 * <p>
 * 创建时间: 2016/7/7 0007 下午 17:43
 * <p>
 * 修改人:   happy
 * <p>
 * 修改时间: 2016/7/7 0007 下午 17:43
 * <p>
 * 修改备注:
 * <p>
 * ===============================================
 */
public class LoadingHelper {

    public int count;

    public void addCount(){
        count ++;
    }

    public void subCount(){
        count --;
    }

    public boolean isZero(){//达到条件
        return 0 >= count;
    }
}

package com.ecaray.basicres.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 类描述:自定义gridview，解决ListView中嵌套gridview显示不正常的问题（1行半）
 * 创建人: CarlLu
 * 创建日期: 2016/8/8 11:10
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

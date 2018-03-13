package com.ecaray.basicres.view.numberpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;


public class ParkNumberPicker extends NumberPicker {
 
    public ParkNumberPicker(Context context) {
        super(context);
    }
 
    public ParkNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public ParkNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
 
    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }
 
    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }
 
    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }
 
    public void updateView(View view) {
        if (view instanceof EditText) {
            //这里修改字体的属性
//            ((EditText) view).setTextColor(Color.parseColor("#BAA785"));
//            ((EditText) view).setTextColor(getResources().getColor(R.color.top_color));
            ((EditText) view).setTextSize(TypedValue.COMPLEX_UNIT_SP,23);
            ((EditText) view).setPadding(0,5,0,5);
//            ((EditText) view).setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

//    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
//        NumberPicker picker = numberPicker;
//        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
//        for (Field pf : pickerFields) {
//            if (pf.getName().equals("mSelectionDivider")) {
//                pf.setAccessible(true);
//                try {
//                    //设置分割线的颜色值
////                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.top_color)));
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (Resources.NotFoundException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//    }
 
}
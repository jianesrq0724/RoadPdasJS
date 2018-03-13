package com.ecaray.basicres.view.numberpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.ecaray.basicres.R;

/**
 * 滚轮时间选择器
 * Created by Administrator on 2015/12/8 0008.
 */
public class NumberPickerView extends LinearLayout implements NumberPicker.OnValueChangeListener, NumberPicker.OnScrollListener {

    private NumberPicker hourPicker;
    private NumberPicker minutePicker;
    private int hour;
    private int minute;
    private float times;

    public NumberPickerView(Context context) {
        super(context);
        initView();
    }

    public NumberPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumberPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_time_picker, NumberPickerView.this);
        hourPicker = (NumberPicker) findViewById(R.id.hourpicker);
        minutePicker = (NumberPicker) findViewById(R.id.minuteicker);
        init();

    }

    private String[] minutes = {"00", "30"};
    private String[] minutesOne = {"00"};
    String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};

    private void init() {

        hourPicker.setDisplayedValues(hours);
        hourPicker.setOnValueChangedListener(this);
        hourPicker.setOnScrollListener(this);
        hourPicker.setMaxValue(hours.length - 1);
        hourPicker.setMinValue(0);
        hourPicker.setValue(0);
        //不可编辑
        hourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        setTwo(true);
    }

    private void setTwo(boolean isTwo) {
        minutePicker.setDisplayedValues(isTwo ? minutes : minutesOne);

        minutePicker.setOnValueChangedListener(this);
        minutePicker.setOnScrollListener(this);
        minutePicker.setMaxValue(isTwo ? minutes.length - 1 : minutesOne.length - 1);
        minutePicker.setMinValue(0);
        minutePicker.setValue(30);
        //不可编辑
        minutePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }

    public void setDefaultValue() {
        hourPicker.setValue(0);
        minutePicker.setValue(30);
        times = hourPicker.getValue() + minutePicker.getValue() % 2 * 0.5f;
    }

    /**
     * 设置选择器value
     */
    public void setNumValue(int totalMinutes) {
        //拆分小时和分
        int hour = Math.max(0, Math.abs(totalMinutes / 60));
        int minute = Math.max(0, Math.abs(totalMinutes % 60));
        if (minute >= 30) {
            minute = 1;
        } else {
            minute = 0;
        }
        if (hour >= 24) {
            hour = 24;
            minute = 0;
        }
        hourPicker.setValue(hour);
        minutePicker.setValue(minute);
        times = hourPicker.getValue() + minutePicker.getValue() % 2 * 0.5f;
    }

    public float getTimeValue() {
        return times;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (hourPicker.getValue() == hours.length - 1 && minutePicker.getValue() != 0) {
            minutePicker.setValue(0);
            hourPicker.setValue(hours.length - 1);
            return;
        }
        if (picker.getId() == R.id.hourpicker) {
            this.hour = newVal;
        } else if (picker.getId() == R.id.minuteicker) {
            this.minute = newVal;
        }
        times = hour + minute % 2 * 0.5f;
        if (isStop) {
            if (pickerListner != null) {
                pickerListner.select(times);
            }
        }
        if (newVal == hours.length - 1 && minutePicker.getValue() != 0) {
            minutePicker.setValue(0);
        }
    }

    private boolean isStop = true;

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            //后续滑动
            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING:
                isStop = false;
                break;
            //滑动停止
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                if (hourPicker.getValue() == hours.length - 1 && minutePicker.getValue() != 0) {
                    minutePicker.setValue(0);
                    hourPicker.setValue(hours.length - 1);
                    return;
                }
                this.hour = hourPicker.getValue();
                this.minute = minutePicker.getValue();
                times = hour + minute % 2 * 0.5f;
                isStop = true;
                if (pickerListner != null) {
                    pickerListner.select(times);
                }
                break;
            //滑动中
            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                isStop = false;
                break;
        }
    }

    public interface NumberPickerListener {
        void select(float times);
    }

    private NumberPickerListener pickerListner;

    /**
     * 注册监听
     *
     * @param seletor
     */
    public void setPickerSelector(NumberPickerListener seletor) {
        this.pickerListner = seletor;
    }

}

package com.ecaray.basicres.view.TextSelector;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ecaray.basicres.R;


/**
 *
 *充值界面的点选按钮
 *
 */
public class WalleteAmountSelectTextViewNoAct extends Fragment implements View.OnClickListener {

    /**
     * 选项个数
     */
    private final int total = 3;

    /**
     * 所有选项
     */
    private TextView[] textViews = new TextView[total];

    /**
     * 当前选择的金额
     */
    private   int currentMoney;

    /**
     * 当前是否被选择
     */
    private  boolean isSlected;

    /**
     * 金额输入框
     */
    EditText moneyEdit;
    private int drawableId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wallet_amount_selector, container, false);
    }

    public WalleteAmountSelectTextViewNoAct() {
        super();
    }


    public void setMoneyEdit(EditText moneyEdit) {
        this.moneyEdit = moneyEdit;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {
        TextView mTv_amount_one = (TextView) getView().findViewById(R.id.tv_amount_one);
        TextView mTv_amount_two = (TextView) getView().findViewById(R.id.tv_amount_two);
        TextView mTv_amount_three = (TextView) getView().findViewById(R.id.tv_amount_three);

        textViews[0] = mTv_amount_one;
        textViews[1] = mTv_amount_two;
        textViews[2] = mTv_amount_three;

        //设置点击事件
        for (int i = 0; i < total; i++) {
            textViews[i].setOnClickListener(this);
            if (i == 0) {
                textViews[i].setBackgroundResource(drawableId);
            }else{
                textViews[i].setBackgroundResource(R.drawable.background_wallet_amount_unselected);
            }
        }
        //默认选中100
        currentMoney = 50;
        selected(textViews[0]);
    }
    public void setBackground(int drawableId){
        this.drawableId = drawableId;
    }




    @Override
    public void onClick(View v) {
        setAllUnselected();
        int i = v.getId();
        if (i == R.id.tv_amount_one) {
            currentMoney = 50;
            selected(textViews[0]);

        } else if (i == R.id.tv_amount_two) {
            currentMoney = 100;
            selected(textViews[1]);

        } else if (i == R.id.tv_amount_three) {
            currentMoney = 500;
            selected(textViews[2]);

        }
    }

    public int getCurrentMoney(){
        return currentMoney;
    }
    public boolean isSlected(){
        return isSlected;
    }

    //设置所有选项为未被选
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setAllUnselected() {
        for (int i = 0; i < total; i++) {
            textViews[i].setBackgroundResource(R.drawable.background_wallet_amount_unselected);
            textViews[i].setTextColor(getResources().getColor(R.color.black));
        }
        isSlected = false;
        moneyEdit.setCursorVisible(true);
    }

    //设为选中
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.background_wallet_amount_selected1);
        textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.black));
        isSlected = true;
        if (moneyEdit != null) {
            moneyEdit.setText("");
            moneyEdit.setCursorVisible(false);
        }
    }
}
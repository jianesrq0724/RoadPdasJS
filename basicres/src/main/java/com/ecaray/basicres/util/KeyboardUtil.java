package com.ecaray.basicres.util;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.ecaray.basicres.R;


/**
 * 类描述: 自定义键盘工具类
 * @author : Eric_Huang
 * 创建时间: 2016/6/29 14:59
 * 修改人:Eric_Huang
 * 修改时间: 2016/6/29 14:59
 */
public class KeyboardUtil {

    public static final int KEYCODE_UP = -7;
    public static final int KEYCODE_DOWN = -8;

    private Context ctx;
    private Activity act;
    private KeyboardView keyboardView;
    /**
     * 数字键盘
     */
    private Keyboard kNum;

    /**
     * 字母键盘1
     */
    private Keyboard kLetter1;

    /**
     * 字母键盘2
     */
    private Keyboard kLetter2;

    /**
     * 是否数据键盘
     */
    public boolean isnun = false;

    /**
     * 是否大写
     */
    public boolean isupper = false;
    public boolean mIsShowLetter = true;

    private EditText ed;

    private SlideView mSlideView;

    public KeyboardUtil(Activity act, Context ctx, EditText edit) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_keyboard);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(kLetter1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    public KeyboardUtil(Activity act, Context ctx, EditText edit, boolean isShowLetter) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_key_board);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        if (isShowLetter == true) {
            keyboardView.setKeyboard(kLetter1);
        } else {
            keyboardView.setKeyboard(kNum);
            mIsShowLetter = false;
        }
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    public KeyboardUtil(Activity act, Context ctx, EditText edit, SlideView slideView) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_keyboard);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(kLetter1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
        mSlideView = slideView;
    }

    public KeyboardUtil(Activity act, Context ctx, EditText edit, KeyboardView keyboardView) {
        this.act = act;
        this.ctx = ctx;
        this.ed = edit;
        kNum = new Keyboard(ctx, R.xml.number_keyboard);
        kLetter1 = new Keyboard(ctx, R.xml.letter_keyboard_1);
        kLetter2 = new Keyboard(ctx, R.xml.letter_keyboard_2);
        this.keyboardView = keyboardView;
        keyboardView.setKeyboard(kLetter1);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            // 完成
            if (primaryCode == Keyboard.KEYCODE_DONE) {
                if (mSlideView != null){
                    mSlideView.hideView();
                }

                hideKeyboard();
                // 回退
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                // 大小写切换
            } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                keyboardView.setKeyboard(kLetter2);
                // 数字键盘切换
            } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
                if(mIsShowLetter){
                    if (isnun) {
                        isnun = false;
                        keyboardView.setKeyboard(kLetter1);
                    } else {
                        isnun = true;
                        keyboardView.setKeyboard(kNum);
                    }
                }
                // 字母键盘上一页
            } else if (primaryCode == KEYCODE_UP) {
                keyboardView.setKeyboard(kLetter1);
                // 字母键盘下一页
            } else if (primaryCode == KEYCODE_DOWN) {
                keyboardView.setKeyboard(kLetter2);
                // go left
            } else if (primaryCode == 57419) {
                if (start > 0) {
                    ed.setSelection(start - 1);
                }
                // go right
            } else if (primaryCode == 57421) {
                if (start < ed.length()) {
                    ed.setSelection(start + 1);
                }
            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };

    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }

    public interface SlideView {
        void hideView();
    }

}
/*
 * 文件名称:          SheetButton.java
 *
 * 编译器:            android2.2
 * 时间:              下午6:06:50
 */
package com.office.viewer.excel.sheetbar;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.office.viewer.R;


public class SheetButton extends LinearLayout {

    private static final int FONT_SIZE = 12;

    private static final int SHEET_BUTTON_MIN_WIDTH = 100;

    private int sheetIndex;

    private TextView textView;

    private boolean active;


    public SheetButton(Context context, String sheetName, int sheetIndex) {
        super(context);
        setOrientation(HORIZONTAL);
        this.sheetIndex = sheetIndex;

        init(context, sheetName);
    }

    private void init(Context context, String sheetName) {
        textView = new TextView(context);
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_normal_color));
        textView.setPadding(6, 6, 6, 6);
        textView.setText(sheetName);
        textView.setTextSize(FONT_SIZE);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        int w = (int) textView.getPaint().measureText(sheetName) + 12;
        w = Math.max(w, SHEET_BUTTON_MIN_WIDTH);
        addView(textView, new LayoutParams(w, LayoutParams.MATCH_PARENT));
    }

    /**
     *
     */
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!active) {
                    textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_normal_color));
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 选中或取消选中用到
     */
    public void changeFocus(boolean gainFocus) {
        active = gainFocus;
        if (gainFocus) {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_select_color));
        } else {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_normal_color));
        }
    }

    public int getSheetIndex() {
        return this.sheetIndex;
    }

    public void dispose() {
        textView = null;
    }

}

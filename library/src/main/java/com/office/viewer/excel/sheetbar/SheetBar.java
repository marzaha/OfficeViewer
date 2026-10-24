/*
 * 文件名称:          SheetList.java
 *
 * 编译器:            android2.2
 * 时间:              下午4:13:50
 */
package com.office.viewer.excel.sheetbar;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.office.viewer.R;
import com.office.viewer.constant.EventConstant;
import com.office.viewer.system.IControl;

import java.util.Vector;

/**
 * <p>
 * <p>
 * Read版本:        Read V1.0
 * <p>
 * 作者:            ljj8494
 * <p>
 * 日期:            2011-11-15
 * <p>
 * 负责人:          ljj8494
 * <p>
 * 负责小组:
 * <p>
 * <p>
 */
public class SheetBar extends HorizontalScrollView implements OnClickListener {

    public SheetBar(Context context) {
        super(context);
    }

    public SheetBar(Context context, IControl control, int minimumWidth) {
        super(context);
        this.control = control;
        this.setVerticalFadingEdgeEnabled(false);
        this.setFadingEdgeLength(0);
        if (minimumWidth == getResources().getDisplayMetrics().widthPixels) {
            this.minimumWidth = -1;
        } else {
            this.minimumWidth = minimumWidth;
        }
        init();
    }

    /**
     *
     */
    public void onConfigurationChanged(Configuration newConfig) {
        sheetbarFrame.setMinimumWidth(minimumWidth == -1 ? getResources().getDisplayMetrics().widthPixels : minimumWidth);
    }

    /**
     *
     */
    private void init() {
        Context context = this.getContext();
        sheetbarFrame = new LinearLayout(context);
        sheetbarFrame.setGravity(Gravity.BOTTOM);

        Drawable drawable = getResources().getDrawable(R.drawable.shape_bg_color);

        sheetbarFrame.setBackground(drawable);
        sheetbarFrame.setOrientation(LinearLayout.HORIZONTAL);
        sheetbarFrame.setGravity(Gravity.CENTER_VERTICAL);
        sheetbarFrame.setMinimumWidth(minimumWidth == -1 ? getResources().getDisplayMetrics().widthPixels : minimumWidth);
        sheetbarHeight = 90;

        // sheetButton
        @SuppressWarnings("unchecked")
        Vector<String> vec = (Vector<String>) control.getActionValue(EventConstant.SS_GET_ALL_SHEET_NAME, null);
        LayoutParams parmasButton = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        parmasButton.leftMargin = 2;
        parmasButton.rightMargin = 2;

        int count = vec.size();
        for (int i = 0; i < count; i++) {
            SheetButton sheetButton = new SheetButton(context, vec.get(i), i);
            if (currentSheet == null) {
                currentSheet = sheetButton;
                currentSheet.changeFocus(true);
            }
            sheetButton.setOnClickListener(this);
            sheetbarFrame.addView(sheetButton, parmasButton);

            if (i < count - 1) {
                View view = new View(context);
                sheetbarFrame.addView(view, parmasButton);
            }
        }

        addView(sheetbarFrame, new LayoutParams(LayoutParams.WRAP_CONTENT, sheetbarHeight));
    }

    /**
     *
     */
    public void onClick(View v) {
        currentSheet.changeFocus(false);

        SheetButton sb = (SheetButton) v;
        sb.changeFocus(true);
        currentSheet = sb;

        control.actionEvent(EventConstant.SS_SHOW_SHEET, currentSheet.getSheetIndex());
    }

    public void setFocusSheetButton(int index) {
        if (currentSheet.getSheetIndex() == index) {
            return;
        }

        int count = sheetbarFrame.getChildCount();
        View view = null;
        for (int i = 0; i < count; i++) {
            view = sheetbarFrame.getChildAt(i);
            if (view instanceof SheetButton && ((SheetButton) view).getSheetIndex() == index) {
                currentSheet.changeFocus(false);

                currentSheet = (SheetButton) view;
                currentSheet.changeFocus(true);
                break;
            }
        }

        // sheetbar scrolled
        int screenWidth = control.getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int barWidth = sheetbarFrame.getWidth();
        if (barWidth > screenWidth) {
            int left = view.getLeft();
            int right = view.getRight();
            int off = (screenWidth - (right - left)) / 2;

            off = left - off;
            if (off < 0) {
                off = 0;
            } else if (off + screenWidth > barWidth) {
                off = barWidth - screenWidth;
            }

            scrollTo(off, 0);
        }
    }

    /**
     * @return Returns the sheetbarHeight.
     */
    public int getSheetbarHeight() {
        return sheetbarHeight;
    }

    /**
     *
     */
    public void dispose() {
        currentSheet = null;
        if (sheetbarFrame != null) {
            int count = sheetbarFrame.getChildCount();
            View v;
            for (int i = 0; i < count; i++) {
                v = sheetbarFrame.getChildAt(i);
                if (v instanceof SheetButton) {
                    ((SheetButton) v).dispose();
                }
            }
            sheetbarFrame = null;
        }
    }

    //
    private int minimumWidth;
    //
    private int sheetbarHeight;
    //
    private SheetButton currentSheet;
    //
    private IControl control;
    //
    private LinearLayout sheetbarFrame;

}

package com.office.viewer.system;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import com.office.viewer.common.ICustomDialog;
import com.office.viewer.common.IOfficeToPicture;
import com.office.viewer.common.ISlideShow;

public abstract class AbstractControl implements IControl {

    @Override
    public void layoutView(int x, int y, int w, int h) {

    }

    @Override
    public void actionEvent(int actionID,  Object obj) {

    }

    @Override
    public Object getActionValue(int actionID, Object obj) {
        return null;
    }

    @Override
    public int getCurrentViewIndex() {
        return -1;
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public Dialog getDialog(Activity activity, int id) {
        return null;
    }

    @Override
    public IMainFrame getMainFrame() {
        return null;
    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public IFind getFind() {
        return null;
    }

    @Override
    public boolean isAutoTest() {
        return false;
    }

    @Override
    public IOfficeToPicture getOfficeToPicture() {
        return null;
    }

    @Override
    public ICustomDialog getCustomDialog() {
        return null;
    }

    @Override
    public boolean isSlideShow() {
        return false;
    }

    @Override
    public ISlideShow getSlideShow() {
        return null;
    }

    @Override
    public IReader getReader() {
        return null;
    }

    @Override
    public boolean openFile(String filePath) {
        return false;
    }

    @Override
    public byte getApplicationType() {
        return -1;
    }

    @Override
    public void dispose() {

    }
}

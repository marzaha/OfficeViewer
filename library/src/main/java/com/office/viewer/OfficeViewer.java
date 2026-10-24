package com.office.viewer;

import android.content.Context;
import android.widget.FrameLayout;

import com.office.viewer.system.IMainViewer;
import com.office.viewer.system.MainViewer;

public class OfficeViewer implements IMainViewer {

    private IMainViewer iMainViewer;

    public OfficeViewer(Context context) {
        iMainViewer = new MainViewer(context);
    }

    @Override
    public void loadFile(FrameLayout layout, String filePath) {
        iMainViewer.loadFile(layout, filePath);
    }

    @Override
    public void dispose() {
        iMainViewer.dispose();
    }

}

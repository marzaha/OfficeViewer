package com.office.viewer.system;

import android.widget.FrameLayout;

public interface IMainViewer {

    void loadFile(FrameLayout layout, String filePath);

    void dispose();

}

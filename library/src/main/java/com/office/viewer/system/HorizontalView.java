package com.office.viewer.system;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class HorizontalView extends Activity {

    private FrameLayout mainView;

    private DarkViewer officeViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        initView();
        showFile();
    }

    private void initView() {
        mainView = new FrameLayout(this);
        mainView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mainView.setBackgroundColor(getColor(android.R.color.black));
        setContentView(mainView);
    }

    protected void hideStatusBar() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showFile() {
        String filePath = getIntent().getStringExtra("filePath");
        officeViewer = new DarkViewer(this);
        officeViewer.loadFile(mainView, filePath);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onDestroy() {
        officeViewer.dispose();
        officeViewer = null;
        super.onDestroy();
    }

}

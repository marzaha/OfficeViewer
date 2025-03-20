package com.officeviewer.demo;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.office.viewer.OfficeViewer;

public class FileShowActivity extends AppCompatActivity {

    private OfficeViewer officeViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_show);
        initView();
    }

    private void initView() {
        FrameLayout frameLayout = findViewById(R.id.fl_office_viewer);
        String filePath = getIntent().getStringExtra("filePath");

        officeViewer = new OfficeViewer(this);
        officeViewer.loadFile(frameLayout, filePath);
    }

    @Override
    protected void onDestroy() {
        officeViewer.dispose();
        officeViewer = null;
        super.onDestroy();
    }

}
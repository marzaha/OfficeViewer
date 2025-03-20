package com.officeviewer.demo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.List;

public class FileListActivity extends AppCompatActivity {

    private ListView mListView;

    private FileListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        initView();

        copyTestFile();
        showFileList();
    }

    private void initView() {
        mListView = findViewById(R.id.lv_file_list);
        mAdapter = new FileListAdapter(this);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String filePath = mAdapter.getList().get(i).getAbsolutePath();
                openFile(filePath);
            }
        });
    }

    private void copyTestFile() {
        String targetDir = this.getFilesDir().getAbsolutePath() + File.separator + "testFile";
        File targetFile = new File(targetDir);
        if (!targetFile.exists()) {
            targetFile.mkdir();
        }
        try {
            AssetManager assetManager = this.getAssets();
            String[] filesPath = assetManager.list("testFile");
            for (String filePath : filesPath) {
                InputStream inputStream = assetManager.open("testFile" + File.separator + filePath);
                ReadableByteChannel source = Channels.newChannel(inputStream);
                FileChannel destFile = new FileOutputStream(targetDir + File.separator + filePath).getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while (source.read(buffer) > 0) {
                    buffer.flip();
                    destFile.write(buffer);
                    buffer.clear();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showFileList() {
        String dirPath = this.getFilesDir().getAbsolutePath() + File.separator + "testFile";
        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        List<File> fileList = Arrays.asList(files);
        mAdapter.setData(fileList);
    }

    private void openFile(String filePath) {
        Intent intent = new Intent();
        intent.putExtra("filePath", filePath);
        intent.setClass(this, FileShowActivity.class);
        startActivity(intent);
    }

}
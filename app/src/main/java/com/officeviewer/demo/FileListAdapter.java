package com.officeviewer.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListAdapter extends BaseAdapter {

    private Context context;
    private List<File> fileList = new ArrayList<>();

    public FileListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file_list, null);
        TextView fileName = view.findViewById(R.id.tv_name);
        File file = fileList.get(position);
        fileName.setText(file.getName());
        return view;
    }

    public void setData(List<File> list) {
        fileList.clear();
        if (list != null && list.size() > 0) {
            fileList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public List<File> getList() {
        return fileList;
    }

}

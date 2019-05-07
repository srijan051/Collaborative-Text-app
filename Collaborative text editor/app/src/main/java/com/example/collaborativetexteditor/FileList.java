package com.example.collaborativetexteditor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FileList extends ArrayAdapter<FilesAS> {

    Activity context;
    private ArrayList<FilesAS> filelist;

    public FileList(Activity context, ArrayList<FilesAS> filelist) {
        super(context, R.layout.activity_listlayout, filelist);
        this.context = context;
        this.filelist = filelist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_listlayout,null,true);
        TextView textViewTitle = listViewItem.findViewById(R.id.textView_title);
        TextView textViewTime = listViewItem.findViewById(R.id.textView_time);
        FilesAS filesAS = filelist.get(position);

        textViewTitle.setText(filesAS.getTitle());
        textViewTime.setText(filesAS.getDate());
        return listViewItem;
    }

}

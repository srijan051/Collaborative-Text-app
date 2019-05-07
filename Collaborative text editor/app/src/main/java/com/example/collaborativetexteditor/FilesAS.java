package com.example.collaborativetexteditor;

import java.io.Serializable;

public class FilesAS implements Serializable {
    private String Fileid;
    private String Title;
    private String Text;
    private String Date;

    public FilesAS() {
    }

    public String getFileid() {
        return Fileid;
    }

    public void setFileid(String fileid) {
        Fileid = fileid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

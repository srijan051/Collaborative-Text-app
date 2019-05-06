package com.example.collaborativetexteditor;

import java.io.Serializable;

public class FilesAS implements Serializable {
    private String Title;
    private String Text;

    public FilesAS() {
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

}

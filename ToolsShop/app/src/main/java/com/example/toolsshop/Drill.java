package com.example.toolsshop;

import androidx.annotation.NonNull;

public class Drill {
    private String title;
    private String info;
    private int resourseId;

    public Drill(String title, String info, int resourseId) {
        this.title = title;
        this.info = info;
        this.resourseId = resourseId;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getResourseId() {
        return resourseId;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}

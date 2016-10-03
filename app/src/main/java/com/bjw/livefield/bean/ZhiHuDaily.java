package com.bjw.livefield.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 19:58
 * description:
 */
public class ZhiHuDaily {
    @SerializedName("date")
    private String date;
    @SerializedName("top_stories")
    private ArrayList<ZhihuDailyItem> mZhihuDailyItems;
    @SerializedName("stories")
    private ArrayList<ZhihuDailyItem> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ZhihuDailyItem> getZhiHuDailyItems() {
        return mZhihuDailyItems;
    }

    public void setZhiHuDailyItems(ArrayList<ZhihuDailyItem> zhihuDailyItems) {
        this.mZhihuDailyItems = zhihuDailyItems;
    }

    public ArrayList<ZhihuDailyItem> getStories() {
        return stories;
    }

    public void setStories(ArrayList<ZhihuDailyItem> stories) {
        this.stories = stories;
    }
}

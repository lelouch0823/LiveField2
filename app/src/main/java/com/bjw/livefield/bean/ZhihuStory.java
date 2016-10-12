package com.bjw.livefield.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * author: Administrator
 * created on: 2016/9/29 0029 19:58
 * description:
 */
public class ZhihuStory  {
    @SerializedName("body")
    private String body;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("share_url")
    private String mShareUrl;
    @SerializedName("css")
    private String[] css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return mShareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.mShareUrl = shareUrl;
    }

    public String[] getCss() {
        return css;
    }

    public void setCss(String[] css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "ZhihuStory{" +
                "body='" + body + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", mShareUrl='" + mShareUrl + '\'' +
                ", css=" + Arrays.toString(css) +
                '}';
    }
}

package com.xdandroid.sample;

import java.io.Serializable;

/**
 * Created by XingDa on 2016/5/12.
 */
public class SampleBean implements Serializable {

    public SampleBean() {
    }

    public SampleBean(String message, int imageResId) {
        this.message = message;
        this.imageResId = imageResId;
    }

    private String message;
    private int imageResId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

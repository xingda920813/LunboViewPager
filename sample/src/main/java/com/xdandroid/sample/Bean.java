package com.xdandroid.sample;

import java.io.*;

/**
 * Created by XingDa on 2016/5/12.
 */
public class Bean implements Serializable {

    public Bean(String message, int imageResId) {
        this.message = message;
        this.imageResId = imageResId;
    }

    public String message;
    public int imageResId;
}

package com.xdandroid.sample;

import java.io.*;

public class Bean implements Serializable {

    public Bean(String message, int imageResId) {
        this.message = message;
        this.imageResId = imageResId;
    }

    public String message;
    public int imageResId;
}

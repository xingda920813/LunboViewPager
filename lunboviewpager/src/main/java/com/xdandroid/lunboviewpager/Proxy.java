package com.xdandroid.lunboviewpager;

import android.support.v4.view.*;
import android.view.*;

import java.util.*;

/**
 * Created by XingDa on 2016/5/10.
 */
public class Proxy<T> {

    protected List<T> list;
    protected long interval;
    protected Adapter adapter;
    protected SinglePictureAdapter singlePictureAdapter;

    public Proxy(List<T> list, long interval, Adapter pagerAdapter) {
        if (list.size() == 2) list.addAll(new ArrayList<>(list));
        this.list = list;
        this.interval = interval;
        this.adapter = pagerAdapter;
        this.singlePictureAdapter = new SinglePictureAdapter() {
            @Override
            protected void onImageClick(View view, int position) {
                adapter.onImageClick(view, position);
            }

            @Override
            protected View showImage(View inflatedLayout, int position) {
                return adapter.showImage(inflatedLayout, position);
            }

            @Override
            protected int getViewPagerItemLayoutResId() {
                return adapter.getViewPagerItemLayoutResId();
            }
        };
    }

    public void onBindView(ViewPager viewPager, CirclePageIndicator indicator) {
        if (list.size() == 1) {
            indicator.setVisibility(View.GONE);
            viewPager.setAdapter(singlePictureAdapter);
        } else {
            indicator.setVisibility(View.VISIBLE);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(list.size() * 500);
            indicator.setViewPager(viewPager, new PagerHandler(viewPager, interval));
        }
    }
}

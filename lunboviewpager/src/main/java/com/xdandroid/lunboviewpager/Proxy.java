package com.xdandroid.lunboviewpager;

import android.support.v4.view.*;
import android.view.*;

import java.util.*;

public class Proxy<T> {

    protected List<T> mList;
    protected long mInterval;
    protected Adapter mAdapter;
    protected SinglePictureAdapter mSinglePictureAdapter;

    public Proxy(List<T> list, long interval, Adapter pagerAdapter) {
        mList = list;
        mInterval = interval;
        mAdapter = pagerAdapter;
        mSinglePictureAdapter = new SinglePictureAdapter() {
            protected void onImageClick(View view, int position) {mAdapter.onImageClick(view, position);}
            protected View showImage(View inflatedLayout, int position) {return mAdapter.showImage(inflatedLayout, position);}
            protected int getViewPagerItemLayoutResId() {return mAdapter.getViewPagerItemLayoutResId();}
        };
    }

    public void onBindView(ViewPager viewPager, CirclePageIndicator indicator) {
        if (mList.size() == 1) {
            indicator.setVisibility(View.GONE);
            viewPager.setAdapter(mSinglePictureAdapter);
        } else {
            indicator.setVisibility(View.VISIBLE);
            viewPager.setAdapter(mAdapter);
            viewPager.setCurrentItem(mList.size() * 500);
            indicator.setViewPager(viewPager, new PagerHandler(viewPager, mInterval));
        }
    }
}

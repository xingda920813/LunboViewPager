package com.xdandroid.lunboviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XingDa on 2016/01/28.
 */
public abstract class WithoutLunboPagerAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(getViewPagerItemLayoutResId(), container, false);
        final View imageView = showImage(view, position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClick(imageView,position);
            }
        });
        container.addView(view);
        return view;
    }

    protected abstract void onImageClick(View view, int position);
    protected abstract View showImage(View inflatedLayout, int position);
    protected abstract int getViewPagerItemLayoutResId();

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

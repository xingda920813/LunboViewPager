package com.xdandroid.lunboviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by XingDa on 2015/12/30.
 */
public abstract class Adapter extends PagerAdapter {

    protected Context context;
    protected View[] views = new View[20];
    protected View[] sdvs = new View[20];

    public Adapter(Context context) {
        this.context = context;
        initImageViews();
    }

    public void initImageViews() {
        for (int i = 0; i < getImageCount(); i++) {
            views[i] = LayoutInflater.from(context).inflate(getViewPagerItemLayoutResId(), null, false);
            sdvs[i] = showImage(views[i], i);
            final int finalI = i;
            sdvs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImageClick(sdvs[finalI], finalI);
                }
            });
        }
    }

    @Override
    public int getCount() {
        return (getImageCount() * 1000);
    }

    protected abstract View showImage(View inflatedLayout, int position);
    protected abstract int getViewPagerItemLayoutResId();
    protected abstract int getImageCount();
    protected abstract void onImageClick(View view, int position);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views[position % getImageCount()];
        if (view.getParent() != null) {
            ((ViewGroup) (view.getParent())).removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}

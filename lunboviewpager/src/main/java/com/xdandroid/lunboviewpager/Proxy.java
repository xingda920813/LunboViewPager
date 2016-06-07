package com.xdandroid.lunboviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by XingDa on 2016/5/10.
 */
public class Proxy<T> {

    protected List<T> list;
    protected long interval;
    protected Adapter adapter;
    protected SinglePictureAdapter singlePictureAdapter;
    protected PagerHandler handler;

    public Proxy(List<T> list, long interval, Adapter pagerAdapter) {
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

    @SuppressWarnings({"CollectionAddedToSelf", "TryWithIdenticalCatches"})
    public void onBindView(ViewPager viewPager, View indicator) {
        Class<? extends View> clazz = indicator.getClass();
        Class[] methodArgs = new Class[2];
        methodArgs[0] = ViewPager.class;
        methodArgs[1] = PagerHandler.class;
        Method method;
        try {
            method = clazz.getDeclaredMethod("setViewPager", methodArgs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        method.setAccessible(true);
        Object[] args = new Object[2];
        if (list.size() >= 3) {
            indicator.setVisibility(View.VISIBLE);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(list.size() * 500);
            handler = new PagerHandler(viewPager, interval);
            args[0] = viewPager;
            args[1] = handler;
            try {
                method.invoke(indicator,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (list.size() == 2) {
            list.addAll(list);
            indicator.setVisibility(View.VISIBLE);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(list.size() * 500);
            handler = new PagerHandler(viewPager, interval);
            args[0] = viewPager;
            args[1] = handler;
            try {
                method.invoke(indicator,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (list.size() == 1) {
            indicator.setVisibility(View.GONE);
            viewPager.setAdapter(singlePictureAdapter);
        }
    }
}

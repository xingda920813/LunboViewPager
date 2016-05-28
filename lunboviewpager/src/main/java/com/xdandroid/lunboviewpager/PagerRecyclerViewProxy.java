package com.xdandroid.lunboviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by XingDa on 2016/5/10.
 */
public class PagerRecyclerViewProxy<T> {

    protected List<T> list;
    protected long interval;
    protected LunboPagerAdapter lunboPagerAdapter;
    protected WithoutLunboPagerAdapter withoutLunboPagerAdapter;
    protected PagerHandler pagerHandler;

    public PagerRecyclerViewProxy(List<T> list, long interval, LunboPagerAdapter pagerAdapter) {
        this.list = list;
        this.interval = interval;
        this.lunboPagerAdapter = pagerAdapter;
        this.withoutLunboPagerAdapter = new WithoutLunboPagerAdapter() {
            @Override
            protected void onImageClick(View view, int position) {
                lunboPagerAdapter.onImageClick(view, position);
            }

            @Override
            protected View showImage(View inflatedLayout, int position) {
                return lunboPagerAdapter.showImage(inflatedLayout, position);
            }

            @Override
            protected int getViewPagerItemLayoutResId() {
                return lunboPagerAdapter.getViewPagerItemLayoutResId();
            }
        };
    }

    @SuppressWarnings({"unchecked", "CollectionAddedToSelf"})
    public void onCreateViewHolder(ViewPager viewPager) {
        if (list.size() >= 3) {
            viewPager.setAdapter(lunboPagerAdapter);
            viewPager.setCurrentItem(list.size() * 500);
        } else if (list.size() == 2) {
            list.addAll(list);
            viewPager.setAdapter(lunboPagerAdapter);
            viewPager.setCurrentItem(list.size() * 500);
        }
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    public void onBindViewHolder(ViewPager viewPager, View indicator) {
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
            lunboPagerAdapter.notifyDataSetChanged();
            viewPager.setAdapter(lunboPagerAdapter);
            pagerHandler = new PagerHandler(viewPager, interval);
            args[0] = viewPager;
            args[1] = pagerHandler;
            try {
                method.invoke(indicator,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (list.size() == 2) {
            indicator.setVisibility(View.VISIBLE);
            lunboPagerAdapter.notifyDataSetChanged();
            viewPager.setAdapter(lunboPagerAdapter);
            pagerHandler = new PagerHandler(viewPager, interval);
            args[0] = viewPager;
            args[1] = pagerHandler;
            try {
                method.invoke(indicator,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (list.size() == 1) {
            indicator.setVisibility(View.GONE);
            viewPager.setAdapter(withoutLunboPagerAdapter);
        }
    }
}

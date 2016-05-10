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

    private List<T> list;
    private LunboPagerAdapter lunboPagerAdapter;
    private long interval;
    private WithoutLunboPagerAdapter withoutLunboPagerAdapter;
    private PagerHandler pagerHandler;

    public PagerRecyclerViewProxy(List<T> list, LunboPagerAdapter lunboPagerAdapter, long interval, WithoutLunboPagerAdapter withoutLunboPagerAdapter) {
        this.list = list;
        this.lunboPagerAdapter = lunboPagerAdapter;
        this.interval = interval;
        this.withoutLunboPagerAdapter = withoutLunboPagerAdapter;
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
        args[0] = viewPager;
        args[1] = pagerHandler;
        if (list.size() >= 3) {
            indicator.setVisibility(View.VISIBLE);
            lunboPagerAdapter.notifyDataSetChanged();
            viewPager.setAdapter(lunboPagerAdapter);
            pagerHandler = new PagerHandler(viewPager, interval);
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

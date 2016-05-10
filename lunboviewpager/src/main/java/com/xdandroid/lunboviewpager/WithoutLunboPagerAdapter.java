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
        return getImageCount();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(getLayoutResId(), container, false);
        final View imageView = showImage(view, position);
        if (mOnImageClickLitener != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnImageClickLitener.onImageClick(imageView,position);
                }
            });
        }
        container.addView(view);
        return view;
    }

    protected abstract View showImage(View inflatedLayout, int position);
    protected abstract int getLayoutResId();
    protected abstract int getImageCount();

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnImageClickLitener {
        public void onImageClick(View view, int position);
    }
    private OnImageClickLitener mOnImageClickLitener;
    public void setOnImageClickLitener(OnImageClickLitener mOnImageClickLitener) {
        this.mOnImageClickLitener = mOnImageClickLitener;
    }
}

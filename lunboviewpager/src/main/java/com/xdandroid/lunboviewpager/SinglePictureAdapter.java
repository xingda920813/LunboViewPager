package com.xdandroid.lunboviewpager;

import android.support.v4.view.*;
import android.view.*;

/**
 * Created by XingDa on 2016/01/28.
 */
public abstract class SinglePictureAdapter extends PagerAdapter {

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

    /**
     * 点击事件
     * @param view inflatedLayout渲染出来的View
     * @param position position
     */
    protected abstract void onImageClick(View view, int position);
    /**
     * 示例 :
     * ImageView imageView = (ImageView) inflatedLayout.findViewById(R.id.iv_lunbo);
     * Picasso.with(mContext).load(mList.get(position).imageResId).into(imageView);
     * return imageView;
     * @param inflatedLayout 根据getViewPagerItemLayoutResId指定的资源ID渲染出来的View
     * @param position position
     * @return 图片控件的对象(ImageView, DraweeView, etc..)
     */
    protected abstract View showImage(View inflatedLayout, int position);
    /**
     * @return ViewPager的每一页的Layout资源ID
     */
    protected abstract int getViewPagerItemLayoutResId();

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

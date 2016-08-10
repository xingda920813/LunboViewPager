package com.xdandroid.lunboviewpager;

import android.content.*;
import android.support.v4.view.*;
import android.view.*;

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

    /**
     * @return 总页数
     */
    protected abstract int getImageCount();

    /**
     * 点击事件
     * @param view inflatedLayout渲染出来的View
     * @param position position
     */
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

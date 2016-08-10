package com.xdandroid.sample;

import android.content.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.squareup.picasso.*;
import com.xdandroid.lunboviewpager.Adapter;
import com.xdandroid.lunboviewpager.*;

import java.util.*;

/**
 * Created by XingDa on 2016/5/12.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private Proxy<Bean> proxy;

    public MainAdapter(final Context context, final List<Bean> list) {
        this.context = context;
        this.proxy = new Proxy<>(list, 4000, new Adapter(context) {
            /**
             * 示例 :
             * ImageView imageView = (ImageView) inflatedLayout.findViewById(R.id.iv_lunbo);
             * Picasso.with(mContext).load(mList.get(position).imageResId).into(imageView);
             * return imageView;
             * @param inflatedLayout 根据getViewPagerItemLayoutResId指定的资源ID渲染出来的View
             * @param position position
             * @return 图片控件的对象(ImageView, DraweeView, etc..)
             */
            protected View showImage(View inflatedLayout, int position) {
                ImageView imageView = (ImageView) inflatedLayout.findViewById(R.id.iv_lunbo);
                Picasso.with(context).load(list.get(position).imageResId).into(imageView);
                return imageView;
            }
            /**
             * @return ViewPager的每一页的Layout资源ID
             */
            protected int getViewPagerItemLayoutResId() {
                return R.layout.item_in_viewpager;
            }
            /**
             * @return 总页数
             */
            protected int getImageCount() {
                return list.size();
            }
            /**
             * 点击事件
             * @param view inflatedLayout渲染出来的View
             * @param position position
             */
            protected void onImageClick(View view, int position) {
                Snackbar.make(view,list.get(position).message,Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, int position) {
        //设置当前被选中的圆点的填充颜色
        holder.indicator_lunbo.setFillColor(context.getResources().getColor(R.color.colorAccent));
        //设置圆点半径
        holder.indicator_lunbo.setRadius(UIUtils.dp2px(holder.indicator_lunbo.getContext(), 3.25f));
        //设置圆点之间的距离相对于圆点半径的倍数
        holder.indicator_lunbo.setDistanceBetweenCircles(3.0);
        proxy.onBindView(holder.vp_lunbo,holder.indicator_lunbo);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            vp_lunbo = (ViewPager) itemView.findViewById(R.id.vp_lunbo);
            indicator_lunbo = (CirclePageIndicator) itemView.findViewById(R.id.indicator_lunbo);
        }
        ViewPager vp_lunbo;
        CirclePageIndicator indicator_lunbo;
    }
}

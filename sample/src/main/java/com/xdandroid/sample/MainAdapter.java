package com.xdandroid.sample;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xdandroid.lunboviewpager.Adapter;
import com.xdandroid.lunboviewpager.Proxy;
import com.xdandroid.lunboviewpager.CirclePageIndicator;

import java.util.List;

/**
 * Created by XingDa on 2016/5/12.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private Proxy<SampleBean> proxy;

    public MainAdapter(final Context context, final List<SampleBean> list) {
        this.context = context;
        this.proxy = new Proxy<>(list, 4000, new Adapter(context) {
            @Override
            protected View showImage(View inflatedLayout, int position) {
                ImageView imageView = (ImageView) inflatedLayout.findViewById(R.id.iv_lunbo);
                Picasso.with(context).load(list.get(position).getImageResId()).into(imageView);
                return imageView;
            }

            @Override
            protected int getViewPagerItemLayoutResId() {
                return R.layout.item_in_viewpager;
            }

            @Override
            protected int getImageCount() {
                return list.size();
            }

            @Override
            protected void onImageClick(View view, int position) {
                Snackbar.make(view,list.get(position).getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, int position) {
        holder.indicator_lunbo.setFillColor(context.getResources().getColor(R.color.colorAccent));
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

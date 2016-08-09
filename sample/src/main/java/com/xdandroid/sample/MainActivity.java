package com.xdandroid.sample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xdandroid.lunboviewpager.Adapter;
import com.xdandroid.lunboviewpager.Proxy;
import com.xdandroid.lunboviewpager.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SampleBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setupMockData();
        setupViewPagerInRecyclerView();
        setupViewPagerDirectlyInActivity();
    }

    private void setupMockData() {
        list = new ArrayList<>();
        list.add(new SampleBean("小米MAX超耐久直播",R.mipmap.img1));
        list.add(new SampleBean("嘘——看我发现了什么",R.mipmap.img2));
        list.add(new SampleBean("母亲，您辛苦了",R.mipmap.img3));
        list.add(new SampleBean("哭过、笑过、真爱过。",R.mipmap.img4));
    }

    private void setupViewPagerInRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this, list));
    }

    private void setupViewPagerDirectlyInActivity() {
        ViewPager vp_lunbo = (ViewPager) findViewById(R.id.vp_lunbo);
        CirclePageIndicator indicator_lunbo = (CirclePageIndicator) findViewById(R.id.indicator_lunbo);
        indicator_lunbo.setFillColor(getResources().getColor(R.color.colorAccent));
        Proxy<SampleBean> proxy = new Proxy<>(list, 4000, new Adapter(this) {
            protected View showImage(View inflatedLayout, int position) {
                ImageView imageView = (ImageView) inflatedLayout.findViewById(R.id.iv_lunbo);
                Picasso.with(MainActivity.this).load(list.get(position).getImageResId()).into(imageView);
                return imageView;
            }
            protected int getViewPagerItemLayoutResId() {return R.layout.item_in_viewpager;}
            protected int getImageCount() {return list.size();}
            protected void onImageClick(View view, int position) {Snackbar.make(view,list.get(position).getMessage(),Snackbar.LENGTH_SHORT).show();}
        });
        proxy.onBindView(vp_lunbo,indicator_lunbo);
    }
}

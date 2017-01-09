package com.xdandroid.sample;

import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

import com.squareup.picasso.*;
import com.xdandroid.lunboviewpager.Adapter;
import com.xdandroid.lunboviewpager.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    List<Bean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        setupMockData();
        setupViewPagerInRecyclerView();
        setupViewPagerDirectlyInActivity();
    }

    void setupMockData() {
        mList = new ArrayList<>();
        mList.add(new Bean("小米MAX超耐久直播",R.mipmap.img1));
        mList.add(new Bean("嘘——看我发现了什么",R.mipmap.img2));
        mList.add(new Bean("母亲，您辛苦了",R.mipmap.img3));
        mList.add(new Bean("哭过、笑过、真爱过。",R.mipmap.img4));
    }

    void setupViewPagerInRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this, mList));
    }

    void setupViewPagerDirectlyInActivity() {
        ViewPager vp_lunbo = (ViewPager) findViewById(R.id.vp_lunbo);
        CirclePageIndicator indicator_lunbo = (CirclePageIndicator) findViewById(R.id.indicator_lunbo);
        //设置当前被选中的圆点的填充颜色
        indicator_lunbo.setFillColor(getResources().getColor(R.color.colorAccent));
        //设置圆点半径
        indicator_lunbo.setRadius(UIUtils.dp2px(this, 3.25f));
        //设置圆点之间的距离相对于圆点半径的倍数
        indicator_lunbo.setDistanceBetweenCircles(3.0);
        Proxy<Bean> proxy = new Proxy<>(mList, 4000, new Adapter(this) {
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
                Picasso.with(MainActivity.this).load(mList.get(position).imageResId).into(imageView);
                return imageView;
            }
            /**
             * @return ViewPager的每一页的Layout资源ID
             */
            protected int getViewPagerItemLayoutResId() {return R.layout.item_in_viewpager;}
            /**
             * @return 总页数
             */
            protected int getImageCount() {return mList.size();}
            /**
             * 点击事件
             * @param view inflatedLayout渲染出来的View
             * @param position position
             */
            protected void onImageClick(View view, int position) {Snackbar.make(view, mList.get(position).message,Snackbar.LENGTH_SHORT).show();}
        });
        proxy.onBindView(vp_lunbo,indicator_lunbo);
    }
}

package com.app.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.demo.util.DensityUtils;

import java.util.List;

/**
 * Created by ${zhaoyanjun} on 2017/3/15.
 * ViewPage 指示器
 */

public class ViewPagerIndicator {
    private TextView yiba_viewpager_tab1_tv;
    private TextView yiba_viewpager_tab2_tv;
    private TextView yiba_viewpager_tab3_tv;
    private RelativeLayout yiba_viewpager_tab1_rel;
    private RelativeLayout yiba_viewpager_tab2_rel;
    private RelativeLayout yiba_viewpager_tab3_rel;
    private Context context;
    private Activity activity;
    private List<String> titleList;
    private View yiba_viewpager_indicator;
    private int screenWith = 0;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private int offset = 0;  //指示器偏移量

    public ViewPagerIndicator(Context context, ViewPager viewPager, List<String> titleList) {
        this.context = context;
        this.viewPager = viewPager;
        this.activity = (Activity) context;
        this.titleList = titleList;

        intView();
    }

    private void intView() {
        screenWith = DensityUtils.getScreenWidth(context);
        offset = DensityUtils.dp2px(context, 64);
        yiba_viewpager_tab1_tv = activity.findViewById(R.id.yiba_viewpager_tab1_tv);
        yiba_viewpager_tab2_tv = activity.findViewById(R.id.yiba_viewpager_tab2_tv);
        yiba_viewpager_tab3_tv = activity.findViewById(R.id.yiba_viewpager_tab3_tv);

        yiba_viewpager_tab1_rel = activity.findViewById(R.id.yiba_viewpager_tab1_rel);
        yiba_viewpager_tab2_rel = activity.findViewById(R.id.yiba_viewpager_tab2_rel);
        yiba_viewpager_tab3_rel = activity.findViewById(R.id.yiba_viewpager_tab3_rel);

        yiba_viewpager_indicator = activity.findViewById(R.id.yiba_viewpager_indicator);

        yiba_viewpager_tab1_tv.setText(titleList.get(0));
        yiba_viewpager_tab2_tv.setText(titleList.get(1));
        yiba_viewpager_tab3_tv.setText(titleList.get(2));

        pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //指示器移动
                viewPagerIndicatorAnimation(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(pageChangeListener);

        yiba_viewpager_tab1_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        yiba_viewpager_tab2_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        yiba_viewpager_tab3_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        ViewGroup.LayoutParams params = yiba_viewpager_indicator.getLayoutParams();
        params.width = (screenWith / 3) - offset;
        yiba_viewpager_indicator.setLayoutParams(params);

    }

    private void changeTab(int tab) {
        yiba_viewpager_tab1_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_unselected));
        yiba_viewpager_tab2_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_unselected));
        yiba_viewpager_tab3_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_unselected));

        if (tab == 0) {
            yiba_viewpager_tab1_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_selected));
        } else if (tab == 1) {
            yiba_viewpager_tab2_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_selected));
        } else {
            yiba_viewpager_tab3_tv.setTextColor(context.getResources().getColor(R.color.yiba_viewpager_tip_selected));
        }
    }

    private void viewPagerIndicatorAnimation(int position, float positionOffset) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) yiba_viewpager_indicator.getLayoutParams();
        int marginLeft = (int) ((screenWith / 3) * (position + positionOffset) + offset / 2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.setMarginStart(marginLeft);
        } else {
            params.leftMargin = marginLeft;
        }
        yiba_viewpager_indicator.setLayoutParams(params);
    }


    public void destory() {
        if (activity != null) {
            activity = null;
        }

        if (context != null) {
            context = null;
        }
        viewPager.removeOnPageChangeListener(pageChangeListener);
    }
}

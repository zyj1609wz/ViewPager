package com.app.demo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.demo.adapter.MyFragmentPagerAdapter;
import com.app.demo.fragment.Fragment1;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyFragmentPagerAdapter adapter;
    private ViewPagerIndicator viewPagerIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String title = "fragment" + i;
            fragmentList.add(Fragment1.newInstance(title));
            titleList.add(title);
        }
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);

        viewPagerIndicator = new ViewPagerIndicator(this, viewPager, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPagerIndicator.destory();
    }
}

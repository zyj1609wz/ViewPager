package com.app.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${zhaoyanjun} on 2017/3/8.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private List<String> titleList ;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list , List<String> titleList ) {
        super(fm);
        this.list = list;
        this.titleList = titleList ;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get( position ) ;
    }

}



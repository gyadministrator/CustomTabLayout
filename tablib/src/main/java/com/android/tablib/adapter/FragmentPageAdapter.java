package com.android.tablib.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @ClassName:FragmentPageAdapter
 * @Author:CreatBy wlh
 * @Time:2019/4/4 11点
 * @Email:m15904921255@163.com
 * @Desc:TODO
 */
public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private String[] titles;

    public FragmentPageAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

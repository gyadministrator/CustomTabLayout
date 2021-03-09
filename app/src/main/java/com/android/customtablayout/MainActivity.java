package com.android.customtablayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.tablib.adapter.FragmentPageAdapter;
import com.android.tablib.view.CustomTabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CustomTabLayout tabLayout;
    private ViewPager viewPager;
    private String[] mTitles;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mTitles = new String[]{"待接单", "已接单", "制作中", "待验单", "已完成", "已取消", "弃单"};
        fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            BlankFragment fragment = new BlankFragment();
            fragments.add(fragment);
        }
        viewPager.setOffscreenPageLimit(mTitles.length);
        viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), fragments, mTitles));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.initLayout();
    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
    }
}

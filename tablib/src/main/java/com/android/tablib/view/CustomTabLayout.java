package com.android.tablib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.tablib.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: CustomTabLayout
 * @Package: com.android.tablib.view
 * @ClassName: CustomTabLayout
 * @Author: 1984629668@qq.com
 * @CreateDate: 2021/3/9 14:07
 */
public class CustomTabLayout extends TabLayout {
    private int normalTextColor = Color.BLACK;
    private int selectTextColor = Color.RED;
    private float normalTextSize = 12;
    private float selectTextSize = 16;
    private List<Tab> tabList = new ArrayList<>();

    public CustomTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTabLayout);
        normalTextColor = typedArray.getColor(R.styleable.CustomTabLayout_normalTextColor, Color.BLACK);
        selectTextColor = typedArray.getColor(R.styleable.CustomTabLayout_selectTextColor, Color.RED);
        normalTextSize = typedArray.getDimension(R.styleable.CustomTabLayout_normalTextSize, 12);
        selectTextSize = typedArray.getDimension(R.styleable.CustomTabLayout_selectTextSize, 16);
        typedArray.recycle();
        tabList.clear();
    }

    public void initLayout() {
        if (getTabCount() == 0) return;
        //默认添加第一个
        tabList.add(getTabAt(0));
        for (int i = 0; i < getTabCount(); i++) {
            Tab tabAt = getTabAt(i);
            if (tabAt != null) {
                tabAt.setCustomView(R.layout.custom_tab_item);
                View customView = tabAt.getCustomView();
                if (customView != null) {
                    TextView tvItem = customView.findViewById(R.id.tv_item);
                    if (i == 0) {
                        tvItem.setTextColor(selectTextColor);
                        tvItem.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                        tvItem.setTextSize(selectTextSize / 2);
                        tvItem.setText(tabAt.getText());
                    } else {
                        tvItem.setTextSize(normalTextSize / 2);
                        tvItem.setTextColor(normalTextColor);
                        tvItem.setText(tabAt.getText());
                    }
                }
            }
        }

        addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                if (!tabList.contains(tab)) {
                    tabList.add(tab);
                }
                handlerTab(tab);
            }

            @Override
            public void onTabUnselected(Tab tab) {
            }

            @Override
            public void onTabReselected(Tab tab) {
            }
        });
    }

    private void handlerTab(Tab tab) {
        for (int i = 0; i < tabList.size(); i++) {
            Tab tabAt = tabList.get(i);
            View customView;
            if (tabAt != null) {
                customView = tabAt.getCustomView();
                if (TextUtils.equals(tabAt.getText(), tab.getText())) {
                    if (customView != null) {
                        customView.findViewById(R.id.tv_item).setSelected(true);
                        TextView tv = customView.findViewById(R.id.tv_item);
                        tv.setTextColor(selectTextColor);
                        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                        tv.setTextSize(selectTextSize / 2);
                        tv.setAlpha(0.9f);//透明度
                        tv.invalidate();
                    }
                } else {
                    if (customView != null) {
                        customView.findViewById(R.id.tv_item).setSelected(false);
                        TextView tv = customView.findViewById(R.id.tv_item);
                        tv.setTextColor(normalTextColor);
                        tv.setTextSize(normalTextSize / 2);
                        tv.setAlpha(0.6f);
                        tv.invalidate();
                    }
                }
            }
        }
    }
}

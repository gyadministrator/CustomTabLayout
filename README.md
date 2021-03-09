先上图！！！！！：

![image](https://github.com/gyadministrator/CustomTabLayout/blob/master/images/spot.png)

怎样使用?

工程的Gradle引入方式：

    repositories {
            google()
            jcenter()
            mavenCentral()
        }

    allprojects {
        repositories {
            google()
            jcenter()
            maven { url 'https://jitpack.io' }
            mavenCentral()
        }
    }

  dependencies {
		implementation 'com.github.gyadministrator:CustomTabLayout:1.1'
	}


在activity使用，非常简单。
  
在xml中添加这个view。
  
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <com.android.tablib.view.CustomTabLayout
        android:id="@+id/tab_layout"
        android:layout_width="match_parent"
        android:layout_height="44dp"
        android:background="#FFFFFF"
        app:normalTextColor="#666666"
        app:normalTextSize="12sp"
        app:selectTextColor="#016590"
        app:selectTextSize="16sp"
        app:tabIndicatorColor="#016590"
        app:tabMaxWidth="0dp"
        app:tabMode="auto" />

    <androidx.viewpager.widget.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="10dp"
        android:layout_weight="1" />

</LinearLayout>

 activity中使用
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



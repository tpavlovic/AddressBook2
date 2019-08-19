package com.example.uros.projekat3novo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayoutID);
        viewPager = (ViewPager)findViewById(R.id.viewPagerID);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new ContactFragment(),"Contacts");
        adapter.addFragment(new FavoriteFragment(),"Favorite");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}

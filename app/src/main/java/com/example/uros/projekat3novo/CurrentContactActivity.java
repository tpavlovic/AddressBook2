package com.example.uros.projekat3novo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class CurrentContactActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayoutID);
        viewPager = (ViewPager)findViewById(R.id.viewPagerID);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new DetailsFragment(),"Details");
        adapter.addFragment(new DeleteFragment(),"Delete");
        adapter.addFragment(new EditFragment(),"Edit");

       if(getIntent().hasExtra("pos")){
           position = 2;
       }

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        tabLayout.setupWithViewPager(viewPager);

    }
}

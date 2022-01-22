package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ListLog extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 pager2;
    private FragmentLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle(getIntent().getStringExtra(ProductAdapter.EXTRA_PRODUCT_NAME));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_log);

        tabLayout = (TabLayout) findViewById(R.id.tl_log);
        pager2 = (ViewPager2) findViewById(R.id.vp2_log);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentLogAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Input"));
        tabLayout.addTab(tabLayout.newTab().setText("Output"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}
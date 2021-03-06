package com.example.weatherapp.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.R;
import com.example.weatherapp.ui.base.BaseActivity;
import com.example.weatherapp.ui.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class OnBoardActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager pager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    Button next;

    private int currentPage;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_on_board;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        String texts[] = new String[]{"In the application", "in the application you can find out information about the weather",
                "for attention"};
        int[] pictures = new int[]{R.drawable.welcome, R.drawable.weather, R.drawable.thakn_you};

        PagerAdapter adapter = new OnBoardAdapter(OnBoardActivity.this, texts, pictures);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        tabLayout.setupWithViewPager(pager, true);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
//                if (position )
//                next.setVisibility();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    public void onClick(View v) {
        if (pager.getCurrentItem() != 2) {
            pager.setCurrentItem(currentPage + 1);
        } else {
            startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_onboard_skip, menu);
        return true;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                MainActivity.start(this);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

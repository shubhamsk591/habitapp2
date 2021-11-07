package com.example.habitapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ViewPager pages;
    TabLayout mtablayout;
    TabItem firstitem,seconditem,thirditem;
    FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("IS_FIRST_TIME", true)) {
            //show your dialog here
            openPopUpWindow();
            //change the value of your sharedPreferences
            sharedPreferences.edit().putBoolean("IS_FIRST_TIME", false).apply();
        }

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        pages=findViewById(R.id.view_pager);
        mtablayout=findViewById(R.id.tabLayout);
        firstitem=findViewById(R.id.firsttab);
        seconditem=findViewById(R.id.secondtab);
        thirditem=findViewById(R.id.thirdtab);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        adapter =new PageAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mtablayout.getTabCount());
        pages.setAdapter(adapter);
        mtablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pages.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pages.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mtablayout));
        Button motivationButton=findViewById(R.id.motivation_button);
        motivationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent =new Intent(view.getContext(),MotivationInsight.class);
                startActivity(myintent);
            }
        });

    }

    private void openPopUpWindow() {
        Intent popupw=new Intent(MainActivity.this,Popup.class);
        startActivity(popupw);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()){
            case R.id.Home_item:
                openPopUpWindow();
                break;
            case R.id.Categories_item:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.Setting_item:
                startActivity(new Intent(this, MainActivity.class));

                break;
            case R.id.Rating_item:
                Toast.makeText(this,R.string.Rating,Toast.LENGTH_SHORT).show();
                break;
            case R.id.ContactUs_item:
                Toast.makeText(this,R.string.ContactUs,Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;

        }
        return false;

    }

}
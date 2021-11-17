package com.example.habitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ViewPager pages;
    TabLayout mtablayout;
    TabItem firstitem,seconditem,thirditem;
    FragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the id
        getUIid();
        //for navigation drawer
        drawerfunction();
        //check date
        timeupdate();
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
        ImageButton motivationButton=findViewById(R.id.motivation_button);
        motivationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent =new Intent(view.getContext(),MotivationInsight.class);
                startActivity(myintent);
            }
        });

    }

    private void drawerfunction() {
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
    }

    private void getUIid() {
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        pages=findViewById(R.id.view_pager);
        mtablayout=findViewById(R.id.tabLayout);
        firstitem=findViewById(R.id.firsttab);
        seconditem=findViewById(R.id.secondtab);
        thirditem=findViewById(R.id.thirdtab);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }



    private void openPopUpWindow() {
        Intent popupw=new Intent(getApplicationContext(),Popup.class);
        startActivity(popupw);
    }
    private void timeupdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ", Locale.US);
        String currentDateandTime = sdf.format(new Date());
        DataBaseDateUpdate dataBaseDateUpdate = new DataBaseDateUpdate(getApplicationContext());
        Cursor stdate = dataBaseDateUpdate.getdate();
        stdate.moveToFirst();
            String pre=stdate.getString(1);
            if (pre.equals(currentDateandTime)) {
                Log.d("if ","Work");
               return;
                }
            else{
                Log.d("else ","Work");
                openPopUpWindow();
                boolean a = dataBaseDateUpdate.updatedate(currentDateandTime);
                if (a) {
                    addnewentryfor(currentDateandTime);
                }
            }
        }
    private void addnewentryfor(String time) {
        DataBaseTracker dataBaseTracker=new DataBaseTracker(getApplicationContext());
        Databasehelper databasehelper=new Databasehelper(getApplicationContext());
        Cursor cdata = databasehelper.getdata();
        while (cdata.moveToNext()) {
            int id=cdata.getInt(0);
            String name=cdata.getString(1);
            dataBaseTracker.addDatabaseitemtracker(id,name,time,0);
            databasehelper.setCompleted(id,name,0);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch(item.getItemId()){

            case R.id.Home_item:
                openPopUpWindow();
                break;
            case R.id.Setting_item:
                Toast.makeText(getApplicationContext(),"Welcome to change theme",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Rating_item:
                Toast.makeText(this,R.string.Rating,Toast.LENGTH_SHORT).show();
                break;
            case R.id.ContactUs_item:
                Toast.makeText(this,R.string.ContactUs,Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_item:
                startActivity(new Intent(this,Loginform.class));
                break;
            case R.id.register_item:
                startActivity(new Intent(this,Registernew.class));
                break;
            default:
                return false;

        }
        return false;

    }

}
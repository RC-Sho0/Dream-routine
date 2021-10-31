package com.example.dream_routine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //sidebar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //action bar
    private ActionBar actionBar;

    //UI views
    private ViewPager viewPager;

    private ArrayList<Swiper> modeArrayList;
    private SwiperAdapter swiperAdapter;

    // List todo
    ListView list;
    ArrayList<Todo> arrayList;
    TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //init actionbar
        actionBar = getSupportActionBar();

        //init UI views
        viewPager = findViewById(R.id.swiperview);
        loadCards();

        //set view pager change listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        list = findViewById(R.id.todolist);

        arrayList = Todo.initTodo();

        todoAdapter = new TodoAdapter(Dashboard.this, R.layout.todo_item, arrayList);

        list.setAdapter(todoAdapter);
        //sidebar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open_menu, R.string.Close_menu);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
}

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadCards() {
        //init list
        modeArrayList = new ArrayList<>();

        //add items to list
        modeArrayList.add(new Swiper(
                "10 task",
                "University"
        ));
        modeArrayList.add(new Swiper(
                "5 task",
                "Bussiness"
        ));
        modeArrayList.add(new Swiper(
                "10 task",
                "Community"
        ));

        //setup adapter
        swiperAdapter = new SwiperAdapter(this, modeArrayList);

        //set adapter to view pager
        viewPager.setAdapter(swiperAdapter);
        //set default padding from left/right
        viewPager.setPadding(100, 0, 100, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}
package com.example.do_i_need_it;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout framelayout;


    //Fragments
    //creating object of the fragments
    private DashBoardFragment dashBoardFragment;
    private ManageFragment manageFragment;
    private WishlistFragment wishlistFragment;
    private AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Do I Need It");
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottomNavigationbar);
        framelayout = findViewById(R.id.main_frame);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);

        //Fragment initialize
        dashBoardFragment = new DashBoardFragment();
        manageFragment = new ManageFragment();
        wishlistFragment = new WishlistFragment();
        accountFragment = new AccountFragment();

        setFragment(dashBoardFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.dashboard:
                        setFragment(dashBoardFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.dashboard_color);
                        return true;

                    case R.id.manage:
                        setFragment(manageFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.income_color);
                        return true;

                    case R.id.wishlist:
                        setFragment(wishlistFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.expense_color);
                        return true;

                    case R.id.account:
                        setFragment(accountFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.account_color);
                        return true;

                    default:
                        return false;
                }
            }
        });




    }

    //method to set fragments
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }

    public  void displaySelectedListener(int itemId){
        Fragment fragment = null;
        switch (itemId) {
            case R.id.dashboard:
                fragment = new DashBoardFragment();
                break;
            case R.id.manage:
                fragment = new ManageFragment();
                break;
            case R.id.wishlist:
                fragment = new WishlistFragment();
                break;
            case R.id.account:
                fragment = new AccountFragment();
                break;
        }

        if(fragment!=null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame,fragment);
            ft.commit();
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedListener(item.getItemId());
        return true;
    }
}
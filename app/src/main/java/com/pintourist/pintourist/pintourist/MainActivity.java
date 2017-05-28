package com.pintourist.pintourist.pintourist;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MapsFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener,
        LeaderboardFragment.OnFragmentInteractionListener{


    private String TAG = " MainActiviry";
    private AHBottomNavigationViewPager viewPager;
    private AHBottomNavigation bottomNavigation;
    private MainViewPagerAdapter adapter;
    private Fragment currentFragment;
    //private DemoFragment currentFragment;
    //private DemoViewPagerAdapter adapter;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().getThemedContext();
        toolbar.setTitleTextColor(0xFFFFFFFF);
        /*final Fragment[] fragment = {new MapsFragment(), new ProfileFragment(), new LeaderboardFragment()};
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment[0])
                .commit();
        */
        //Firebasecode
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, World!");
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);


        boolean useMenuResource=true;
        if (useMenuResource) {
            //tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_main);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation);
        } else {
            AHBottomNavigationItem item1 = new AHBottomNavigationItem("Maps", R.drawable.ic_map_black_24dp, R.color.colorPrimary);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem("Leaderboard", R.drawable.ic_account_circle_black_24dp, R.color.colorPrimary);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem("Profile", R.drawable.ic_group_black_24dp, R.color.colorPrimary);
            bottomNavigation.setCurrentItem(0);
            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);
            bottomNavigationItems.add(item3);
            bottomNavigation.addItems(bottomNavigationItems);
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .show(new ProfileFragment())
                .commit();
        //bottomNavigation.setTranslucentNavigationEnabled(true);
        //Refer to https://github.com/aurelhubert/ahbottomnavigation
        // Disable the translation inside the CoordinatorLayout
        //bottomNavigation.setBehaviorTranslationEnabled(false);
        // Change colors
        bottomNavigation.setAccentColor(this.getResources().getColor(R.color.colorPrimary));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        // Set background color
        bottomNavigation.setDefaultBackgroundColor(this.getResources().getColor(R.color.white));
        //bottomNavigation.setTranslucentNavigationEnabled(true);
        // Use colored navigation with circle reveal effect
        //bottomNavigation.setColored(true);
        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //Fragment frag = null;
                Log.d(TAG, "Selected " +
                        position);


                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    //currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    //currentFragment.willBeHidden();
                }

                //currentFragment.willBeDisplayed();


                //currentFragment.willBeDisplayed();
                viewPager.setCurrentItem(position, false);
                currentFragment = adapter.getCurrentFragment();




                switch (position) {
                    case 0:

                    case 1:

                        //currentFragment.willBeHidden();
                    case 2:

                }

                /*switch (position) {
                    case 0:


				viewPager.setCurrentItem(position, false);
				currentFragment = adapter.getCurrentFragment();
				currentFragment.willBeDisplayed();


                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment[0])
                                .commit();

                    case 1:

                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment[1])
                                .commit();

                    case 2:
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment[2])
                                .commit();


                }*/



                return true;
            }
        });

        viewPager.setOffscreenPageLimit(4);

        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        //adapter.setPrimaryItem(viewPager,1, new MapsFragment());
        viewPager.setAdapter(adapter);
        currentFragment = adapter.getCurrentFragment();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
            }
        });
        // Enable the translation of the FloatingActionButton
        bottomNavigation.manageFloatingActionButtonBehavior(fab);

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*private void selectFragment(MenuItem item) {
        MapsFragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {

            case R.id.action_favorites:
                frag = MapsFragment.newInstance(null, null);

                break;

        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i < mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        //updateToolbarText(item.getTitle());

        if (frag != null) {
            /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, "");
            ft.commit();
        }
    }*/

}

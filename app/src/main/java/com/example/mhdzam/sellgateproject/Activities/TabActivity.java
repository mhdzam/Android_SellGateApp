package com.example.mhdzam.sellgateproject.Activities;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mhdzam.sellgateproject.Adapters.SectionsPagerAdapter;
import com.example.mhdzam.sellgateproject.Fragments.PlaceholderFragment;
import com.example.mhdzam.sellgateproject.R;
import com.example.mhdzam.sellgateproject.other.CircleTransform;

public class TabActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    // start drawer things
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "https://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "http://www.messagescollection.com/wp-content/uploads/2015/04/cute-cat-profile-for-facebook.jpg";
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    public static final String TAG_BASKET ="basket";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    //end here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabb);
        mHandler = new Handler();
       // mapping();
        setSupportActionBar(toolbar);
        // Navigation view header
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        // load nav menu header data
       // loadNavHeader();
        // initializing navigation menu
        //setUpNavigationView();
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
//    private  void mapping()
//    {
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//
////        navigationView = (NavigationView) findViewById(R.id.nav_view);
////        fab = (FloatingActionButton) findViewById(R.id.fab);
////        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        navHeader = navigationView.getHeaderView(0);
//      //  txtName = (TextView) navHeader.findViewById(R.id.name);
////        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
////        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
////        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
////        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
////        // Set up the ViewPager with the sections adapter.
////        mViewPager = (ViewPager) findViewById(R.id.viewpager);
////        mViewPager.setAdapter(mSectionsPagerAdapter);
//        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
//    }
    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
//    private void loadNavHeader() {
//        // name, website
//        txtName.setText("Mis Web");
//        txtWebsite.setText("www.syriatel.sy/mhdzam");
//
//        // loading header background image
//        Glide.with(this).load(urlNavHeaderBg)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgNavHeaderBg);
//
//        // Loading profile image
//        Glide.with(this).load(urlProfileImg)
//                .crossFade()
//                .thumbnail(0.5f)
//                .bitmapTransform(new CircleTransform(this))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgProfile);
//
//        // showing dot next to notifications label
//       // navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
//
//    }
//    private void setToolbarTitle() {
//        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
//    }
//    private void selectNavMenu() {
//        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
//    }
//    /***
//     * Returns respected fragment that user
//     * selected from navigation menu
//     */
//    private void  loadHomeFragment(){
//        // selecting appropriate nav menu item
//        selectNavMenu();
//
//        // set toolbar title
//        setToolbarTitle();
//
//        // if user select the current navigation menu again, don't do anything
//        // just close the navigation drawer
//        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
//            drawer.closeDrawers();
//
//            // show or hide the fab button
//           // toggleFab();
//            return;
//    }
//
//        Runnable mPendingRunnable = new Runnable() {
//            @Override
//            public void run() {
//                // update the main content by replacing fragments
//                Fragment fragment = getHomeFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
//                        android.R.anim.fade_out);
//              //  fragmentTransaction.replace(R.id.flContent, fragment, CURRENT_TAG);
//                fragmentTransaction.commitAllowingStateLoss();
//            }
//        };
//        // If mPendingRunnable is not null, then add to the message queue
//        if (mPendingRunnable != null) {
//            mHandler.post(mPendingRunnable);
//        }
//
//        // show or hide the fab button
//       // toggleFab();
//
//        //Closing drawer on item click
//        drawer.closeDrawers();
//
//        // refresh toolbar menu
//        invalidateOptionsMenu();
//    }
//    private Fragment getHomeFragment() {
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//       switch (navItemIndex) {
//            case 0:
//                // home
//                PlaceholderFragment homeFragment = new PlaceholderFragment();
//                tabLayout.setVisibility(View.VISIBLE);
//                return PlaceholderFragment.newInstance(0);
//
//            case 1:
//                // basket
//                MyBasketFragment Home = new MyBasketFragment();
//                tabLayout.setVisibility(View.GONE);
//                return Home;
//
//            case 3:
//                // notifications fragment
//                NotificationsFragment notificationsFragment = new NotificationsFragment();
//                return notificationsFragment;
//           default:
//                return new PlaceholderFragment();
//       }
//    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
              //  loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }
    // show or hide the fab
//    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tabb, menu);
//        return true;
//    }
//    private void displaySelectedScreen(int id){
//        Fragment fragment = null;
//        switch (id){
//            case R.id.nav_basket:
//                fragment = new MyBasketFragment();
//                break;
//            case R.id.nav_home:
//                fragment = new PlaceholderFragment();
//
//        }
//        if (fragment !=null){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//          //  ft.replace(R.id.flContent,fragment);
//            ft.commit();
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//    }
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        displaySelectedScreen(id);
//
//        return true;
//    }
}


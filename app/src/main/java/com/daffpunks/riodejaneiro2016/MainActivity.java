package com.daffpunks.riodejaneiro2016;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daffpunks.riodejaneiro2016.Fragment.MedalsFragment;
import com.daffpunks.riodejaneiro2016.Fragment.NewsFragment;
import com.daffpunks.riodejaneiro2016.Fragment.ScheduleFragmentMain;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private static final int NEWS_ID    = 1;
    private static final int MEDALS_ID  = 2;
    private static final int SCHEDULE_ID = 3;
    private static final int ABOUT_ID   = 4;

    SmoothActionBarDrawerToggle mDrawerToggle;
    FragmentManager             fragmentManager;
    DrawerLayout                mDrawerLayout;

    private Drawer          mDrawer;
    private AccountHeader   mHeader;


    public void openDrawer() {
        mDrawer.openDrawer();
    }

    public void closeDrawer() {
        mDrawer.closeDrawer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DefaultTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_activity_container, ScheduleFragmentMain.newInstance(), "fragment_main")
                    .commit();
        }

        handleDrawer(savedInstanceState);

    }

    private void handleDrawer(Bundle savedInstanceState) {
        final Activity activity = this;
        // Setup account header
        mHeader = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.headeraccount2)
                .build();

        // Setup drawer
        mDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(mHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_title_news)
                                .withIcon(R.drawable.ic_newspaper_black_24dp).withIconTintingEnabled(true).withIdentifier(NEWS_ID),

                        new PrimaryDrawerItem().withName(R.string.drawer_title_medals)
                                .withIcon(R.drawable.ic_star_circle_black_24dp).withIconTintingEnabled(true).withIdentifier(MEDALS_ID),

                        new PrimaryDrawerItem().withName(R.string.drawer_title_schedule)
                                .withIcon(R.drawable.ic_calendar_black_24dp).withIconTintingEnabled(true).withIdentifier(SCHEDULE_ID),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName(R.string.drawer_title_about)
                                .withIcon(R.drawable.ic_information_black_24dp).withIconTintingEnabled(true).withSelectable(false)
                                .withIdentifier(ABOUT_ID)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (drawerItem.getIdentifier()) {
                            default:
                            case NEWS_ID:
                                mDrawerToggle.runWhenIdle(new Runnable() {
                                    @Override
                                    public void run() {
                                        fragmentManager
                                                .beginTransaction()
                                                .replace(R.id.main_activity_container, NewsFragment.newInstance(), "fragment_main")
                                                .commit();

                                    }
                                });
                                break;
                            case MEDALS_ID:
                                mDrawerToggle.runWhenIdle(new Runnable() {
                                    @Override
                                    public void run() {
                                        fragmentManager
                                                .beginTransaction()
                                                .replace(R.id.main_activity_container, MedalsFragment.newInstance(), "fragment_main")
                                                .commit();

                                    }
                                });
                                break;
                            case SCHEDULE_ID:
                                mDrawerToggle.runWhenIdle(new Runnable() {
                                    @Override
                                    public void run() {
                                        fragmentManager
                                                .beginTransaction()
                                                .replace(R.id.main_activity_container, ScheduleFragmentMain.newInstance(), "fragment_main")
                                                .commit();

                                    }
                                });
                                break;
                            case ABOUT_ID:
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        mDrawerLayout = mDrawer.getDrawerLayout();
        mDrawerToggle = new SmoothActionBarDrawerToggle(this, mDrawerLayout, null, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawer.setSelection(SCHEDULE_ID);

    }



    private class SmoothActionBarDrawerToggle extends ActionBarDrawerToggle {

        private Runnable runnable;

        public SmoothActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);
            if (runnable != null && newState == DrawerLayout.STATE_IDLE) {
                runnable.run();
                runnable = null;
            }
        }

        public void runWhenIdle(Runnable runnable) {
            this.runnable = runnable;
        }
    }
}

























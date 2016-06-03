package com.daffpunks.riodejaneiro2016.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daffpunks.riodejaneiro2016.R;
import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 25.04.2016.
 */
public class ScheduleFragmentMain extends BaseFragment {
    @Bind(R.id.toolbar)         Toolbar         mToolbar;
    @Bind(R.id.pager)           ViewPager       viewPager;
    @Bind(R.id.main_tabs)       TabLayout       tabLayout;
    @Bind(R.id.toolbarLayout)   LinearLayout    toolbarLayout;

    private NavigationAdapter viewPagerAdapter;

    android.support.v4.app.FragmentManager fragmentManagerV4;
    android.app.FragmentManager            fragmentManager;

    public ScheduleFragmentMain(){

    }

    public static ScheduleFragmentMain newInstance() {
        ScheduleFragmentMain fragment = new ScheduleFragmentMain();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManagerV4 = getFragmentManager();
        fragmentManager = getMainFragmentManager();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_fragment_main, container, false);
        ButterKnife.bind(this, root);

        mToolbar.setTitle("Расписание игр");

        setHasOptionsMenu(true);

        setupToolbarForFragment(mToolbar);

        viewPagerAdapter = new NavigationAdapter(getFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        if (tabLayout.getTabCount() == 17) {
            for(int i = 0; i < 17; i++) {
                tabLayout.getTabAt(i).setText("АВГ " + (i + 5));
            }
        }

        viewPager.setCurrentItem(0, true);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.calendar_menu, menu);
    }

    SсheduleFragment sсheduleFragment;

    protected class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createItem(int position) {
            switch (position) {
                case 0:
                    sсheduleFragment = SсheduleFragment.newInstance(5);
                    return sсheduleFragment;
                case 1:
                    sсheduleFragment = SсheduleFragment.newInstance(6);
                    return sсheduleFragment;
                case 2:
                    sсheduleFragment = SсheduleFragment.newInstance(7);
                    return sсheduleFragment;
                case 3:
                    sсheduleFragment = SсheduleFragment.newInstance(8);
                    return sсheduleFragment;
                case 4:
                    sсheduleFragment = SсheduleFragment.newInstance(9);
                    return sсheduleFragment;
                case 5:
                    sсheduleFragment = SсheduleFragment.newInstance(10);
                    return sсheduleFragment;
                case 6:
                    sсheduleFragment = SсheduleFragment.newInstance(11);
                    return sсheduleFragment;
                case 7:
                    sсheduleFragment = SсheduleFragment.newInstance(12);
                    return sсheduleFragment;
                case 8:
                    sсheduleFragment = SсheduleFragment.newInstance(13);
                    return sсheduleFragment;
                case 9:
                    sсheduleFragment = SсheduleFragment.newInstance(14);
                    return sсheduleFragment;
                case 10:
                    sсheduleFragment = SсheduleFragment.newInstance(15);
                    return sсheduleFragment;
                case 11:
                    sсheduleFragment = SсheduleFragment.newInstance(16);
                    return sсheduleFragment;
                case 12:
                    sсheduleFragment = SсheduleFragment.newInstance(17);
                    return sсheduleFragment;
                case 13:
                    sсheduleFragment = SсheduleFragment.newInstance(18);
                    return sсheduleFragment;
                case 14:
                    sсheduleFragment = SсheduleFragment.newInstance(19);
                    return sсheduleFragment;
                case 15:
                    sсheduleFragment = SсheduleFragment.newInstance(20);
                    return sсheduleFragment;
                case 16:
                    sсheduleFragment = SсheduleFragment.newInstance(21);
                    return sсheduleFragment;
            }
            sсheduleFragment = SсheduleFragment.newInstance(0);
            return sсheduleFragment;
        }

        @Override
        public int getCount() {
            return 17;
        }

    }

}

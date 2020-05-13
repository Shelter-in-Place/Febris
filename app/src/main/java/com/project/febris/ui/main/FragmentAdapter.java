package com.project.febris.ui.main;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.project.febris.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private static final String TAG = "FragmentAdapter";
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Log.d(TAG, "position: " + position);
        switch (position){
            case 0:
                fragment = new Fragment1();
                Log.d(TAG, "Frag 1 built " + fragment.getId());
                break;
            case 1:
                fragment = new Fragment2();
                Log.d(TAG, "Frag 2 built " + fragment.getId());
                break;
            case 2:
                fragment = new Fragment3();

                Log.d(TAG, "Frag 3 built " + fragment.getId());

        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
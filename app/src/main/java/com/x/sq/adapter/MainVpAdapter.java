package com.x.sq.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.x.sq.fragment.BlogFragment;
import com.x.sq.fragment.HelpFragment;
import com.x.sq.fragment.NoteFragment;
import com.x.sq.fragment.SearchFragment;

/**
 * Created by xudafeng on 2017/11/10.
 */

public class MainVpAdapter extends FragmentPagerAdapter {

    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new NoteFragment();
        else if(position == 1)
            return new BlogFragment();
        else if(position == 2)
            return new SearchFragment();
        return new HelpFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}

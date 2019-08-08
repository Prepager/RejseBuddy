package com.rejsebuddy;

import android.content.Context;
import android.util.Pair;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    /**
     * The parent context state.
     */
    Context ctx;

    /**
     * The page adapter tab string ids and classes.
     */
    List<Pair<Integer, Class<? extends Fragment>>> tabs = new ArrayList<Pair<Integer, Class<? extends Fragment>>>() {{
        add(new Pair<Integer, Class<? extends Fragment>>(R.string.trips_title, TripsFragment.class));
        add(new Pair<Integer, Class<? extends Fragment>>(R.string.contacts_title, ContactsFragment.class));
    }};

    /**
     * TODO
     *
     * @param context The parent context state.
     * @param manager The fragment manager.
     */
    public MainPageAdapter(Context context, FragmentManager manager) {
        super(manager);
        ctx = context;
    }

    /**
     * Returns the title for the passed index.
     *
     * @param index The index of the requested tab.
     * @return The title of the requested tab.
     */
    @Override
    public CharSequence getPageTitle(int index) {
        System.out.print(ctx.getString(tabs.get(index).first));
        return ctx.getString(tabs.get(index).first);
    }

    /**
     * Returns a new instance of the passed index.
     *
     * @param index The index of the requested tab.
     * @return The new instance of the requested tab.
     */
    @Override
    public Fragment getItem(int index) {
        try {
            return tabs.get(index).second.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns the amount of tabs.
     *
     * @return The amount of tabs.
     */
    @Override
    public int getCount() {
        return tabs.size();
    }

}

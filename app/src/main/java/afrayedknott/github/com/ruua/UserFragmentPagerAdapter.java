package afrayedknott.github.com.ruua;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;


public class UserFragmentPagerAdapter extends FragmentPagerAdapter {

    Context cont;
    private FragmentManager mFragmentManager;

    public UserFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a UserFragment (defined as a static inner class below).
        return UserFragment.newInstance(position + 1, cont);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

}

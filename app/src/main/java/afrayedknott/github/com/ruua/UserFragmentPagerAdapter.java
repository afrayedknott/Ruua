package afrayedknott.github.com.ruua;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;


public class UserFragmentPagerAdapter extends FragmentPagerAdapter {

    Context cont;
    private FragmentManager mFragmentManager;

    public UserFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment tempPagerFragment = null;

        switch(position) {

            case 0:
                tempPagerFragment = FieldWorkerFragment.newInstance(1);
                break;
            case 1:
                tempPagerFragment = SupervisorFragment.newInstance(1);
                break;
            case 2:
                tempPagerFragment = AdminFragment.newInstance(1);
                break;
        }

        return tempPagerFragment;

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

}

package afrayedknott.github.com.ruua;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;


public class UserFragmentPagerAdapter extends FragmentPagerAdapter {

    Context cont;
    private FragmentManager mFragmentManager;
    private int userRole;

    public UserFragmentPagerAdapter(FragmentManager fm, int role) {
        super(fm);
        mFragmentManager = fm;
        this.userRole = role;
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
        switch(userRole) {
            case 0: return 1;
            case 1: return 2;
            case 2: return 3;
            default : return 1;
        }
    }

}

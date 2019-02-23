package afrayedknott.github.com.ruua;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class UserFragmentPagerAdapter extends FragmentPagerAdapter {

    Context cont;
    private FragmentManager mFragmentManager;
    private User signedInUser;
    ArrayList<User> assignedEmployees;

    public UserFragmentPagerAdapter(FragmentManager fm, User signedInUser, ArrayList<User> assignedEmployees) {
        super(fm);
        mFragmentManager = fm;
        this.signedInUser = signedInUser;
        this.assignedEmployees = assignedEmployees;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment tempPagerFragment = null;

        switch(position) {

            case 0:
                tempPagerFragment = FieldWorkerFragment.newInstance(signedInUser);
                break;
            case 1:
                tempPagerFragment = SupervisorFragment.newInstance(signedInUser, assignedEmployees);
                break;
            case 2:
                tempPagerFragment = AdminFragment.newInstance(signedInUser, assignedEmployees);
                break;
        }

        return tempPagerFragment;

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        switch(signedInUser.getCurrentUserRole()) {
            case "field":
                return 1;
            case "supervisor": return 2;
            case "admin": return 3;
            default : return 1;
        }
    }

}

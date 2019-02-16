package afrayedknott.github.com.ruua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    private User signedAdminInUser;
    private User signedSupervisorInUser;
    private User signedFieldInUser;
    private User signedInUser;
    private ArrayList<User> assignedEmployees;

    //Views
    private Button fieldWorkerSignInButton;
    private Button supervisorSignInButton;
    private Button adminSignInButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();

        setDummyAdminUser();
        setDummySupervisorUser();
        setDummyFieldUser();
        assignedEmployees = new ArrayList<User>(0);

        fieldWorkerSignInButton = findViewById(R.id.field_worker_signin_button);
        fieldWorkerSignInButton.setOnClickListener(fieldWorkerSignInButtonOnClickListener);
        supervisorSignInButton = findViewById(R.id.supervisor_signin_button);
        supervisorSignInButton.setOnClickListener(supervisorSignInButtonOnClickListener);
        adminSignInButton = findViewById(R.id.admin_signin_button);
        adminSignInButton.setOnClickListener(adminSignInButtonOnClickListener);

    }

    private View.OnClickListener fieldWorkerSignInButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            signedInUser = signedFieldInUser;
            pullAssignedEmployees(signedInUser);
            intentToStartActivity.putExtra("signed_in_user", signedInUser);
            intentToStartActivity.putParcelableArrayListExtra("assigned_employees", assignedEmployees);
            startActivity(intentToStartActivity);

        }

    };

    private View.OnClickListener supervisorSignInButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            signedInUser = signedSupervisorInUser;
            pullAssignedEmployees(signedInUser);
            intentToStartActivity.putExtra("signed_in_user", signedInUser);
            intentToStartActivity.putParcelableArrayListExtra("assigned_employees", assignedEmployees);
            startActivity(intentToStartActivity);

        }

    };

    private View.OnClickListener adminSignInButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            signedInUser = signedAdminInUser;
            pullAssignedEmployees(signedInUser);
            intentToStartActivity.putExtra("signed_in_user", signedInUser);
            intentToStartActivity.putParcelableArrayListExtra("assigned_employees", assignedEmployees);
            startActivity(intentToStartActivity);

        }

    };

    private void setDummyAdminUser(){

        signedAdminInUser = new User("001", "jechoi", "Jay", "Choi", User.ADMIN);
        signedAdminInUser.addAddress("2750 e washington blvd pasadena ca 91107");
        signedAdminInUser.addAddress("201 S Lake Ave, Pasadena, CA 91101");
        signedAdminInUser.addAddress("355 N Rosemead Blvd, Pasadena, CA 91107");
        signedAdminInUser.setEmployeeIDList(new ArrayList<String>(Arrays.asList("002", "003")));

    }

    private void setDummySupervisorUser(){

        signedSupervisorInUser = new User("002", "grace", "Grace", "Choi", User.SUPERVISOR);
        signedSupervisorInUser.addAddress("2750 e washington blvd pasadena ca 91107");
        signedSupervisorInUser.addAddress("201 S Lake Ave, Pasadena, CA 91101");
        signedSupervisorInUser.addAddress("355 N Rosemead Blvd, Pasadena, CA 91107");
        signedSupervisorInUser.addAddress("1055 Wilshire Blvd, Los Angeles, CA 90017 ");
        signedSupervisorInUser.addAddress("2675 Foothill Blvd, La Crescenta, CA 91214");
        signedSupervisorInUser.addAddress("3233 Foothill Blvd, La Crescenta-Montrose, CA 91214");
        signedSupervisorInUser.addAddress("440 Vermont Ave #100, Los Angeles, CA 90020");
        signedSupervisorInUser.addAddress("3250 W Olympic Blvd, Los Angeles, CA 90006");
        signedSupervisorInUser.addAddress("920 Foothill Blvd, La Cañada Flintridge, CA 91011");
        signedSupervisorInUser.setEmployeeIDList(new ArrayList<String>(Arrays.asList("003")));

    }

    private void setDummyFieldUser(){

        signedFieldInUser = new User("003", "eugene", "Eugene", "Choi", User.FIELD);
        signedFieldInUser.addAddress("2675 Foothill Blvd, La Crescenta, CA 91214");
        signedFieldInUser.addAddress("3233 Foothill Blvd, La Crescenta-Montrose, CA 91214");
        signedFieldInUser.addAddress("920 Foothill Blvd, La Cañada Flintridge, CA 91011");

    }

    private void pullAssignedEmployees(User signedInUser){

        switch(signedInUser.getEmployeeID()) {
            case "001":
                assignedEmployees.add(signedSupervisorInUser);
                assignedEmployees.add(signedFieldInUser);
                break;
            case "002":
                assignedEmployees.add(signedFieldInUser);
                break;
            default:
                break;
        }

    }

}

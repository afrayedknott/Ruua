package afrayedknott.github.com.ruua;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

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

    private Intent intentToStartActivity;

    private FirestoreHandler fsH;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();

        fsH = new FirestoreHandler();
        assignedEmployees = new ArrayList<User>(0);

        fieldWorkerSignInButton = findViewById(R.id.field_worker_signin_button);
        fieldWorkerSignInButton.setOnClickListener(fieldWorkerSignInButtonOnClickListener);
        supervisorSignInButton = findViewById(R.id.supervisor_signin_button);
        supervisorSignInButton.setOnClickListener(supervisorSignInButtonOnClickListener);
        adminSignInButton = findViewById(R.id.admin_signin_button);
        adminSignInButton.setOnClickListener(adminSignInButtonOnClickListener);

        intentToStartActivity = new Intent(HomeActivity.this, UserFragmentedActivity.class);
    }

    private View.OnClickListener fieldWorkerSignInButtonOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            fsH.getUserFromFirestore("003");
        }

    };

    private View.OnClickListener supervisorSignInButtonOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            fsH.getUserFromFirestore("002");
        }

    };

    private View.OnClickListener adminSignInButtonOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            fsH.getUserFromFirestore("001");
        }

    };

    private void pullSignedInUserFromFirestore2(String employeeID)
    {

        fsH.getUserFromFirestore(employeeID).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot)
            {
                if(documentSnapshot.exists())
                {
                    signedInUser = documentSnapshot.toObject(User.class);
                }
                else
                    {
                    Toast.makeText(HomeActivity.this, "Document does not exist",Toast.LENGTH_SHORT).show();
                    }
            }
        }).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot)
            {
                if (documentSnapshot.exists()) {
                    for(int employeeIDIter = 0; employeeIDIter < signedInUser.getAssignedEmployeeIDList().size(); employeeIDIter++)
                    {
                        User tempUser = documentSnapshot.toObject(User.class);
                        assignedEmployees.add(tempUser);
                    }
                    intentToStartActivity.putExtra("signed_in_user", signedInUser);
                    intentToStartActivity.putParcelableArrayListExtra("assigned_employees", assignedEmployees);
                    startActivity(intentToStartActivity);
                }
                else
                    {
                    Toast.makeText(HomeActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    /*
    private void pullSignedInUserFromFirestore(String employeeID)
    {
        fsH.getUserFromFirestore(employeeID).continueWithTask(new Continuation<DocumentSnapshot, Task<List<Task<DocumentSnapshot>>>>(){
            @Override
            public Task<List<Task<DocumentSnapshot>>> then(@NonNull Task<DocumentSnapshot> task) throws Exception {
                signedInUser = task.getResult().toObject(User.class);
                List<Task<DocumentSnapshot>> tasks = new ArrayList<>();
                for(int employeeIDIter = 0; employeeIDIter < signedInUser.getAssignedEmployeeIDList().size(); employeeIDIter++)
                {
                    tasks.add(fsH.getUserFromFirestore(signedInUser.getAssignedEmployeeIDList().get(employeeIDIter)));
                }
                return Tasks.whenAllSuccess(tasks);
            }
        }).addOnSuccessListener(new OnSuccessListener<List<Task<DocumentSnapshot>>>()
        {
            @Override
            public void onSuccess(List<Task<DocumentSnapshot>> task)
            {
                    for(Task<DocumentSnapshot> doc : task)
                    {
                        User tempUser = doc.getResult().toObject(User.class);
                        assignedEmployees.add(tempUser);
                    }
                    intentToStartActivity.putExtra("signed_in_user", signedInUser);
                    intentToStartActivity.putParcelableArrayListExtra("assigned_employees", assignedEmployees);
                    startActivity(intentToStartActivity);
            }
        });
    }
    */
}

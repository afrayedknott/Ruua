package afrayedknott.github.com.ruua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    //Views
    Button fieldWorkerSignInButton;
    Button supervisorSignInButton;
    Button adminSignInButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();

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
            intentToStartActivity.putExtra("role", 0);
            startActivity(intentToStartActivity);

        }

    };

    private View.OnClickListener supervisorSignInButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            intentToStartActivity.putExtra("role", 1);
            startActivity(intentToStartActivity);

        }

    };

    private View.OnClickListener adminSignInButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            intentToStartActivity.putExtra("role", 2);
            startActivity(intentToStartActivity);

        }

    };



}

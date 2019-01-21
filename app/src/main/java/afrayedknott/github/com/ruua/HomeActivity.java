package afrayedknott.github.com.ruua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    //Views
    Button userSignInButton;
    Button mapOpenButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getSupportActionBar().hide();

        userSignInButton = findViewById(R.id.user_signin_button);
        userSignInButton.setOnClickListener(userSigninOnClickListener);

        mapOpenButton = findViewById(R.id.map_open_button);
        mapOpenButton.setOnClickListener(mapOpenOnClickListener);

    }

    private View.OnClickListener userSigninOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, UserFragmentedActivity.class);
            intentToStartActivity.putExtra("username", "Username");
            startActivity(intentToStartActivity);

        }

    };

    private View.OnClickListener mapOpenOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intentToStartActivity =
                    new Intent(HomeActivity.this, MapsActivity.class);
            startActivity(intentToStartActivity);

        }

    };



}

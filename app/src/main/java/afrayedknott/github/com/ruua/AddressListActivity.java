package afrayedknott.github.com.ruua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class AddressListActivity extends AppCompatActivity implements RecyclerViewAdapterAssignedAddressesList.ItemClickListener{

    private User clickedUser;

    RecyclerViewAdapterAssignedAddressesList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        clickedUser = this.getIntent().getParcelableExtra("user");

        // set up the RecyclerView

        RecyclerView recyclerView = findViewById(R.id.recycler_view_address_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterAssignedAddressesList(this, clickedUser.getAddressList());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intentToStartActivity =
                new Intent(this, AddressEditorActivity.class);
        intentToStartActivity.putExtra("address", clickedUser.getAddressList().get(position));
        intentToStartActivity.putStringArrayListExtra("address_list", clickedUser.getAddressList());
        startActivity(intentToStartActivity);

    }

}

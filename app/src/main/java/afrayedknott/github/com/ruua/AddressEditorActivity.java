package afrayedknott.github.com.ruua;

import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class AddressEditorActivity extends FragmentActivity implements OnMapReadyCallback {

    //location data related properties
    private LocationHandler locationHandler;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private ArrayList<String> stringAddressArrayList;

    //view properties;
    private GoogleMap mMap;
    private String stringAddressSelected;
    private EditText addressInput;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_editor);

        //deliver Intent extras
        stringAddressSelected = this.getIntent().getStringExtra("address");
        stringAddressArrayList = this.getIntent().getStringArrayListExtra("address_list");

        //instantiate views and customize their properties (excluding map)
        addressInput = findViewById(R.id.edittext_address_input);
        addressInput.setText(stringAddressSelected);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(addressInput, 1, 17, 1,
                TypedValue.COMPLEX_UNIT_DIP);

        submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(submitButtonOnClickListener);

        /* instantiate map fragment view and location objects and their customizations
        via onMapReady() which is notified and handled by getMapAsync()
        */
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private View.OnClickListener submitButtonOnClickListener = new View.OnClickListener(){
        public void onClick(View v) {

            locationHandler =
                    new LocationHandler(AddressEditorActivity.this, addressInput.getText().toString());
            mMap.addMarker(new MarkerOptions().position(locationHandler.getLatLng()).title("submission"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationHandler.getLatLng(), 15.0f));

        }
    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        for(Integer mapMarkerIter = 0; mapMarkerIter < stringAddressArrayList.size(); mapMarkerIter++){

            locationHandler = new LocationHandler(this, stringAddressArrayList.get(mapMarkerIter));
            mMap.addMarker(new MarkerOptions().position(locationHandler.getLatLng()).title(mapMarkerIter.toString()));

        }

       /*
        locationHandler = new LocationHandler(this, stringAddressArrayList.get(0));
        mMap.addMarker(new MarkerOptions().position(locationHandler.getLatLng()).title("0"));

        locationHandler = new LocationHandler(this, stringAddressArrayList.get(1));
        mMap.addMarker(new MarkerOptions().position(locationHandler.getLatLng()).title("1"));

        locationHandler = new LocationHandler(this, stringAddressArrayList.get(2));
        mMap.addMarker(new MarkerOptions().position(locationHandler.getLatLng()).title("2"));
        */

        locationHandler = new LocationHandler(this, stringAddressSelected);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationHandler.getLatLng(), 15.0f));

    }



}

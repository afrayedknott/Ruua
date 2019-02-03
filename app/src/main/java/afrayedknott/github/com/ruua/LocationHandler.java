package afrayedknott.github.com.ruua;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


public class LocationHandler implements Serializable {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private String stringAddress;
    private Address address;
    private LatLng latLng;
    private Context context;
    private Geocoder coder;

    public LocationHandler(Context ctxt, String strAddress) {

        context = ctxt;
        coder = new Geocoder(context);
        stringAddress = strAddress;
        getLatLngFromAddress();
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

    }

    public LocationHandler(Context ctxt, LatLng LL) {

        context = ctxt;
        coder = new Geocoder(context);
        latLng = LL;
        getAddressFromLatLng();
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

    }

    public void setStringAddress(String stringAddressInput){this.stringAddress = stringAddressInput;}

    public String getStringAddress(){return stringAddress;}

    public void setAddress(Address addressInput){this.address = addressInput;}

    public Address getAddress(){return address;}

    public void setLatLng(LatLng latLngInput){this.latLng = latLngInput;}

    public LatLng getLatLng(){return latLng;}

    private void getLatLngFromAddress() {

        List<Address> addressList;

        try {
            // May throw an IOException
            addressList = coder.getFromLocationName(stringAddress, 5);
            if (addressList == null) {
            }

            address = addressList.get(0);
            latLng = new LatLng(address.getLatitude(), address.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    private void getAddressFromLatLng() {

        Geocoder coder = new Geocoder(context);
        List<Address> addressList;

        try {
            // May throw an IOException
            addressList = coder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if (addressList == null) { }
            address = addressList.get(0);
            stringAddress = address.toString();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
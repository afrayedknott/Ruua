package afrayedknott.github.com.ruua;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class GPSTracker implements Serializable {

    private boolean requestingLocationUpdates = false;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private Location location;
    private LatLng p1 = null;
    private static int UPDATE_INTERVAL = 5000; // MILLISECONDS
    private static int FASTEST_INTERVAL = 3000; // MILLISECONDS
    private static int DISPLACEMENT = 10; // METERS

    public GPSTracker() {



    }

    public LatLng getLocationFromAddress(Context activityContext, String strAddress){

        Geocoder coder = new Geocoder(activityContext);
        List<Address> address;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }

}
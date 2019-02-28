package afrayedknott.github.com.ruua;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GeofenceManager {

    //Constants
    private static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 500;
    private static final float GEOFENCE_RADIUS_IN_METERS = 800;

    private Context context;
    private GeofencingClient geofencingClient;
    private ArrayList<Geofence> geofenceList;
    private HashMap<String, Boolean> siteVisitedMap;
    private String geofenceKey;
    private PendingIntent pendingIntent;

    public GeofenceManager(Context inputContext, PendingIntent importPendingInt)
    {
        this.context = inputContext;
        this.pendingIntent = importPendingInt;
        geofenceList = new ArrayList<>(0);
        geofencingClient = LocationServices.getGeofencingClient(context);
    }

    private void buildNewGeofence(LatLng inputLatLng)
    {
        geofenceList.add(
                new Geofence.Builder().
                        setRequestId(geofenceKey).
                        setCircularRegion(inputLatLng.longitude, inputLatLng.latitude, GEOFENCE_RADIUS_IN_METERS).
                        setExpirationDuration(GEOFENCE_EXPIRATION_IN_MILLISECONDS).
                        setLoiteringDelay(60000). //sets how many ms they need to be within geofencing for trigger
                        setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT).
                        build());
    }

    public void buildNewGeofenceList(ArrayList<LatLng> inputLatLngList)
    {
        for(LatLng eachLatLang : inputLatLngList)
        {
            buildNewGeofence(eachLatLang);
        }
    }

    //specifying which list of geofences to monitor and how they initially trigger
    public GeofencingRequest getGeofencingRequest()
    {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    public GeofencingClient getGeofencingClient() {
        return geofencingClient;
    }

    public void setGeofencingClient(GeofencingClient geofencingClient) {
        this.geofencingClient = geofencingClient;
    }

    public void addGeofence()
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            getGeofencingClient().addGeofences(getGeofencingRequest(), pendingIntent)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Geofences added
                            // ...
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to add geofences
                            // ...
                        }
                    });
        }
    }
}

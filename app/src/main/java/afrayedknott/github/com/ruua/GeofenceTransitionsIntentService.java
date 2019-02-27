package afrayedknott.github.com.ruua;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

public class GeofenceTransitionsIntentService extends IntentService {

    List<Geofence> triggeringGeofences;
    String geofenceTransitionDetails;
    GeofencingEvent geofencingEvent;
    // Handles the interaction of a triggered geofence

    public GeofenceTransitionsIntentService() {
        super("GeofenceTransitionsIntentService");
        triggeringGeofences = new ArrayList<>(0);
        geofenceTransitionDetails = "";
    }

    protected void onHandleIntent(Intent intent) {
        geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e("eugene", errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();


        // Test that the reported transition was of interest.
        switch(geofenceTransition){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                stringLogGeofenceEvent("entered geofence");
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                stringLogGeofenceEvent("exited geofence");
                break;
            default:
                Integer stringGeofenceTransition = (Integer) geofenceTransition;
                Log.e("eugene", stringGeofenceTransition.toString());
                break;
        }

    }

    private void stringLogGeofenceEvent(String event)
    {
        // Get the geofences that were triggered. A single event can trigger
        // multiple geofences.
        triggeringGeofences = geofencingEvent.getTriggeringGeofences();

        // Get the transition details as a String.
        geofenceTransitionDetails =
                getGeofenceTransitionDetails(this, event, triggeringGeofences);

        // Send notification and log the transition details.
        // sendNotification(geofenceTransitionDetails);
        Log.i("eugene", geofenceTransitionDetails);
    }

    private String getGeofenceTransitionDetails(Context context, String trickledEvent, List<Geofence> listOfGeofences)
    {
        String stringContext = context.toString();
        String stringListOfGeofences = listOfGeofences.toString();
        return stringContext + "; " + trickledEvent + "; " + stringListOfGeofences;

    }

}

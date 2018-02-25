package com.cslcteam1.winterwonderhackapp2018v2.services;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cslcteam1.winterwonderhackapp2018v2.db.DatabaseMain;
import com.cslcteam1.winterwonderhackapp2018v2.db.EntityGeoFence;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Larkin on 2/24/2018.
 */

public class GeoFenceIntentService extends IntentService {

    public GeoFenceIntentService(String name) {
        super(name);
        Globals.geofencingClient = LocationServices.getGeofencingClient(this);
        loadGeofences();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        GeofencingEvent geofenceTrigger = GeofencingEvent.fromIntent(intent);
        int geofenceTransition = geofenceTrigger.getGeofenceTransition();
        List<Geofence> triggeredGeoFences = geofenceTrigger.getTriggeringGeofences();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {

        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){

        }
    }

    //FOR TESTING PURPOSES BLOW IT UP LATER
    private void loadGeofences() {
        Geofence.Builder geofenceBuilder = new Geofence.Builder();
        geofenceBuilder.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT);

        DatabaseMain db = Room.databaseBuilder(getApplicationContext(), DatabaseMain.class, "database-main").build();
        List<EntityGeoFence> entityGeoFences = db.geoFenceDao().getAll();
        List<Geofence> geofences = new ArrayList<>();
        for(EntityGeoFence entityGeoFence : entityGeoFences){
            System.out.println("HELLO BOIS IZ LOADZING");
            geofences.add(geofenceBuilder.setCircularRegion(entityGeoFence.lat, entityGeoFence.lon, entityGeoFence.radius).build());
        }
        GeofencingRequest.Builder geofenceRequestBuilder = new GeofencingRequest.Builder();
        geofenceRequestBuilder.setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER);
        geofenceRequestBuilder.addGeofences(geofences);

        GeofencingRequest request = geofenceRequestBuilder.build();
        try{
            Globals.geofencingClient.addGeofences(request, Globals.geofenceIntent);
        } catch (SecurityException se){

        }
    }

}

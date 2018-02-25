package com.cslcteam1.winterwonderhackapp2018v2.services;

import android.app.PendingIntent;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.cslcteam1.winterwonderhackapp2018v2.db.DatabaseMain;
import com.cslcteam1.winterwonderhackapp2018v2.db.EntityGeoFence;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Larkin on 2/25/2018.
 */

public class Globals {
    public static PendingIntent geofenceIntent;
    public static GeofencingClient geofencingClient;
    //FOR TESTING PURPOSES BLOW IT UP LATER

    public static void loadGeofences(Context context) {
        final Geofence.Builder geofenceBuilder = new Geofence.Builder();
        geofenceBuilder.setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT);

        final DatabaseMain db = Room.databaseBuilder(context, DatabaseMain.class, "database-main").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<EntityGeoFence> entityGeoFences = db.geoFenceDao().getAll();
                List<Geofence> geofences = new ArrayList<>();
                if(entityGeoFences.size() > 0){
                    for(EntityGeoFence entityGeoFence : entityGeoFences){
                        System.out.println("HELLO BOIS IZ LOADZING");
                        geofences.add(geofenceBuilder.setCircularRegion(entityGeoFence.lat, entityGeoFence.lon, entityGeoFence.radius).setRequestId(entityGeoFence.id).setExpirationDuration(1000000000).build());
                    }
                    GeofencingRequest.Builder geofenceRequestBuilder = new GeofencingRequest.Builder();
                    geofenceRequestBuilder.setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER);
                    geofenceRequestBuilder.addGeofences(geofences);

                    GeofencingRequest request = geofenceRequestBuilder.build();
                    try{
                        System.out.println("HELLO FRIEND: "+Globals.geofenceIntent);
                        Globals.geofencingClient.addGeofences(request, Globals.geofenceIntent);
                    } catch (SecurityException se){

                    }
                }
            }
        }).start();
    }
}

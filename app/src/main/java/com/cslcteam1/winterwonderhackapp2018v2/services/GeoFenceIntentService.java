package com.cslcteam1.winterwonderhackapp2018v2.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.Context;
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
    }

    public GeoFenceIntentService() {
        super("GeoFenceIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        GeofencingEvent geofenceTrigger = GeofencingEvent.fromIntent(intent);
        int geofenceTransition = geofenceTrigger.getGeofenceTransition();
        List<Geofence> triggeredGeoFences = geofenceTrigger.getTriggeringGeofences();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            System.out.println("I AM ENTERING YOU NOOB");
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);
        } else if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){
            System.out.println("I AM EXITING YOU NOOB");
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
        }
    }


}

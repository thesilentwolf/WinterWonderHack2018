package com.cslcteam1.winterwonderhackapp2018v2.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;


import java.util.ArrayList;

/**
 * Created by Alexander Larkin on 2/24/2018.
 */

@Entity
public class EntityGeoFence {

    @PrimaryKey
    @NonNull
    public String id;

    public double lon;
    public double lat;

    public ArrayList<String> permissions;

    @FloatRange(from = 0.0)
    public float radius;

    public EntityGeoFence(@NonNull String id, double lat, double lon, @FloatRange(from = 0.0) float radius, ArrayList<String> permissions) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
        this.radius = radius;
        this.permissions = permissions;
    }
}

package com.cslcteam1.winterwonderhackapp2018v2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Alexander Larkin on 2/24/2018.
 */

@Database(version = 1, entities = {EntityGeoFence.class}, exportSchema = false)
public abstract class DatabaseMain extends RoomDatabase {
    abstract public DAOGeoFence geoFenceDao();
}

package com.cslcteam1.winterwonderhackapp2018v2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Alexander Larkin on 2/24/2018.
 */
@Dao
public interface DAOGeoFence {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public void insertEntityGeoFence(EntityGeoFence geofence);

    @Delete
    public void deleteEntityGeoFence(EntityGeoFence geofence);

    @Update
    public void updateEntityGeoFence(EntityGeoFence geofence);

    @Query("SELECT * FROM EntityGeoFence")
    public List<EntityGeoFence> getAll();

    @Query("SELECT * FROM EntityGeoFence WHERE id = :id")
    public List<EntityGeoFence> queryById(String id);

}

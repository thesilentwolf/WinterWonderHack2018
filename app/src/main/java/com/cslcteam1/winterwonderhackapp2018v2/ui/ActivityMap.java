package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.cslcteam1.winterwonderhackapp2018v2.R;
import com.cslcteam1.winterwonderhackapp2018v2.db.DAOGeoFence;
import com.cslcteam1.winterwonderhackapp2018v2.db.DatabaseMain;
import com.cslcteam1.winterwonderhackapp2018v2.db.EntityGeoFence;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class ActivityMap extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback{

    private TextView mTextMessage;

    GoogleMap gMap;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent x;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    x = new Intent(ActivityMap.this, ActivityList.class );
                    startActivity( x );
                    return true;
                case R.id.navigation_notifications:
                    x = new Intent(ActivityMap.this, ActivitySettings.class);
                    startActivity( x );
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SupportMapFragment mapFragement = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragement.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            gMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            gMap.setMyLocationEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                createTestData();
                loadGeoFences();

            }
        }).start();

    }

    private final void loadGeoFences(){

        DatabaseMain db = Room.databaseBuilder(getApplicationContext(), DatabaseMain.class, "database-main").build();
        final List<EntityGeoFence> listGeofence = db.geoFenceDao().getAll();

        ActivityMap.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(EntityGeoFence geoFence : listGeofence){
                    CircleOptions circle = new CircleOptions().center(new LatLng(geoFence.lat, geoFence.lon)).radius(geoFence.radius).strokeColor(Color.BLUE);
                    gMap.addCircle(circle);
                }
            }
        });
    }

    private final void createTestData(){
        DatabaseMain db = Room.databaseBuilder(getApplicationContext(), DatabaseMain.class, "database-main").build();
        EntityGeoFence geofence1 = new EntityGeoFence("first", 47.07,-88.10, 100);
        EntityGeoFence geofence2 = new EntityGeoFence("second", 47.056,-88.30, 100);
        EntityGeoFence geofence3 = new EntityGeoFence("third", 47.12,-88.11, 100);
        EntityGeoFence geofence4 = new EntityGeoFence("fourth", 47.20,-88.13, 100);
        EntityGeoFence geofence5 = new EntityGeoFence("five", 47.1,-88.25, 100);
        db.geoFenceDao().insertEntityGeoFence(geofence1);
        db.geoFenceDao().insertEntityGeoFence(geofence2);
        db.geoFenceDao().insertEntityGeoFence(geofence3);
        db.geoFenceDao().insertEntityGeoFence(geofence4);
        db.geoFenceDao().insertEntityGeoFence(geofence5);
    }
}

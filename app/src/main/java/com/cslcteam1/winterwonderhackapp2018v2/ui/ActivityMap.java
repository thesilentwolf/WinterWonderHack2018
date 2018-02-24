package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.cslcteam1.winterwonderhackapp2018v2.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class ActivityMap extends AppCompatActivity implements OnMapReadyCallback{

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

//        SupportMapFragment mapFragement = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragement.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
    }
}

package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.Manifest;
import android.app.PendingIntent;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.cslcteam1.winterwonderhackapp2018v2.R;
import com.cslcteam1.winterwonderhackapp2018v2.db.DatabaseMain;
import com.cslcteam1.winterwonderhackapp2018v2.db.EntityGeoFence;
import com.cslcteam1.winterwonderhackapp2018v2.services.GeoFenceIntentService;
import com.cslcteam1.winterwonderhackapp2018v2.services.Globals;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    private Button bt;
    private ListView lv;
    private ArrayList<String> strArr;
    private ArrayAdapter<String> adapter;


    public void loadSaved(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               DatabaseMain db = Room.databaseBuilder(getApplicationContext(), DatabaseMain.class, "database-main").build();
               final List<EntityGeoFence> listGeofence = db.geoFenceDao().getAll();

               ActivityList.this.runOnUiThread(new Runnable() {
                   @Override
                   public void run() {

                       ScrollView scrl= (ScrollView)findViewById(R.id.scrollView);
                       LinearLayout ll=new LinearLayout(getApplicationContext());
                       ll.setOrientation(LinearLayout.VERTICAL);
                       ll.setLayoutParams(new ViewGroup.LayoutParams(
                               ViewGroup.LayoutParams.FILL_PARENT,
                               ViewGroup.LayoutParams.MATCH_PARENT));
                       scrl.addView(ll);

                       for(EntityGeoFence geoFence : listGeofence) {
                           LinearLayout lh = new LinearLayout(getApplicationContext());
                           lh.setLayoutParams(new ViewGroup.LayoutParams(
                                   ViewGroup.LayoutParams.FILL_PARENT,
                                   ViewGroup.LayoutParams.MATCH_PARENT));


                           TextView tv=new TextView(getApplicationContext());
                           tv.setTextColor(Color.BLACK);
                           tv.setPadding(50,50,150,50);
                           Log.i("INFO","HELLO I AM NOT HERE"+geoFence.id);
                           tv.setText(geoFence.id);
                           lh.addView(tv);
                           Switch sw = new Switch(getApplicationContext());
                           Button btn = new Button(getApplicationContext());
                           btn.setText("Edit");
                           lh.addView(btn);
                           ll.addView(lh);
                       }
                   }
               });
           }
       }).start();
    }
    public void init(){
        bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent x = new Intent(ActivityList.this, ActivityAppList.class);
                startActivity(x);
            }
        });



    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent x;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    x = new Intent( ActivityList.this, ActivityMap.class );
                    startActivity( x );
                    return true;
                case R.id.navigation_notifications:
                    x = new Intent( ActivityList.this, ActivitySettings.class );
                    startActivity( x );
                    return true;
            }



            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        loadSaved();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent msgIntent = new Intent(this, GeoFenceIntentService.class);
        Globals.geofencingClient = LocationServices.getGeofencingClient(this);
        Globals.geofenceIntent = PendingIntent.getService(this, 0, msgIntent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        Globals.loadGeofences(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

    }
}

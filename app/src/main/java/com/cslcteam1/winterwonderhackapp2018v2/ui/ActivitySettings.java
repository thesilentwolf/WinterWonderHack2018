package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cslcteam1.winterwonderhackapp2018v2.R;

import java.util.List;
import java.util.logging.Logger;

public class ActivitySettings extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Intent x;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    x = new Intent( ActivitySettings.this, ActivityMap.class);
                    startActivity( x );
                    return true;
                case R.id.navigation_dashboard:
                    x = new Intent(ActivitySettings.this, ActivityList.class );
                    startActivity( x );
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final Button listAllApps = findViewById(R.id.listAllApps);
        listAllApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivitySettings.this, ActivityAppList.class);
                startActivity(i);
            }
        });
    }

}

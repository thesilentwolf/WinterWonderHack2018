package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {

    private Button bt;
    private ListView lv;
    private ArrayList<String> strArr;
    private ArrayAdapter<String> adapter;
    private int i = 0;
    public void init(){
        ScrollView scrl= (ScrollView)findViewById(R.id.scrollView);
        final LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        scrl.addView(ll);
        bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LinearLayout lh = new LinearLayout(getApplicationContext());
                lh.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

                i++;
                TextView tv=new TextView(getApplicationContext());
                tv.setText("Work" + i);
                tv.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                lh.addView(tv);
                Switch sw = new Switch(getApplicationContext());
                sw.setText("");
                sw.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                lh.addView(sw);
                Button btn = new Button(getApplicationContext());
                btn.setText("...");
                btn.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                lh.addView(btn);
                ll.addView(lh);
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        init();
    }
}

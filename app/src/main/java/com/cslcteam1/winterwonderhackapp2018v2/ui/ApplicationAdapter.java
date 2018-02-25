package com.cslcteam1.winterwonderhackapp2018v2.ui;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cslcteam1.winterwonderhackapp2018v2.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by stevenh214 on 2/24/18.
 */

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> appsList = null;
    private Context context;
    private PackageManager packageManager;

    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<ApplicationInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }

    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.app_list_row, null);
        }

        final ApplicationInfo applicationInfo = appsList.get(position);
        if (null != applicationInfo) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            final TextView packageName = (TextView) view.findViewById(R.id.app_paackage);
            ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);
            ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.app_toggle);

            appName.setText(applicationInfo.loadLabel(packageManager));
            packageName.setText(applicationInfo.packageName);
            iconview.setImageDrawable(applicationInfo.loadIcon(packageManager));
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        ActivityAppList.enabledList.put(applicationInfo.packageName, applicationInfo.packageName);
                    }
                    else {
                        ActivityAppList.enabledList.remove(applicationInfo.packageName);
                    }
                    //Log.d("SETTINGS", "Button: " + applicationInfo.packageName + " Status: " + b);
                }
            });
        }
        return view;
    }

}

package com.cslcteam1.winterwonderhackapp2018v2.services;

import android.app.NotificationManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by stevenh214 on 2/25/18.
 */

public class NotificationService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
//        super.onNotificationPosted(sbn);
        Log.i("SETTINGS","**********  onNotificationPosted");
        Log.i("SETTINGS","ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        NotificationService.this.cancelAllNotifications();
//        NotificationManager.
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("SETTINGS","********** onNOtificationRemoved");
        Log.i("SETTINGS","ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

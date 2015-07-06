package com.dmm.ignorer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dmm.ignorer.configure.Configure;
import com.dmm.ignorer.preferences.MainPreferences;
import com.dmm.ignorer.receivers.CallReceiver;


public class StartForm extends Activity {
    NotificationManager notificationManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_form);

        if (getIntent() != null) {
            cancelNotification();
        }
    }

    @Override
    public void onBackPressed() {
        finalizeClosingActivity();
        super.onBackPressed();
    }

    private void finalizeClosingActivity() {
        cancelNotification();
        setReceiverState(false);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startApp();
                break;
            case R.id.btnStop:
                stopApp();
                break;
            case R.id.btnConfigure:
                configureApp();
                break;
        }
    }

    private void configureApp() {
        Intent i = new Intent(this, Configure.class);
        startActivity(i);
    }

    private void startApp() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Notification notification = buildNotification();
        notificationManager.notify(Globals.NOTIF_CODE, notification);
        setReceiverState(true);
        finish();
    }

    private void stopApp() {
        finalizeClosingActivity();
        finish();
    }

    private void setReceiverState(boolean enable) {
        PackageManager pm = getPackageManager();
        ComponentName compName = new ComponentName(this, CallReceiver.class);
        int operation = enable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        pm.setComponentEnabledSetting(compName, operation, PackageManager.DONT_KILL_APP);
    }

    private Notification buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.phone);
        builder.setContentTitle(getResources().getString(R.string.app_name));
        builder.setContentText(getResources().getString(R.string.notif_text));
        Intent intent2fire = new Intent(this, StartForm.class);
        intent2fire.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        android.support.v4.app.TaskStackBuilder stackBuilder = android.support.v4.app.TaskStackBuilder.create(this);
        stackBuilder.addParentStack(StartForm.class);
        stackBuilder.addNextIntent(intent2fire);

        PendingIntent resultIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);//createPendingResult(Globals.REQ_CODE_SHOW_START_ACTIVITY, intent2fire, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultIntent);
        Notification notif = builder.build();
        notif.flags = Notification.FLAG_NO_CLEAR;
        return notif;
    }

    private void cancelNotification() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.cancel(Globals.NOTIF_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent prefAct = new Intent(this, MainPreferences.class);
            startActivity(prefAct);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

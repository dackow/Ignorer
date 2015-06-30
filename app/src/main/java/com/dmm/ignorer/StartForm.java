package com.dmm.ignorer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                startApp();
                break;
            case R.id.btnStop:
                stopApp();
                break;
        }
    }

    private void startApp() {
        //register receiver
        //...
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Notification notification = buildNotification();
        notificationManager.notify(Globals.NOTIF_CODE, notification);
        finish();
    }

    private void stopApp() {
        //unregister receiver
        cancelNotification();
        finish();
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
        return builder.build();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

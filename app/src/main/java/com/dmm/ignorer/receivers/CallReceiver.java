package com.dmm.ignorer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.dmm.ignorer.Globals;
import com.dmm.ignorer.services.CallIgnorerIntentService;

public class CallReceiver extends BroadcastReceiver {
    public CallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(Globals.TAG, "onReceive!!!!");

        Intent intentService = new Intent(Intent.ACTION_SYNC, null, context, CallIgnorerIntentService.class);
        context.startService(intentService);




        //read options
       // SharedPreferences prefs = context.getSharedPreferences(Globals.PREF_NAME, Context.MODE_PRIVATE);
        //String s = prefs.getString("muteCalls","");
//        muteCallResourcesName = getResources().getResourceEntryName(R.id.muteCalls);
//        ignoreCallsResourcesName = getResources().getResourceEntryName(R.id.ignoreCalls);





    }
}

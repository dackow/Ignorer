package com.dmm.ignorer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dmm.ignorer.Globals;

public class CallReceiver extends BroadcastReceiver {
    public CallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(Globals.TAG, "onReceive!!!!");
    }
}

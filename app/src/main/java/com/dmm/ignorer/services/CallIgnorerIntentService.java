package com.dmm.ignorer.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.dmm.ignorer.R;

/**
 * Created by waldekd on 2015-07-01.
 */
public class CallIgnorerIntentService extends IntentService {

    private String muteCallResourcesName;
    private String ignoreCallsResourcesName;
    private boolean isMuteCall = false;
    private boolean isIgnoreCall = false;

    public CallIgnorerIntentService() {
        super("CallIgnorerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        muteCallResourcesName = getResources().getResourceEntryName(R.id.muteCalls);
        ignoreCallsResourcesName = getResources().getResourceEntryName(R.id.ignoreCalls);

        isMuteCall = prefs.getBoolean(muteCallResourcesName, false);
        isIgnoreCall = prefs.getBoolean(ignoreCallsResourcesName, false);

        final String text = isIgnoreCall ? "Call is being ignored" : isMuteCall ? "Call is being muted" : "";

        Handler handler = new Handler(getMainLooper());

        if (!TextUtils.isEmpty(text)) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                }
            };

            handler.post(run);
        }


    }
}

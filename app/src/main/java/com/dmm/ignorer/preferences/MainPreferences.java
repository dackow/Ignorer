package com.dmm.ignorer.preferences;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.dmm.ignorer.Globals;
import com.dmm.ignorer.R;

public class MainPreferences extends PreferenceActivity {
    private CheckBoxPreference muteCheckBox;
    private CheckBoxPreference ignoreCheckBox;
    private SharedPreferences prefs;

    private String muteCallResourcesName;
    private String ignoreCallsResourcesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_screen);

        muteCallResourcesName = getResources().getResourceEntryName(R.id.muteCalls);
        ignoreCallsResourcesName = getResources().getResourceEntryName(R.id.ignoreCalls);

        muteCheckBox = (CheckBoxPreference) findPreference(muteCallResourcesName);
        ignoreCheckBox = (CheckBoxPreference) findPreference(ignoreCallsResourcesName);

        Context context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        boolean muteCalls = prefs.getBoolean(muteCallResourcesName, false);
        boolean ignoreCalls = prefs.getBoolean(ignoreCallsResourcesName, false);

        if (muteCalls) {
            muteCheckBox.setEnabled(true);
        }

        if (ignoreCalls) {
            ignoreCheckBox.setEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(muteCallResourcesName, muteCheckBox.isChecked());
        editor.putBoolean(ignoreCallsResourcesName, ignoreCheckBox.isChecked());
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

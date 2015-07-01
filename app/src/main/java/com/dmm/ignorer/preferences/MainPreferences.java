package com.dmm.ignorer.preferences;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;

import com.dmm.ignorer.R;

public class MainPreferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private CheckBoxPreference muteCheckBox;
    private CheckBoxPreference ignoreCheckBox;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_screen);

        muteCheckBox = (CheckBoxPreference)findPreference("muteCalls");
        ignoreCheckBox = (CheckBoxPreference)findPreference("ignoreCalls");

        prefs = this.getPreferences(Context.MODE_PRIVATE);

        boolean muteCalls = prefs.getBoolean("muteCalls", false);
        boolean ignoreCalls = prefs.getBoolean("ignoreCalls",false);

        if(muteCalls){
            muteCheckBox.setEnabled(true);
        }

        if(ignoreCalls){
            ignoreCheckBox.setEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        prefs.registerOnSharedPreferenceChangeListener(this);
        super.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("muteCalls", muteCheckBox.isEnabled());
        editor.putBoolean("ignoreCalls", ignoreCheckBox.isEnabled());
        editor.commit();

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences.Editor editor = prefs.edit();
        switch(key){
            case "muteCalls":
                editor.putBoolean("muteCalls", muteCheckBox.isEnabled());
                break;
            case "ignoreCalls":
                editor.putBoolean("ignoreCalls", ignoreCheckBox.isEnabled());
                break;
        }
        editor.commit();
    }
}

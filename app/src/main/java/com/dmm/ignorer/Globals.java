package com.dmm.ignorer;

/**
 * Created by waldekd on 2015-06-30.
 */
public class Globals {
    //Tag
    public static final String TAG = "WDIgnorer";

    //changing this make sure that AndroidManifest.xml will be changed as well
    public static final String ACTION_SHOW_START_ACTIVITY = "com.dmm.ignorer.action.SHOW_START_ACTIVITY";
    public static final String ACTION_SHOW_PREFERENCES = "com.dmm.ignorer.action.ACTION_SHOW_PREFERENCES";

    //request codes
    public static final int REQ_CODE_SHOW_START_ACTIVITY = 0;

    //notification codes
    public static final int NOTIF_CODE = 0;

    //preferences code
    public static final int PREF_CODE = 0;

//    //preferences name
//    public static final String PREF_NAME = "IgnorerPreferences";

    //services
    public static final String CALL_IGNORE_SERVICE_NAME = "CallIgnoreServiceName";

}

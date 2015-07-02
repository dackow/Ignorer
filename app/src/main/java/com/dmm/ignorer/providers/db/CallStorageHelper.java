package com.dmm.ignorer.providers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by waldekd on 2015-07-02.
 */
public class CallStorageHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "comments.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE;
    private static final String DATABASE_DROP;

    static {
        DATABASE_CREATE = "create table " + Contract.TABLE_NAME + " (" + Contract.COLUMN_ID + " integer primary key autoincrement, " + Contract.COLUMN_PHONE_NUMBER + " text not null, " + Contract.COLUMN_COMMENT + " text not null, " + Contract.COLUMN_CATEGORY + " text not null, " + Contract.COLUMN_ACTIVE + " text not null);";
        DATABASE_DROP = "drop table if exists " + Contract.TABLE_NAME;
    }

    public CallStorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }

    public class Contract {
        public static final String TABLE_NAME = "blocked_contacts";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_COMMENT = "comment";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_ACTIVE = "active";
    }

    public static final String[] ALL_COLUMNS = {Contract.COLUMN_ID, Contract.COLUMN_PHONE_NUMBER, Contract.COLUMN_COMMENT, Contract.COLUMN_ACTIVE, Contract.COLUMN_CATEGORY};

}

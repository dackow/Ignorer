package com.dmm.ignorer.providers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dmm.ignorer.domain.CallInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by waldekd on 2015-07-02.
 */
public class CallDBManager {
    private static String[] allColumns;
    private SQLiteDatabase db;
    private CallStorageHelper helper;

    public CallDBManager(Context context) {
        helper = new CallStorageHelper(context);
    }

    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public CallInfo createCallToIgnore(String phone, String comment, String category) {
        ContentValues values = new ContentValues();
        values.put(CallStorageHelper.Contract.COLUMN_PHONE_NUMBER, phone);
        values.put(CallStorageHelper.Contract.COLUMN_COMMENT, comment);
        values.put(CallStorageHelper.Contract.COLUMN_CATEGORY, category);

        long insertId = db.insert(CallStorageHelper.Contract.TABLE_NAME, null, values);

        Cursor cursor = db.query(CallStorageHelper.Contract.TABLE_NAME, allColumns, createQueryById(insertId), null, null, null, null);
        cursor.moveToFirst();

        CallInfo newCallInfo = cursorToCallInfo(cursor);
        cursor.close();
        return newCallInfo;
    }

    public void deleteCallToIgnore(CallInfo call) {
        long id = call.getId();
        db.delete(CallStorageHelper.Contract.TABLE_NAME, createQueryById(id), null);
    }

    public List<CallInfo> getAllCallsToIgnore() {
        List<CallInfo> callInfos = new ArrayList<>();
        Cursor cursor = db.query(CallStorageHelper.Contract.TABLE_NAME, allColumns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                CallInfo info = cursorToCallInfo(cursor);
                callInfos.add(info);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return callInfos;
    }

    public CallInfo getCallToIgnore(long id) {
        CallInfo callInfo = null;
        Cursor cursor = db.query(CallStorageHelper.Contract.TABLE_NAME, allColumns, createQueryById(id), null, null, null, null);

        if (cursor.moveToFirst()) {
            callInfo = cursorToCallInfo(cursor);
        }

        cursor.close();
        return callInfo;
    }

    public boolean updateCallToIgnore(long id, String phone, String comment, String category, boolean enabled) {
        ContentValues values = new ContentValues();
        values.put(CallStorageHelper.Contract.COLUMN_PHONE_NUMBER, phone);
        values.put(CallStorageHelper.Contract.COLUMN_COMMENT, comment);
        values.put(CallStorageHelper.Contract.COLUMN_CATEGORY, category);
        values.put(CallStorageHelper.Contract.COLUMN_ACTIVE, enabled ? "Y" : "N");

        return db.update(CallStorageHelper.Contract.TABLE_NAME, values, createQueryById(id), null) > 0;
    }

    public boolean setCallActive(CallInfo call, boolean enabled) {
        ContentValues args = new ContentValues();
        args.put(CallStorageHelper.Contract.COLUMN_ACTIVE, enabled ? "Y" : "N");
        return db.update(CallStorageHelper.Contract.TABLE_NAME, args, createQueryById(call.getId()), null) > 0;
    }


    private String createQueryById(long id) {
        return String.format("%s = %d", CallStorageHelper.Contract.COLUMN_ID, id);
    }

    //{Contract.COLUMN_ID, Contract.COLUMN_PHONE_NUMBER, Contract.COLUMN_COMMENT, Contract.COLUMN_ACTIVE, Contract.COLUMN_CATEGORY};
    private CallInfo cursorToCallInfo(Cursor cursor) {
        CallInfo callInfo = new CallInfo();
        callInfo.setId(cursor.getLong(0));
        callInfo.setPhone_number(cursor.getString(1));
        callInfo.setComment(cursor.getString(2));
        callInfo.setActive("Y".equals(cursor.getString(3)));
        callInfo.setCategory(cursor.getString(4));
        return callInfo;
    }
}

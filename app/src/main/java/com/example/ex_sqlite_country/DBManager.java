package com.example.ex_sqlite_country;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private MainActivity dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new MainActivity(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(MainActivity.SUBJECT, name);
        contentValue.put(MainActivity.DESC, desc);
        database.insert(MainActivity.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { MainActivity._ID, MainActivity.SUBJECT, MainActivity.DESC };
        Cursor cursor = database.query(MainActivity.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MainActivity.SUBJECT, name);
        contentValues.put(MainActivity.DESC, desc);
        int i = database.update(MainActivity.TABLE_NAME, contentValues, MainActivity._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(MainActivity.TABLE_NAME, MainActivity._ID + "=" + _id, null);
    }
}

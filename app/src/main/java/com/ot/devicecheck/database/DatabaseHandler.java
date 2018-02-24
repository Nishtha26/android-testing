package com.ot.devicecheck.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ot.devicecheck.dataObject.SQLElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjal Gupta on 09-11-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "deviceCheck";

    // Contacts table name
    private static final String TABLE_NAME = "elements";

    // Contacts Table Columns names
    private static final String KEY_ELEMENT = "element";
    private static final String KEY_VERDICT = "verdict";
    private static final String KEY_TIME = "timestamp";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ELEMENT + " TEXT PRIMARY KEY," + KEY_VERDICT + " TEXT,"
                + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addElement(SQLElement element){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ELEMENT, element.getElement());
        values.put(KEY_VERDICT, element.getVerdict());
        values.put(KEY_TIME, element.getTimeStamp());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public List<SQLElement> getAllElements(){
        List<SQLElement> elementList = new ArrayList<SQLElement>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SQLElement element = new SQLElement();
                element.setElement(cursor.getString(0));
                Log.i("db",cursor.getString(0));
                element.setVerdict(cursor.getString(1));
                element.setTimeStamp(cursor.getString(2));
                elementList.add(element);
            } while (cursor.moveToNext());
        }

        return elementList;
    }

    public int updateElement(SQLElement element){
        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ELEMENT,element.getElement());
        values.put(KEY_VERDICT,element.getVerdict());
        values.put(KEY_TIME,element.getTimeStamp());

        return sqldb.update(TABLE_NAME, values, KEY_ELEMENT + "=?",new String[]{String.valueOf(element.getElement())});
    }
}

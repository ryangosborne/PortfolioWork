package com.example.a3_picselect;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class DBAdapter {

    // properties (final) used throughout DBAdapter
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_ALBUM = "album";
    public static final String KEY_RATING = "rating";
    public static final String KEY_COMMENT = "comment";
    public static final String TAG = "DBAdapter";
    public static final String DATABASE_NAME = "MyDB";
    public static final String DATABASE_TABLE = "album_ratings";
    public static final int DATABASE_VERSION = 3;

    // create the table
    private static final String DATABASE_CREATE = "create table album_ratings(_id integer primary key autoincrement,"
            + "album text not null, rating int not null, comment text not null);";
    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    // functionality
    public DBAdapter(Context context){
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    } // end DBAdapter constructor

    public static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(DATABASE_CREATE); // executing database create
            }
            catch(SQLException e){ // throw exception if db can't be created
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } // end onCreate

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrade database from version" + oldVersion + " to " + newVersion +
                    "which will destroy old data.");
            db.execSQL("DROP TABLE IF EXISTS album_ratings");
            onCreate(db);
        } // end onUpgrade
    } // end named inner class Helper


    public DBAdapter open()throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this; // returns writeable database via adapter to DBActivity
    } // end open

    public void close(){
        DBHelper.close();
    } // end close

    // CRUD METHODS
    // insert
    public long insert(String album, int rating, String comment){
        ContentValues cv = new ContentValues(); // contentValues
        cv.put(KEY_ALBUM, album);
        cv.put(KEY_RATING, rating);
        cv.put(KEY_COMMENT, comment);
        return db.insert(DATABASE_TABLE, null, cv);
    } // end insert

    public boolean delete(long rowId){ // not implemented
        return db.delete(DATABASE_TABLE, KEY_ROW_ID+rowId, null) > 0;
    } // end delete

} // end DBAdapter class

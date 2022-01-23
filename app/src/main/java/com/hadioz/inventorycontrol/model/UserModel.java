package com.hadioz.inventorycontrol.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hadioz.inventorycontrol.User;

public class UserModel extends SQLiteOpenHelper {
    public static final String TABLE_USER = "tb_uers";
    public static final String C1_ID = "id";
    public static final String C2_NAME = "name";
    public static final String C3_USERNAME = "username";
    public static final String C4_PASSWORD = "password";
    public static final String C5_POSITION ="position";

    public UserModel(Context context) {
        super(context, "db_user",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                C1_ID + " TEXT PRIMARY KEY, " +
                C2_NAME + " TEXT, " +
                C3_USERNAME + " TEXT, " +
                C4_PASSWORD + " TEXT, " +
                C5_POSITION + " TEXT)";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLE_USER +"';");
        onCreate(db);
    }

    public long InsertUser(String ID, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C1_ID, ID);
        values.put(C3_USERNAME, username);
        values.put(C4_PASSWORD, password);

        long insert = db.insert(TABLE_USER, null, values);
        return insert;
    }

    public void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
    }

    public Cursor getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor =  db.rawQuery(query,null);
        return cursor;
    }


}

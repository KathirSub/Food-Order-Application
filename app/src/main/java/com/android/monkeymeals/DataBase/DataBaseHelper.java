package com.android.monkeymeals.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
/* REFERENCE : https://www.youtube.com/watch?v=8obgNNlj3Eo&t=445s */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "LoginInformation.db";
    public DataBaseHelper(Context context) {
        super(context, "LoginInformation.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table usersInformation(Email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists usersInformation");
    }

    public Boolean insertData(String Email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Email", Email);
        contentValues.put("password", password);
        long result = MyDB.insert("usersInformation", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String Email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersInformation where Email = ?", new String[]{Email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String Email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usersInformation where Email = ? and password = ?", new String[] {Email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
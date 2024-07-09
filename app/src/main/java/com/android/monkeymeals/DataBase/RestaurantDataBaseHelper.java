package com.android.monkeymeals.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

/* REFERENCE : https://www.youtube.com/watch?v=8obgNNlj3Eo&t=445s */
public class RestaurantDataBaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "RestaurantInformation.db";
    public RestaurantDataBaseHelper(Context context) {
        super(context, "RestaurantInformation.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table RestaurantInformation(RestaurantName TEXT primary key, Address TEXT, ImageURL TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists RestaurantInformation");
    }

    public Boolean insertData(String RestaurantName, String Address, String ImageURL){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("RestaurantName", RestaurantName);
        contentValues.put("Address", Address);
        contentValues.put("ImageUrl", ImageURL);
        long result = MyDB.insert("RestaurantInformation", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkRestaurant(String RestaurantName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from RestaurantInformation where RestaurantName = ?", new String[]{RestaurantName});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }




}

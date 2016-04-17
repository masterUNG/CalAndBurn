package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Admin on 2/4/2559.
 */
public class HisFoodData extends AppCompatActivity {

    //Explicit
    private String[] dateAllStrings, dateStrings; // dateAll เวลาทีี่ซ้ำได้


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.his_food_data);

        //Read All SQLite
        readAllSQLite();

    }   // Main Method

    private void readAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.table_calary, null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        dateAllStrings = new String[intCount];
        String[] foodStrings = new String[intCount];
        String[] amountStrings = new String[intCount];
        String[] calFoodStrings = new String[intCount];

        for (int i=0;i<intCount;i++) {
            dateAllStrings[i] = cursor.getString(1);
            cursor.moveToNext();
        }   // for
        cursor.close();

        dateStrings = findDate(dateAllStrings);


    }   // readAllSQLite

    private String[] findDate(String[] dateAllStrings) {

        ArrayList<String> stringArrayList = new ArrayList<String>();
        for (int i=0;i<dateAllStrings.length;i++) {
            stringArrayList.add(dateAllStrings[i]);
        }   // for

        Object[] objects = stringArrayList.toArray();
        for (Object myObj : objects) {

            if (stringArrayList.indexOf(myObj) != stringArrayList.lastIndexOf(myObj)) {
                stringArrayList.remove(stringArrayList.lastIndexOf(myObj));
            }   // if

        }   // for

        Log.d("17April", "ArrayList ==> " + stringArrayList);

        return new String[0];
    }


} // Main Class

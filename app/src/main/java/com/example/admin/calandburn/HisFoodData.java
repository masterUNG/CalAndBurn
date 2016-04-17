package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Admin on 2/4/2559.
 */
public class HisFoodData extends AppCompatActivity {

    //Explicit
    private String[] dateAllStrings, dateStrings; // dateAll เวลาทีี่ซ้ำได้
    private ListView listView;
    private ArrayList<String> stringArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.his_food_data);

        //Bind Widget
        listView = (ListView) findViewById(R.id.calorielistview);

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

        findDate(dateAllStrings);

        //Create ListView
        createListView();


    }   // readAllSQLite

    private void createListView() {

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                

            }   // onItemClick
        });


    }

    private String[] findDate(String[] dateAllStrings) {

        stringArrayList = new ArrayList<String>();
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

package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchFood extends AppCompatActivity {

    //Explicit
    private EditText searchEditText;
    private String searchString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        //Bind Widget
        searchEditText = (EditText) findViewById(R.id.editText11);

    } // Main Method

    public void clickSearch(View view) {

        try {

            searchString = searchEditText.getText().toString().trim();
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE namefood = " + "'" + searchString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[4];
            String price = cursor.getString(2);


        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "หาไม่เจอ", "ไม่มีข้อมูลนี่ใน ฐานข้อมูลของเรา");
        }

    }   // clickSearch

}   // Main Class

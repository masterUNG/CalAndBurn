package com.example.admin.calandburn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Admin on 31/3/2559.
 */
public class EditProfile extends AppCompatActivity {

    //Explicit
    private String[] currentUserStrings;
    private EditText nameEditText, ageEditText, heightEditText, weightEditText;
    private TextView sexTextView, exerciseTextView;
    private RadioGroup sexRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Spinner exerciseSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Bind Widget
        bindWidget();

        //Read All Data
        readAllData();

        //Show View
        showView();

    }   // Main Method

    public void clickSaveEdit(View view) {

    }   // clickSave

    private void showView() {

        nameEditText.setText(currentUserStrings[2]);
        sexTextView.setText("เพศ: " + currentUserStrings[3]);
        ageEditText.setText(currentUserStrings[4]);
        heightEditText.setText(currentUserStrings[5]);
        weightEditText.setText(currentUserStrings[6]);
        exerciseTextView.setText("กิจกรรม: " + findCurrentExercise(currentUserStrings[7]));
    }

    private String findCurrentExercise(String currentUserString) {

        String[] myResultStrings = getResources().getStringArray(R.array.my_act);
        String strResult = myResultStrings[Integer.parseInt(currentUserString)];

        return strResult;
    }

    private void bindWidget() {

        nameEditText = (EditText) findViewById(R.id.editText7);
        ageEditText = (EditText) findViewById(R.id.editText8);
        heightEditText = (EditText) findViewById(R.id.editText9);
        weightEditText = (EditText) findViewById(R.id.editText10);
        sexTextView = (TextView) findViewById(R.id.txtSex);
        exerciseTextView = (TextView) findViewById(R.id.txtExercise);
        sexRadioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        maleRadioButton = (RadioButton) findViewById(R.id.radioButton3);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton4);
        exerciseSpinner = (Spinner) findViewById(R.id.spinner2);

    }   // bindWidget

    private void readAllData() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();
        currentUserStrings = new String[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            currentUserStrings[i] = cursor.getString(i);
        }       // for
        cursor.close();


    }   // readAll

}   // Main Class

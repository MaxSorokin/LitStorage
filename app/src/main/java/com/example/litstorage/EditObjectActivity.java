package com.example.litstorage;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class EditObjectActivity extends AppCompatActivity {

    //region DatePicker variables
    private static final String TAG = "EditObjectActivity";

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    //endregion

    String[] locations = {"Ломоносовский", "Профсоюзная", "Кржижановского", "Библиотека"};
    String[] types = {"Компьютеры и мониторы", "Периферия"};
    String[] subTypes = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_object);

        //region LocationSpinner
        Spinner spinner = (Spinner) findViewById(R.id.location);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //endregion

        //region DatePicker
        displayDate = (TextView) findViewById(R.id.datePicker);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditObjectActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: date:dd/mm/yyyy " + dayOfMonth + "." + month + "." + year);

                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setTextColor(Color.BLACK);
                displayDate.setText(" Дата актуализации:" + date);
            }
        };
        //endregion

        
    }
}

package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.fai.tngciremai.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add_book extends AppCompatActivity {
Spinner jumlah_peserta;
EditText tanggal_keberangkatan;
private DatePickerDialog datePickerDialog;
private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        jumlah_peserta = (Spinner) findViewById(R.id.jumlah_peserta);
        tanggal_keberangkatan = (EditText) findViewById(R.id.tanggal_berangkat);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tanggal_keberangkatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(Add_book.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tanggal_keberangkatan.setText(dateFormatter.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

    }
}
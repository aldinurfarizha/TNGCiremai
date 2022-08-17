package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fai.tngciremai.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
public class Add_book extends AppCompatActivity {
Spinner jumlah_peserta, jasa_porter;
EditText tanggal_keberangkatan;
Button lanjut;
Integer total_peserta;
private DatePickerDialog datePickerDialog;
private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        jumlah_peserta = (Spinner) findViewById(R.id.jumlah_peserta);
        tanggal_keberangkatan = (EditText) findViewById(R.id.tanggal_berangkat);
        jasa_porter=(Spinner)findViewById(R.id.jasa_porter);
        lanjut=(Button)findViewById(R.id.btn_lanjut);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jumlah_peserta.getSelectedItem().toString().equals("--Pilih--")){
                    Toast.makeText(Add_book.this, "Jumlah Peserta Belum di pilih !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(tanggal_keberangkatan.getText().toString().isEmpty()){
                    Toast.makeText(Add_book.this, "Tanggal Belum di pilih", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(jasa_porter.getSelectedItem().toString().equals("--Pilih--")){
                    Toast.makeText(Add_book.this, "Jasa Porter Belum di pilih", Toast.LENGTH_SHORT).show();
                    return;
                }
                total_peserta=Integer.parseInt(jumlah_peserta.getSelectedItem().toString());
                if(total_peserta < 4 && jasa_porter.getSelectedItem().toString().equals("Tidak")){
                    Toast.makeText(Add_book.this, "Peserta Kurang dari 4 Wajib memilih Porter", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date datekeberangkatan= null;
                try {
                    datekeberangkatan = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal_keberangkatan.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date= new Date();
                String datesekar = new SimpleDateFormat("yyyy-MM-dd").format(date);
                Date datesekarang= null;
                try {
                    datesekarang = new SimpleDateFormat("yyyy-MM-dd").parse(datesekar);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(datesekar.equals(tanggal_keberangkatan.getText().toString())){
                    Toast.makeText(Add_book.this, "Minimal booking H-1", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(datekeberangkatan.before(datesekarang)){
                    Toast.makeText(Add_book.this, "Tidak bisa memilih tanggal di masa lampau", Toast.LENGTH_SHORT).show();
                    return;
                }


                date.setTime(date.getTime() + 30L * 24 * 60 * 60 * 1000);
                String date30 = new SimpleDateFormat("yyyy-MM-dd").format(date);
                Date dateafter30= null;
                try {
                    dateafter30 = new SimpleDateFormat("yyyy-MM-dd").parse(date30);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(datekeberangkatan.after(dateafter30)){
                    Toast.makeText(Add_book.this, "Maximal booking H-30", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Add_book.this, "mangkat", Toast.LENGTH_SHORT).show();
            }
        });
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
package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fai.tngciremai.Model.Credential;
import com.fai.tngciremai.Util.SharedPrefManager;
import com.fai.tngciremai.booking.Add_book;
import com.fai.tngciremai.booking.Term;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Dashboard extends AppCompatActivity {
CardView booking, porter,riwayat;
TextView nama_lengkap;
Credential credential;
ImageView btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        booking=(CardView) findViewById(R.id.btn_booking);
        nama_lengkap=(TextView)findViewById(R.id.nama_lengkap);
        btn_logout=(ImageView)findViewById(R.id.btn_logout);
        porter=(CardView)findViewById(R.id.btn_porter);
        riwayat=(CardView)findViewById(R.id.btn_riwayat);
        credential = SharedPrefManager.getInstance(this).get();
        nama_lengkap.setText(credential.getNama_lengkap());
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RiwayatBooking.class));
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Dashboard.this, SweetAlertDialog.ERROR_TYPE)
                        .setContentText("Anda ingin Logout?")
                        .setConfirmText("Ya")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                finish();
                                SharedPrefManager.getInstance(getApplicationContext()).logout();
                            }
                        })
                        .show();
            }
        });
        porter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PorterList.class));
            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Term.class));
            }
        });
    }
}
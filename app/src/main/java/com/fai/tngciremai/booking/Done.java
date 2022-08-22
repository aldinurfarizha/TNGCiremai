package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fai.tngciremai.Dashboard;
import com.fai.tngciremai.R;

public class Done extends AppCompatActivity {
Button oke;
TextView kode_booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        kode_booking=(TextView)findViewById(R.id.kode_boking);
        Intent intent = getIntent();
        kode_booking.setText(intent.getExtras().getString("kode_keberangkatan"));
        oke=(Button) findViewById(R.id.btn_oke);
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
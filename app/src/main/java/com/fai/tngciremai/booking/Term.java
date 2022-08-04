package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.fai.tngciremai.R;

public class Term extends AppCompatActivity {
Button lanjut;
CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        lanjut =(Button) findViewById(R.id.btn_lanjut);
        checkBox = (CheckBox)findViewById(R.id.check);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    startActivity(new Intent(getApplicationContext(), Add_book.class));
                }else{
                    Toast.makeText(Term.this, "Anda Harus Menyetujui Aturan Yang berlaku", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
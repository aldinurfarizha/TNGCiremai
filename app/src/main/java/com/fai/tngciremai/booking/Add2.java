package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Dashboard;
import com.fai.tngciremai.Login;
import com.fai.tngciremai.Model.Credential;
import com.fai.tngciremai.R;
import com.fai.tngciremai.Util.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Add2 extends AppCompatActivity {
Spinner jenis_identitas1,jenis_identitas2;
Spinner jenis_kelamin1, jenis_kelamin2;
EditText nomor_identitas1, nomor_identitas2;
EditText nama_lengkap1, nama_lengkap2;
EditText nomor_telepon1, nomor_telepon2;
EditText alamat1, alamat2;
Button selesai;

Credential credential;
String tanggal_berangkat, jumlah_peserta, id_porter;
JSONObject dataPeserta;
JSONObject dataToSend;
ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        jenis_identitas1=(Spinner) findViewById(R.id.jenis_identitas1);
        jenis_identitas2=(Spinner) findViewById(R.id.jenis_identitas2);

        jenis_kelamin1=(Spinner) findViewById(R.id.jenis_kelamin1);
        jenis_kelamin2=(Spinner) findViewById(R.id.jenis_kelamin2);

        nomor_identitas1=(EditText) findViewById(R.id.nomor_identitas1);
        nomor_identitas2=(EditText) findViewById(R.id.nomor_identitas2);

        nama_lengkap1=(EditText) findViewById(R.id.nama_lengkap1);
        nama_lengkap2=(EditText) findViewById(R.id.nama_lengkap2);

        nomor_telepon1=(EditText) findViewById(R.id.nomor_telepon1);
        nomor_telepon2=(EditText) findViewById(R.id.nomor_telepon2);

        alamat1=(EditText) findViewById(R.id.alamat1);
        alamat2=(EditText) findViewById(R.id.alamat2);

        selesai=(Button) findViewById(R.id.btn_selesai);
        credential = SharedPrefManager.getInstance(this).get();
        dataPeserta = new JSONObject();
        dataToSend = new JSONObject();
        Intent intent = getIntent();
        tanggal_berangkat=intent.getExtras().getString("tanggal_berangkat");
        jumlah_peserta=intent.getExtras().getString("jumlah_peserta");
        id_porter=intent.getExtras().getString("id_porter");
        dialog=new ProgressDialog(Add2.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        AndroidNetworking.initialize(getApplicationContext());
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKeberangkatan();
            }
        });
    }
    public void addKeberangkatan(){

        if(jenis_identitas1.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Identitas 1 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_identitas2.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Identitas 2 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }


        if(TextUtils.isEmpty(nomor_identitas1.getText().toString())){
            nomor_identitas1.setError("Kolom ini wajib di isi");
            nomor_identitas1.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_identitas2.getText().toString())){
            nomor_identitas2.setError("Kolom ini wajib di isi");
            nomor_identitas2.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(nama_lengkap1.getText().toString())){
            nama_lengkap1.setError("Kolom ini wajib di isi");
            nama_lengkap1.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nama_lengkap2.getText().toString())){
            nama_lengkap2.setError("Kolom ini wajib di isi");
            nama_lengkap2.requestFocus();
            return;
        }


        if(jenis_kelamin1.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Kelamin 1 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_kelamin2.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Kelamin 2 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }


        if(TextUtils.isEmpty(nomor_telepon1.getText().toString())){
            nomor_telepon1.setError("Kolom ini wajib di isi");
            nomor_telepon1.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_telepon2.getText().toString())){
            nomor_telepon2.setError("Kolom ini wajib di isi");
            nomor_telepon2.requestFocus();
            return;
        }


        if(TextUtils.isEmpty(alamat1.getText().toString())){
            alamat1.setError("Kolom ini wajib di isi");
            alamat1.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(alamat2.getText().toString())){
            alamat2.setError("Kolom ini wajib di isi");
            alamat2.requestFocus();
            return;
        }
        dialog.show();
        AndroidNetworking.post(ServerUrl.ADD_KEBERANGKATAN)
                .addBodyParameter("tanggal_berangkat", tanggal_berangkat)
                .addBodyParameter("iduser", credential.getIduser())
                .addBodyParameter("id_porter", id_porter)
                .addBodyParameter("jumlah_peserta", jumlah_peserta)

                .addBodyParameter("jenis_identitas1", jenis_identitas1.getSelectedItem().toString())
                .addBodyParameter("jenis_identitas2", jenis_identitas2.getSelectedItem().toString())

                .addBodyParameter("jenis_kelamin1", jenis_kelamin1.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin2", jenis_kelamin2.getSelectedItem().toString())

                .addBodyParameter("no_identitas1", nomor_identitas1.getText().toString())
                .addBodyParameter("no_identitas2", nomor_identitas2.getText().toString())

                .addBodyParameter("nama_lengkap1", nama_lengkap1.getText().toString())
                .addBodyParameter("nama_lengkap2", nama_lengkap2.getText().toString())

                .addBodyParameter("no_identitas1", nomor_identitas1.getText().toString())
                .addBodyParameter("no_identitas2", nomor_identitas2.getText().toString())

                .addBodyParameter("nomor_telp1", nomor_telepon1.getText().toString())
                .addBodyParameter("nomor_telp2", nomor_telepon2.getText().toString())

                .addBodyParameter("alamat1", alamat1.getText().toString())
                .addBodyParameter("alamat2", alamat2.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        try {
                            String message = response.getString("message");
                            if(response.getBoolean("status")){
                                Intent intent = new Intent(getApplicationContext(), Done.class);
                                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                                final String kode_keberangkatan = response.getString("kode_keberangkatan");
                                intent.putExtra("kode_keberangkatan", kode_keberangkatan);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal, Koneksi bermasalah", Toast.LENGTH_LONG).show();
                        Log.e("error:", error.toString());
                    }
                });
    }

}
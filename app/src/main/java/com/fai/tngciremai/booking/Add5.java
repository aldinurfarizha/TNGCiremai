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

public class Add5 extends AppCompatActivity {
    Spinner jenis_identitas1,jenis_identitas2, jenis_identitas3, jenis_identitas4, jenis_identitas5;
    Spinner jenis_kelamin1, jenis_kelamin2, jenis_kelamin3, jenis_kelamin4, jenis_kelamin5;
    EditText nomor_identitas1, nomor_identitas2, nomor_identitas3, nomor_identitas4, nomor_identitas5;
    EditText nama_lengkap1, nama_lengkap2, nama_lengkap3, nama_lengkap4, nama_lengkap5;
    EditText nomor_telepon1, nomor_telepon2, nomor_telepon3, nomor_telepon4, nomor_telepon5;
    EditText alamat1, alamat2, alamat3, alamat4, alamat5;
    Button selesai;

    Credential credential;
    String tanggal_berangkat, jumlah_peserta, id_porter;
    JSONObject dataPeserta;
    JSONObject dataToSend;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add5);
        jenis_identitas1=(Spinner) findViewById(R.id.jenis_identitas1);
        jenis_kelamin1=(Spinner) findViewById(R.id.jenis_kelamin1);
        nomor_identitas1=(EditText) findViewById(R.id.nomor_identitas1);
        nama_lengkap1=(EditText) findViewById(R.id.nama_lengkap1);
        nomor_telepon1=(EditText) findViewById(R.id.nomor_telepon1);
        alamat1=(EditText) findViewById(R.id.alamat1);


        jenis_identitas2=(Spinner) findViewById(R.id.jenis_identitas2);
        jenis_kelamin2=(Spinner) findViewById(R.id.jenis_kelamin2);
        nomor_identitas2=(EditText) findViewById(R.id.nomor_identitas2);
        nama_lengkap2=(EditText) findViewById(R.id.nama_lengkap2);
        nomor_telepon2=(EditText) findViewById(R.id.nomor_telepon2);
        alamat2=(EditText) findViewById(R.id.alamat2);


        jenis_identitas3=(Spinner) findViewById(R.id.jenis_identitas3);
        jenis_kelamin3=(Spinner) findViewById(R.id.jenis_kelamin3);
        nomor_identitas3=(EditText) findViewById(R.id.nomor_identitas3);
        nama_lengkap3=(EditText) findViewById(R.id.nama_lengkap3);
        nomor_telepon3=(EditText) findViewById(R.id.nomor_telepon3);
        alamat3=(EditText) findViewById(R.id.alamat3);


        jenis_identitas4=(Spinner) findViewById(R.id.jenis_identitas4);
        jenis_kelamin4=(Spinner) findViewById(R.id.jenis_kelamin4);
        nomor_identitas4=(EditText) findViewById(R.id.nomor_identitas4);
        nama_lengkap4=(EditText) findViewById(R.id.nama_lengkap4);
        nomor_telepon4=(EditText) findViewById(R.id.nomor_telepon4);
        alamat4=(EditText) findViewById(R.id.alamat4);


        jenis_identitas5=(Spinner) findViewById(R.id.jenis_identitas5);
        jenis_kelamin5=(Spinner) findViewById(R.id.jenis_kelamin5);
        nomor_identitas5=(EditText) findViewById(R.id.nomor_identitas5);
        nama_lengkap5=(EditText) findViewById(R.id.nama_lengkap5);
        nomor_telepon5=(EditText) findViewById(R.id.nomor_telepon5);
        alamat5=(EditText) findViewById(R.id.alamat5);


        selesai=(Button) findViewById(R.id.btn_selesai);
        credential = SharedPrefManager.getInstance(this).get();
        dataPeserta = new JSONObject();
        dataToSend = new JSONObject();
        Intent intent = getIntent();
        tanggal_berangkat=intent.getExtras().getString("tanggal_berangkat");
        jumlah_peserta=intent.getExtras().getString("jumlah_peserta");
        id_porter=intent.getExtras().getString("id_porter");
        dialog=new ProgressDialog(Add5.this);
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
        if(jenis_identitas3.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Identitas 3 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_identitas4.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Identitas 4 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_identitas5.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Identitas 5 Belum di isi", Toast.LENGTH_SHORT).show();
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
        if(TextUtils.isEmpty(nomor_identitas3.getText().toString())){
            nomor_identitas3.setError("Kolom ini wajib di isi");
            nomor_identitas3.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_identitas4.getText().toString())){
            nomor_identitas4.setError("Kolom ini wajib di isi");
            nomor_identitas4.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_identitas5.getText().toString())){
            nomor_identitas5.setError("Kolom ini wajib di isi");
            nomor_identitas5.requestFocus();
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
        if(TextUtils.isEmpty(nama_lengkap3.getText().toString())){
            nama_lengkap3.setError("Kolom ini wajib di isi");
            nama_lengkap3.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nama_lengkap4.getText().toString())){
            nama_lengkap4.setError("Kolom ini wajib di isi");
            nama_lengkap4.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nama_lengkap5.getText().toString())){
            nama_lengkap5.setError("Kolom ini wajib di isi");
            nama_lengkap5.requestFocus();
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
        if(jenis_kelamin3.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Kelamin 3 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_kelamin4.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Kelamin 4 Belum di isi", Toast.LENGTH_SHORT).show();
            return;
        }
        if(jenis_kelamin5.getSelectedItem().toString().equals("--Pilih--")){
            Toast.makeText(this, "Jenis Kelamin 5 Belum di isi", Toast.LENGTH_SHORT).show();
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
        if(TextUtils.isEmpty(nomor_telepon3.getText().toString())){
            nomor_telepon3.setError("Kolom ini wajib di isi");
            nomor_telepon3.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_telepon4.getText().toString())){
            nomor_telepon4.setError("Kolom ini wajib di isi");
            nomor_telepon4.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(nomor_telepon5.getText().toString())){
            nomor_telepon5.setError("Kolom ini wajib di isi");
            nomor_telepon5.requestFocus();
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
        if(TextUtils.isEmpty(alamat3.getText().toString())){
            alamat3.setError("Kolom ini wajib di isi");
            alamat3.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(alamat4.getText().toString())){
            alamat4.setError("Kolom ini wajib di isi");
            alamat4.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(alamat5.getText().toString())){
            alamat5.setError("Kolom ini wajib di isi");
            alamat5.requestFocus();
            return;
        }
        dialog.show();
        AndroidNetworking.post(ServerUrl.ADD_KEBERANGKATAN)
                .addBodyParameter("tanggal_berangkat", tanggal_berangkat)
                .addBodyParameter("iduser", credential.getIduser())
                .addBodyParameter("id_porter", id_porter)
                .addBodyParameter("jumlah_peserta", jumlah_peserta)

                .addBodyParameter("jenis_identitas1", jenis_identitas1.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin1", jenis_kelamin1.getSelectedItem().toString())
                .addBodyParameter("no_identitas1", nomor_identitas1.getText().toString())
                .addBodyParameter("nama_lengkap1", nama_lengkap1.getText().toString())
                .addBodyParameter("no_identitas1", nomor_identitas1.getText().toString())
                .addBodyParameter("nomor_telp1", nomor_telepon1.getText().toString())
                .addBodyParameter("alamat1", alamat1.getText().toString())


                .addBodyParameter("jenis_identitas2", jenis_identitas2.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin2", jenis_kelamin2.getSelectedItem().toString())
                .addBodyParameter("no_identitas2", nomor_identitas2.getText().toString())
                .addBodyParameter("nama_lengkap2", nama_lengkap2.getText().toString())
                .addBodyParameter("no_identitas2", nomor_identitas2.getText().toString())
                .addBodyParameter("nomor_telp2", nomor_telepon2.getText().toString())
                .addBodyParameter("alamat2", alamat2.getText().toString())



                .addBodyParameter("jenis_identitas3", jenis_identitas3.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin3", jenis_kelamin3.getSelectedItem().toString())
                .addBodyParameter("no_identitas3", nomor_identitas3.getText().toString())
                .addBodyParameter("nama_lengkap3", nama_lengkap3.getText().toString())
                .addBodyParameter("no_identitas3", nomor_identitas3.getText().toString())
                .addBodyParameter("nomor_telp3", nomor_telepon3.getText().toString())
                .addBodyParameter("alamat3", alamat3.getText().toString())


                .addBodyParameter("jenis_identitas4", jenis_identitas4.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin4", jenis_kelamin4.getSelectedItem().toString())
                .addBodyParameter("no_identitas4", nomor_identitas4.getText().toString())
                .addBodyParameter("nama_lengkap4", nama_lengkap4.getText().toString())
                .addBodyParameter("no_identitas4", nomor_identitas4.getText().toString())
                .addBodyParameter("nomor_telp4", nomor_telepon4.getText().toString())
                .addBodyParameter("alamat4", alamat4.getText().toString())


                .addBodyParameter("jenis_identitas5", jenis_identitas5.getSelectedItem().toString())
                .addBodyParameter("jenis_kelamin5", jenis_kelamin5.getSelectedItem().toString())
                .addBodyParameter("no_identitas5", nomor_identitas5.getText().toString())
                .addBodyParameter("nama_lengkap5", nama_lengkap5.getText().toString())
                .addBodyParameter("no_identitas5", nomor_identitas5.getText().toString())
                .addBodyParameter("nomor_telp5", nomor_telepon5.getText().toString())
                .addBodyParameter("alamat5", alamat5.getText().toString())



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
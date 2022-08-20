package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Dashboard;
import com.fai.tngciremai.Model.Credential;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.PorterList;
import com.fai.tngciremai.R;
import com.fai.tngciremai.Util.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CekStatusPorter extends AppCompatActivity {
ProgressDialog dialog;
String tanggal_berangkat,jumlah_peserta, nama_Porter, id_porter, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_status_porter);

        Intent intent = getIntent();
        tanggal_berangkat=intent.getExtras().getString("tanggal_berangkat");
        jumlah_peserta=intent.getExtras().getString("jumlah_peserta");
        nama_Porter=intent.getExtras().getString("nama_porter");
        id_porter=intent.getExtras().getString("id_porter");
        dialog=new ProgressDialog(CekStatusPorter.this);
        dialog.setMessage("Mengecek Jadwal Porter "+nama_Porter);
        dialog.setCancelable(false);
        AndroidNetworking.initialize(getApplicationContext());
        cekPorter();
    }
    public void cekPorter(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.CEK_PORTER)
                .addBodyParameter("tanggal_berangkat", tanggal_berangkat)
                .addBodyParameter("id_porter", id_porter)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hide();
                        try {
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            message = response.getString("message");
                            if(response.getBoolean("status")){
                                new SweetAlertDialog(CekStatusPorter.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setContentText("Porter "+nama_Porter+" Tersedia")
                                        .setConfirmText("Lanjut")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                add_person();
                                            }
                                        })
                                        .show();
                            }else{
                                new SweetAlertDialog(CekStatusPorter.this, SweetAlertDialog.ERROR_TYPE)
                                        .setContentText("Porter "+nama_Porter+" Tidak tersedia, Karena "+message)
                                        .setConfirmText("Ya")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                finish();
                                            }
                                        })
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error:", error.toString());
                    }
                });
    }
    public void add_person(){
        switch(jumlah_peserta) {
            case "2":
                Intent intent = new Intent(getApplicationContext(), Add2.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_porter", id_porter);
                intent.putExtra("tanggal_berangkat", tanggal_berangkat);
                intent.putExtra("jumlah_peserta", jumlah_peserta);
                startActivity(intent);
                break;
            case "3":
                // code block
                break;
        }
    }
}
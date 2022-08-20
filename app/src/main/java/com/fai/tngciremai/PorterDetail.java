package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Adapter.SertifikatPorterAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.Model.SertifikatPorterModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PorterDetail extends AppCompatActivity {
    ProgressDialog dialog;
    SertifikatPorterAdapter sertifikatPorterAdapter;
    RecyclerView recyclerView;
    ArrayList<SertifikatPorterModel> sertifikatPorterModels;
    TextView nama, frequensi, tahun_pengalaman, no_sertifikat;
    ImageView imageView;
    String id_porter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_detail);
        Intent intent = getIntent();
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        nama=(TextView)findViewById(R.id.nama);
        frequensi=(TextView)findViewById(R.id.frequensi);
        tahun_pengalaman=(TextView)findViewById(R.id.tahun_penglaman);
        imageView=(ImageView)findViewById(R.id.image_view);
        no_sertifikat=(TextView)findViewById(R.id.no_sertifikat);
        dialog=new ProgressDialog(PorterDetail.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        sertifikatPorterModels = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        id_porter=intent.getExtras().getString("id");
        nama.setText(intent.getExtras().getString("nama_lengkap"));
        frequensi.setText(intent.getExtras().getString("frequensi")+" Kali Booked");
        tahun_pengalaman.setText(intent.getExtras().getString("tahun_pengalaman")+" Tahun Pengalaman");
        getData();
        Picasso.get()
                .load(intent.getExtras().getString("foto"))
                .into(imageView);
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.SERTIFIKAT_PORTER)
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
                            JSONArray array = jsonObject.getJSONArray("data");
                            if(array.length()==0){
                                nodata();
                                return;
                            }
                            for (int i=0; i<array.length(); i++){
                                JSONObject ob=array.getJSONObject(i);
                                SertifikatPorterModel sertifikatPorterModel=new SertifikatPorterModel(ob.getString("nama_sertifikasi"),ob.getString("tgl_sertifikasi"));
                                sertifikatPorterModels.add(sertifikatPorterModel);
                            }
                            sertifikatPorterAdapter = new SertifikatPorterAdapter(sertifikatPorterModels);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PorterDetail.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(sertifikatPorterAdapter);
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
    public void nodata(){
        no_sertifikat.setVisibility(View.VISIBLE);
    }
}
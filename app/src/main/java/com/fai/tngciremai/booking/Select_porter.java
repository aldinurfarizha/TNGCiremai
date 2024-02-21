package com.fai.tngciremai.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Adapter.PorterPilihAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.Model.PorterPilihModel;
import com.fai.tngciremai.PorterList;
import com.fai.tngciremai.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Select_porter extends AppCompatActivity {
    ProgressDialog dialog;
    PorterPilihAdapter porterPilihAdapter;
    RecyclerView recyclerView;
    ArrayList<PorterPilihModel> porterPilihModels;
    String tanggal_berangkat, jumlah_peserta;
    LinearLayout hasPorter,noPorter;
    Button btnLanjutkan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_porter);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        hasPorter=(LinearLayout)findViewById(R.id.has_porter);
        noPorter=(LinearLayout)findViewById(R.id.no_porter);
        btnLanjutkan=(Button)findViewById(R.id.btn_lanjutkan);
        Intent intent = getIntent();
        tanggal_berangkat=intent.getExtras().getString("tanggal_berangkat");
        jumlah_peserta=intent.getExtras().getString("jumlah_peserta");
        dialog=new ProgressDialog(Select_porter.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        porterPilihModels = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        getData();
        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("PORTER", getSelectedPorters().toString());
            }
        });
    }
    public ArrayList<String> getSelectedPorters() {
        if (porterPilihAdapter != null) {
            return porterPilihAdapter.getSelectedPorters();
        }
        return new ArrayList<>();
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.LIST_PORTER)
                .addBodyParameter("date", tanggal_berangkat)
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
                                noPorter.setVisibility(View.VISIBLE);
                            }else{
                                hasPorter.setVisibility(View.VISIBLE);
                                for (int i=0; i<array.length(); i++){
                                    JSONObject ob=array.getJSONObject(i);
                                    PorterPilihModel porterPilihModel=new PorterPilihModel(ob.getString("id_porter"),ob.getString("nama_porter"), ob.getString("foto"), ob.getString("tahun_pengalaman"), ob.getString("frequensi_porter"), tanggal_berangkat, jumlah_peserta);
                                    porterPilihModels.add(porterPilihModel);
                                }
                                porterPilihAdapter = new PorterPilihAdapter(porterPilihModels);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Select_porter.this, LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(porterPilihAdapter);
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
}
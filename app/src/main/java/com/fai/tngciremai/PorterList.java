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
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PorterModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PorterList extends AppCompatActivity {
ProgressDialog dialog;
    PorterAdapter porterAdapter;
    RecyclerView recyclerView;
    ArrayList<PorterModel> porterModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_list);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        dialog=new ProgressDialog(PorterList.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        porterModels = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        getData();
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.LIST_PORTER)
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
                            for (int i=0; i<array.length(); i++){
                                JSONObject ob=array.getJSONObject(i);
                                PorterModel porterModel=new PorterModel(ob.getString("id_porter"),ob.getString("nama_porter"), ob.getString("foto"), ob.getString("tahun_pengalaman"), ob.getString("frequensi_porter"));
                                porterModels.add(porterModel);
                            }
                            porterAdapter = new PorterAdapter(porterModels);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PorterList.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(porterAdapter);
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
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
                if(getSelectedPorters().size()==0){
                     Toast.makeText(getApplicationContext(), "Minimal Pilih 1 Porter", Toast.LENGTH_LONG).show();
                } else if(getSelectedPorters().size()>=5){
                    Toast.makeText(getApplicationContext(), "Maximal 5 Porter", Toast.LENGTH_LONG).show();
                }
                else {
                    switch(jumlah_peserta) {
                        case "2":
                            Intent intent = new Intent(getApplicationContext(), Add2.class);
                            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("id_porter", getSelectedPorters().toString());
                            intent.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent);
                            break;
                        case "3":
                            Intent intent3 = new Intent(getApplicationContext(), Add3.class);
                            intent3.setFlags(intent3.FLAG_ACTIVITY_NEW_TASK);
                            intent3.putExtra("id_porter", getSelectedPorters().toString());
                            intent3.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent3.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent3);
                            break;
                        case "4":
                            Intent intent4 = new Intent(getApplicationContext(), Add4.class);
                            intent4.setFlags(intent4.FLAG_ACTIVITY_NEW_TASK);
                            intent4.putExtra("id_porter", getSelectedPorters().toString());
                            intent4.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent4.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent4);
                            break;
                        case "5":
                            Intent intent5 = new Intent(getApplicationContext(), Add5.class);
                            intent5.setFlags(intent5.FLAG_ACTIVITY_NEW_TASK);
                            intent5.putExtra("id_porter", getSelectedPorters().toString());
                            intent5.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent5.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent5);
                            break;
                        case "6":
                            Intent intent6 = new Intent(getApplicationContext(), Add6.class);
                            intent6.setFlags(intent6.FLAG_ACTIVITY_NEW_TASK);
                            intent6.putExtra("id_porter", getSelectedPorters().toString());
                            intent6.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent6.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent6);
                            break;
                        case "7":
                            Intent intent7 = new Intent(getApplicationContext(), Add7.class);
                            intent7.setFlags(intent7.FLAG_ACTIVITY_NEW_TASK);
                            intent7.putExtra("id_porter", getSelectedPorters().toString());
                            intent7.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent7.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent7);
                            break;
                        case "8":
                            Intent intent8 = new Intent(getApplicationContext(), Add8.class);
                            intent8.setFlags(intent8.FLAG_ACTIVITY_NEW_TASK);
                            intent8.putExtra("id_porter", getSelectedPorters().toString());
                            intent8.putExtra("tanggal_berangkat", tanggal_berangkat);
                            intent8.putExtra("jumlah_peserta", jumlah_peserta);
                            startActivity(intent8);
                            break;
                    }
                }
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
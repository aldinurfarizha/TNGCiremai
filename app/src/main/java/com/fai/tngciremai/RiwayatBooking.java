package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.BookingAdapter;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.Credential;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.Model.RiwayatBookingModel;
import com.fai.tngciremai.Util.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RiwayatBooking extends AppCompatActivity {
    ProgressDialog dialog;
    BookingAdapter bookingAdapter;
    RecyclerView recyclerView;
    ArrayList<RiwayatBookingModel> riwayatBookingModels;
    Credential credential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_booking);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        dialog=new ProgressDialog(RiwayatBooking.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        riwayatBookingModels = new ArrayList<>();
        credential = SharedPrefManager.getInstance(this).get();
        AndroidNetworking.initialize(getApplicationContext());
        getData();
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.RIWAYAT_BOOK)
                .addBodyParameter("iduser", credential.getIduser())
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
                                RiwayatBookingModel riwayatBookingModel=new RiwayatBookingModel(ob.getString("kode_keberangkatan"),ob.getString("tanggal_berangkat"), ob.getString("nama_lengkap"), ob.getString("jumlah_pembayaran"), ob.getString("status_keberangkatan"), ob.getString("biaya_tiket"),ob.getString("biaya_porter"), ob.getString("status_pembayaran") );
                                riwayatBookingModels.add(riwayatBookingModel);
                            }
                            bookingAdapter = new BookingAdapter(riwayatBookingModels);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RiwayatBooking.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(bookingAdapter);
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
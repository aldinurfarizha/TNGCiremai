package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Adapter.PesertaAdapter;
import com.fai.tngciremai.Adapter.PorterAdapter;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PesertaModel;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.booking.CekStatusPorter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class DetailRiwayat extends AppCompatActivity {
TextView tanggal_berangkat, nama_porter, biaya_tiket, biaya_porter, jumlah_pembayaran, status, kode_keberangkatan;
ImageView qrcode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String kode_keberangkatans;
    ProgressDialog dialog;
    PesertaAdapter pesertaAdapter;
    RecyclerView recyclerView;
    ArrayList<PesertaModel> pesertaModels;
    Button batal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat);
        tanggal_berangkat=(TextView) findViewById(R.id.tanggal_berangkat);
        nama_porter=(TextView) findViewById(R.id.nama_porter);
        biaya_tiket=(TextView) findViewById(R.id.biaya_tiket);
        biaya_porter=(TextView) findViewById(R.id.biaya_porter);
        jumlah_pembayaran=(TextView) findViewById(R.id.total_pembayaran);
        status=(TextView) findViewById(R.id.status);
        kode_keberangkatan=(TextView) findViewById(R.id.kode_keberangkatan);
        qrcode=(ImageView) findViewById(R.id.qrcode);
        batal=(Button)findViewById(R.id.btn_batal);
        Intent intent = getIntent();
        kode_keberangkatans=intent.getExtras().getString("id");
        kode_keberangkatan.setText(intent.getExtras().getString("id"));
        nama_porter.setText(intent.getExtras().getString("nama_porter"));
        tanggal_berangkat.setText(intent.getExtras().getString("tanggal_berangkat"));
        biaya_tiket.setText(intent.getExtras().getString("biaya_tiket"));
        biaya_porter.setText(intent.getExtras().getString("biaya_porter"));
        status.setText(intent.getExtras().getString("status"));
        jumlah_pembayaran.setText(intent.getExtras().getString("total_biaya"));
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        dialog=new ProgressDialog(DetailRiwayat.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        pesertaModels = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());
        generateQR();
        if(intent.getExtras().getString("status").equals("Menunggu Jadwal")){
            batal.setVisibility(View.VISIBLE);
        }
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailRiwayat.this);
                alertDialog.setTitle("PEMBATALAN KEBERANGKATAN");
                alertDialog.setMessage("Anda yakin akan melakukan pembatalan terhadap keberangkatan anda ? ");
                alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        batal();
                    }
                });
                alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
            }
        });
    }
    public void generateQR(){
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;
        qrgEncoder = new QRGEncoder(kode_keberangkatans, null, QRGContents.Type.TEXT, dimen);
        try {
            bitmap = qrgEncoder.getBitmap();
            qrcode.setImageBitmap(bitmap);
        } catch (Error e) {
            Log.e("Tag", e.toString());
        }
        getData();
    }
    public void batal(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.BATAL)
                .addBodyParameter("kode_keberangkatan", kode_keberangkatans)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.hide();
                        new SweetAlertDialog(DetailRiwayat.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setContentText("Pembatalan Keberangkatan berhasil")
                                .setConfirmText("oke")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.e("error:", error.toString());
                    }
                });
    }
    public void getData(){
        dialog.show();
        AndroidNetworking.post(ServerUrl.LIST_PESERTA)
                .addBodyParameter("kode_keberangkatan", kode_keberangkatans)
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
                                PesertaModel pesertaModel=new PesertaModel(ob.getString("id_peserta"),ob.getString("nama_lengkap"), ob.getString("tipe_identitas"), ob.getString("jenis_kelamin"), ob.getString("nomor_telp"), ob.getString("alamat"));
                                pesertaModels.add(pesertaModel);
                            }
                            pesertaAdapter = new PesertaAdapter(pesertaModels);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailRiwayat.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(pesertaAdapter);
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
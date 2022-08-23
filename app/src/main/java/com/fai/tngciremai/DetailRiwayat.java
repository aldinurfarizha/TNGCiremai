package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class DetailRiwayat extends AppCompatActivity {
TextView tanggal_berangkat, nama_porter, biaya_tiket, biaya_porter, jumlah_pembayaran, status, kode_keberangkatan;
ImageView qrcode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String kode_keberangkatans;
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
        Intent intent = getIntent();
        kode_keberangkatans=intent.getExtras().getString("id");
        kode_keberangkatan.setText(intent.getExtras().getString("id"));
        nama_porter.setText(intent.getExtras().getString("nama_porter"));
        tanggal_berangkat.setText(intent.getExtras().getString("tanggal_berangkat"));
        biaya_tiket.setText(intent.getExtras().getString("biaya_tiket"));
        biaya_porter.setText(intent.getExtras().getString("biaya_porter"));
        status.setText(intent.getExtras().getString("status"));
        jumlah_pembayaran.setText(intent.getExtras().getString("total_biaya"));
        generateQR();
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

    }
}
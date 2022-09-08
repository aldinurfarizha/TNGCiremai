package com.fai.tngciremai.Adapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.Model.PorterPilihModel;
import com.fai.tngciremai.PorterDetail;
import com.fai.tngciremai.R;
import com.fai.tngciremai.booking.CekStatusPorter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PorterPilihAdapter extends RecyclerView.Adapter<PorterPilihAdapter.ItemViewHolder> {

    private ArrayList<PorterPilihModel> dataList;
    Context context;
    Integer nomor=1;
    public PorterPilihAdapter(ArrayList<PorterPilihModel> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_porter_pilih, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Context context = holder.imageView.getContext();
        Picasso.get()
                .load(ServerUrl.IMG_URL+dataList.get(position).getFoto())
                .into(holder.imageView);
    holder.nama_lengkap.setText(dataList.get(position).getNama_lengkap());
    holder.tahun_pengalaman.setText("Pengalaman "+dataList.get(position).getTahun_pengalaman()+" Tahun");
    holder.frequensi.setText(dataList.get(position).getFrequensi()+" Kali Booked");
    holder.nomor.setText(""+nomor);
    holder.pilih.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Konfirmasi");
            alertDialog.setMessage("Anda yakin akan memilih "+dataList.get(position).getNama_lengkap()+" sebagai porter anda ? ");
            alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context.getApplicationContext(), CekStatusPorter.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    final String id_porter = dataList.get(position).getId_porter();
                    final String tanggal_berangkat= dataList.get(position).getTgl_berangkat();
                    final String jumlah_peserta=dataList.get(position).getJumlah_peserta();
                    final String nama_porter=dataList.get(position).getNama_lengkap();
                    intent.putExtra("id_porter",id_porter);
                    intent.putExtra("tanggal_berangkat",tanggal_berangkat);
                    intent.putExtra("jumlah_peserta", jumlah_peserta);
                    intent.putExtra("nama_porter", nama_porter);
                    context.startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = alertDialog.create();
            dialog.show();
        }
    });
    holder.card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context.getApplicationContext(), PorterDetail.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            final String id = dataList.get(position).getId_porter();
            final String nama_lengkap= dataList.get(position).getNama_lengkap();
            final String foto=ServerUrl.IMG_URL+dataList.get(position).getFoto();
            final String frequensi= dataList.get(position).getFrequensi();
            final String tahun_pengalaman = dataList.get(position).getTahun_pengalaman();
            intent.putExtra("id",id);
            intent.putExtra("nama_lengkap",nama_lengkap);
            intent.putExtra("foto", foto);
            intent.putExtra("frequensi", frequensi);
            intent.putExtra("tahun_pengalaman", tahun_pengalaman);
            context.startActivity(intent);
        }
    });
    nomor++;
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_lengkap, tahun_pengalaman, frequensi, nomor;
        private CardView card;
        private ImageView imageView;
        private Button pilih;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            nama_lengkap=(TextView) itemView.findViewById(R.id.nama);
            tahun_pengalaman =(TextView) itemView.findViewById(R.id.tahun_penglaman);
            frequensi=(TextView) itemView.findViewById(R.id.frequensi);
            imageView=(ImageView) itemView.findViewById(R.id.image_view);
            pilih=(Button) itemView.findViewById(R.id.pilih);
            nomor=(TextView) itemView.findViewById(R.id.nomor);
        }
    }
}
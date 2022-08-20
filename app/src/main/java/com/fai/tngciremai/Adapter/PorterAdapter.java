package com.fai.tngciremai.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.PorterDetail;
import com.fai.tngciremai.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PorterAdapter extends RecyclerView.Adapter<PorterAdapter.ItemViewHolder> {

    private ArrayList<PorterModel> dataList;
    Context context;

    public PorterAdapter(ArrayList<PorterModel> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_porter, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_lengkap, tahun_pengalaman, frequensi;
        private CardView card;
        private ImageView imageView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            nama_lengkap=(TextView) itemView.findViewById(R.id.nama);
            tahun_pengalaman =(TextView) itemView.findViewById(R.id.tahun_penglaman);
            frequensi=(TextView) itemView.findViewById(R.id.frequensi);
            imageView=(ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
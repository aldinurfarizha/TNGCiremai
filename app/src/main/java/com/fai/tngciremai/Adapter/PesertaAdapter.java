package com.fai.tngciremai.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fai.tngciremai.DetailRiwayat;
import com.fai.tngciremai.Model.PesertaModel;
import com.fai.tngciremai.Model.RiwayatBookingModel;
import com.fai.tngciremai.R;
import com.fai.tngciremai.Util.RupiahFormat;

import java.util.ArrayList;

public class PesertaAdapter extends RecyclerView.Adapter<PesertaAdapter.ItemViewHolder> {

    private ArrayList<PesertaModel> dataList;
    Context context;
    Integer nomor=1;
    public PesertaAdapter(ArrayList<PesertaModel> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_peserta, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Context context = holder.card.getContext();
        holder.nama.setText(dataList.get(position).getNama());
        holder.jenis_identitas.setText(dataList.get(position).getJenis_identitas());
        holder.jenis_kelamin.setText(dataList.get(position).getJenis_kelamin());
        holder.nomor_telp.setText(dataList.get(position).getNomor_telepon());
        holder.alamat.setText(dataList.get(position).getAlamat());
        holder.no.setText(""+nomor+".");
        nomor++;
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nama, jenis_identitas, jenis_kelamin, nomor_telp, alamat, no;
        private CardView card;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            nama= (TextView) itemView.findViewById(R.id.nama);
            jenis_identitas = (TextView) itemView.findViewById(R.id.jenis_identitas);
            jenis_kelamin = (TextView) itemView.findViewById(R.id.jenis_kelamin);
            nomor_telp=(TextView) itemView.findViewById(R.id.nomor_telepon);
            alamat=(TextView) itemView.findViewById(R.id.alamat);
            no=(TextView) itemView.findViewById(R.id.no);
        }
    }
}
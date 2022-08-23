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
import com.fai.tngciremai.DetailRiwayat;
import com.fai.tngciremai.Model.PorterModel;
import com.fai.tngciremai.Model.RiwayatBookingModel;
import com.fai.tngciremai.PorterDetail;
import com.fai.tngciremai.R;
import com.fai.tngciremai.RiwayatBooking;
import com.fai.tngciremai.Util.RupiahFormat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ItemViewHolder> {

    private ArrayList<RiwayatBookingModel> dataList;
    Context context;

    public BookingAdapter(ArrayList<RiwayatBookingModel> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_booking, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Context context = holder.card.getContext();
        final String status_name;
        switch (dataList.get(position).getStatus()){
            case "0":
                status_name="Menunggu Jadwal";
                break;
            case "1":
                status_name="Berangkat";
                break;
            case "2":
                status_name="Selesai";
                break;
            case "3":
                status_name="Batal";
                break;

            default:
                status_name="";
                break;
        }
        final String total_biaya=RupiahFormat.convert(Double.valueOf(dataList.get(position).getTotal_biaya()));
        final String biaya_tiket=RupiahFormat.convert(Double.valueOf(dataList.get(position).getBiaya_tiket()));
        final String biaya_porter=RupiahFormat.convert(Double.valueOf(dataList.get(position).getBiaya_porter()));
        holder.tanggal_berangkat.setText(dataList.get(position).getTanggal_berangkat());
        holder.nama_porter.setText(dataList.get(position).getNama_porter());
        holder.total_biaya.setText(total_biaya);
        holder.status.setText(status_name);
        holder.kode_keberangkatan.setText(dataList.get(position).getId_keberangkatan());
        holder.card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context.getApplicationContext(), DetailRiwayat.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            final String id = dataList.get(position).getId_keberangkatan();
            final String tanggal_berangkat= dataList.get(position).getTanggal_berangkat();
            final String nama_porter = dataList.get(position).getNama_porter();
            final String total_biaya = RupiahFormat.convert(Double.valueOf(dataList.get(position).getTotal_biaya()));
            final String biaya_tiket = RupiahFormat.convert(Double.valueOf(dataList.get(position).getBiaya_tiket()));
            final String biaya_porter = RupiahFormat.convert(Double.valueOf(dataList.get(position).getBiaya_porter()));
            final String status = dataList.get(position).getStatus();

            intent.putExtra("id",id);
            intent.putExtra("nama_porter",nama_porter);
            intent.putExtra("tanggal_berangkat", tanggal_berangkat);
            intent.putExtra("total_biaya", total_biaya);
            intent.putExtra("biaya_tiket", biaya_tiket);
            intent.putExtra("biaya_porter", biaya_porter);
            intent.putExtra("status", status_name);
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tanggal_berangkat, nama_porter, total_biaya, status,kode_keberangkatan;
        private CardView card;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            tanggal_berangkat=(TextView) itemView.findViewById(R.id.tanggal_berangkat);
            nama_porter=(TextView) itemView.findViewById(R.id.nama_porter);
            total_biaya=(TextView) itemView.findViewById(R.id.total_biaya);
            status =(TextView) itemView.findViewById(R.id.status);
            kode_keberangkatan = (TextView) itemView.findViewById(R.id.kode_keberangkatan);
        }
    }
}
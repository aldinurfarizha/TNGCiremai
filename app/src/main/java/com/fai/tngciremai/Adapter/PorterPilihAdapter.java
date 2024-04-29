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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    private ArrayList<String> selectedPorters;
    public PorterPilihAdapter(ArrayList<PorterPilihModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.selectedPorters = new ArrayList<>();
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
    holder.best_partner.setText("Kolaborasi Terbaik dengan:"+dataList.get(position).getBest_partner());
    if(dataList.get(position).getBest_partner().equals("null")){
        holder.best_partner_relative.setVisibility(View.GONE);
    }else{
        holder.best_partner_relative.setVisibility(View.VISIBLE);
    }
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
    holder.pilih.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                selectedPorters.add(dataList.get(position).getId_porter());
            } else {
                selectedPorters.remove(dataList.get(position).getId_porter());
            }
        });
    nomor++;
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }
    public ArrayList<String> getSelectedPorters() {
        return selectedPorters;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_lengkap, tahun_pengalaman, frequensi, nomor, best_partner;
        private CardView card;
        private ImageView imageView;
        private CheckBox pilih;
        private RelativeLayout best_partner_relative;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            nama_lengkap=(TextView) itemView.findViewById(R.id.nama);
            tahun_pengalaman =(TextView) itemView.findViewById(R.id.tahun_penglaman);
            frequensi=(TextView) itemView.findViewById(R.id.frequensi);
            imageView=(ImageView) itemView.findViewById(R.id.image_view);
            pilih=(CheckBox) itemView.findViewById(R.id.checklist_porter);
            nomor=(TextView) itemView.findViewById(R.id.nomor);
            best_partner_relative=(RelativeLayout) itemView.findViewById(R.id.best_partner_relative);
            best_partner=(TextView) itemView.findViewById(R.id.best_partner);
        }
    }
}
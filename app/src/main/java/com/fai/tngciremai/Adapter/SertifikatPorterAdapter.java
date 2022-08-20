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
import com.fai.tngciremai.Model.SertifikatPorterModel;
import com.fai.tngciremai.PorterDetail;
import com.fai.tngciremai.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SertifikatPorterAdapter extends RecyclerView.Adapter<SertifikatPorterAdapter.ItemViewHolder> {

    private ArrayList<SertifikatPorterModel> dataList;
    Context context;

    public SertifikatPorterAdapter(ArrayList<SertifikatPorterModel> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_sertifikat_porter, parent, false);
        return new ItemViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Context context = holder.card.getContext();
        holder.sertifikat.setText(dataList.get(position).getSertifikat());
        holder.tahun.setText(dataList.get(position).getTahun());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView sertifikat, tahun;
        private CardView card;
        public ItemViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card);
            sertifikat=(TextView) itemView.findViewById(R.id.sertifikat);
            tahun=(TextView) itemView.findViewById(R.id.tahun);
        }
    }
}
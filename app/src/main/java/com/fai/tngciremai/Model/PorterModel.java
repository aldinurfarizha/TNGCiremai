package com.fai.tngciremai.Model;

public class PorterModel {
    String id_porter, nama_lengkap, foto, tahun_pengalaman, frequensi;

    public PorterModel(String id_porter, String nama_lengkap, String foto, String tahun_pengalaman, String frequensi) {
        this.id_porter = id_porter;
        this.nama_lengkap = nama_lengkap;
        this.foto = foto;
        this.tahun_pengalaman = tahun_pengalaman;
        this.frequensi = frequensi;
    }

    public String getId_porter() {
        return id_porter;
    }

    public void setId_porter(String id_porter) {
        this.id_porter = id_porter;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTahun_pengalaman() {
        return tahun_pengalaman;
    }

    public void setTahun_pengalaman(String tahun_pengalaman) {
        this.tahun_pengalaman = tahun_pengalaman;
    }

    public String getFrequensi() {
        return frequensi;
    }

    public void setFrequensi(String frequensi) {
        this.frequensi = frequensi;
    }
}

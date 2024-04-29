package com.fai.tngciremai.Model;

public class PorterPilihModel {
    String id_porter, nama_lengkap, foto, tahun_pengalaman, frequensi, tgl_berangkat, jumlah_peserta, best_partner;

    public PorterPilihModel(String id_porter, String nama_lengkap, String foto, String tahun_pengalaman, String frequensi, String tgl_berangkat, String jumlah_peserta, String best_partner) {
        this.id_porter = id_porter;
        this.nama_lengkap = nama_lengkap;
        this.foto = foto;
        this.tahun_pengalaman = tahun_pengalaman;
        this.frequensi = frequensi;
        this.tgl_berangkat = tgl_berangkat;
        this.jumlah_peserta = jumlah_peserta;
        this.best_partner=best_partner;
    }

    public String getBest_partner() {
        return best_partner;
    }

    public void setBest_partner(String best_partner) {
        this.best_partner = best_partner;
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

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }

    public String getJumlah_peserta() {
        return jumlah_peserta;
    }

    public void setJumlah_peserta(String jumlah_peserta) {
        this.jumlah_peserta = jumlah_peserta;
    }
}

package com.fai.tngciremai.Model;

public class RiwayatBookingModel {
    String id_keberangkatan, tanggal_berangkat, nama_porter, total_biaya, status, biaya_tiket, biaya_porter;

    public RiwayatBookingModel(String id_keberangkatan, String tanggal_berangkat, String nama_porter, String total_biaya, String status, String biaya_tiket, String biaya_porter) {
        this.id_keberangkatan = id_keberangkatan;
        this.tanggal_berangkat = tanggal_berangkat;
        this.nama_porter = nama_porter;
        this.total_biaya = total_biaya;
        this.status = status;
        this.biaya_tiket = biaya_tiket;
        this.biaya_porter = biaya_porter;
    }

    public String getId_keberangkatan() {
        return id_keberangkatan;
    }

    public void setId_keberangkatan(String id_keberangkatan) {
        this.id_keberangkatan = id_keberangkatan;
    }

    public String getTanggal_berangkat() {
        return tanggal_berangkat;
    }

    public void setTanggal_berangkat(String tanggal_berangkat) {
        this.tanggal_berangkat = tanggal_berangkat;
    }

    public String getNama_porter() {
        return nama_porter;
    }

    public void setNama_porter(String nama_porter) {
        this.nama_porter = nama_porter;
    }

    public String getTotal_biaya() {
        return total_biaya;
    }

    public void setTotal_biaya(String total_biaya) {
        this.total_biaya = total_biaya;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBiaya_tiket() {
        return biaya_tiket;
    }

    public void setBiaya_tiket(String biaya_tiket) {
        this.biaya_tiket = biaya_tiket;
    }

    public String getBiaya_porter() {
        return biaya_porter;
    }

    public void setBiaya_porter(String biaya_porter) {
        this.biaya_porter = biaya_porter;
    }
}

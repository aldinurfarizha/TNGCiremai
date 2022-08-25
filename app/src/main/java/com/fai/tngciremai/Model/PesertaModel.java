package com.fai.tngciremai.Model;

public class PesertaModel {
    String id_peserta, nama, jenis_identitas, jenis_kelamin, nomor_telepon, alamat;

    public PesertaModel(String id_peserta, String nama, String jenis_identitas, String jenis_kelamin, String nomor_telepon, String alamat) {
        this.id_peserta = id_peserta;
        this.nama = nama;
        this.jenis_identitas = jenis_identitas;
        this.jenis_kelamin = jenis_kelamin;
        this.nomor_telepon = nomor_telepon;
        this.alamat = alamat;
    }

    public String getId_peserta() {
        return id_peserta;
    }

    public void setId_peserta(String id_peserta) {
        this.id_peserta = id_peserta;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_identitas() {
        return jenis_identitas;
    }

    public void setJenis_identitas(String jenis_identitas) {
        this.jenis_identitas = jenis_identitas;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

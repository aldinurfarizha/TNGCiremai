package com.fai.tngciremai.Model;

public class Credential {
    private String iduser, nama_lengkap;

    public Credential(String iduser, String nama_lengkap) {
        this.iduser = iduser;
        this.nama_lengkap = nama_lengkap;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }
}
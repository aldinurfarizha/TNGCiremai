package com.fai.tngciremai.Model;

public class SertifikatPorterModel {
    String sertifikat, tahun;

    public SertifikatPorterModel(String sertifikat, String tahun) {
        this.sertifikat = sertifikat;
        this.tahun = tahun;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }
}

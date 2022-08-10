package com.fai.tngciremai.Config;

public class ServerUrl {


  //Root URL
  //private static final String ROOT_URL = "http://45.156.24.136/rsud45web/api/";
  private static final String ROOT_URL = "http://192.168.56.1/TNGCiremai_web/api/";
  //Getting Image Conf URL
  public static final String GET_IMAGE ="";

  //SubRoot URL
  public static final String LOGIN= ROOT_URL + "login";
  public static final String REGISTER= ROOT_URL + "register";
  public static final String DASHBOARD_DOKTER= ROOT_URL + "dashboard_dokter";
  public static final String DASHBOARD_PASIEN= ROOT_URL + "dashboard_pasien";
  public static final String GET_DOKTER= ROOT_URL + "get_dokter";
  public static final String ANTRI_POLI= ROOT_URL + "daftar_poli";
  public static final String STATUS_ANTRIAN= ROOT_URL + "status_antrian";
  public static final String BATAL= ROOT_URL + "batal";
  public static final String PROVINSI= ROOT_URL + "get_province";
  public static final String KABUPATEN= ROOT_URL + "get_regencies";
  public static final String KECAMATAN= ROOT_URL + "get_districts";
  public static final String REGISTRASI_PASIEN= ROOT_URL + "registrasi_pasien";
  public static final String STATUS_PASIEN=ROOT_URL + "check_status";

}

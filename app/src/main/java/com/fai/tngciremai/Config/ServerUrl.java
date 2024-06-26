package com.fai.tngciremai.Config;

public class ServerUrl {


  //Root URL
  private static final String SERVER_ADDRESS="http://pendakianciremai.my.id/";
  private static final String SERVER_NAME="";
  private static final String SERVER_PATH_API="api/";
  private static final String SERVER_PATH_IMAGE="private/file_upload/";
  private static final String ROOT_URL = SERVER_ADDRESS+SERVER_NAME+SERVER_PATH_API;
  public static final String IMG_URL= SERVER_ADDRESS + SERVER_NAME + SERVER_PATH_IMAGE;
  //Getting Image Conf URL
  //SubRoot URL
  public static final String LOGIN= ROOT_URL + "login";
  public static final String REGISTER= ROOT_URL + "register";
  public static final String ADD_KEBERANGKATAN= ROOT_URL + "add_keberangkatan";
  public static final String CEK_TANGGAL_KEBERANGKATAN= ROOT_URL + "cek_tanggal";
  public static final String LIST_PORTER=ROOT_URL + "list_porter";
  public static final String LIST_PESERTA=ROOT_URL + "list_peserta";
  public static final String SERTIFIKAT_PORTER=ROOT_URL + "sertifikat_porter";
  public static final String CEK_PORTER=ROOT_URL + "cek_ketersediaan_porter";
  public static final String RIWAYAT_BOOK=ROOT_URL + "riwayat_book";
  public static final String BATAL=ROOT_URL + "batal";



}

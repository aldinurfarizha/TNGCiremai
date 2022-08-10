package com.fai.tngciremai.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.fai.tngciremai.Login;
import com.fai.tngciremai.Model.Credential;


public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "RIFAI";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public void userLogin(Credential credential) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, credential.getIduser());
        editor.putString(KEY_NAMA, credential.getNama_lengkap());
        editor.apply();
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAMA, null) != null;
    }
    public Credential get() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Credential(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_NAMA, null)
        );
    }
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }
}
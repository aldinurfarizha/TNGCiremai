package com.fai.tngciremai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.fai.tngciremai.Config.ServerUrl;
import com.fai.tngciremai.Model.Credential;
import com.fai.tngciremai.Util.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Login extends AppCompatActivity {
Button login;
RelativeLayout daftar;
EditText email, password;
ProgressDialog dialog;
String message;
Boolean loginstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btn_login);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        dialog=new ProgressDialog(Login.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        AndroidNetworking.initialize(getApplicationContext());
        SessionCheck();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emails = email.getText().toString();
                final String passwords =  password.getText().toString();
                if (TextUtils.isEmpty(emails)) {
                    email.setError("Kolom ini tidak boleh kosong");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(passwords)) {
                    password.setError("Kolom ini tidak boleh kosong");
                    password.requestFocus();
                    return;
                }
                dialog.show();
                AndroidNetworking.post(ServerUrl.LOGIN)
                        .addBodyParameter("email", email.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .setTag("Login Prosses")
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                dialog.dismiss();
                                try {
                                    message = response.getString("message");
                                    loginstatus=response.getBoolean("login");
                                    if(loginstatus){
                                        JSONObject arr =response.getJSONObject("data");
                                        Credential credential= new Credential(
                                                arr.getString("iduser"),
                                                arr.getString("nama_lengkap")
                                        );
                                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(credential);
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                    }else{
                                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onError(ANError error) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), message+error.toString(), Toast.LENGTH_LONG).show();
                                Log.e("error:", error.toString());
                            }
                        });
            }
        });
        daftar = (RelativeLayout) findViewById(R.id.btn_daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

    }
    public void SessionCheck(){
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        }
    }
}
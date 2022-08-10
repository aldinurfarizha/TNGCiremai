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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
Button register;
ProgressDialog dialog;
EditText email, nama_lengkap, password, konfirm_password, nomor_telepon;
String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button)findViewById(R.id.btn_daftar);
        email=(EditText)findViewById(R.id.email);
        nama_lengkap=(EditText)findViewById(R.id.nama_lengkap);
        password=(EditText)findViewById(R.id.password);
        konfirm_password=(EditText)findViewById(R.id.confirm_password);
        nomor_telepon=(EditText)findViewById(R.id.nomor_telepon);
        dialog=new ProgressDialog(Register.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        AndroidNetworking.initialize(getApplicationContext());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emails=email.getText().toString();
                final String nama_lengkaps=nama_lengkap.getText().toString();
                final String passwords=password.getText().toString();
                final String konfirm_passwords=konfirm_password.getText().toString();
                final String nomor_telepons=nomor_telepon.getText().toString();
                if (TextUtils.isEmpty(emails)) {
                    email.setError("Kolom ini tidak boleh kosong");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(nama_lengkaps)) {
                    nama_lengkap.setError("Kolom ini tidak boleh kosong");
                    nama_lengkap.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(passwords)) {
                    password.setError("Kolom ini tidak boleh kosong");
                    password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(konfirm_passwords)) {
                    konfirm_password.setError("Kolom ini tidak boleh kosong");
                    konfirm_password.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(nomor_telepons)) {
                    nomor_telepon.setError("Kolom ini tidak boleh kosong");
                    nomor_telepon.requestFocus();
                    return;
                }
                if(!passwords.equals(konfirm_passwords)){
                    Toast.makeText(Register.this, "Password Tidak sesuai", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(nomor_telepons.length()<9){
                    nomor_telepon.setError("Nomor Telepon tidak kurang dari 9 Digit");
                    nomor_telepon.requestFocus();
                    return;
                }
                if(nomor_telepons.length()>13){
                    nomor_telepon.setError("Nomor Telepon tidak Lebih dari 13 Digit");
                    nomor_telepon.requestFocus();
                    return;
                }
                if(!isEmailValid(emails)){
                    email.setError("Format Email tidak benar");
                    email.requestFocus();
                    return;
                }
                dialog.show();

                AndroidNetworking.post(ServerUrl.REGISTER)
                        .addBodyParameter("email", email.getText().toString())
                        .addBodyParameter("password", password.getText().toString())
                        .addBodyParameter("telp", nomor_telepon.getText().toString())
                        .addBodyParameter("nama_lengkap", nama_lengkap.getText().toString())
                        .setPriority(Priority.IMMEDIATE)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                dialog.dismiss();
                                try {
                                    message = response.getString("message");
                                    if(response.getBoolean("status")){
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
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
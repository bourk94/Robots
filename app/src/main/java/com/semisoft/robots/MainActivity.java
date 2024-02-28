package com.semisoft.robots;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.semisoft.robots.Services.RetrofitInstance;
import com.semisoft.robots.Services.Server;
import com.semisoft.robots.Services.ServerResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private CheckBox remember_me;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        remember_me = findViewById(R.id.remember_me);
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> login());
    }

    private String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(){
        Server server = RetrofitInstance.getInstance().create(Server.class);
        Call<ServerResponse> call = server.login(email.getText().toString(), hashPassword(password.getText().toString()));

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse serverResponse = response.body();
                if (serverResponse.isValide()) {
                    Toast.makeText(MainActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {

                }
                });
    }








}


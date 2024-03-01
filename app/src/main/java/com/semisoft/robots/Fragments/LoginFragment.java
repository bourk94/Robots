package com.semisoft.robots.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.semisoft.robots.R;
import com.semisoft.robots.Services.RetrofitInstance;
import com.semisoft.robots.Services.Server;
import com.semisoft.robots.Services.ServerResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private EditText email;
    private EditText password;
    private CheckBox remember_me;
    private Button login;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        remember_me = view.findViewById(R.id.remember_me);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(v -> login(v));
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
    public void login(View v){
        Server server = RetrofitInstance.getInstance().create(Server.class);
        Call<ServerResponse> call = server.login(email.getText().toString(), hashPassword(password.getText().toString()));
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (serverResponse.isValide()) {
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_loginFragment_to_roverRemoteFragment);
                } else {
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
            }
        });
    }
}
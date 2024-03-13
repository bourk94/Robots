package com.semisoft.robots.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.semisoft.robots.R;
import com.semisoft.robots.Services.RetrofitInstance;
import com.semisoft.robots.Services.Server;
import com.semisoft.robots.Services.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private EditText email;
    private EditText password;
    private CheckBox remember_me;
    private Button login;
    private SharedPreferences preferences;
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
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        remember_me = view.findViewById(R.id.remember_me);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(v -> login(v));

        if (preferences.getBoolean("remember_me", false)) {
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.jamalFragment);
        }
    }

//    private String hashPassword(String password){
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            byte[] hash = md.digest(password.getBytes());
//            StringBuilder sb = new StringBuilder();
//            for (byte b : hash) {
//                sb.append(String.format("%02x", b));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void login(View v){
        Server server = RetrofitInstance.getInstance().create(Server.class);
        Call<ServerResponse> call = server.login(email.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                assert serverResponse != null;
                if (serverResponse.isValide()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("remember_me", remember_me.isChecked());
                    editor.commit();
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.from_login_to_jamal);
                }
                if (serverResponse.getMessage().getFailure() != null) {
                    Toast.makeText(getContext(), serverResponse.getMessage().getFailure(), Toast.LENGTH_LONG).show();
                }
                if (serverResponse.getMessage().getEmail() != null) {
                    email.setError(serverResponse.getMessage().getEmail()[0]);
                }
                if (serverResponse.getMessage().getPassword() != null) {
                    password.setError(serverResponse.getMessage().getPassword()[0]);
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}
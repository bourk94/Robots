package com.semisoft.robots.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.semisoft.robots.R;

public class DawgMobileFragment extends Fragment {
    ImageButton toJamal, logout;
    SharedPreferences preferences;

    public DawgMobileFragment() {
        // Required empty public constructor
    }

    public static DawgMobileFragment newInstance(String param1, String param2) {
        return new DawgMobileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dawgmobile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toJamal = view.findViewById(R.id.toJamal);
        logout = view.findViewById(R.id.dawgmobile_logout);
        toJamal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.from_dawgmobile_to_jamal);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("remember_me", false);
                editor.commit();
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.loginFragment);
            }
        });
    }
}
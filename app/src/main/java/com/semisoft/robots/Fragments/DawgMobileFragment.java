package com.semisoft.robots.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.semisoft.robots.R;
import com.semisoft.robots.Services.MqttClient;

public class DawgMobileFragment extends Fragment {
    ImageButton toJamal, logout;
    ImageButton north_west, north, north_east, west, east, south_west, south, south_east, rot_left, rot_right;
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

    @SuppressLint("ClickableViewAccessibility")
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

        MqttClient client = new MqttClient("172.16.87.53", 1883);
        client.connect();

        // remote buttons
        north_west = view.findViewById(R.id.north_west);
        north = view.findViewById(R.id.north);
        north_east = view.findViewById(R.id.north_east);
        west = view.findViewById(R.id.west);
        east = view.findViewById(R.id.east);
        south_west = view.findViewById(R.id.south_west);
        south = view.findViewById(R.id.south);
        south_east = view.findViewById(R.id.south_east);
        rot_left = view.findViewById(R.id.rot_left);
        rot_right = view.findViewById(R.id.rot_right);

        north_west.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "q");
            }
            return false;
        });
        north.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "w");
            }
            return false;
        });
        north_east.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "e");
            }
            return false;
        });
        west.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "a");
            }
            return false;
        });
        east.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "d");
            }
            return false;
        });
        south_west.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "z");
            }
            return false;
        });
        south.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "s");
            }
            return false;
        });
        south_east.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "c");
            }
            return false;
        });
        rot_left.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "o");
            }
            return false;
        });
        rot_right.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                client.sendMessage("dawgmobile", "stop");
            } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                client.sendMessage("dawgmobile", "p");
            }
            return false;
        });
    }
}
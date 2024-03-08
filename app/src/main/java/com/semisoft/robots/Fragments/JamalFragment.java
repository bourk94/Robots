package com.semisoft.robots.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.semisoft.robots.Domain.Action;
import com.semisoft.robots.R;
import com.semisoft.robots.Services.MqttClient;
import com.semisoft.robots.Utils.ActionRVAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JamalFragment extends Fragment {
    private ImageButton toDawg, logout, jamal_forward, jamal_back, jamal_rot_left, jamal_rot_right;
    private SharedPreferences preferences;
    private RecyclerView rv;
    private List<Action> actions;

    public JamalFragment() {
        // Required empty public constructor
    }

    public static JamalFragment newInstance(String param1, String param2) {
        return new JamalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jamal, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        toDawg = view.findViewById(R.id.toDawg);
        logout = view.findViewById(R.id.jamal_logout);
        jamal_forward = view.findViewById(R.id.jamal_forward);
        jamal_back = view.findViewById(R.id.jamal_back);
        jamal_rot_left = view.findViewById(R.id.jamal_rot_left);
        jamal_rot_right = view.findViewById(R.id.jamal_rot_right);

        MqttClient client = new MqttClient("172.16.87.53", 1883);
        client.connect();

        toDawg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.from_jamal_to_dawgmobile);
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

        jamal_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    client.sendMessage("jamal_remote", "stop");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    client.sendMessage("jamal_remote", "forward");
                }

                return false;
            }
        });
        jamal_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    client.sendMessage("jamal_remote", "stop");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    client.sendMessage("jamal_remote", "back");
                }

                return false;
            }
        });
        jamal_rot_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    client.sendMessage("jamal_remote", "stop");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    client.sendMessage("jamal_remote", "rot_left");
                }

                return false;
            }
        });
        jamal_rot_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    client.sendMessage("jamal_remote", "stop");
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    client.sendMessage("jamal_remote", "rot_right");
                }

                return false;
            }
        });

        

        actions = initActions();

        rv = view.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ActionRVAdapter(actions, client));
    }

    private List<Action> initActions() {
        List<Action> fileActions = new ArrayList<Action>();

        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.modes);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Type actionListType = new TypeToken<List<Action>>() {}.getType();
            fileActions = gson.fromJson(jsonString, actionListType);

            List<Action> filteredActions = new ArrayList<Action>();
            for (Action action : fileActions) {
                if (action.getRobot().equals("jamal")) {
                    filteredActions.add(action);
                }
            }

            return filteredActions;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileActions;
    }
}
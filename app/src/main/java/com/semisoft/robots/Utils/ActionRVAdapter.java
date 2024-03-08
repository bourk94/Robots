package com.semisoft.robots.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.semisoft.robots.Domain.Action;
import com.semisoft.robots.R;
import com.semisoft.robots.Services.MqttClient;

import java.util.List;

public class ActionRVAdapter extends RecyclerView.Adapter {
    private List<Action> actions;
    private MqttClient client;

    public ActionRVAdapter(List<Action> actions, MqttClient client) {
        this.actions = actions;
        this.client = client;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.action_card, parent, false);
        return new ActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActionViewHolder actionViewHolder = (ActionViewHolder) holder;
        actionViewHolder.actionLabel.setText(actions.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    public class ActionViewHolder extends RecyclerView.ViewHolder {
        TextView actionLabel;
        CardView actionCard;

        public ActionViewHolder(@NonNull android.view.View itemView) {
            super(itemView);

            actionLabel = itemView.findViewById(R.id.actionLabel);
            actionCard = itemView.findViewById(R.id.actionCard);

            actionCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Action action = actions.get(position);
                    client.sendMessage("jamal_remote", action.getName());
                }
            });
        }
    }
}

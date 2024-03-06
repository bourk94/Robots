package com.semisoft.robots.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.semisoft.robots.Domain.Action;
import com.semisoft.robots.R;

import java.util.List;

public class ActionRVAdapter extends RecyclerView.Adapter {
    private List<Action> actions;

    public ActionRVAdapter(List<Action> actions) {
        this.actions = actions;
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

    public static class ActionViewHolder extends RecyclerView.ViewHolder {
        TextView actionLabel;

        public ActionViewHolder(@NonNull android.view.View itemView) {
            super(itemView);

            actionLabel = itemView.findViewById(R.id.actionLabel);
        }
    }
}

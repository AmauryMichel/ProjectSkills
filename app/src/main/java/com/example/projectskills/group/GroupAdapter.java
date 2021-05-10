package com.example.projectskills.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectskills.R;

import java.util.Vector;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvGroupName;
        public TextView tvGroupAdmin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            tvGroupAdmin = itemView.findViewById(R.id.tvGroupAdmin);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View groupView = inflater.inflate(R.layout.model_list_group, parent, false);

        return new ViewHolder(groupView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = lGroups.get(position);

        TextView tvName = holder.tvGroupName;
        tvName.setText(group.getGroupName());

        TextView tvAdmin = holder.tvGroupAdmin;
        if (group.isGroupManager()){
            tvAdmin.setText(R.string.tvGroupManager);
        }
    }

    @Override
    public int getItemCount() {
        return lGroups.size();
    }

    private final Vector<Group> lGroups;

    public GroupAdapter(Vector<Group> lGroups) {
        this.lGroups = lGroups;
    }
}

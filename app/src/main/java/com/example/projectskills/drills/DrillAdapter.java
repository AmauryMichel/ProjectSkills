package com.example.projectskills.drills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectskills.R;

import java.util.Vector;

public class DrillAdapter extends RecyclerView.Adapter<DrillAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDrillName;
        public TextView tvDrillDesc;
        public TextView tvDrillGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDrillName = itemView.findViewById(R.id.tvDrillName);
            tvDrillDesc = itemView.findViewById(R.id.tvDrillDesc);
            tvDrillGroup = itemView.findViewById(R.id.tvDrillGroup);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View drillView = inflater.inflate(R.layout.model_list_drill, parent, false);

        return new ViewHolder(drillView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Drill drill = lDrills.get(position);

        TextView tvName = holder.tvDrillName;
        tvName.setText(drill.getDrillName());

        TextView tvDesc = holder.tvDrillDesc;
        tvDesc.setText(drill.getDrillDesc());

        TextView tvGroup = holder.tvDrillGroup;
        tvGroup.setText(drill.getDrillGroupName());
    }

    @Override
    public int getItemCount() {
        return lDrills.size();
    }

    private Vector<Drill> lDrills;

    public DrillAdapter(Vector<Drill> lDrills) {
        this.lDrills = lDrills;
    }
}

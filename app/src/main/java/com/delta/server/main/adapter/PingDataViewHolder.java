package com.delta.server.main.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delta.server.R;
import com.delta.server.api.data.PingFilesDTO;

public class PingDataViewHolder extends RecyclerView.ViewHolder {

    private final TextView nameCountry;
    private final TextView ipAddress;
    private final TextView sessionCount;
    private final TextView pingCount;

    public PingDataViewHolder(@NonNull View itemView) {
        super(itemView);
        nameCountry = itemView.findViewById(R.id.country_name);
        ipAddress = itemView.findViewById(R.id.ip_address);
        sessionCount = itemView.findViewById(R.id.session_count);
        pingCount = itemView.findViewById(R.id.ping_count);

    }

    public void bind(PingFilesDTO pingFile) {
        nameCountry.setText(pingFile.getLocation().getShortName());
        ipAddress.setText(pingFile.getKey());
        sessionCount.setText(pingFile.getInfo());

    }
}

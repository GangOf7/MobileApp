package com.blackpearl.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blackpearl.Models.DeviceList_Item;
import com.blackpearl.R;

import java.util.ArrayList;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceListViewHolder> {


    private ArrayList<DeviceList_Item> devicesList;
    private OnItemClickListener onItemClickListener;

    public DeviceListAdapter(ArrayList<DeviceList_Item> listOfDevices) {
        devicesList = listOfDevices;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public DeviceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_device_list_item, parent, false);
        DeviceListViewHolder deviceListViewHolder = new DeviceListViewHolder(v, onItemClickListener);
        return deviceListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceListViewHolder holder, int position) {

        DeviceList_Item currentItem = devicesList.get(position);
        holder.deviceName.setText(currentItem.getDeviceName());
        holder.deviceLocation.setText(currentItem.getDeviceLocation());
        holder.deviceStatus.setText(currentItem.getDeviceStatus());
    }

    @Override
    public int getItemCount() {
        return devicesList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public static class DeviceListViewHolder extends RecyclerView.ViewHolder {

        TextView deviceName;
        TextView deviceLocation;
        TextView deviceStatus;

        public DeviceListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.listItem_deviceName);
            deviceLocation = itemView.findViewById(R.id.listItem_deviceLocation);
            deviceStatus = itemView.findViewById(R.id.listItem_deviceStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, view);
                        }
                    }
                }
            });
        }
    }
}

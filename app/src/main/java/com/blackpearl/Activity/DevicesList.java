package com.blackpearl.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blackpearl.Controllers.AppVariables;
import com.blackpearl.Models.Device;
import com.blackpearl.Models.DeviceList_Item;
import com.blackpearl.Models.User;
import com.blackpearl.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class DevicesList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeviceListAdapter reAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_list);

        //setting Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        String listFilter = intent.getStringExtra("listFilter");
        Log.d("listFilter", listFilter);
        ArrayList<DeviceList_Item> devicesList = new ArrayList<DeviceList_Item>();

        setActivityTitle(listFilter);

        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        String userObjString = sharedPreferences.getString("userDetails", "");
        Gson gson = new Gson();
        AppVariables.user = gson.fromJson(userObjString, User.class);

        devicesList = filterDeviceList(AppVariables.user.getDeviceDetail(), listFilter);
        TextView listUpdatedOn = (TextView) findViewById(R.id.title_list_last_updated);
        if (devicesList.size() > 0) {

            listUpdatedOn.setVisibility(View.GONE);
        } else {
            listUpdatedOn.setText(R.string.empty_list);
        }
        buildRecyclerView(devicesList);


    }

    private void setActivityTitle(String listFilter) {

        TextView activityTitle = (TextView) findViewById(R.id.title_DevicesList);
        TextView activitysubTitle = (TextView) findViewById(R.id.subtitle_devicesList);


        if (listFilter.contentEquals("ALL")) {
            activityTitle.setText(R.string.title_list_devices_all);
            activitysubTitle.setText(R.string.sub_title_list_devices_all);
        } else if (listFilter.contentEquals("GREEN")) {
            activityTitle.setText(R.string.title_list_devices_safe);
            activitysubTitle.setText(R.string.sub_title_list_devices_safe);
        } else if (listFilter.contentEquals("RED")) {
            activityTitle.setText(R.string.title_list_devices_risk);
            activitysubTitle.setText(R.string.sub_title_list_devices_risk);
        } else if (listFilter.contentEquals("INACTIVE")) {
            activityTitle.setText(R.string.title_list_devices_inactive);
            activitysubTitle.setText(R.string.sub_title_list_devices_inactive);
        }
    }

    private void buildRecyclerView(ArrayList<DeviceList_Item> devicesList) {
        recyclerView = findViewById(R.id.recyclerView_deviceList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        reAdapter = new DeviceListAdapter(devicesList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reAdapter);

        reAdapter.setOnItemClickListener(new DeviceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Device device = AppVariables.user.getDeviceDetail().get(position);
                Intent intent = new Intent(getApplicationContext(), DeviceDetail.class);
                intent.putExtra("devicePosition", position);
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                        0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());
            }
        });
    }

    private ArrayList<DeviceList_Item> filterDeviceList(ArrayList<Device> deviceDetail, String listFilter) {
        ArrayList<DeviceList_Item> deviceList_items = new ArrayList<DeviceList_Item>();
        for (Device currentDevice : deviceDetail) {
            boolean addToList = false;
            DeviceList_Item item = new DeviceList_Item();
            if (listFilter.contentEquals("ALL")) {
                addToList = true;
            } else {
                if (currentDevice.getColor().equalsIgnoreCase(listFilter)) {
                    addToList = true;
                }
            }
            if (addToList) {
                item.setDeviceName(currentDevice.getDevice_Name());
                String loc = currentDevice.getArea() + ", " + currentDevice.getState() + ", " + currentDevice.getCountry();
                item.setDeviceLocation(loc);
                item.setDeviceStatus(currentDevice.getColor());
                deviceList_items.add(item);
            }
        }
        return deviceList_items;
    }
}

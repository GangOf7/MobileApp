package com.blackpearl.Controllers;

import com.blackpearl.Models.Device;

import java.util.HashMap;
import java.util.List;

public class DeviceConfiguration {

    public static HashMap<String, Integer> getOverallStatus(List<Device> devicesList) {

        HashMap<String, Integer> statusMap = new HashMap<String, Integer>();
        int count_red = 0, count_green = 0, count_amber = 0;
        for (Device currentDevice : devicesList) {

            if (currentDevice.getColor().equalsIgnoreCase("green")) {
                count_green++;
            } else if (currentDevice.getColor().equalsIgnoreCase("amber")) {
                count_amber++;
            } else if (currentDevice.getColor().equalsIgnoreCase("red")) {
                count_red++;
            }
        }

        statusMap.put("green", count_green);
        statusMap.put("amber", count_amber);
        statusMap.put("red", count_red);

        return statusMap;
    }


}




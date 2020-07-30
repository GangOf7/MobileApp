package com.blackpearl.Models;

public class DeviceList_Item {

    String deviceName, deviceLocation,deviceStatus;

    public DeviceList_Item(String deviceName, String deviceLocation, String deviceStatus) {
        this.deviceName = deviceName;
        this.deviceLocation = deviceLocation;
        this.deviceStatus = deviceStatus;
    }

    public DeviceList_Item() {
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}

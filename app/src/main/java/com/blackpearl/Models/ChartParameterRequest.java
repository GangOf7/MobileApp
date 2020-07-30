package com.blackpearl.Models;

import com.google.gson.annotations.Expose;

public class ChartParameterRequest {

    @Expose
    private int DeviceID;
    @Expose
    private int ParameterID;
    @Expose
    private String Type;


    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public int getParameterID() {
        return ParameterID;
    }

    public void setParameterID(int parameterID) {
        ParameterID = parameterID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}

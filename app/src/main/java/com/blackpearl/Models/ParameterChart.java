package com.blackpearl.Models;

import java.util.ArrayList;

public class ParameterChart {

    String deviceID, deviceName, paramID, paramName;
    ArrayList<ChartData> parameterValues;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getParamID() {
        return paramID;
    }

    public void setParamID(String paramID) {
        this.paramID = paramID;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public ArrayList<ChartData> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(ArrayList<ChartData> parameterValues) {
        this.parameterValues = parameterValues;
    }
}

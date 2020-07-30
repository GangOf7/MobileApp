package com.blackpearl.Controllers;

import java.util.ArrayList;
import java.util.Arrays;

public class ApiConstants {

    public static final String BASE_URL = "https://piratesbay-chipper-roan-rs.eu-gb.mybluemix.net";
    public static final String GET_USER = "/api/UserView/";
    public static final String GET_GRAPH_DATA = "/api/DeviceGrpahs";
    public static final String GET_ACTIVE_PARAMETER_FOR_DEVICE = "/api/DeviceGrpahs/";


    public static final double THRESHOLD_TEMPERATURE = 50;
    public static final double THRESHOLD_PH_LEVEL = 8.5;
    public static final double THRESHOLD_PARTICAL_LEVEL = 500;
    public static final double THRESHOLD_OXYGEN_LEVEL = 80;
    public static final double THRESHOLD_SALINITY = 50;

    public static final ArrayList<String> DEVICE_PARAMETERS = new ArrayList<>(Arrays.asList("Temperature", "PH Level", "Particle Level", "Oxygen Level", "Salinity Level"));



    public static final ArrayList<String> VALUES_TEMPERATURE = new ArrayList<>(Arrays.asList(
            "50.0",
            "50.0",
            "48.0",
            "50.0",
            "50.0",
            "52.0",
            "53.0",
            "53.0",
            "55.0",
            "56.0",
            "58.0",
            "58.0",
            "60.0",
            "60.0",
            "62.0",
            "64.0",
            "64.0",
            "63.0",
            "60.0",
            "58.0",
            "55.0",
            "55.0",
            "53.0",
            "52.0"
    ));

    public static final ArrayList<String> VALUES_PH_LEVEL = new ArrayList<>(Arrays.asList(
            "6.0",
            "8.0",
            "6.0",
            "5.0",
            "6.0",
            "5.0",
            "6.0",
            "5.0",
            "5.0",
            "5.0",
            "5.0",
            "5.0",
            "6.0",
            "6.0",
            "6.0",
            "5.0",
            "6.0",
            "6.0",
            "6.0",
            "5.0",
            "5.0",
            "5.0",
            "5.0",
            "5.0"
    ));

    public static final ArrayList<String> VALUES_TDS = new ArrayList<>(Arrays.asList(
            "900.1",
            "900.1",
            "902.1",
            "902.1",
            "903.1",
            "904.1",
            "906.1",
            "908.1",
            "903.1",
            "905.1",
            "906.1",
            "906.1",
            "907.1",
            "906.1",
            "904.1",
            "903.1",
            "904.1",
            "903.1",
            "904.1",
            "902.1",
            "900.1",
            "900.1",
            "900.1",
            "900.1"
            ));

    public static final ArrayList<String> VALUES_TDO = new ArrayList<>(Arrays.asList(
            "7.12",
            "7.14",
            "7.15",
            "7.10",
            "7.12",
            "7.14",
            "7.16",
            "7.19",
            "7.19",
            "7.19",
            "7.16",
            "7.15",
            "7.13",
            "7.14",
            "7.13",
            "7.14",
            "7.13",
            "7.14",
            "7.12",
            "7.13",
            "7.12",
            "7.14",
            "7.15",
            "7.14"
            ));

    public static final ArrayList<String> VALUES_SALINITY = new ArrayList<>(Arrays.asList(
            "537",
            "538",
            "547",
            "548",
            "547",
            "548",
            "548",
            "547",
            "548",
            "547",
            "548",
            "548",
            "547",
            "545",
            "547",
            "548",
            "547",
            "548",
            "547",
            "548",
            "548",
            "544",
            "544",
            "542"
            ));

    public static final int PARAMETER_ID_TEMPERATURE = 1;
    public static final int PARAMETER_ID_PH = 2;
    public static final int PARAMETER_ID_TDS = 3;
    public static final int PARAMETER_ID_TDO = 4;
    public static final int PARAMETER_ID_SALINITY = 5;


}

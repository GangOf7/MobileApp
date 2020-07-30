package com.blackpearl.Controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.blackpearl.Activity.MainActivity;
import com.blackpearl.Models.ChartParameterRequest;
import com.blackpearl.Models.ParameterChart;
import com.blackpearl.Models.User;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class RetrofitCall {

    public static User getUserDetailsFromAPI() {

        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<User> userdetails = retrofitInterface.getFullUserDetails(ApiConstants.GET_USER + "1");
        userdetails.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User currentUser = response.body();
                Context applicationContext = MainActivity.getContextOfApplication();
                Gson gson = new Gson();
                AppVariables.userObjectString = gson.toJson(currentUser);
                Log.d("JSON:OBJECT_STRING", AppVariables.userObjectString);
                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor shaEditor = sharedPreferences.edit();
                shaEditor.putString("userDetails", AppVariables.userObjectString);
                shaEditor.commit();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                AppVariables.user = new User();
            }
        });
        Context applicationContext = MainActivity.getContextOfApplication();
        //Log.d("API Response", AppVariables.usrObjectString);
        SharedPreferences sharedPreferences;
        sharedPreferences = applicationContext.getSharedPreferences("userDetails", MODE_PRIVATE);
        String userObjString = sharedPreferences.getString("userDetails", "");
        Gson gson = new Gson();
        AppVariables.user = gson.fromJson(userObjString, User.class);
        return AppVariables.user;
    }

    public static ParameterChart getChartDataForParameter(ChartParameterRequest chartParameterRequest) {

        ParameterChart fetchedChart;
        fetchedChart = new ParameterChart();
        AppVariables.chartResponseString = "";
        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<ParameterChart> chartDetails = retrofitInterface.getChartDetails(chartParameterRequest);

        chartDetails.enqueue(new Callback<ParameterChart>() {
            @Override
            public void onResponse(Call<ParameterChart> call, Response<ParameterChart> response) {
                ParameterChart parameterChart = response.body();
                Context applicationContext = MainActivity.getContextOfApplication();
                Gson gson = new Gson();
                AppVariables.chartResponseString = gson.toJson(parameterChart);
                Log.d("CHART_RESPONSE_STRING", AppVariables.chartResponseString);

                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("chartResponse", MODE_PRIVATE);
                SharedPreferences.Editor shaEditor = sharedPreferences.edit();
                shaEditor.putString("chartResponse", AppVariables.chartResponseString);
                shaEditor.commit();
            }

            @Override
            public void onFailure(Call<ParameterChart> call, Throwable t) {
                ParameterChart fetchedChart = new ParameterChart();
            }
        });


        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences;
        sharedPreferences = applicationContext.getSharedPreferences("chartResponse", MODE_PRIVATE);
        String chartResponse = sharedPreferences.getString("chartResponse", "");
        Gson gson = new Gson();
        fetchedChart = gson.fromJson(chartResponse, ParameterChart.class);
        AppVariables.chartResponseString = "";
        return fetchedChart;
    }

    public static ArrayList<Integer> getActiveParametersForDevice(int deviceId) {

        RetrofitInterface retrofitInterface = RetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<ArrayList<Integer>> activeParameters = retrofitInterface.getActiveParameters(ApiConstants.GET_ACTIVE_PARAMETER_FOR_DEVICE + deviceId);

        activeParameters.enqueue(new Callback<ArrayList<Integer>>() {
            @Override
            public void onResponse(Call<ArrayList<Integer>> call, Response<ArrayList<Integer>> response) {
                ArrayList<Integer> activeParameterResponse = response.body();
                Context applicationContext = MainActivity.getContextOfApplication();
                Gson gson = new Gson();
                AppVariables.activeParameterResponseString = gson.toJson(activeParameterResponse);
                Log.d("JSON:OBJECT_STRING", AppVariables.activeParameterResponseString);
                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("activeParameterResponse", MODE_PRIVATE);
                SharedPreferences.Editor shaEditor = sharedPreferences.edit();
                shaEditor.putString("activeParameterResponse", AppVariables.activeParameterResponseString);
                shaEditor.commit();
            }

            @Override
            public void onFailure(Call<ArrayList<Integer>> call, Throwable t) {

            }
        });

        Context applicationContext = MainActivity.getContextOfApplication();
        SharedPreferences sharedPreferences;
        sharedPreferences = applicationContext.getSharedPreferences("activeParameterResponse", MODE_PRIVATE);
        String userObjString = sharedPreferences.getString("activeParameterResponse", "");
        Gson gson = new Gson();
        ArrayList<Integer> integers = gson.fromJson(userObjString, ArrayList.class);
        return integers;

    }
}

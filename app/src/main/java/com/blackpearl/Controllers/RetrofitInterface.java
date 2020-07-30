package com.blackpearl.Controllers;

import com.blackpearl.Models.ChartParameterRequest;
import com.blackpearl.Models.ParameterChart;
import com.blackpearl.Models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @GET
    public Call<User> getFullUserDetails(@Url String url);

    @GET
    public Call<ArrayList<Integer>> getActiveParameters(@Url String url);

    @POST(ApiConstants.GET_GRAPH_DATA)
    Call<ParameterChart> getChartDetails(@Body ChartParameterRequest chartParameterRequest);
}

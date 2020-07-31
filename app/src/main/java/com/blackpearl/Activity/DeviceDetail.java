package com.blackpearl.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.blackpearl.Controllers.ApiConstants;
import com.blackpearl.Controllers.RetrofitCall;
import com.blackpearl.Models.ChartParameterRequest;
import com.blackpearl.Models.Device;
import com.blackpearl.Models.DeviceParameter;
import com.blackpearl.Models.User;
import com.blackpearl.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DeviceDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);

        //setting Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        User user = RetrofitCall.getUserDetailsFromAPI();
        TextView activityTitle = (TextView) findViewById(R.id.text_deviceTitile);
        TextView lastUpdatedOn = (TextView) findViewById(R.id.text_lastUpdatedOn);
        Intent intent = getIntent();
        int devicePosition = intent.getIntExtra("devicePosition", 0);
        final Device device = user.getDeviceDetail().get(devicePosition);

        activityTitle.setText(device.getDevice_Name() + " at " + device.getArea());
        lastUpdatedOn.setText(getString(R.string.device_last_updated_on) + " 29th July, 2020" + device.getLastupdatedon());
        displayCurrentDetails(device);

        final LinearLayout layout_chart_temperature = findViewById(R.id.layout_chart_temperature);
        final LinearLayout layout_chart_phLevel = findViewById(R.id.layout_chart_phLevel);
        final LinearLayout layout_chart_particleLevel = findViewById(R.id.layout_chart_particleLevel);
        final LinearLayout layout_chart_oxygenLevel = findViewById(R.id.layout_chart_oxygenLevel);
        final LinearLayout layout_chart_salinity = findViewById(R.id.layout_chart_salinity);


        final LineChart mChart_temperature = findViewById(R.id.chart_temperature);
        final LineChart mChart_phLevel = findViewById(R.id.chart_phLevel);
        final LineChart mChart_TDS = findViewById(R.id.chart_particleLevel);
        final LineChart mChart_TDO = findViewById(R.id.chart_oxygenLevel);
        final LineChart mChart_salinity = findViewById(R.id.chart_salinityLevel);

        mChart_temperature.setNoDataText("Tap to view performance");

        mChart_phLevel.setNoDataText("Tap to view performance");
        mChart_TDS.setNoDataText("Tap to view performance");
        mChart_TDO.setNoDataText("Tap to view performance");
        mChart_salinity.setNoDataText("Tap to view performance");
/*
        Paint p = mChart_temperature.getPaint(Chart.PAINT_INFO);
        p.setTextSize();*/


        layout_chart_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChartForParameter(device, ApiConstants.PARAMETER_ID_TEMPERATURE, mChart_temperature);
                layout_chart_temperature.invalidate();
            }
        });

        layout_chart_phLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChartForParameter(device, ApiConstants.PARAMETER_ID_PH, mChart_phLevel);
                layout_chart_phLevel.invalidate();
            }
        });
        layout_chart_particleLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChartForParameter(device, ApiConstants.PARAMETER_ID_TDS, mChart_TDS);
                layout_chart_particleLevel.invalidate();
            }
        });
        layout_chart_oxygenLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChartForParameter(device, ApiConstants.PARAMETER_ID_TDO, mChart_TDO);
                layout_chart_oxygenLevel.invalidate();
            }
        });
        layout_chart_salinity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayChartForParameter(device, ApiConstants.PARAMETER_ID_SALINITY, mChart_salinity);
                layout_chart_salinity.invalidate();
            }
        });


        AsyncTaskRunner runner = new AsyncTaskRunner();
        String sleepTime = "3";
        runner.execute(sleepTime);


    }

    private void displayChartForParameter(Device device, final int parameterID, final LineChart mChart) {

        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);

        String deviceId = device.getId();
        Log.d("deviceId", deviceId);

        ChartParameterRequest chartParameterRequest = new ChartParameterRequest();
        chartParameterRequest.setDeviceID(Integer.parseInt(deviceId));
        chartParameterRequest.setParameterID(parameterID);
        chartParameterRequest.setType("Daily");


        ArrayList<Entry> values = new ArrayList<>();

        final HashMap<Integer, String> hourMap = new HashMap<>();
        hourMap.put(1, "12 AM");
        hourMap.put(2, "1 AM");
        hourMap.put(3, "2 AM");
        hourMap.put(4, "3 AM");
        hourMap.put(5, "4 AM");
        hourMap.put(6, "5 AM");
        hourMap.put(7, "6 AM");
        hourMap.put(8, "7 AM");
        hourMap.put(9, "8 AM");
        hourMap.put(10, "9 AM");
        hourMap.put(11, "10 AM");
        hourMap.put(12, "11 AM");
        hourMap.put(13, "12 PM");
        hourMap.put(14, "1 PM");
        hourMap.put(15, "2 PM");
        hourMap.put(16, "3 PM");
        hourMap.put(17, "4 PM");
        hourMap.put(18, "5 PM");
        hourMap.put(19, "6 PM");
        hourMap.put(20, "7 PM");
        hourMap.put(21, "8 PM");
        hourMap.put(22, "9 PM");
        hourMap.put(23, "10 PM");
        hourMap.put(24, "11 PM");

        ArrayList<String> dataArrayList = new ArrayList<>();


        String label = "";
        Drawable drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.layout_gradient_grey_white);
        if (parameterID == ApiConstants.PARAMETER_ID_TEMPERATURE) {
            label = "Temperature";
            drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.layout_gradient_grey_white);
            dataArrayList = ApiConstants.VALUES_TEMPERATURE;
        } else if (parameterID == ApiConstants.PARAMETER_ID_PH) {
            label = "PH Level";
            drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.gradient_amber_white_vertical);
            dataArrayList = ApiConstants.VALUES_PH_LEVEL;
        } else if (parameterID == ApiConstants.PARAMETER_ID_TDS) {
            label = "Total Dissolved Solids";
            drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.gradient_green_white_vertical);
            dataArrayList = ApiConstants.VALUES_TDS;
        } else if (parameterID == ApiConstants.PARAMETER_ID_TDO) {
            label = "Total Dissolved Oxygen";
            drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.gradient_white_blue);
            dataArrayList = ApiConstants.VALUES_TDO;
        } else if (parameterID == ApiConstants.PARAMETER_ID_SALINITY) {
            label = "Salinity";
            drawable = ContextCompat.getDrawable(DeviceDetail.this, R.drawable.gradient_red_white_vertical);
            dataArrayList = ApiConstants.VALUES_SALINITY;
        }

        for (int index = 0; index < dataArrayList.size(); index++) {
            String chartData = dataArrayList.get(index);
            values.add(new Entry((index + 1), Float.parseFloat(chartData)));
        }


        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, label + " as on 29 July, 2020");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGraphGreen));
            set1.setCircleColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGraphGreen));
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {

                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGraphGreen));
            }


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);

            XAxis xAxis = mChart.getXAxis();

            xAxis.setValueFormatter(new IndexAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return hourMap.get((int) value);
                }
            });

            mChart.setData(data);
        }
    }


    private void displayCurrentDetails(Device device) {

        ArrayList<DeviceParameter> deviceParameters = device.getParameterValues();

        LinearLayout card_temperature = (LinearLayout) findViewById(R.id.layout_device_temperature);
        LinearLayout card_phLevel = (LinearLayout) findViewById(R.id.layout_device_phLevel);
        LinearLayout card_particalLevel = (LinearLayout) findViewById(R.id.layout_device_particalLevel);
        LinearLayout card_oxygenLevel = (LinearLayout) findViewById(R.id.layout_device_oxygenLevel);
        LinearLayout card_ammonia = (LinearLayout) findViewById(R.id.layout_device_ammonia_level);
        LinearLayout card_salinity = (LinearLayout) findViewById(R.id.layout_device_salinityLevel);

        TextView tv_currentValue_temperature = (TextView) findViewById(R.id.textView_currentValue_temperature);
        TextView tv_currentValue_phLevel = (TextView) findViewById(R.id.textView_currentValue_phLevel);
        TextView tv_currentValue_particalLevel = (TextView) findViewById(R.id.textView_currentValue_particalLevel);
        TextView tv_currentValue_oxygenLevel = (TextView) findViewById(R.id.textView_currentValue_oxygen);
        TextView tv_currentValue_ammonia = (TextView) findViewById(R.id.textView_currentValue_ammoniaLevel);
        TextView tv_currentValue_salinity = (TextView) findViewById(R.id.textView_currentValue_salinityLevel);

        TextView tv_currentStatus_temperature = (TextView) findViewById(R.id.textView_currentStatus_temperature);
        TextView tv_currentStatus_phLevel = (TextView) findViewById(R.id.textView_currentStatus_phLevel);
        TextView tv_currentStatus_particalLevel = (TextView) findViewById(R.id.textView_currentStatus_particalLevel);
        TextView tv_currentStatus_oxygenLevel = (TextView) findViewById(R.id.textView_currentStatus_oxygenLevel);
        TextView tv_currentStatus_salinity = (TextView) findViewById(R.id.textView_currentStatus_salinityLevel);


        for (DeviceParameter deviceParameter : deviceParameters) {
            String parameterValue = deviceParameter.getLastValue();
            if (deviceParameter.getName().equalsIgnoreCase("Temperature")) {
                tv_currentValue_temperature.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_temperature, deviceParameter.getColor());

            } else if (deviceParameter.getName().equalsIgnoreCase("PH Level")) {
                tv_currentValue_phLevel.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_phLevel, deviceParameter.getColor());

            } else if (deviceParameter.getName().equalsIgnoreCase("Total Dissolved Solid")) {
                tv_currentValue_particalLevel.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_particalLevel, deviceParameter.getColor());

            } else if (deviceParameter.getName().equalsIgnoreCase("Dissolved Oxygen")) {
                tv_currentValue_oxygenLevel.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_oxygenLevel, deviceParameter.getColor());

            } else if (deviceParameter.getName().equalsIgnoreCase("Ammonia Level")) {
                tv_currentValue_ammonia.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_ammonia, deviceParameter.getColor());

            } else if (deviceParameter.getName().equalsIgnoreCase("Salinity Level")) {
                tv_currentValue_salinity.setText(deviceParameter.getLastValue());
                setDeviceParameterBackground(card_salinity, deviceParameter.getColor());
            }
        }
    }

    private void setDeviceParameterBackground(LinearLayout parameterCard, String color) {

        if (color.equalsIgnoreCase("green")) {
            parameterCard.setBackgroundResource(R.drawable.gradient_green_white_vertical);
        } else if (color.equalsIgnoreCase("amber")) {
            parameterCard.setBackgroundResource(R.drawable.gradient_amber_white_vertical);
        } else if (color.equalsIgnoreCase("red")) {
            parameterCard.setBackgroundResource(R.drawable.gradient_red_white_vertical);
        }
    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Fetching data..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(DeviceDetail.this,
                    "Fetching device details",
                    "Please wait.....");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //  finalResult.setText(text[0]);


        }
    }
}

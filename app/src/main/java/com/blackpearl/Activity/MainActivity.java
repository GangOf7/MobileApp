
package com.blackpearl.Activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.blackpearl.Controllers.AppVariables;
import com.blackpearl.Controllers.DeviceConfiguration;
import com.blackpearl.Controllers.RetrofitCall;
import com.blackpearl.Controllers.Reusables;
import com.blackpearl.Models.User;
import com.blackpearl.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;
    Toolbar toolbar;

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();

        //setting Toolbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //getting user details from API
        User currentUser = RetrofitCall.getUserDetailsFromAPI();
        if (Reusables.isNotNullAndEmpty(AppVariables.user.getName())) {
            Toast.makeText(getApplicationContext(), "Welcome, " + AppVariables.user.getName() + " !!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unable to fetch user details", Toast.LENGTH_LONG).show();
        }

        //Setting username in title
        TextView titleUsername = (TextView) findViewById(R.id.textView_username);
        titleUsername.setText(getString(R.string.home_title_username) + " " + AppVariables.user.getName() + " !!");

        //setting homepage pie graph
        setHomepageGraph(currentUser);

        //Adding Listeners to homepage cards
        CardView allDevices = findViewById(R.id.cardView_main_device_all);
        allDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevicesList.class);
                intent.putExtra("listFilter", "ALL");
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                        0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());
            }
        });


        CardView safeDevices = findViewById(R.id.cardView_main_device_Safe);
        safeDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevicesList.class);
                intent.putExtra("listFilter", "GREEN");
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                        0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());
            }
        });


        CardView riskDevices = findViewById(R.id.cardView_main_device_risk);
        riskDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevicesList.class);
                intent.putExtra("listFilter", "RED");
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                        0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());
            }
        });


        CardView inactiveDevices = findViewById(R.id.cardView_main_device_inactive);
        inactiveDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DevicesList.class);
                intent.putExtra("listFilter", "INACTIVE");
                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                        0, view.getWidth(), view.getHeight());
                startActivity(intent, options.toBundle());
            }
        });

        CardView cardView_main_generalGuidelines = findViewById(R.id.cardView_main_generalGuidelines);
        cardView_main_generalGuidelines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("bn");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void setHomepageGraph(User currentUser) {

        HashMap<String, Integer> overAllStatusList = DeviceConfiguration.getOverallStatus(currentUser.getDeviceDetail());

        colorLayoutBasedOnCount(overAllStatusList);

        //Initialising Graph
        PieChart piechart_HomePage = findViewById(R.id.pieChart_Homepage);
        TextView graphTextSafeDevices = (TextView) findViewById(R.id.text_safeDevices);
        TextView graphTextAmberDevices = (TextView) findViewById(R.id.text_amberDevices);
        TextView graphTextRedDvices = (TextView) findViewById(R.id.text_riskDevices);
        graphTextSafeDevices.setVisibility(View.GONE);
        graphTextAmberDevices.setVisibility(View.GONE);
        graphTextRedDvices.setVisibility(View.GONE);

        ArrayList<PieEntry> chartValues = new ArrayList<>();
        int deviceCount = Integer.parseInt(currentUser.getDeviceCount());
        List<Integer> colorArray = new ArrayList<>();

        if (overAllStatusList.get("green") > 0) {


            chartValues.add(new PieEntry(overAllStatusList.get("green"), "Safe"));
            graphTextSafeDevices.setVisibility(View.VISIBLE);
            graphTextSafeDevices.setSelected(true);
            colorArray.add(ContextCompat.getColor(getApplicationContext(), R.color.color_green_teal));
            if (deviceCount == overAllStatusList.get("green")) {
                graphTextSafeDevices.setText(getString(R.string.home_chart_text_device_safe_all));
            } else {
                graphTextSafeDevices.setText(overAllStatusList.get("green") + " " + getString(R.string.home_chart_text_device_safe));
            }

        } else if (overAllStatusList.get("amber") > 0) {
            colorArray.add(ContextCompat.getColor(getApplicationContext(), R.color.colorStatusAmber));
            chartValues.add(new PieEntry(overAllStatusList.get("amber"), "Warning"));
            graphTextAmberDevices.setVisibility(View.VISIBLE);

            if (deviceCount == overAllStatusList.get("amber")) {
                graphTextAmberDevices.setText(getString(R.string.home_chart_text_device_amber_all));
            } else {
                graphTextAmberDevices.setText(overAllStatusList.get("amber") + " " + getString(R.string.home_chart_text_device_amber));
            }

        } else if (overAllStatusList.get("red") > 0) {
            colorArray.add(ContextCompat.getColor(getApplicationContext(), R.color.color_red_dark));
            chartValues.add(new PieEntry(overAllStatusList.get("red"), "At Risk"));
            graphTextRedDvices.setVisibility(View.VISIBLE);

            if (deviceCount == overAllStatusList.get("red")) {
                graphTextRedDvices.setText(getString(R.string.home_chart_text_device_risk_all));
            } else {
                graphTextRedDvices.setText(overAllStatusList.get("red") + " " + getString(R.string.home_chart_text_device_risk));
            }
        }


        PieDataSet dataSet = new PieDataSet(chartValues, "");
        dataSet.setColors(colorArray);

        PieData chartData = new PieData(dataSet);
        piechart_HomePage.setData(chartData);
        piechart_HomePage.setDrawSliceText(false);
        piechart_HomePage.setCenterText("Device Status");
        piechart_HomePage.getDescription().setEnabled(false);


    }

    private void colorLayoutBasedOnCount(HashMap<String, Integer> overAllStatusList) {

        //LinearLayout graphLayout = findViewById(R.id.linearlayout_card_homepage_graph);
        RelativeLayout userNameLayout = findViewById(R.id.layout_text_username);
        LinearLayout layout_graph_username = findViewById(R.id.text_userName);

        int countGreen = overAllStatusList.get("green");
        int countAmber = overAllStatusList.get("amber");
        int countRed = overAllStatusList.get("red");

        if (countGreen >= countAmber && countGreen >= countRed)
            layout_graph_username.setBackgroundResource(R.drawable.gradient_green_white_vertical);

        else if (countAmber >= countGreen && countAmber >= countRed)
            layout_graph_username.setBackgroundResource(R.drawable.gradient_amber_white_vertical);

        else
            layout_graph_username.setBackgroundResource(R.drawable.gradient_red_white_vertical);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.layout_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.translate:
                Locale locale = new Locale("bn");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

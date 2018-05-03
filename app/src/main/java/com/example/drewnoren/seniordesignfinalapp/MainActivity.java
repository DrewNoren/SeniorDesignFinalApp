package com.example.drewnoren.seniordesignfinalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {


    Button buttonDisplay;
    Button buttonStats;
    //List of ArrayLists to save SQL Data to
    public List<String> totalTimeStats = new ArrayList<String>();   // For Stats
    public List<String> totalTempStats = new ArrayList<String>();  // For Stats
    public List<String> totalTimeDisplay = new ArrayList<String>();   // For Display
    public List<String> totalTempDisplay = new ArrayList<String>();  // For Display

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // For Display

        buttonDisplay = (Button)findViewById(R.id.buttonD);
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDBforList updateDBL = new updateDBforList();
                updateDBL.execute();

            }
        } );

        // For Stats

        buttonStats = (Button)findViewById(R.id.buttonS);
        buttonStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDBforStats updateDBS = new updateDBforStats();
                updateDBS.execute();
            }
        } );

    }


    // Toast to update App
    public void showToast(String show) {
        Toast newToast = Toast.makeText(getApplicationContext(), show, Toast.LENGTH_LONG);
        newToast.show();
    }


    //This is for updating arraylists for Display

    public class updateDBforList extends AsyncTask<Void, Void, Void> {

        String msg;
        // show toast before updating database
        @Override
        protected void onPreExecute() {
            showToast("Establishing Connection");
        }

        // show message for completion after data is updated
        @Override
        protected void onPostExecute(Void aVoid) {
            showToast(msg);
            try {
                Intent ListTab = new Intent(MainActivity.this, ListActivity.class);
                ListTab.putStringArrayListExtra("totalTimeDisplay", (ArrayList<String>) totalTimeDisplay);
                ListTab.putStringArrayListExtra("totalTempDisplay", (ArrayList<String>) totalTempDisplay);
                startActivity(ListTab);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Establishing a connection to the database
        @Override
        protected Void doInBackground(Void... params) {
            // define details for connection
            String host = "drewnorendbseniordesign.c9qjhr31lxtb.us-east-2.rds.amazonaws.com"; //Endpoint
            String database = "HealthDB"; //DB name
            String user = "drewnoren"; // Username
            String password = "drewnorenpassword"; //Password for Database


            //JDBC Driver connection with URL
            String url = "jdbc:mysql://" + host + ":3306/" + database + "?user=" + user + "&password=" + password;


            // Load driver for JDBC
            try {
                Class.forName("com.mysql.jdbc.Driver");
                msg = "Driver loaded";
            }

            // Throw exception if driver is not loaded
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                e.getMessage();
                msg = "Driver did not load";
            }

            // Create connection object
            Connection connection = null;

            //Connect to  database
            try {
                connection = DriverManager.getConnection(url);
                msg = "Successful Connection";
            }
            catch (SQLException e) {
                e.getMessage();
                e.printStackTrace();
                msg = "Bad Connection";
            }

            try {
                // Get all the data
                Statement stmt = connection.createStatement();
                String getDataQuery = "SELECT * FROM HealthDataReal"; //Table Name
                ResultSet getrset = stmt.executeQuery(getDataQuery);
                while (getrset.next()) {

                    totalTimeDisplay.add(getrset.getString(1));
                    totalTempDisplay.add(getrset.getString(2));
                }
                connection.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                msg = "The query was unsuccessful";
            }
            return null;
        }
    }







    //Same, but for Updating the data for Stats activity
    public class updateDBforStats extends AsyncTask<Void,Void,Void> {
        String msg;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                Intent StatsTab = new Intent(MainActivity.this, HealthStatisticsActivity.class);
                StatsTab.putStringArrayListExtra("totalTimeStats", (ArrayList<String>) totalTimeStats);
                StatsTab.putStringArrayListExtra("totalTempStats", (ArrayList<String>) totalTempStats);
                startActivity(StatsTab);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String host = "drewnorendbseniordesign.c9qjhr31lxtb.us-east-2.rds.amazonaws.com";
            String database = "HealthDB";
            String user = "drewnoren";
            String password = "drewnorenpassword";
            String url = "jdbc:mysql://" + host + ":3306/" + database + "?user=" + user + "&password=" + password;


            try {
                Class.forName("com.mysql.jdbc.Driver");
                msg = "Driver successfully loaded";
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                e.getMessage();
                msg = "Driver did not load";
            }

            //Time to Establish Connection to DB
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url);
                msg = "Successful Connection";
            }
            catch (SQLException e) {
                e.getMessage();
                e.printStackTrace();
                msg = "Bad Connection";
            }
            //Query to write over
            try {
                Statement stmt = connection.createStatement();
                String getDataQuery = "SELECT * FROM HealthDataReal"; //Table Name
                ResultSet getrset = stmt.executeQuery(getDataQuery);
                while (getrset.next()) {
                    totalTimeStats.add(getrset.getString(1));
                    totalTempStats.add(getrset.getString(2));
                }
                connection.close();
            }
            catch(Exception e){
                e.printStackTrace();
                msg = e.getMessage();
            }
            return null;
        }
    }


}


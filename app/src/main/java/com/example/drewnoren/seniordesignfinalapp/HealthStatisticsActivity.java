package com.example.drewnoren.seniordesignfinalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import android.widget.TextView;

public class HealthStatisticsActivity extends AppCompatActivity {

    public int[] intTimes;
    public double[] doubleTemps;
    public double minimum,maximum,average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_statistics);

        Intent intent1 = getIntent();
        TextView tvAverage = (TextView)findViewById(R.id.t_average);
        TextView tvMin = (TextView)findViewById(R.id.t_minimum);
        TextView tvMax = (TextView)findViewById(R.id.t_maximum);



        ArrayList<String> timesStats = intent1.getStringArrayListExtra("totalTimeStats");
        ArrayList<String> tempsStats = intent1.getStringArrayListExtra("totalTempStats");

        intTimes = new int[timesStats.size()];
        doubleTemps = new double[timesStats.size()];

        for(int i = 0; i < timesStats.size();i++) {
            intTimes[i] = Integer.parseInt(timesStats.get(i));
            doubleTemps[i] = Double.parseDouble(tempsStats.get(i));
        }

        //Time to do stats

        minimum = 99999;
        for(int i = 0; i < intTimes.length;i++)
        {
            if(maximum<=doubleTemps[i]) // Check max
                maximum=doubleTemps[i];

            if(minimum>=doubleTemps[i]) // Check min
                minimum=doubleTemps[i];

            average+=doubleTemps[i]; //Add value at i to total average
        }

        average = average/intTimes.length;

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        tvAverage.setText(df.format(average));
        tvMax.setText(String.valueOf(maximum));
        tvMin.setText(String.valueOf(minimum));
    }
}

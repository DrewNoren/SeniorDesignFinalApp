package com.example.drewnoren.seniordesignfinalapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    Context ct;
    public int[] intTimes;
    public double[] doubleTemps;
    ListView listView,listView2;
    Activity activity;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intentList = getIntent();

        ArrayList<String> timesList = intentList.getStringArrayListExtra("totalTimeDisplay");
        ArrayList<String> tempsList = intentList.getStringArrayListExtra("totalTempDisplay");
        intTimes = new int[timesList.size()];
        doubleTemps = new double[timesList.size()];

        for(int i = 0; i < timesList.size();i++) {
            intTimes[i] = Integer.parseInt(timesList.get(i));
            doubleTemps[i] = Double.parseDouble(tempsList.get(i));
        }

        listView = (ListView)findViewById(R.id.displaylistview);
        //Object Var's
        int id,time;
        double temp;
        ArrayList<DataSlot> dataList = new ArrayList<DataSlot>();

        for(int i = 0; i < intTimes.length;i++)
        {
            id=intTimes[i];
            time=intTimes[i];
            temp=doubleTemps[i];
            DataSlot data = new DataSlot(id,time,temp);
            dataList.add(data);
        }

        //adapter = new DataAdapter(this, R.layout.rows,dataList);
        //listView.setAdapter(adapter);

        listView2 = (ListView)findViewById(R.id.displaylistview);
        listView2.setAdapter(new yourAdapter(ListActivity.this,dataList));


    }
    class yourAdapter extends BaseAdapter {

        Context context;
        String[] data;
        ArrayList<DataSlot> myDataList;
        private LayoutInflater inflater = null;

        public yourAdapter(Context context, ArrayList<DataSlot> dataList) {
            // TODO Auto-generated constructor stub
            this.context = context;
            this.myDataList= dataList;
           // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return myDataList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //View vi = convertView;
            DataSlot dataSlot = myDataList.get(position);
            convertView = LayoutInflater.from(context).inflate(R.layout.rows,null);
            //if (vi == null)
            //    vi = inflater.inflate(R.layout.row, null);
            TextView tx_id = (TextView) convertView.findViewById(R.id.t_id);
            TextView tx_time = (TextView) convertView.findViewById(R.id.t_time);
            TextView tx_temp = (TextView) convertView.findViewById(R.id.t_temp);

            tx_id.setText("ID: "+ Integer.toString(dataSlot.getId()));
            tx_time.setText("Time: "+Integer.toString(dataSlot.getTime()));
            tx_temp.setText("Temp(C): "+Double.toString(dataSlot.getTemp()));
            //TextView text = (TextView) vi.findViewById(R.id.text);
            //text.setText(data[position]);
            return convertView;
        }
    }
}
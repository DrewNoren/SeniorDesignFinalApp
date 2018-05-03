package com.example.drewnoren.seniordesignfinalapp;

/**
 * Created by Drew Noren on 4/25/2018.
 */

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Drew Noren on 4/18/2018.
 */

public class DataAdapter extends ArrayAdapter<DataSlot> {

    List list = new ArrayList();
    private Context ct;
    int mResource;

    public DataAdapter(Context context, int resource, ArrayList<DataSlot> objects) {
        super(context, resource,objects);
        ct = context;
        mResource = resource;
    }

    public void add(DataSlot object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Information in Rows
        int id = getItem(position).getId();
        int time = getItem(position).getTime();
        double temp = getItem(position).getTemp();

        DataSlot dataSlot = new DataSlot(id, time, temp);

        LayoutInflater inflater = LayoutInflater.from(ct);
        convertView = inflater.inflate(mResource, parent, false);
        //convertView = inflater.inflate(R.layout.displayrows, null);

        TextView tx_id = (TextView) convertView.findViewById(R.id.t_id);
        TextView tx_time = (TextView) convertView.findViewById(R.id.t_time);
        TextView tx_temp = (TextView) convertView.findViewById(R.id.t_temp);

        tx_id.setText(Integer.toString(id));
        tx_time.setText(Integer.toString(time));
        tx_temp.setText(Double.toString(temp));

        return convertView;
    }
}
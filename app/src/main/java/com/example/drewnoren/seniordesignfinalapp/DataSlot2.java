package com.example.drewnoren.seniordesignfinalapp;

/**
 * Created by Drew Noren on 4/26/2018.
 */

public class DataSlot2 {

    private String id,time,temp;

    public DataSlot2(String id, String time, String temperature) {

        this.setId(id);
        this.setTime(time);
        this.setTemp(temperature);
    }


    //Getter's and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temperature) {
        this.temp = temperature;
    }


}


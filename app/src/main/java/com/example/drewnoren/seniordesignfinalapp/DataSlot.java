package com.example.drewnoren.seniordesignfinalapp;

/**
 * Created by Drew Noren on 4/25/2018.
 */

    public class DataSlot {
        private int id,time;
        private double temp;

        public DataSlot(int id, int time, double temperature) {

            this.setId(id);
            this.setTime(time);
            this.setTemp(temperature);
        }


        //Getter's and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temperature) {
            this.temp = temperature;
        }


    }


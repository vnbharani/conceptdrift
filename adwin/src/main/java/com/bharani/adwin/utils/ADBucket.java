package com.bharani.adwin.utils;

public class ADBucket {

    private int capacity;
    private double content;

    ADBucket(int capacity,double content){
        this.capacity = capacity;
        this.content = content;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public void incCapacity(){
        this.capacity++;
    }

}

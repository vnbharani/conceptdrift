package com.bharani.adwin.utils;

public class ADBuckets {

    private ADBucket[] buckets;
    private ADBuckets prev;
    private ADBuckets next;
    private int count;
    private double sum;

    //Initialize all buckets
    //Initialize all prev and next to do the looping
    ADBuckets(int size, ADBuckets prev, ADBuckets next) {

        buckets = new ADBucket[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new ADBucket(0, 0);
        }

        if (prev != null) {
            prev.setNext(this);
            this.setPrev(prev);
        }
        if (next != null) {
            this.setNext(next);
            next.setPrev(this);
        }
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ADBucket[] getBuckets() {
        return buckets;
    }

    public ADBuckets getPrev() {
        return prev;
    }

    public void setPrev(ADBuckets prev) {
        this.prev = prev;
    }

    public ADBuckets getNext() {
        return next;
    }

    public void setNext(ADBuckets next) {
        this.next = next;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setBuckets(ADBucket[] buckets) {
        this.buckets = buckets;
    }
}

package com.bharani.adwin.utils;


/**
 * This algorithm detects the change in input using ADWIN algorithm
 */
public class ADWinAlgorithm {
    private int windowLength;
    private int minWindowLength;
    private double windowVariance;
    private double windowSum;
    private double delta;
    private int t;
    private int minSubWindowLength;
    private int minT;
    private int M;
    private ADBucketsList adBucketsList;

    ADWinAlgorithm(){

        delta = 0.02;
        minSubWindowLength = 5;
        minWindowLength = 10;
        minT= 35;
        M=5;
        adBucketsList = null;
        windowLength =0;
        windowSum =0;
    }


    public int getWindowLength() {
        return windowLength;
    }

    public void setWindowLength(int windowLength) {
        this.windowLength = windowLength;
    }

    public int getMinWindowLength() {
        return minWindowLength;
    }

    public void setMinWindowLength(int minWindowLength) {
        this.minWindowLength = minWindowLength;
    }

    public double getWindowVariance() {
        return windowVariance;
    }

    public void setWindowVariance(double windowVariance) {
        this.windowVariance = windowVariance;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getMinSubWindowLength() {
        return minSubWindowLength;
    }

    public void setMinSubWindowLength(int minSubWindowLength) {
        this.minSubWindowLength = minSubWindowLength;
    }

    public int getMinT() {
        return minT;
    }

    public void setMinT(int minT) {
        this.minT = minT;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public ADBucketsList getAdBucketsList() {
        return adBucketsList;
    }

    public void setAdBucketsList(ADBucketsList adBucketsList) {
        this.adBucketsList = adBucketsList;
    }


    public boolean insertInput(double value){
        if(adBucketsList == null)
            adBucketsList = new ADBucketsList();
        adBucketsList.addAnInput(value);
        windowLength++;
        t++;
        windowSum+=value;
        double winDowMean = windowSum/windowLength;
        return isChangeDetected();
    }

    public boolean isChangeDetected(){

        //Go to the end of the bucketList
        boolean isChanged = false;
        //Till we see there are no changes
        //Only after few iterations
        if( t%minT ==0 && windowLength >= minWindowLength) {
            boolean reducedWindowLengthFlag = true;
            while(reducedWindowLengthFlag) {
                reducedWindowLengthFlag = false;
                int n0=0;
                int n1=windowLength;
                double sum0=0;
                double sum1=windowSum;
                //get last bucket
                ADBuckets buckets = adBucketsList.getHead();
                while (buckets.getNext() != null)
                    buckets = buckets.getNext();
                //Find the reduced window
                boolean isExit  = false;
                while (!isExit && buckets.getPrev() != null) {
                    ADBucket[] bucketsList = buckets.getBuckets();
                    for (int i =  buckets.getCount()-1; i <=0; i--) {
                        n0+=bucketsList[i].getCapacity();
                        n1-=bucketsList[i].getCapacity();
                        sum0+=bucketsList[i].getContent();
                        sum1-=bucketsList[i].getContent();
                        double mudiff = (sum0/n0) -(sum1/n1);
                        if(n0>minWindowLength&& n1>minWindowLength &&reduceExpression(n0,n1,mudiff)){
                            ADBuckets deletedBucket = adBucketsList.deleteLastBucket();
                            windowLength-=deletedBucket.getCount();
                            windowSum-=deletedBucket.getSum();
                            isChanged = true;
                            isExit = true;
                            reducedWindowLengthFlag = true;
                            break;
                        }
                    }
                }
            }
        }
        return isChanged;



    }

    public boolean reduceExpression(int n0, int n1, double muDiff){

        double m = 1.0/((1.0/n0)+(1.0/n1));
        double logValue = Math.log((4*windowLength)/delta);
        double epcilonCut = Math.sqrt((1/(2*m))*logValue);
        //    double logValue = Math.log((2*windowLength)/delta);
        //  double epcilonCut = Math.sqrt((2/windowLength)*windowVariance*logValue)+(2/(3*m))*logValue;
        return Math.abs(muDiff) >epcilonCut;
    }
}

package com.bharani.adwin.utils;

public class ADBucketsList {

    private ADBuckets head;
    private int M;

    ADBucketsList(){
        M=5;
        head = new ADBuckets(M+1,null,null);
    }

    ADBucketsList(int eachBucketSize){
        M=eachBucketSize;
        head = new ADBuckets(M+1,null,null);
    }

    public ADBuckets getHead() {
        return head;
    }

    public void setHead(ADBuckets head) {
        this.head = head;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    ADBucketsList(int eachBucketSize, ADBuckets head){
        M=eachBucketSize;
        this.head = head;
    }


    public void addAnInput(double value){

        // Insert at the head of the row
        //Create a bucket
        ADBucket newBucket = new ADBucket(1,value);
        head.getBuckets()[head.getCount()] = newBucket;
        head.setSum(head.getSum()+value);
        head.setCount(head.getCount()+1);
        checkForCompression(head);
    }

    public void addInputAtNextBucket(ADBuckets curr,ADBuckets next, ADBucket value){

        if(next == null) {
            next = new ADBuckets(M + 1, curr, null);
        }
        next.getBuckets()[next.getCount()] = value;
        next.setSum(next.getSum()+value.getContent());
        next.setCount(next.getCount()+1);

    }

    public void checkForCompression(ADBuckets head){

        if(head == null)
            return;
        if(head.getCount()==M+1){
            //get the Oldest buckets
            ADBucket b0 = head.getBuckets()[0];
            ADBucket b1 = head.getBuckets()[1];

            ADBucket compressedBucket = new ADBucket(0,0);
            compressedBucket.setContent(b0.getContent()+b1.getContent());
            compressedBucket.setCapacity(b0.getCapacity()+b1.getCapacity());
            addInputAtNextBucket(head,head.getNext(),compressedBucket);

            // Move the newset to oldest
            for(int i=0;i<=M-2;i++){
                head.getBuckets()[i]= head.getBuckets()[i+2];
            }
            //Reduce count
            head.setCount(head.getCount()-2);
        }
        checkForCompression(head.getNext());
    }

    //Delete last bucket
    public ADBuckets deleteLastBucket(){
        ADBuckets curr = head;
        while(curr.getNext()!=null){
            curr = curr.getNext();
        }
        ADBuckets deletedBuckets = curr;
        curr.getPrev().setNext(null);
        return deletedBuckets;
    }

}

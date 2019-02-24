package com.bharani.adwin.utils;

import org.junit.Test;

public class ADBucketTest {

    public void preapreHead(){

    ADBuckets buc1= new ADBuckets(5,null,null);
    ADBuckets buc2 = new ADBuckets(5,buc1,null);
    ADBuckets buc3 = new ADBuckets(5,buc2,null);

    ADBucketsList list = new ADBucketsList(4,buc1);
       buc1.toString();
       buc2.toString();
       buc3.toString();

    }

    @Test
    public void checkC(){
        ADBucketsList list = new ADBucketsList(5);
        double n=2;
        for(int i=0;i<15;i++){
            list.addAnInput(n-(i*0.1));
        }
        System.out.println(list);
    }

}

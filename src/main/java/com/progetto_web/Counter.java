package com.progetto_web;

import java.io.Serializable;
import java.util.Calendar;

public class Counter implements Serializable {
    int count=0;
    Calendar time= Calendar.getInstance();

    public String toString(){
        StringBuffer s=null;
        if(count==0) {
            s = new StringBuffer("<p>0</p>");
        } else {
                s=new StringBuffer("<p><br><br><br><br>hits= ");
                s.append(count);
            }
        return s.toString();
    }

    public synchronized int getValue(){
        return count;
    }

    public synchronized void setValue(int value){
        count=value;
        time=Calendar.getInstance();}

}

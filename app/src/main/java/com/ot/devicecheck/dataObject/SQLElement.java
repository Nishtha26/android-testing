package com.ot.devicecheck.dataObject;

/**
 * Created by Pranjal Gupta on 09-11-2017.
 */

public class SQLElement {

    String element;
    String verdict;
    String timeStamp;

    public SQLElement(){

    }

    public SQLElement (String element, String verdict, String timeStamp) {
        this.element=element;
        this.verdict=verdict;
        this.timeStamp=timeStamp;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element){
        this.element=element;
    }

    public String getVerdict(){
        return verdict;
    }

    public void setVerdict(String verdict){
        this.verdict=verdict;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp){
        this.timeStamp=timeStamp;
    }
}

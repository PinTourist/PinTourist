package com.pintourist.pintourist.pintourist.Object;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fedebyes on 09/05/17.
 */
@IgnoreExtraProperties
public class Question {

     private String Q="";
    private String A1="";
    private String A2="";
    private String A3="";
    private String A4="";

    public Question(){

    }
    public Question(String Q,String A1,String A2,String A3,String A4){
        this.Q=Q;
        this.A1=A1;
        this.A2=A2;
        this.A3=A3;
        this.A4=A4;
    }
    public String getQ(){return  Q;}
    public String getA1(){return  A1;}
    public String getA2(){return  A2;}
    public String getA3(){return  A3;}
    public String getA4(){return  A4;}

}

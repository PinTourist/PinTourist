package com.pintourist.pintourist.pintourist.Object;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by fedebyes on 09/05/17.
 */
@IgnoreExtraProperties
public class Question {

     private String q="";
    private String a1="";
    private String a2="";
    private String a3="";
    private String a4="";
    private String ans="";

    public Question(){

    }
    public Question(String q,String a1,String a2,String a3,String a4, String ans){
        this.q=q;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.ans=ans;
    }
    public String getQ(){return  q;}
    public String getA1(){return  a1;}
    public String getA2(){return  a2;}
    public String getA3(){return  a3;}
    public String getA4(){return  a4;}

    public String getAns() {
        return ans;
    }
}

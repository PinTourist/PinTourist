package com.pintourist.pintourist.pintourist.Object;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fedebyes on 27/04/17.
 */

public class Pin {

   private String Name="";
   private String Comment="";
    private Question q=new Question();
    private List<Question> questionList=new ArrayList<Question>(){
        {
            add(q);
        }
    };
    /*private Map question=new HashMap<String,List<Question>>(){
        {
            put("ciao",questionList);
        }
    };*/

    private int id=0;
    private double Lat=0;
    private double Lng=0;

    private LatLng LatLng ;


    public Pin(){

        //just to be here
    }

    public Pin(double Lat, double Lng, String Name, String Comment, List<Question> questionList){
        this.Lat=Lat;
        this.Lng=Lng;
        this.Name=Name;
        this.Comment=Comment;
        //this.question= (HashMap)  question;
        this.questionList=questionList;




    }
    public LatLng getLatLng() {
        return new LatLng(Lat,Lng);

    }
    public double getLat(){
        return this.Lat;
    }
    public double getLng(){
        return this.Lng;
    }
    public String getName(){
        return this.Name;
    }
    public String getComment(){
        return this.Comment;
    }
    public List<Question> getQuestionList(){return this.questionList;}
    //public HashMap getQuestionsList(){return (HashMap) question;}
    //public void setQuestions(List<Object> question){this.question=question;}




}

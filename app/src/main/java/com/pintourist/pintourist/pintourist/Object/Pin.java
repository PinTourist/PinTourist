package com.pintourist.pintourist.pintourist.Object;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fedebyes on 27/04/17.
 */
@IgnoreExtraProperties
public class Pin {

   private String name;
   private String comment;
    private Question q=new Question();
    private String picURL;
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
    private double lat=0;
    private double lng=0;

    private LatLng LatLng ;


    public Pin(){

        //just to be here
    }

    public Pin(double lat, double lng, String name,String picURL, String comment, List<Question> questionList){
        this.lat=lat;
        this.lng=lng;
        this.name=name;
        this.comment=comment;
        //this.question= (HashMap)  question;
        this.questionList=questionList;
        this.picURL=picURL;




    }
    public LatLng getLatLng() {
        return new LatLng(lat,lng);

    }
    public String getPicURL(){
        return this.picURL;
    }
    public double getLat(){
        return this.lat;
    }
    public double getLng(){
        return this.lng;
    }
    public String getName(){
        return this.name;
    }
    public String getComment(){
        return this.comment;
    }
    public List<Question> getQuestionList(){return this.questionList;}
    //public HashMap getQuestionsList(){return (HashMap) question;}
    //public void setQuestions(List<Object> question){this.question=question;}




}

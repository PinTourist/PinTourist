package com.pintourist.pintourist.pintourist.Object;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedebyes on 27/04/17.
 */

public class Pin {

   private String Name;
   private String Comment;
    private ArrayList<Question> questions;

    private double Lat=0;
    private double Lng=0;

    private LatLng LatLng ;


    public Pin(){

        //just to be here
    }

    public Pin(double Lat, double Lng, String Name, String Comment, ArrayList<Question> questions){
        this.Lat=Lat;
        this.Lng=Lng;
        this.Name=Name;
        this.Comment=Comment;
        this.questions=questions;



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
    public ArrayList<Question> getQuestions(){return questions;}
}

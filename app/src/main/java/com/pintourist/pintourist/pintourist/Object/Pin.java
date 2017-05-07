package com.pintourist.pintourist.pintourist.Object;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by fedebyes on 27/04/17.
 */

public class Pin {

   private String Name;
   private String Comment;
    public String getName(){
        return this.Name;
    }
    public String getComment(){
        return this.Comment;
    }
    private double Lat=0;
    private double Lng;

    private LatLng LatLng ;

    public LatLng getLatLng() {
       return LatLng;

    }
    public double getLat(){
        return this.Lat;
    }
    public Pin(){


    }
    public Pin(double Lat, double Lng){
        this.Lat=Lat;
        this.Lng=Lng;
        this.LatLng = new LatLng(Lat,Lng);
    }
}

package com.pintourist.pintourist.pintourist;

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
    private double Lat;
    private double Lng;

    private LatLng LatLng = new LatLng(Lat,Lng);

    public com.google.android.gms.maps.model.LatLng getLatLng() {
        return LatLng;
    }
    Pin(){


    }
}

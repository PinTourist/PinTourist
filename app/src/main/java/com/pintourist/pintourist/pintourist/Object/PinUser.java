package com.pintourist.pintourist.pintourist.Object;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by fedebyes on 15/05/17.
 */

public class PinUser {
    public String userName;
    public String email;
    public double points;
    public List<Pin> conqueredPin;

    public PinUser(){

    }
    public PinUser(String userName, String email){
        this.userName=userName;
        this.email=email;
    }


}

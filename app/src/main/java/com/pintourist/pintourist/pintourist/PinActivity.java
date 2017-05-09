package com.pintourist.pintourist.pintourist;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pintourist.pintourist.pintourist.Object.Pin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PinActivity extends AppCompatActivity {

    private String TAG= "Pin Activity";
    private DatabaseReference database;
    private Pin pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pin);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));



        //creation

        //things of activity



        //get extras
        final double Lat =(double) getIntent().getExtras().get("Lat");
        final double Lng= (double) getIntent().getExtras().get("Lng");
        Log.d(TAG, "Lat:"+Lat+"  Lng:"+Lng);
        //get Info from DB
        database = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ref = database;


        Query query= ref.child("pins");
        query.orderByChild("lat").equalTo(Lat).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"number of found pins"+dataSnapshot.getChildrenCount());
                if(dataSnapshot.exists()){
                    final ArrayList<Pin> pins = new ArrayList<Pin>();
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        // Pin pin= dataSnapshot.getValue(Pin.class);
                        pins.add(singleSnapshot.getValue(Pin.class));
                        pin=pins.get(0);
                        //if (Lat==pin.getLat() && Lng==pin.getLng()){

                        //pin.getName();
                        //getSupportActionBar().setTitle(pin.getName());
                        collapsingToolbarLayout.setTitle(pin.getName());
                        Log.d(TAG, "Pin with " + pin.getName()+ " found");

                        //Log.d(TAG, "Question 1 of pin" + pin.getQuestions().getClass()+ " found");
                        //    break;
                        //}


                    }
                }
                else {
                    Log.d(TAG,"The pin with Lat "+Lat+" doesn't exists");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });







        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Pin");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

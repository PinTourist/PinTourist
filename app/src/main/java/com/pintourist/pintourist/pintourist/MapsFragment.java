package com.pintourist.pintourist.pintourist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pintourist.pintourist.pintourist.Object.Pin;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 122;
    private DatabaseReference database;
    private String TAG= "MapsFragment";
    private String[] PERMISSIONS = new String[]{ ACCESS_FINE_LOCATION,
           ACCESS_COARSE_LOCATION};
    private View mapView ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GoogleMap googleMap;

    public MapsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getMapAsync(this);



    }





    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        permissionRequest();
        setUpMap(googleMap);

        Log.d(TAG,"Maps Ready");




    }

    private void setUpMap(final GoogleMap googleMap) {

        //googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnInfoWindowClickListener(this);

        //permissionRequest();
        LatLng ROMA = new LatLng(41.9000, 12.5000);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ROMA)      // Sets the center of the map to mi position
                .zoom(10)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setPadding(0,180,0,0);
        LatLng Marker1 = new LatLng(41.9000, 12.6000);
        LatLng Marker2 = new LatLng(41.8000, 12.5000);
        final LatLng Marker3 = new LatLng(41.9000, 12.4000);
        googleMap.addMarker(new MarkerOptions().position(Marker1)
                .title("Marker1"));
        googleMap.addMarker(new MarkerOptions().position(Marker2)
                .title("Marker2"));
        googleMap.addMarker(new MarkerOptions().position(Marker3)
                .title("Marker3"));
        //Firebasecode
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //offline capabilities
        database = FirebaseDatabase.getInstance().getReference();


        DatabaseReference ref = database;
        final List<Pin> pins=new ArrayList<Pin>();
        Log.d(TAG, String.valueOf(pins.size()));
        Query query= ref.child("pin").child("pins");
        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, String.valueOf(pins.size()));
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    pins.add(singleSnapshot.getValue(Pin.class));
                    Log.d(TAG,"new Pin added");
                    Log.d(TAG, String.valueOf(pins.size()));
                    Pin pin=singleSnapshot.getValue(Pin.class);

                    Log.d(TAG, String.valueOf(pin.getLatLng()));
                    LatLng position= pin.getLatLng();
                    googleMap.addMarker(new MarkerOptions().position(pin.getLatLng())
                            .title(pin.getName()));

                    //LatLng MarkerTemp = new LatLng(singleSnapshot.getValue());
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });



    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        /*Log.d(TAG, "Infowindow clicked");
        final Fragment fragment= new PinFragment();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        */

        Intent intent = new Intent(this.getActivity(), PinActivity.class);
        LatLng position= marker.getPosition();
        intent.putExtra("Lat", position.latitude);
        intent.putExtra("Lng", position.longitude);
        startActivity(intent);

    }




    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();

       // setUpMapIfNeeded();

    }

    private void setUpMapIfNeeded() {
        if (googleMap == null) {
            Log.d(TAG, "SetupMapIfNeededCalled");
            getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap gMap) {
                    googleMap = gMap;
                    setUpMap(googleMap);
                    gMap.getUiSettings().setMapToolbarEnabled(true);
                    permissionRequest();

                }
            });
        }
    }

    private void permissionRequest() {

        //googleMap.setMyLocationEnabled(true);
        if (Build.VERSION.SDK_INT < 23) {
            if(googleMap!=null){
                googleMap.setMyLocationEnabled(true);
            }
            //Log.d(TAG, "sdk<23");
        } else {
            int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                 ACCESS_FINE_LOCATION);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //Log.d(TAG, "permission granted on check");
                if(googleMap!=null)googleMap.setMyLocationEnabled(true);
            } else {
                // request permission.
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, MY_PERMISSIONS_REQUEST_LOCATION);
                //Log.d(TAG, "Requesting permission " + MY_PERMISSIONS_REQUEST_LOCATION);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}

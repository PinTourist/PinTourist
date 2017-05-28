package com.pintourist.pintourist.pintourist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pintourist.pintourist.pintourist.Object.Pin;
import com.pintourist.pintourist.pintourist.Object.PinUser;
import com.pintourist.pintourist.pintourist.functions.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseUser user ;
    private FirebaseAuth auth;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private View rootView;
    private String TAG= "Profile Fragment";
    private DatabaseReference database;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       inflater = getActivity().getLayoutInflater();
        rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        Button logout=(Button) rootView.findViewById(R.id.button_logout);
        ImageView user_profile_photo= (ImageView) rootView.findViewById(R.id.user_profile_photo);
        TextView user_profile_name= (TextView) rootView.findViewById(R.id.user_profile_name);
        TextView user_profile_email= (TextView) rootView.findViewById(R.id.email_textview);
        final TextView user_profile_points=(TextView) rootView.findViewById(R.id.point_textview);


        logout.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        //Log.d(TAG,user.getDisplayName());
        if (user != null) {

            utilities.getGraphRequest(user);
            // Name, email address, and profile photo Url
            checkPinUser(user);
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            user_profile_name.setText(name);
            user_profile_email.setText(email);


            database = FirebaseDatabase.getInstance().getReference();
            Query query= database.child("pinUser").child(user.getUid());
            Log.d(TAG, "Searching   " +user.getUid());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Log.d(TAG, String.valueOf(dataSnapshot.getKey()));
                    if(dataSnapshot.exists()){
                        PinUser pinUser= dataSnapshot.getValue(PinUser.class);
                        Log.d(TAG, "found"+ pinUser.userName);
                        user_profile_points.setText(String.valueOf( pinUser.points));
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });

            // show The Image in a ImageView
            new DownloadImageTask((ImageView) rootView.findViewById(R.id.user_profile_photo))
                    .execute(String.valueOf(photoUrl));
            //Log.d(TAG,name+ " "+ email+ " "+ photoUrl);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_logout:
                FirebaseAuth.getInstance().signOut();
                Intent mainIntent = new Intent().setClass(
                        getActivity(), LoginActivity.class);
                startActivity(mainIntent);
                getActivity().finish();


        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    public void checkPinUser(final FirebaseUser user){
        database = FirebaseDatabase.getInstance().getReference();
        /*HashMap<String, PinUser> map= new HashMap<>();
        map.put("email", new PinUser());
        database.child("pinUser").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d(TAG, "Created");
            }
        });*/

        Query query= database.child("pinUser");
        query.limitToFirst(1).equalTo(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){


                }else{
                    //creating userscore
                    /*Log.d(TAG, "Creating PinUser of "+user.getDisplayName());
                    PinUser pinUser= new PinUser(user.getDisplayName(),user.getEmail(),0);
                    database.child("pinUser").child(user.getUid()).setValue(pinUser);
                    */
                }
               }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }


    public  PinUser  getPinUser(final FirebaseUser user){
        final String TAG= "getPinUser()";
        final DatabaseReference database;
        database = FirebaseDatabase.getInstance().getReference();
        Query query= database.child("pinUser");
        query.limitToFirst(1).equalTo(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PinUser pinUser= dataSnapshot.getValue(PinUser.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
        return null;
    }

}

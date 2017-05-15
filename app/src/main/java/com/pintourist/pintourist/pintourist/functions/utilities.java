package com.pintourist.pintourist.pintourist.functions;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pintourist.pintourist.pintourist.Object.PinUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by fedebyes on 15/05/17.
 */

public class utilities {




    public static void getGraphRequest(final FirebaseUser  Fuser) {


        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    String TAG="Graph Request";
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        try {
                            //Log.d("Graph Response", "user = " + response.toString());
                            //Log.d("Graph Response", "Informazioni prelevate da Facebook");
                            if(object!=null) {

                                String email = response.getJSONObject().getString("email");
                                String lastname = response.getJSONObject().getString("last_name");
                                String firstname = response.getJSONObject().getString("first_name");
                                String friends = response.getJSONObject().getString("friends");
                                //friends2 = response.getJSONArray().get("")
                                Log.d(TAG,email);

                                JSONObject friendsJSON = (JSONObject) response.getJSONObject().get("friends");
                                Log.d("friendsJSON",friendsJSON.toString());
                                JSONArray friendsData = friendsJSON.getJSONArray("data");
                                Log.d("friendsData",friendsData.toString());

                                //list string into JSONARRAY
                                //JSONArray friendArray=new JSONArray();

                                //List<String> friendsParse=new ArrayList<String>();
                                String friendlist="";
                                for(int i=0;i<friendsData.length();i++){
                                    //friendArray.put(friendsData.getJSONObject(i).get("id"));
                                    friendlist+=friendsData.getJSONObject(i).get("id")+",";
                                    //String it= friendsData.getJSONObject(i).getString("id");
                                    //friendsParse.add(it);

                                }
                                //Log.d("Facebook utils", friendArray.toString());
                                //Log.d("Facebook utils",friendArray.get(0).toString());
                                //friendArray.
                                String facebookId = response.getJSONObject().getString("id");

                                //Log.d("friendsParse",friendsParse.toString());

                                //Log.d("Graph Response", "FriendS" + friends);

                                //Log.d("Graph Response", "email" + email);


                                /*useP.setUsername(email);
                                userP.setEmail(email);
                                userP.put("Facebook", "true");
                                userP.put("fName", firstname);
                                userP.put("lName",lastname);
                                userP.put("facebookId",facebookId);
                                //friends tha uses app

                                userP.put("friends", friendlist);
                                //Log.d("Facebook utils", userP.get("friends").toString());
                                //Log.d("Facebook utils",  userP.get("friends").get(0).toString());
                                // URL=response.getJSONObject().getString("profile");

                                //dowload facebook image
                                //new DownloadImage().execute(URL);

                                JSONObject picture = response.getJSONObject().getJSONObject("picture");
                                JSONObject data = picture.getJSONObject("data");
                                final String pictureUrl = data.getString("url");

                                //userP.save();*/

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(TAG, "error JSON");

                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,link,first_name,last_name,friends,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }







}

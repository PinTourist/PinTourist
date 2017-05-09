package com.pintourist.pintourist.pintourist;



import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pintourist.pintourist.pintourist.Object.Pin;
import com.pintourist.pintourist.pintourist.Object.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedebyes on 09/05/17.
 */

public class PinDialogFragment extends DialogFragment {
    private View rootView;
    private String TAG="PinDialog";
    private Pin pin;
    private List<Question> questionList;
    private Question question;

    public interface PinDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    PinDialogListener mListner;

    public PinDialogFragment newInstance(String PinName){
        PinDialogFragment dialog=new PinDialogFragment();
        Bundle args=new Bundle();
        args.putString("PinName",PinName);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String PinName=getArguments().getString("PinName");
        //Log.d(TAG,"PinName found: "+PinName);
        //Bundle args = new Bundle();

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        rootView = inflater.inflate(R.layout.dialog_question, null);
        builder.setView(rootView);

        final TextView title =(TextView) rootView.findViewById(R.id.dialog_title);
        final TextView body =(TextView) rootView.findViewById(R.id.dialog_body);
        final Button button1=(Button) rootView.findViewById(R.id.dialog_button1);
        final Button button2=(Button) rootView.findViewById(R.id.dialog_button2);
        final Button button3=(Button) rootView.findViewById(R.id.dialog_button3);
        final Button button4=(Button) rootView.findViewById(R.id.dialog_button4);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
       DatabaseReference ref = database;


        Query query= ref.child("pins");
        query.orderByChild("name").equalTo(PinName).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"number of found pins"+dataSnapshot.getChildrenCount());
                if(dataSnapshot.exists()){
                    final ArrayList<Pin> pins = new ArrayList<Pin>();
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        Pin pin=singleSnapshot.getValue(Pin.class);
                        pins.add(pin);
                        questionList=pin.getQuestionList();
                        Log.d(TAG,"found"+pin.getName());
                        question=questionList.get(0);
                        title.setText(pin.getName());
                        body.setText(question.getQ());
                        Log.d(TAG,"found"+question.getQ());
                        button1.setText(question.getA1());
                        button2.setText(question.getA2());
                        button3.setText(question.getA3());
                        button4.setText(question.getA4());


                    }}}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



                // Add action buttons
                /*.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PinDialogFragment.this.getDialog().cancel();
                    }

                }*/
        return builder.create();
    }
}

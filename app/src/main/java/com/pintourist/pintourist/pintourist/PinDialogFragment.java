package com.pintourist.pintourist.pintourist;



import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fedebyes on 09/05/17.
 */

public class PinDialogFragment extends DialogFragment {
    private View rootView;

    public interface PinDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    PinDialogListener mListner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        rootView = inflater.inflate(R.layout.dialog_question, null);
        builder.setView(rootView);

        TextView title =(TextView) rootView.findViewById(R.id.dialog_title);
        TextView body =(TextView) rootView.findViewById(R.id.dialog_body);
        Button button1=(Button) rootView.findViewById(R.id.dialog_button1);
        Button button2=(Button) rootView.findViewById(R.id.dialog_button2);
        Button button3=(Button) rootView.findViewById(R.id.dialog_button3);
        Button button4=(Button) rootView.findViewById(R.id.dialog_button4);
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

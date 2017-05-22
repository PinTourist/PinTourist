package com.pintourist.pintourist.pintourist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pintourist.pintourist.pintourist.Object.PinUser;
import com.pintourist.pintourist.pintourist.functions.RoundedImageView;

import java.util.List;

/**
 * Created by fedebyes on 19/05/17.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    private List<PinUser> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView score;
        public RoundedImageView profile_pic;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            score = (TextView) v.findViewById(R.id.score);
            profile_pic= (RoundedImageView) v.findViewById(R.id.profile_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScoreAdapter(List<PinUser> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ScoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String local_name =mDataset.get(position).userName;
        String local_score= String.valueOf(mDataset.get(position).points);

                holder.name.setText(local_name);
        holder.score.setText(local_score);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


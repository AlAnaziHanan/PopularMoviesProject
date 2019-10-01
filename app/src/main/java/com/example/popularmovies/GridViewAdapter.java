package com.example.popularmovies;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class GridViewAdapter extends ArrayAdapter<Movie> {

    private List<Movie> mGridData ;
    private Context context;

    public GridViewAdapter ( Context context , int resource , List<Movie> mGridData ) {
        super ( context , resource , mGridData );
        this.context = context;
        this.mGridData = mGridData;
    }
    @Override
    public long getItemId ( int i ) {
        return i;
    }


    @Override
    public View getView ( int i , View view ,  ViewGroup viewGroup ) {

        View row = view;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater ();

            view= LayoutInflater.from ( context ).inflate ( R.layout.model,viewGroup,false );
            holder = new ViewHolder ();
            holder.imageView = view.findViewById ( R.id.imageIV );
            view.setTag ( holder );

        } else {

            holder = (ViewHolder) view.getTag ();
        }
        Movie item = mGridData.get ( i );
        Picasso.get ( ).load ( item.getPosterPath ()).into ( holder.imageView );
        return view;

    }

    //update grid data
    public void refreshGridData (ArrayList <Movie> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged ();
    }
    class ViewHolder {
        ImageView imageView;
    }


}
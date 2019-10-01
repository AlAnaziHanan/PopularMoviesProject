package com.example.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;



public class NetworkUtils extends AppCompatActivity {
    private ArrayList<Movie> mGridData;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        GridView mGridView = findViewById ( R.id.moviesGrid );
        ProgressBar progressBar = findViewById ( R.id.bar );
        //Initialize
        mGridData = new ArrayList<>();
        GridViewAdapter mGridAdapter = new GridViewAdapter ( this , R.layout.model , mGridData );
        mGridView.setAdapter( mGridAdapter );
        //Start download
        new Json().execute ();
        progressBar.setVisibility( View.VISIBLE);
    }

    private class Json extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground ( Void... voids ) {
            URLConnection urlConnection;
            BufferedReader bufferedReader;
            ArrayList<Movie> m = new ArrayList<> ();
            try {
                URL movieURL=new URL(MainActivity.BASE_URL);
                urlConnection=movieURL.openConnection ();
                bufferedReader =new BufferedReader ( new InputStreamReader ( urlConnection.getInputStream () ) );
                StringBuilder stringBuffer= new StringBuilder (  );

                String line;
                while((line=bufferedReader.readLine ())!=null){
                    stringBuffer.append ( line );
                }

            } catch (IOException e) {
                e.printStackTrace ();
            }
            return m;
        }
    }

}
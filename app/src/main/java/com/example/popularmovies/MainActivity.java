package com.example.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends  AppCompatActivity  {


    private GridViewAdapter adapter;
    private GridView mGridView;
    ProgressBar progressBar;
    public static final String BASE_URL = "https://api.themoviedb.org/v5/";
    private List<Movie> popularList;
    private List<Movie> topRatedList;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        final ProgressBar progressBar=findViewById ( R.id.bar );
        progressBar.setIndeterminate ( true );

        progressBar.setVisibility ( View.VISIBLE );
        String url = createURL ();
        GetDataService dataService= RetrofitClientInstance.getRetrofitInstance (url).create ( GetDataService.class );

        Call<List<Movie>> call = dataService.getAllPhotos ();

        call.enqueue ( new Callback<List<Movie>> () {
            private Call<List<Movie>> call;
            private Response<List<Movie>> response;

            @Override
            public void onResponse ( Call<List<Movie>> call , Response<List<Movie>> response ) {
                this.call = call;
                this.response = response;
                progressBar.setVisibility ( View.GONE );

                if (response.body () != null) {
                    mGridView = findViewById ( R.id.moviesGrid );
                    adapter = new GridViewAdapter ( MainActivity.this , R.layout.activity_gridview , response.body () );
                    mGridView.setAdapter ( adapter );

                    mGridView.setOnClickListener ( new AdapterView.OnClickListener () {
                        @Override
                        public void onClick ( View view ) {
                            Intent intent = new Intent ( MainActivity.this , Moviedetails.class );
                            intent.putExtra ( "my_recycler_view" , popularList.indexOf ( this ) );
                            startActivity ( intent );
                        }
                    } );
                }
            }
            @Override
            public void onFailure ( Call<List<Movie>> call , Throwable throwable ) {
                progressBar.setVisibility ( View.GONE );
                Toast.makeText ( MainActivity.this, throwable.getMessage (), Toast.LENGTH_LONG ).show ();
            }
        }  );

    }
    private String createURL(){

        final String API_KEY_QUERY_PARAMETER = "api_key";
        //Place key
        final String API_KEY = "";
        Uri uri = Uri.parse ( BASE_URL );
        Uri.Builder builder = uri.buildUpon ();
        builder.appendQueryParameter ( API_KEY_QUERY_PARAMETER, API_KEY );
        return  builder.toString ();
    }

}
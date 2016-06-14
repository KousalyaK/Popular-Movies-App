package com.example.android.androidapp.interfaces;

import com.example.android.androidapp.models.MovieModel;
import com.example.android.androidapp.models.ReviewModel;
import com.example.android.androidapp.models.TrailerResults;
import com.example.android.androidapp.models.TrailerViedoModel;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by anjana on 6/1/16.
 */
public interface MovieApi {

    @POST("/discover/movie")
    public void getMovies(@Body String query, Callback<MovieModel> callback);

    @GET("/movie/{id}/videos")
    void fetchTrailers( @Path("id") String movieId, Callback<TrailerViedoModel> callback );

    @GET("/movie/{id}/reviews")
    void getReviews( @Path("id") String movieId, Callback<ReviewModel> callback );


}

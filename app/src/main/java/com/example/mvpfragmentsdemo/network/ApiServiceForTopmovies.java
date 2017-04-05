package com.example.mvpfragmentsdemo.network;

import com.example.mvpfragmentsdemo.network.apimodeltopmovies.TopRated;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface ApiServiceForTopmovies {
    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);
}

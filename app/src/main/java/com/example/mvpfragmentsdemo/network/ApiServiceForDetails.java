package com.example.mvpfragmentsdemo.network;

import com.example.mvpfragmentsdemo.network.apimodeldetails.Details;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface ApiServiceForDetails {
    @GET("/")
    Observable<Details> getDetails(@Query("t") String title);
}

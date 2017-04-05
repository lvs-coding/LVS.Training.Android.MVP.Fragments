package com.example.mvpfragmentsdemo.details;

import com.example.mvpfragmentsdemo.network.apimodeltopmovies.Result;

import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface Repository {
    Observable<Result> getDetailsFromMemory();

    Observable<Result> getDetailsFromNetwork();

    Observable<Result> getDetailstData();
}

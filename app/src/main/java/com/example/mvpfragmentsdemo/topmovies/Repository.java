package com.example.mvpfragmentsdemo.topmovies;

import com.example.mvpfragmentsdemo.network.apimodeltopmovies.Result;

import rx.Observable;

/**
 * Created by laurent on 4/3/17.
 */

public interface Repository {
    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<Result> getResultData();
}

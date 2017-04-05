package com.example.mvpfragmentsdemo.topmovies;

import com.example.mvpfragmentsdemo.network.ApiServiceForTopmovies;
import com.example.mvpfragmentsdemo.network.apimodeltopmovies.Result;
import com.example.mvpfragmentsdemo.network.apimodeltopmovies.TopRated;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by laurent on 4/3/17.
 */

public class TopMoviesRepository implements Repository {

    private ApiServiceForTopmovies apiServiceForTopmovies;
    private List<Result> results;
    private long timestamp;
    private static final long STALE_MS = 20 * 1000;

    public TopMoviesRepository(ApiServiceForTopmovies apiServiceForTopmovies) {
        this.apiServiceForTopmovies = apiServiceForTopmovies;
        this.timestamp =  System.currentTimeMillis();
        results = new ArrayList<>();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {
        if (isUpToDate()) {
            return Observable.from(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        // Concat 3 results pages
        Observable<TopRated> topRatedObservable = apiServiceForTopmovies.getTopRatedMovies(1)
                .concatWith(apiServiceForTopmovies.getTopRatedMovies(2))
                .concatWith(apiServiceForTopmovies.getTopRatedMovies(3));

        // concatMap operator preserve the objects order
        return topRatedObservable.concatMap(new Func1<TopRated, Observable<Result>>() {
            @Override
            public Observable<Result> call(TopRated topRated) {
                return Observable.from(topRated.getResults());
            }
        }).doOnNext(new Action1<Result>() { // doOnNewt will be called every time a new item is emitted on our observable stream
            @Override
            public void call(Result result) {
                results.add(result); // add results to the cache
            }
        });
    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}

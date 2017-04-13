package com.example.mvpfragmentsdemo.details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mvpfragmentsdemo.network.ApiServiceForDetails;
import com.example.mvpfragmentsdemo.network.ApiServiceForTopmovies;
import com.example.mvpfragmentsdemo.network.apimodeldetails.Details;
import com.example.mvpfragmentsdemo.network.apimodeltopmovies.Result;
import com.example.mvpfragmentsdemo.topmovies.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by laurent on 4/3/17.
 */

public class DetailsRepository implements Repository {
    private final String TAG = DetailsRepository.class.getSimpleName();

    private ApiServiceForDetails apiServiceForDetails;
    Details details;

    public DetailsRepository(ApiServiceForDetails apiServiceForDetails) {
        this.apiServiceForDetails = apiServiceForDetails;
        details = new Details();
    }

    @Override
    public Observable<Details> getDetailsFromNetwork(String movieTitle) {
        Log.d(TAG,"getting details from network");
        Observable<Details> detailsObservable = apiServiceForDetails.getDetails(movieTitle);

        return detailsObservable;
    }

    @Override
    public Observable<Details> getDetailsData(String movieTitle) {

        return getDetailsFromNetwork(movieTitle);
    }
}

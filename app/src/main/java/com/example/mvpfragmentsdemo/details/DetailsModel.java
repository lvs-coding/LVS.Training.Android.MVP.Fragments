package com.example.mvpfragmentsdemo.details;


import com.example.mvpfragmentsdemo.network.apimodeldetails.Details;

import rx.Observable;
import rx.functions.Func1;

public class DetailsModel implements DetailsFragmentMVP.Model {
    private Repository repository;

    public DetailsModel(Repository repository) {

        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result(String movieTitle) {
        return repository.getDetailsData(movieTitle).map(new Func1<Details, ViewModel>() {
            @Override
            public ViewModel call(Details details) {
                return new ViewModel(details);
            }
        });
    }
}

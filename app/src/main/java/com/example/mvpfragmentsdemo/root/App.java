package com.example.mvpfragmentsdemo.root;

import android.app.Application;

import com.example.mvpfragmentsdemo.details.DetailsModule;
import com.example.mvpfragmentsdemo.network.ApiModuleForDetails;
import com.example.mvpfragmentsdemo.network.ApiModuleForTopmovies;
import com.example.mvpfragmentsdemo.topmovies.TopMoviesModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModuleForTopmovies(new ApiModuleForTopmovies())
                .topMoviesModule(new TopMoviesModule())
                .apiModuleForDetails(new ApiModuleForDetails())
                .detailsModule(new DetailsModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}

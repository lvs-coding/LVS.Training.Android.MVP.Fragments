package com.example.mvpfragmentsdemo.root;

import com.example.mvpfragmentsdemo.network.ApiModuleForDetails;
import com.example.mvpfragmentsdemo.network.ApiModuleForTopmovies;
import com.example.mvpfragmentsdemo.topmovies.TopMoviesFragment;
import com.example.mvpfragmentsdemo.topmovies.TopMoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForTopmovies.class,TopMoviesModule.class})
public interface ApplicationComponent {
    void inject(TopMoviesFragment target);
}

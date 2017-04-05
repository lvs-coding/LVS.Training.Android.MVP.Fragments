package com.example.mvpfragmentsdemo.topmovies;

import com.example.mvpfragmentsdemo.network.ApiServiceForTopmovies;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TopMoviesModule {

    @Provides
    public TopMoviesFragmentMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesFragmentMVP.Model topMoviesModel) {
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesFragmentMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new TopMoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(ApiServiceForTopmovies movieApiService) {
        return new TopMoviesRepository(movieApiService);
    }
}

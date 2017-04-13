package com.example.mvpfragmentsdemo.details;

import com.example.mvpfragmentsdemo.network.ApiServiceForDetails;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laurent on 4/3/17.
 */
@Module
public class DetailsModule {
    @Provides
    public DetailsFragmentMVP.Presenter provideDetailsActivityPresenter(DetailsFragmentMVP.Model detailsModel) {
        return new DetailsPresenter(detailsModel);
    }

    @Provides
    public DetailsFragmentMVP.Model provideDetailsActivityModel(Repository repository) {
        return new DetailsModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(ApiServiceForDetails detailsApiService) {
        return new DetailsRepository(detailsApiService);
    }
}

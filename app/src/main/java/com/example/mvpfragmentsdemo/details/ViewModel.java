package com.example.mvpfragmentsdemo.details;

import com.example.mvpfragmentsdemo.network.apimodeldetails.Details;

/**
 * Created by laurent on 4/3/17.
 */

public class ViewModel {
    private String title;
    private String year;
    private String genre;
    private String country;
    private String imdbRating;

    public ViewModel(Details details) {
        this.title = details.getTitle();
        this.year = details.getYear();
        this.genre = details.getGenre();
        this.country = details.getCountry();
        this.imdbRating = details.getImdbRating();
    }

    public String getTitle() {
        return this.title;
    }


    @Override
    public String toString() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getImdbRating() {
        return imdbRating;
    }
}

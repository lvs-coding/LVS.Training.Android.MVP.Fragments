package com.example.mvpfragmentsdemo.topmovies;

/**
 * Created by laurent on 4/3/17.
 */

public class ViewModel {
    private String name;

    public ViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

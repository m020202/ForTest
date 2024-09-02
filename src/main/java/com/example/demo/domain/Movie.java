package com.example.demo.domain;

public class Movie {
    private static final Movie instance = new Movie();

    private Movie(){}

    public static Movie getInstance() {
        return instance;
    }
}

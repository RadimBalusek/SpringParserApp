package com.springframework.parserexample.parserapp.service;

import com.springframework.parserexample.parserapp.data.Movie;
import com.springframework.parserexample.parserapp.data.RestResult;

import java.util.List;

public interface MovieService {

    List<RestResult> searchMovie (Movie movie);
}

package com.springframework.parserexample.parserapp.controllers;

import com.springframework.parserexample.parserapp.data.Movie;
import com.springframework.parserexample.parserapp.data.RestResult;
import com.springframework.parserexample.parserapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VisitorController {

    private MovieService movieService;

    @Autowired
    public void setCustomerService(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    public String handlePostRequest(@ModelAttribute Movie movie, Model model) {
        List<RestResult> outputList = movieService.searchMovie(movie);
        model.addAttribute("inputform", outputList);
        return "result";
    }

    @GetMapping("/movie")
    public String greetingForm(Model model) {
        setConstant(model);
        model.addAttribute("movie", new Movie());
        return "visitor-view";
    }

    //Set check box list for resolution
    private void setConstant(Model model){
        List<String> resolutions = new ArrayList<>();
        for(Movie.Currency currency : Movie.Currency.values()) {
            resolutions.add(currency.getFormat());
        }
        model.addAttribute("resolutions", resolutions);
    }


}
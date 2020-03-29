package com.springframework.parserexample.parserapp.controllers;

import com.springframework.parserexample.parserapp.data.*;
import com.springframework.parserexample.parserapp.service.EmailService;
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

    private EmailService emailService;

    @Autowired
    public VisitorController(MovieService movieService, EmailService emailService) {
        this.movieService = movieService;
        this.emailService = emailService;
    }

    @PostMapping("/movie")
    public String handlePostRequest(@ModelAttribute Movie movie, Model model) {
        List<RestResult> outputList = movieService.searchMovie(movie);
        model.addAttribute("inputform", outputList);
        if (movie.isEmailFlag()) {
            emailService.sendmail(movie.getEmailAddress(), outputList);
        }
        return "result";
    }

    @GetMapping("/movie")
    public String greetingForm(Model model) {
        setConstant(model);
        return "visitor-view";
    }

    //Set check box list for resolution
    private void setConstant(Model model) {
        List<Resolution> resolutions = new ArrayList<>();
        List<MovieFormat> movieFormats = new ArrayList<>();

        for (Resolution.ResolutionType resolutionEnum : Resolution.ResolutionType.values()) {

            if (null == resolutionEnum.getResolutionType()) { // default resolution checkbox checked for type none
                resolutions.add(new Resolution(resolutionEnum.getResolutionType(), true));
            } else {
                resolutions.add(new Resolution(resolutionEnum.getResolutionType(), false));
            }
        }

        for (MovieFormat.FormatId formatIdEnum : MovieFormat.FormatId.values()) {

            if (formatIdEnum.getFormatId().contains("AVI")) {  // default format checkbox checked for type AVI
                movieFormats.add(new MovieFormat(formatIdEnum.getFormatId(), true));
            } else {
                movieFormats.add(new MovieFormat(formatIdEnum.getFormatId(), false));
            }
        }

        model.addAttribute("movieFormats", movieFormats);
        model.addAttribute("resolutions", resolutions);
        model.addAttribute("movie", new Movie());
    }

}
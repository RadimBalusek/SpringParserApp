package com.springframework.parserexample.parserapp.service;

import com.springframework.parserexample.parserapp.data.CamRip;
import com.springframework.parserexample.parserapp.data.Movie;
import com.springframework.parserexample.parserapp.data.RestResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {


    @Override
    public List<RestResult> searchMovie(Movie movie) {

        boolean filter1 = false;;
        boolean filter2 = false;;
        boolean filter3 = false;;
        boolean filter4 = false;;
        boolean filter5 = false;

        String inputName = movie.getName();
        String nameToRestCall = inputName.trim().replaceAll(" ", "+");

        List<RestResult> listBeforeFilter;
        List<RestResult> listAferFilter = new ArrayList<>();
        RestClient restResult = new RestClient();
        listBeforeFilter = restResult.CustomRestClient(nameToRestCall);

        for (RestResult item : listBeforeFilter) {

            // filter for check if result compare format (avi, mp4 etc..)
            if (null == movie.getFormatId()) {
                filter1 = true;
            } else {
                filter1 = MovieFilters.findCharacter(item.getFormat(), movie.getFormatId().toLowerCase());
            }

            // filter for check if result compare resolution (1920x800)
            if (null == movie.getResolutions()){
                filter2 = true;
            } else {
                for (String resolution : movie.getResolutions()) {
                    filter2 = MovieFilters.findCharacter(item.getResolution(), resolution);
                }
            }

            // filter for check if result compare language (cz, eng)
            if (movie.getLanguageId().equals("none")) {
                filter3 = true;
            }else {
                filter3 = MovieFilters.findCharacter(item.getName(), movie.getLanguageId());
            }

            // filter for check if result compare TranslatingId (tit, dabing)
            if (movie.getTranslatingId().equals("none")) {
                filter4 = true;
            }else {
                filter4 = MovieFilters.findCharacter(item.getName(), movie.getTranslatingId());
            }

            // enable test for cam rip, check if name don't compare pointer for cam rip ex.: KINO, CAM, HDCAM
            if (movie.isCamrip()) {
                filter5 = !MovieFilters.findCharacter(item.getName(), CamRip.KINO.getFileName(), CamRip.CAM.getFileName(), CamRip.HDCAM.getFileName());
            }else{
                filter5 = true;
            }

            if (filter1 && filter2 && filter3 && filter4 && filter5) {
                listAferFilter.add(item);
            }
        }

        return listAferFilter;
    }

}


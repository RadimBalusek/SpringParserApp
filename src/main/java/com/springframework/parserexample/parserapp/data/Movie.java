package com.springframework.parserexample.parserapp.data;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Movie {

    //  @Id
    private String name; // rambo

    private List<String> resolutions; // 1920x800

    private String formatId ; // avi

    private String  LanguageId; // EN, CZ

    private String TranslatingId; // tit, dabing

    private boolean camrip; // true

    private boolean emailFlag; // check button if is email allow

    private String emailAddress; // emailAddress

 //   private Date emailTimer ; // Timer
}

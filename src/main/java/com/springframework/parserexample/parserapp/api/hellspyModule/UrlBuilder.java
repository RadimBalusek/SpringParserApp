package com.springframework.parserexample.parserapp.api.hellspyModule;

public class UrlBuilder {

    // first page
    // https://www.hellspy.cz/search/  ?q=                   star+wars   &orderby=inserted_dt  // search according date
    // second page
    // https://www.hellspy.cz/search/  ?p=2&position=top&q=  star+wars   &orderby=inserted_dt

    private static String startUrl = "https://www.hellspy.cz/search/?q=";
    private static String urlNextPageStart = "https://www.hellspy.cz/search/?p=";
    private static String urlNextPageMiddle = "&position=top&q=";
    private static String endUrl = "&orderby=inserted_dt"; // sorted movie according date

    protected static String urlBuilderSinglePage(String name) {
        return startUrl + name + endUrl;
    }

    protected static String urlBuilderMultiPages(int pageNumber, String name) {
        return urlNextPageStart + pageNumber + urlNextPageMiddle + name + endUrl;
    }
}

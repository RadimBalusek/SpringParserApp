package com.springframework.parserexample.parserapp.api.hellspyModule;

import java.util.ArrayList;
import java.util.List;
import com.springframework.parserexample.parserapp.api.SpringRestClient;
import com.springframework.parserexample.parserapp.data.RestResult;

public class CollectOutputList {

    private static UrlBuilder urlBuilder = new UrlBuilder();
    private static MovieRegex movieRegex = new MovieRegex();
    private static SpringRestClient springRestClient = new SpringRestClient();



    public static List<RestResult> CustomRestClient(String movieName) {
        List resultList;
        List resultListNexPage;

        resultList = convertUrlToListObj(urlBuilder.urlBuilderSinglePage(movieName));

        // this loop is use for searching next pages
        for (int i = 2; i < 4; i++) {  // max 4 pages, limit for minimise time delay
            resultListNexPage = convertUrlToListObj(urlBuilder.urlBuilderMultiPages(i, movieName));

            if (resultListNexPage.isEmpty()) {
                break;  // if on next page we don't get any new movie skip from cycle
            }
            resultList.addAll(resultListNexPage); // if we get on next page new movie copy this list to result list and continue to next page
        }
        return resultList;
    }

    // call sprint rest template according url and filter transaction to List
    private static List convertUrlToListObj(String url) {
        List resultList = new ArrayList();
        try {
            resultList = movieRegex.rowDataToString(springRestClient.getProductList(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
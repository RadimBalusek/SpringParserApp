package com.springframework.parserexample.parserapp.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.springframework.parserexample.parserapp.data.RestResult;

public class RestClient {

    private static MovieRegex movieRegex = new MovieRegex();

    public static List<RestResult> CustomRestClient(String name) {

        String hostname = "hellspy.cz";
        int port = 80;
        String protocol = "http";
        HttpHost target = new HttpHost(hostname, port, protocol);

        String url = "/search/?q=";
        String urlNextPageStart = "/search/?p=";
        String urlNextPageEnd = "&position=top&q=";
        String endUrl = "&orderby=inserted_dt"; // sorted movie according date

        DefaultHttpClient httpclient = new DefaultHttpClient();
        String response;
        List resultList = new ArrayList();
        List resultListNexPage = new ArrayList();


        HttpGet getRequest = new HttpGet(hellSpyUrlBuilder(url, name, url));
        resultList = getRestClientList(target, getRequest);

        // this cycle is use for searching one next pages
        for (int i = 2; i < 10; i++) {  // searching till 10 pages max
            getRequest = new HttpGet(hellSpyUrlBuilder(urlNextPageStart, i, urlNextPageEnd, name, endUrl)); // build url for next page
            resultListNexPage = getRestClientList(target, getRequest); // get List of parsed object for next page

            if (resultListNexPage.isEmpty()) {
                break;  // if on next page we don't get any new movie skip from cycle
            }
            resultList.addAll(resultListNexPage); // if we get on next page new movie copy this list to result list and continue to next page
        }
        return resultList;
    }

    private static List getRestClientList(HttpHost target, HttpGet getRequest) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        List resultList = new ArrayList();
        movieRegex = new MovieRegex();
        String response;
        try {
            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            resultList = movieRegex.rowDataToString(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return resultList;
    }

    private static String hellSpyUrlBuilder(String url, String name, String endUrl) {
        return url + name + endUrl;
    }

    // first page
    //https://www.hellspy.cz/search/?position=top&q=star+wars&orderby=inserted_dt  // search according date
    // second page
    //https://www.hellspy.cz/search/?p=2&position=top&q=star+wars&orderby=inserted_dt
    // differences
    // https://www.hellspy.cz/search/?         position=top&q=star+wars&orderby=inserted_dt
    // https://www.hellspy.cz/search/?  p=2&   position=top&q=star+wars&orderby=inserted_dt

    private static String hellSpyUrlBuilder(String urlNextPageStart, int pageNumber, String urlNextPageEnd, String name, String endUrl) {
        return urlNextPageStart + pageNumber + urlNextPageEnd + name + endUrl;
    }

}
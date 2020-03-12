package com.springframework.parserexample.parserapp.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.springframework.parserexample.parserapp.data.RestResult;

/**
 * A simple Java REST GET example using the Apache HTTP library.
 * This executes a call against the Yahoo Weather API service, which is
 * actually an RSS service (http://developer.yahoo.com/weather/).
 * <p>
 * Try this Twitter API URL for another example (it returns JSON results):
 * http://search.twitter.com/search.json?q=%40apple
 * (see this url for more twitter info: https://dev.twitter.com/docs/using-search)
 * <p>
 * Apache HttpClient: http://hc.apache.org/httpclient-3.x/
 */
public class RestClient {


    public static List<RestResult> CustomRestClient(String name) {

        //https://www.hellspy.cz/search/?q=ddd&orderby=inserted_d
        String hostname = "hellspy.cz";
        int port = 80;
        String protocol = "http";
        String url = "/search/?q=";
        String endUrl = "&orderby=inserted_dt"; // the latest movie

        RestClient restClient = new RestClient();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String response = null;
        List resultList = new ArrayList();
        List result = new ArrayList();

        try {
            // specify the host, protocol, and port
            HttpHost target = new HttpHost(hostname, port, protocol);
            HttpGet getRequest = new HttpGet(url + name + endUrl);
            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            String output2 = response.replaceAll("\n" + "", " ").replaceAll("\\s", "");
            Pattern p = Pattern.compile("alt=\"" +
                    "(.{1,100}).(\\w\\w\\w)" +
                    "\"/></div></a><spanclass=\"duration-holderdh-max\">" +
                    "(\\d\\d:\\d\\d:\\d\\d)" +
                    "</span><spanclass=\"resolution-holder\"><span>" +
                    "(\\d*x\\d*)" +
                    "</span></span><spanclass=\"file-size-holder\">" +
                    "(\\d*MB|\\d*\\.\\d*GB)" +
                    "(</span><spanclass=\"evaluation-holderevaluation-holder-upevaluation-holder-up\\d*\"><span>\\d*</span>){0,1}" +
                    "</span></div><divclass=\"dataright\"><h\\d*><ahref=\"/" +
                    "(.{1,50})" +
                    "\">");
            Matcher m = p.matcher(output2);
            while (m.find()) {
                RestResult restResult = new RestResult();
                restResult.setName(m.group(1));
                restResult.setFormat(m.group(2));
                restResult.setTime(m.group(3));
                restResult.setResolution(m.group(4));
                restResult.setSize(m.group(5));
                restResult.setLink("https://www.hellspy.cz/"+m.group(7));
                resultList.add(restResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return resultList;
    }

}
package com.springframework.parserexample.parserapp.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MovieFilters {

    static String prepareString;

    public static boolean findCharacter(String inString, String pattern) {
        boolean result = false;
        prepareString = inString.toLowerCase();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(prepareString);
        while (m.find()) {
            if (m.group() != null) {
                result = true;
            }
        }
        return result;
    }

    public static boolean findCharacter(String inString, String pattern1, String pattern2, String pattern3) {
        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;
        boolean finalResult = false;
        prepareString = inString.toLowerCase();
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(prepareString);
        while (m1.find()) {
            if (m1.group(0) != null) {
                result1 = true;
            } else {
                result1 = false;
            }
        }

        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(prepareString);
        while (m2.find()) {
            if (m2.group(0) != null) {
                result2 = true;
            } else {
                result2 = false;
            }
        }

        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(prepareString);
        while (m3.find()) {
            if (m3.group(0) != null) {
                result3 = true;
            } else {
                result3 = false;
            }
        }

        return result1 || result2 || result3;
    }

}
package com.springframework.parserexample.parserapp.api.hellspyModule;

import com.springframework.parserexample.parserapp.data.RestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieRegex {

    List resultList = new ArrayList();

    public List<RestResult> rowDataToString(String response) {

        String output = response.replaceAll("\n" + "", " ").replaceAll("\\s", ""); // before parsing I erase whitespacpace

        // ## Basic regex knowledge
        // () - mean group ex.: (.{1,100}) - mean group(1) with any type of character (min 1 character)
        // .{1,100} - mean any kind of characters (min 1 character, max 100 character)
        // for special character like " is used \ -> \"
        // w - lower Case no digit character , W - upper case no digit character
        // d - digit character
        // | - logical operator OR ex.: (\d{1,4}MB|\d{1,4}\.\d{1,4}GB)
        //////////////////////////////////////////////////////
        // For regex exist training test - ParserApplicationTests.java + txt file - TrainingGroup.txt
        // Example transaction:
        // "alt="
        //  StarWarsTheCloneWarsS07E03CZtit
        //  .avi"/></div></a><spanclass="duration-holderdh-max">
        //  00:18:22</span><spanclass="resolution-holder"><span>
        //  720x306</span></span><spanclass="file-size-holder">
        //  117MB</span></div><divclass="dataright"><h3>
        //  <ahref="/
        //  star-wars-the-clone-wars-s07e03-cztit-avi/85573401
        //  ">


        Pattern p = Pattern.compile("" +
                "alt=\"" +   // identify start our transaction what we want to parse
                "(.{1,100})" + // group(1) - movie name (ex.: StarWarsTheCloneWarsS07E03CZtit)
                ".(\\w\\w\\w)\"/></div>.{1,100}" +  // group(2) - movie format (ex.: avi, rar etc..)
                "(\\d\\d:\\d\\d:\\d\\d).{1,100}<span>" + // group(3) - movie time (ex.: 00:18:22)
                "(\\d{1,4}x\\d{1,4}).{1,100}holder\">" + // group(4) - movie resolution (ex.: 720x306)
                "(\\d{1,4}MB|\\d{1,4}\\.\\d{1,4}GB)" + // group(5) - movie size (ex.: 117MB)
                ".{1,100}<ahref=\"/" +
                "(.{1,100})" +  // group(6) - movie link (ex.: star-wars-the-clone-wars-s07e03-cztit-avi/85573401)
                "\">");
        Matcher m = p.matcher(output);
        while (m.find()) {
            RestResult restResult = new RestResult();
            restResult.setName(m.group(1));
            restResult.setFormat(m.group(2));
            restResult.setTime(m.group(3));
            restResult.setResolution(m.group(4));
            restResult.setSize(m.group(5));
            restResult.setLink("https://www.hellspy.cz/" + m.group(6));
            resultList.add(restResult);
        }
        return resultList;
    }

}

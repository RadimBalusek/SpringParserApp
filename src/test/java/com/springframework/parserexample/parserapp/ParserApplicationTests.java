package com.springframework.parserexample.parserapp;

import com.springframework.parserexample.parserapp.data.RestResult;
import com.springframework.parserexample.parserapp.api.hellspyModule.MovieRegex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ParserApplicationTests {

    @Test
    void contextLoads() {
    }

// This test take raw data from TrainingGroup file (snapshot real result) and compare number of element from our app regex (MovieRegex.java)
// with simple regex which counts number of 'alt="' index (alt signature- identify our transaction)

    @Test
    void controlRegexOnFile() {
        assertTrue(numberOfFullRegexElement() ==(numberOfShortRegexElement()));
    }

    public static int numberOfFullRegexElement() {
        MovieRegex movieRegex = new MovieRegex();
        List<RestResult> resultList = movieRegex.rowDataToString(prepareString());
        return  resultList.size();
    }


    public static int numberOfShortRegexElement() {

        List resultList = new ArrayList();

        String output = prepareString();
        Pattern p = Pattern.compile("alt=\"(.{1,100})\"/></div>");
        Matcher m = p.matcher(output);
        while (m.find()) {
            RestResult restResult = new RestResult();
            restResult.setName(m.group(1));
            resultList.add(restResult);
        }
        return resultList.size();
    }



    public static String prepareString() {

        String filePath = "/home/radim/IdeaProjects/parserapp/src/main/resources/TrainingGroup";

        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        String content = Files.lines(Paths.get(path), encoding)
                .collect(Collectors.joining(System.lineSeparator()));
        return content.replaceAll("\n" + "", " ").replaceAll("\\s", ""); // before parsing I erase whitespace;
    }


//  ################ Help method for training our regex

    public static void main(String []args){
        System.out.println("Number of element what I get my app:"+trainingRegex());
        System.out.println("Number of alt=\" element:"+numberOfShortRegexElement());
        System.out.println("Result:"+(numberOfFullRegexElement() == numberOfShortRegexElement()));
    }

    public static int trainingRegex() {

        List resultList = new ArrayList();

        String output = prepareString();
        Pattern p = Pattern.compile("alt=\"(.{1,100}).(\\w\\w\\w)\"/></div>.{1,100}(\\d\\d:\\d\\d:\\d\\d).{1,100}<span>(\\d{1,4}x\\d{1,4}).{1,100}holder\">(\\d{1,4}MB|\\d{1,4}\\.\\d{1,4}GB).{1,100}<ahref=\"/(.{1,100})\">");
        //        ".{1,100}<ahref=\"/(.{1,50})\">");
        //  "alt="StarWarsTheCloneWarsS07E03CZtit.avi"/></div>
        //  </a><spanclass="duration-holderdh-max">00:18:22</span><spanclass="resolution-holder"><span>720x306</span></span><spanclass="file-size-holder">117MB</span></div><divclass="dataright"><h3>
        //  <ahref="/star-wars-the-clone-wars-s07e03-cztit-avi/85573401">StarWarsTheCloneWarsS07E03CZtit.avi</a></h3>
        //   "alt="close-btn"/></a>
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
        System.out.println(resultList);
        return resultList.size();
    }


}


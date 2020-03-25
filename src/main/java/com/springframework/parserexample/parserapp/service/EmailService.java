package com.springframework.parserexample.parserapp.service;

import com.springframework.parserexample.parserapp.data.RestResult;

import java.util.List;

public interface EmailService {

     void sendmail(String emailAddress, List<RestResult> restResult);
}

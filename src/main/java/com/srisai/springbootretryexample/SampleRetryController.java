package com.srisai.springbootretryexample;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRetryController {

    @GetMapping("/hello/{name}")
    @Retryable(value = {RuntimeException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000, maxDelay = 10000) )
    public String getInfo(@PathVariable String name) {
        System.out.println("I am in rest controller...");
        String str = null;
        str.length();
        return "Hello "+name;
    }

    @Recover
    public String getRecover(NullPointerException ex) {
        System.out.println("Number format exception happened "+ex.getMessage());
        return "NUll pointer Exception Ocuured ...";
    }

    @Recover
    public String getRecover(NumberFormatException ex) {
        System.out.println("Number format exception happened "+ex.getMessage());
        return "NumberFormatException Ocuured ...";
    }
}

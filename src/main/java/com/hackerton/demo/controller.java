package com.hackerton.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/healthy")
    public String healthCheck() {
        return "I'm healthy!";
    }

}

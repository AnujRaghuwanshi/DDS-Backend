package com.dadadarbar.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheck {

    @GetMapping("/status")
    public String healthCheck(){
        return "ok";
    }

    @GetMapping("/code")
    public Integer isAlive(){
        return 200;
    }
}

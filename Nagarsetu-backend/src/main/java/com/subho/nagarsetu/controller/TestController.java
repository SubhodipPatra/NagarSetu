package com.subho.nagarsetu.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "Nagar Setu Backend Running ";
    }
}

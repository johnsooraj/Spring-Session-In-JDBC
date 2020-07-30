package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {

    @GetMapping("/index")
    public String index() {
        return "Hello World";
    }

    @GetMapping("/index2")
    public String index2() {
        return "Hello World2";
    }

    @GetMapping("/index3")
    public String index3() {
        return "Hello World3";
    }
}

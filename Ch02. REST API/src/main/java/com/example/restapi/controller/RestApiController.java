package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/hello")
    public String hello() {
        var html = "<html> <body> <h1>Hello Spring Boot</h1> </body> </html>";

        return html;
    }
}

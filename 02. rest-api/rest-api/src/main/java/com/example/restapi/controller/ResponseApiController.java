package com.example.restapi.controller;

import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ResponseApiController {

    // http://localhost:8080/api/v1/string
    @GetMapping("/string")
    public String userV1() {
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");

        log.info("user : {}", user);
        return user.toString();
    }

    // http://localhost:8080/api/v1/object
    @GetMapping("/object")
    public UserRequest userV2() {
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");
        return user;
    }

    // http://localhost:8080/api/v1/response-entity
    // @ResponseBody -> @RestController가 아니고 @Controller 일때
    @GetMapping("/response-entity")
    public ResponseEntity<UserRequest> userV3() {
        var user = new UserRequest();
        user.setUserName("홍길동");
        user.setUserAge(10);
        user.setEmail("hong@gmail.com");
        log.info("user : {}", user);

        var response = ResponseEntity
                .status(HttpStatus.CREATED)
                .header("x-custom", "hi")
                .body(user);

        return response;
    }

}

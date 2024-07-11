package com.example.restapi.controller;

import com.example.restapi.model.BookRequest;
import com.example.restapi.model.ContactRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public BookRequest post(@RequestBody BookRequest bookRequest) {
        System.out.println("bookRequest = " + bookRequest);
        return bookRequest; // JSON 형식으로 BODY에 반환
    }

    // TODO RequestBody로 사용자의 이름, 전화번호, 이메일을 받는 POST Method를 만들어라
    @PostMapping("/contact")
    public ContactRequest contact(@RequestBody ContactRequest contactRequest) {
        System.out.println("contactRequest = " + contactRequest);
        return contactRequest;
    }
}

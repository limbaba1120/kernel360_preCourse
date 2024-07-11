package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/hello")
    public String hello() {
        var html = "<html> <body> <h1>Hello Spring Boot</h1> </body> </html>";

        return html;
    }

    //http://localhost:8080/api/echo/lim/age/20/is-man/true
    // TODO 대문자로 변환해서 RETURN => toUpperCase
    // TODO String 타입의 변수 외에 다른 타입 받아보기 (boolean, integer)
    @GetMapping("/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(@PathVariable String message,
                       @PathVariable int age,
                       @PathVariable boolean isMan) {
        System.out.println("echo message : " + message);
        System.out.println("echo age : " + age);
        System.out.println("echo isMan : " + isMan);

        return message.toUpperCase();
    }

    //http://localhost:8080/api/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping("/book")
    public void queryParam(@RequestParam String category,
                           @RequestParam String issuedYear,
                           @RequestParam(name = "issued-month") String issuedMonth,
                           @RequestParam(name = "issued_day") String issuedDay) {
        System.out.println("category = " + category);
        System.out.println("issuedYear = " + issuedYear);
        System.out.println("issuedMonth = " + issuedMonth);
        System.out.println("issuedDay = " + issuedDay);
    }

    //http://localhost:8080/api/book?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    @GetMapping("/book2")
    public void queryParamDto(BookQueryParam bookQueryParam) {
        System.out.println("bookQueryParam = " + bookQueryParam);
    }

    //http://localhost:8080/api/todo?firstNum=10&secondNum=20&isTrue=true
    // TODO Parameter 2가지 받는다. int 형태로 받아서 두 수의 덧셈, 곱셈
    // TODO String 타입 boolean 타입도 받아보기
    @GetMapping("/todo")
    public void queryParamTODO(@RequestParam int firstNum,
                               @RequestParam int secondNum,
                               @RequestParam boolean isTrue) {

        int sum = firstNum + secondNum;
        System.out.println("sum = " + sum);
        int mult = firstNum * secondNum;
        System.out.println("mult = " + mult);

        if (isTrue) {
            System.out.println("isTrue = " + isTrue);
        }
    }

    @DeleteMapping(path = {
            "/user/{userName}/delete",
            "/user/{username}/del"})
    public void delete(@PathVariable String userName) {
        log.info("user-name : {}", userName);
    }



}

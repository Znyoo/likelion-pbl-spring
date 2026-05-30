package com.example.likelion_pbl_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 👈 웹 API 컨트롤러 빈 등록
public class HelloController {

    @GetMapping("/hello") // 👈 GET /hello 요청 매핑
    public String hello() {
        return "Hello, Likelion!";
    }
}
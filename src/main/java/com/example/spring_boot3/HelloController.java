package com.example.spring_boot3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "🚀 Olá munto, Springboot3ApplicationTests!  servidor web está em execução.";
    }
}   
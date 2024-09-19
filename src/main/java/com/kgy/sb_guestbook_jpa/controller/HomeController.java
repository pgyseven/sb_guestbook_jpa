package com.kgy.sb_guestbook_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j // Slf4j logger 객체를 클래스에서 사용할 수 있도록 하는 어노테이션
public class HomeController {
    @GetMapping("/")
    public String index() {

        log.info("HomeController index.html 호출"); // 여기서 보이는 msg는 내가 치는게 아니라 ""안에 입력을 하면 그 내용이 msg임을 VSCode에서 표시해주는 것이다.

        return "index"; // /resources/remplates/index.html을 찾아서 반환
    }

    @GetMapping("/ex1")
    public String ex1() {

        log.info("ex1~~~~~~~~");
        return "/sample/ex1";
    }
}

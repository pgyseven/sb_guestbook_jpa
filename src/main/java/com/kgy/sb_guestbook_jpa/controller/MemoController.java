package com.kgy.sb_guestbook_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/guestbook")
public class MemoController {

    @GetMapping("/list")
    public void listMemos() {
        log.info("listMemos 컨트롤러~~~~~~~~~~");
        // = return "/guestbook/list";
    } 

}
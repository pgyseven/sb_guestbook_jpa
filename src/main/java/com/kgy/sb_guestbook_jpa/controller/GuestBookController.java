package com.kgy.sb_guestbook_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
import com.kgy.sb_guestbook_jpa.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping("/list")
    public void listAll(PageRequestDTO pageRequestDTO, Model model) {
        log.info("listAll 컨트롤러~~~~~~~~~~");

        model.addAttribute("result", guestBookService.getList(pageRequestDTO));
        // = return "/guestbook/list";
    } 

}
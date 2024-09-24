package com.kgy.sb_guestbook_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
import com.kgy.sb_guestbook_jpa.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @GetMapping("/showSaveForm")
    public void showSaveForm() {
        log.info("showSaveForm 컨트롤러~~~~~~~~~~");
    }
    

    @PostMapping("/save")
    public String saveGuestBook(GuestBookDTO guestBookDTO, RedirectAttributes redirectAttributes) {
        log.info("save : " + guestBookDTO.toString());
        Long savedGno = guestBookService.register(guestBookDTO);

        if (savedGno != null) {
            redirectAttributes.addAttribute("status", "success");


        } else {
            redirectAttributes.addAttribute("status", "fail");
        }

        return "redirect:/guestbook/list";

    }
    


    @GetMapping("/read")
    public void guestBookDetailPage(@RequestParam("gno")Long gno, Model model) {
        log.info(gno + "번 방명록 출력");
        model.addAttribute("result", guestBookService.read(gno));
    }
    


}
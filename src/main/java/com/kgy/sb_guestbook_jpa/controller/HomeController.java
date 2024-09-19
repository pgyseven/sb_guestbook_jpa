package com.kgy.sb_guestbook_jpa.controller;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kgy.sb_guestbook_jpa.dto.SampleDTO;

import java.time.LocalDateTime;

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

    @GetMapping("/ex2")
    public ModelAndView exModel(ModelAndView mav) {
        List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i->{
            SampleDTO dto = SampleDTO.builder()
                    .sno(i)
                    .first("First : " + i)
                    .last("Last : " + i)
                    .regDate(LocalDateTime.now())
                    .build();

            System.out.println(dto.toString());

            return dto;
        }).toList();

        mav.addObject("list", list); // Model객체에 바인딩
        mav.setViewName("/sample/exModel"); // 출력할 view 객체의 이름 : /sample/exModel.html

        return mav;
    }
}

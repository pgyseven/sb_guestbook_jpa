package com.kgy.sb_guestbook_jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
import com.kgy.sb_guestbook_jpa.dto.PageResultDTO;
import com.kgy.sb_guestbook_jpa.entity.GuestBook;


@SpringBootTest
public class GuestBookServiceTests {

    @Autowired
    private GuestBookService guestBookService;

    @Test
    public void testPaging() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .page(1)
            .size(10)
            .build();

        PageResultDTO<GuestBookDTO, GuestBook> PageResultDTO = guestBookService.getList(pageRequestDTO);

        for(GuestBookDTO g : PageResultDTO.getDtoList()) {
            System.out.println(g.toString());
        }


        for(Integer i : PageResultDTO.getPageList()) {
            System.out.println(i);
        }
    }
}

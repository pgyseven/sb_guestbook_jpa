package com.kgy.sb_guestbook_jpa.service;

import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.entity.GuestBook;

public interface GuestBookService {
    Long register(GuestBookDTO guestBookDTO);

    // GuestBookDTO(DTO)를 GuestBook(Entity)로 변환하는 메소드(default method로 작성)
    // default method : 인터페이스에 실제 내용(body)을 가지는 메소드를 작성할 수 있는데, 이 때 메소드 앞에 default라는 키워드를 붙여야 하고
    // default method에서는 반드시 반환값을 정해 반환을 해야 한다.

    default GuestBook dtoToEntity(GuestBookDTO guestBookDTO) {
        // default : 같은 패키지 내에서만 쓸 수 있도록 하는 접근제한자

        GuestBook entity = GuestBook.builder()
                .gno(guestBookDTO.getGno())
                .title(guestBookDTO.getTitle())
                .content(guestBookDTO.getContent())
                .writer(guestBookDTO.getWriter())
                .build();

        return entity;
    };
} 

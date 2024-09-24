package com.kgy.sb_guestbook_jpa.service;

import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
import com.kgy.sb_guestbook_jpa.dto.PageResultDTO;
import com.kgy.sb_guestbook_jpa.entity.GuestBook;

public interface GuestBookService {
    Long register(GuestBookDTO guestBookDTO);

    // GuestBookDTO(DTO)를 GuestBook(Entity)로 변환하는 메소드(default method로 작성)
    // default method : 인터페이스에 실제 내용(body)을 가지는 메소드를 작성할 수 있는데, 이 때 메소드 앞에 default라는 키워드를 붙여야 하고
    // default method에서는 반드시 반환값을 정해 반환을 해야 한다.

    // 방명록을 페이징하여 가져오는 메소드
    PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO);

    // 방명록 gno를 가지고 해당 번호의 방명록을 조회하는 메서드
    GuestBookDTO read(Long gno);

    default GuestBook dtoToEntity(GuestBookDTO guestBookDTO) {
        // default : 같은 패키지 내에서만 쓸 수 있도록 하는 접근제한자

        GuestBook entity = GuestBook.builder()
                .gno(guestBookDTO.getGno())
                .title(guestBookDTO.getTitle())
                .content(guestBookDTO.getContent())
                .writer(guestBookDTO.getWriter())
                .build();

        return entity;
        // 위 메서드는 유저가 입력한 데이터(방명록 신규저장, 수정)를 DB에 저장하기 위해 entity로 바꾸는 메서드
        // regDate, modDate는 entity가 DB에 저장될 때 자동으로 감지하여 저장되는 값이므로 DTO에는 그 값이 없다.
        // 
    };

    // GuestBook(Entity)를 GuestBookDTO(DTO)로 변환하는 메소드(default 메소드로 작성)
    default GuestBookDTO entityToDTO(GuestBook guestBook) {
        GuestBookDTO guestBookDTO = GuestBookDTO.builder()
                .gno(guestBook.getGno())
                .title(guestBook.getTitle())
                .content(guestBook.getContent())
                .writer(guestBook.getWriter())
                .regDate(guestBook.getRegDate())
                .modDate(guestBook.getModDate())
                .build();

        return guestBookDTO;
    }

    
} 

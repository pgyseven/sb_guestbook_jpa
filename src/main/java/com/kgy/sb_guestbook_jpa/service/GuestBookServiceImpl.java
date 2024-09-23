package com.kgy.sb_guestbook_jpa.service;

import org.springframework.stereotype.Service;

import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.entity.GuestBook;
import com.kgy.sb_guestbook_jpa.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long register(GuestBookDTO guestBookDTO) {

        log.info(guestBookDTO.toString());

        GuestBook entity = dtoToEntity(guestBookDTO);

        entity = guestBookRepository.save(entity);

        return entity.getGno();
    }

}

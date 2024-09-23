package com.kgy.sb_guestbook_jpa.dto;

import java.util.List;
import java.util.function.Function;
// Function : 메소드의 자체 클래스.
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

// 페이징 정보와 페이징된 데이터를 함께 가지고 있는 DTO 클래스
// <DTO, EN> : 여기에서 DTO는 페이징된 실제 데이터를 의미하고, EN은 페이징 정보를 가지고 있는 entity 객체

// Function<EN, DTO> fn : 엔티티 객체들을 DTO로 변환해주는 함수를 매개변수로 받는다.

public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        this.dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}

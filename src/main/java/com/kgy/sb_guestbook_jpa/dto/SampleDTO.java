package com.kgy.sb_guestbook_jpa.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data // Getter, Setter, toString, equals, hashCode() 자동 생성해주는 annotaion
@Builder
public class SampleDTO {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regDate;
}

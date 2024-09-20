package com.kgy.sb_guestbook_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class GuestBook extends BaseEntity {
    // 상속받은 애들
    // private LocalDateTime regDate;
    // private LocalDateTime modDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;
}

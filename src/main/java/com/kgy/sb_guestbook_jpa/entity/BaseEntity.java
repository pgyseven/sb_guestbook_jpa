package com.kgy.sb_guestbook_jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// 엔티티 관련 작업을 할 때 데이터의 등록 시간, 수정 시간같이 
// 자동으로 처리되어야 하는 부분을 또 다른 엔티티 클래스로 만들자

// @MappedSuperclass : 테이블로 생성되지 않고 다른 클래스의 부모 클래스가 되는 Entity Class
@MappedSuperclass
@Getter
@EntityListeners(value={AuditingEntityListener.class})
// @EntityListeners : 데이터가 언제 insert, update 등 실행되는지 감시해주는 역할
// AuditingEntityListener 클래스에 의해 엔티티의 데이터가 추가되고, 수정될 때 자동으로 감지하여 값을 넣어준다.
abstract class BaseEntity {

    @CreatedDate // 엔티티 객체 데이터의 생성 시간
    @Column(name="regDate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 엔티티 객체 데이터가 수정된 날짜, 시간
    @Column(name="modDate") // updatable = true가 default이기 때문에 쓰지 않아도 된다.
    private LocalDateTime modDate;
}
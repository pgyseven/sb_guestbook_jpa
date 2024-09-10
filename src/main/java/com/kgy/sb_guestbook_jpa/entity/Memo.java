package com.kgy.sb_guestbook_jpa.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // persistence : 데이터를 영구적으로 저장해서 관리하는 기술
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // -> 아래의 클래스를 Entity 클래스(=자바 객체이면서 DB 테이블을 표현한 것)로 만든다는 의미
@Table(name="tbl_memo") // 테이블을 생성할 때 이름을 tbl_memo로 하도록 한다.
@Builder // 컬럼이기도 하면서 클래스이기도 하기 때문에 객체로 만들기 위해서 Builder 어노테이션 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED) // = 기본 생성자를 protected하게 만든다.
@AllArgsConstructor // 얘가 없으면 Builder 패턴에 에러난다.
@Getter // 보통 JPA에서는 Setter를 자동으로 만들지 않는다. 직접 만들어서 쓴다. Setter는 데이터를 변경하는 애기 때문이다.
@ToString
public class Memo {

    @Id // 아래의 멤버 변수를 테이블의 PK로 지정한다.
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    // PK로 지정한 아래의 멤버변수가 UNIQUE한 값을 가지도록 하는 전략
    // IDENTITY : auto-increment처럼 동작
    private Long mno; 

    @Column(length = 200, nullable = false)
    // Column 어노테이션을 이용해 컬럼명, 길이, null or not null을 지정할 수 있다.
    // = 얘는 그냥 일반 컬럼이고, 크기는 200바이트를 준다. 그리고 null이 가능하지 못하도록 설정했다.
    private String memoText;

    private Date registDate;
}

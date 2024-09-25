package com.kgy.sb_guestbook_jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository : Entity 클래스를 기본적인 CRUD는 다 할 수 있도록 만들어졌다.
// 제너릭으로 만들어졌기 때문에 JpaRepository<T, ID>로 사용해야 한다.
// 이 때 T는 해당 Entity 클래스, ID는 그 클래스 PK의 데이터 타입을 입력하면 된다.
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kgy.sb_guestbook_jpa.entity.Memo;
import java.util.List;
import java.util.Optional;


// JpaRepository<CRUD 기능을 만들 Entity 클래스, Entity 클래스 PK 컬럼의 데이터 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> { // 인터페이스끼리 상속받을 때는 extends를 쓴다.
    // JpaRepository는 인터페이스를 상속하는 것만으로도 기본적인 CRUD를 할 수 있는 메소드들이 포함되어 있다.

    // Spring DATA JPA에서 사용자 지정 쿼리문을 작성하는 방법 3가지
    // 1) Query Method : 메서드를 만들때 JPA에서 지정한 키워드를 이용하여 메서드명을 만들면 메서드 이름에 따라 쿼리문을 자동을 생성

    Optional<Memo> findByMnoIs(Long findMno);

    // 2) Querydsl을 이용한 방식 : 쿼리문을 자바 클래스와 메서드를 이용하여 조합하는 방식(JPA 측에서는 가장 속도가 빠르다고 평가하고 있다.)
    // - 문자가 아닌 코드로 쿼리를 작성함으로써, 컴파일 시점에 문법 오류를 쉽게 확인할 수 있다.
    // - 자동 완성등 IDE 의 도움을 받을 수 있다.
    // - 동적 쿼리문 작성이 편리하다.
    // - 설정이 어렵다(Gradle, groovy문법에 의해 설정해야함)
    // - 버전에 따라 설정이 달라진다.


    // 3) @Query 을 이용한 방식 : 프로그래머가 직접 실행될 쿼리문을 기술하는 방식
    // value 속성에 JPQL을 사용할 수 도 있고, nativequerty문을 사용할 수 도 있다.
    @Query(value = "SELECT * FROM tb1_memo WHERE mono_text LIKE %?1%", nativeQuery = true)
    List<Memo> findByMemoTextLike(@Param("searchText") String searchText);

    // JPQL (Java Persistence Query Language) : 데이터 베이스의 테이블을 대상으로 실행되는 쿼리문이 아닌, 자바 엔티티 클래스 대상으로 실행되는 쿼리문
    // - 실제 native Query문과 유사하여 많이 사용된다.
    // - 엔티티는 반드시 대문자를 이용하고, 속성은 소문자를 이용해서 작성해야한다.
    // - 테이블 이름이 아닌 엔티티 이름을 사용해야 한다.
    // - 테이블 별칭 사용을 필수 해야 한다.(별치 사용시 as 키워드 생략 가능)
    // - 동적 쿼리문을 생성하는데 불편하다.

    @Query("select count(m) from Memo m")
    int getCountMemo();

    // 우리 나라에서는 , jpql을 이용해 사용자 지정 쿼리문 작성하는 방식을 제일 많이 따른다.
    // 보통 기본적인 insert , update, delete 등의 DML 문은 JpaRepository 인터페이스에서 상속 받은 메서드를 이용하여 수행(사용자 지정 쿼리문을 만들지 않음)
    // 서브쿼리를 이용한 DML문 이용시에는 JPQL을 이용한다.
    // 기본적인 select(pk 문에 의해 select), 페이징 -> JpaRepository 인터페이스에서 상속 받은 메서드를 이용하여 수행(사용자 지정 쿼리문을 만들지 않음)
    // 복잡한 서브쿼리나, 조인, 집계함수(count, group by 등)의 쿼리문을 사용할때는 jpql을 이용한다.

}

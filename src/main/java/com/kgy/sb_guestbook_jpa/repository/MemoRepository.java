package com.kgy.sb_guestbook_jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository : Entity 클래스를 기본적인 CRUD는 다 할 수 있도록 만들어졌다.
// 제너릭으로 만들어졌기 때문에 JpaRepository<T, ID>로 사용해야 한다.
// 이 때 T는 해당 Entity 클래스, ID는 그 클래스 PK의 데이터 타입을 입력하면 된다.

import com.kgy.sb_guestbook_jpa.entity.Memo;

// JpaRepository<CRUD 기능을 만들 Entity 클래스, Entity 클래스 PK 컬럼의 데이터 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> { // 인터페이스끼리 상속받을 때는 extends를 쓴다.
    // JpaRepository는 인터페이스를 상속하는 것만으로도 기본적인 CRUD를 할 수 있는 메소드들이 포함되어 있다.
}

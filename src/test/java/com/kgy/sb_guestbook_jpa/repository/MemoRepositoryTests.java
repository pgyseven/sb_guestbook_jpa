package com.kgy.sb_guestbook_jpa.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kgy.sb_guestbook_jpa.entity.Memo;


@SpringBootTest // 아래의 클래스가 테스트 클래스임을 알려준다.(스프링 컨텍스트에 접근할 수 있도록 해준다.)
public class MemoRepositoryTests {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void insertTest() {
        Memo newMemo = Memo.builder() 
            .memoText("insert test 메모")
            .registDate(new Timestamp(System.currentTimeMillis()))
            .build();

        // 메모리에 있는 newMemo 객체를 영속적인 공간(DB)에 저장한다는 의미에서 save를 사용하여
        // insert를 시킨다.
        newMemo = memoRepository.save(newMemo); // **save되어 return된 newMemo에는 PK가 들어가있다.
        System.out.println("새로운 메모 등록 성공 ~" + newMemo);
    }

    @Test
    public void selectMemoByMno() {

        Long mno = 2L; // L : Long 타입

        // Optional<> : 자바 17 버전에서 새롭게 나온거
        Optional<Memo> result = memoRepository.findById(mno);

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo.toString());
        } else {
            System.out.println("Memo not found");
        }
    }
}

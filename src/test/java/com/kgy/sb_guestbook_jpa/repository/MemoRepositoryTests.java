package com.kgy.sb_guestbook_jpa.repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

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

        // newMemo 에는 PK가 없다.
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
            //memo 에는 PK가 있다.(이미 영속적인 공간에 저장되어있던 객체)
            Memo memo = result.get();
            System.out.println(memo.toString());
        } else {
            System.out.println("Memo not found");
        }
    }

    @Test
    public void updateTest() {

        Long mno = 1L; 

        Optional<Memo> result = memoRepository.findById(mno);
        if(result.isPresent()) { // mno가 가지고 있는 Memo가 null이
            Memo memo = result.get(); // memo 객체를 가져와서 그 객체의 메모내용을 변경할 수 있는 setter를 호출하여 메모 내용을 변경.
            memo.setMemoText("update Test Memo");

            memoRepository.save(memo); 
        }
    }


    @Test
    public void deleteTest() {
        Long mno = 1L;

        Optional<Memo> result = memoRepository.findById(mno);
        if(result.isPresent()) {
            memoRepository.deleteById(mno);
        }
    }

    
    @Test
    public void insertDummies() { // IntStream : 정수를 순서대로 나열해주는 interface
        // 람다식 형태로 반복문을 돌려 Memo객체를 100개 만들어 insert
        IntStream.rangeClosed(1, 100).forEach(i-> {
            Memo newMemo = Memo.builder()
                .memoText("더미" + i + "번째 메모")
                .registDate(new Timestamp(System.currentTimeMillis()))
                .build();
            memoRepository.save(newMemo); // PK(mno)가 없으므로 jap는 save()에서 memo객체를 insert한다.
        });
    }

    @Test
    public void selectAll() {
        // findAll() : JPA에서 SQL 쿼리를 실행하여 DB에서 모든 Memo 객체를 select하는 메소드
        Iterable<Memo> memos = memoRepository.findAll();
        memos.forEach(System.out::println);
    }

    @Test
    public void testPagingMemo() {
        // JPA가 내부적으로 Dialect(방언=사투리) 객체를 사용하여 연결되어있는 DataSouce의 DataBase 제품에 따른
        // 필요 쿼리문을 호출하여 사용하도록 한다.
        // -> DB가 바뀔 때마다 필요한 페이징 쿼리문을 유지/보수할 필요가 없다.


        // PageRequest : Pageable 인터페이스를 구현한 구현체 클래스
        // (페이징 시 필요한 파라메터(현재 페이지 번호, 1페이지 당 몇개씩 출력)를 담고 있는 객체)
        Sort sort = Sort.by(Order.desc("mno"));
        Pageable Pageable = PageRequest.of(0, 10, sort);

        // Page : 현재 페이징된 데이터와 페이징을 처리할 수 있는 속성을 가지고 있는 객체이다.
        Page<Memo> result = memoRepository.findAll(Pageable);

        for(Memo m : result.getContent()) {
            System.out.println(m.toString());
        }

        System.out.println("===========================================================================");
        System.out.println("총 페이지 후 : " + result.getTotalPages());
        System.out.println("현재 페이지 번호 : " + result.getNumber());
        System.out.println("페이지 당 출력되는 데이터 수 : " + result.getSize());
        System.out.println("다음 페이지가 있습니까? " + result.hasNext());
        System.out.println("이전 페이지가 있습니까? " + result.hasPrevious());
        System.out.println("시작 페이지(0번째) 입니까? " + result.isFirst());
        System.out.println("마지막 페이지 입니까? " + result.isLast());
    }
}




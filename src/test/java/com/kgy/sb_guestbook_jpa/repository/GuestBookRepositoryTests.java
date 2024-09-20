package com.kgy.sb_guestbook_jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kgy.sb_guestbook_jpa.entity.GuestBook;

@SpringBootTest
public class GuestBookRepositoryTests {
    @Autowired
    private GuestBookRepository gusetBookRepository;

    @Test
    public void insertDummies() {
        for (int i = 0 ; i < 150 ; i++) {
            GuestBook guestBook = GuestBook.builder()
                .writer("writer : " + i)
                .title("title : " + i)
                .content("content : " + i)
                .build();

            // guestBook 객체에는 PK인 gno값을 넣지 않고 객체를 만들었다.
            // 그러므로 아래의 save()에서는 insert문이 수행되고,
            // insert문의 수행 이후에 반환된 guestBook에 PK(gno)값이 들어있는 것을 볼 수 있다.
            guestBook = gusetBookRepository.save(guestBook);
            System.out.println(guestBook.toString());
        }
    }
}

package com.kgy.sb_guestbook_jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
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

            // Assert(단정문) : 단정문을 사용하여 테스트가 성공 또는 실패인지 직관적으로 명확하게 코딩하도록 한다.
            Assert.notNull(guestBook, "GuestBook ID should not be null -> 테스트 실패");

            System.out.println(guestBook.toString());
        }
    }

    @Test
    public void updateTest() {
        Long gno = 4L;
        Optional<GuestBook> result = gusetBookRepository.findById(gno);

        if (result.isPresent()) {
            GuestBook gusetbook = result.get();
            LocalDateTime beforeModify = gusetbook.getModDate();

            gusetbook.changeTitle("4updated title");
            gusetbook.changeContent("4updated content");

            // 이 때의 guestbook 객체는 gno(PK)값이 존재하므로 save()에 의해 update문이 수행된다.
            GuestBook modifyGuestBook = gusetBookRepository.save(gusetbook);

            LocalDateTime afterModify = modifyGuestBook.getModDate();
            Assert.isTrue(beforeModify.isBefore(afterModify), "테스트 실패");
        }
    }

    
}

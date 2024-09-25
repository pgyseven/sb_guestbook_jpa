package com.kgy.sb_guestbook_jpa.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.kgy.sb_guestbook_jpa.dto.GuestBookDTO;
import com.kgy.sb_guestbook_jpa.dto.PageRequestDTO;
import com.kgy.sb_guestbook_jpa.dto.PageResultDTO;
import com.kgy.sb_guestbook_jpa.entity.GuestBook;
import com.kgy.sb_guestbook_jpa.repository.GuestBookRepository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService{

    private final GuestBookRepository guestBookRepository;

    @PersistenceContext
    private EntityManager em; // Entity를 관리하는 객체

    @Override
    public Long register(GuestBookDTO guestBookDTO) {

        log.info(guestBookDTO.toString());

        GuestBook entity = dtoToEntity(guestBookDTO);

        entity = guestBookRepository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO pageRequestDTO) {

        String jpql = "select g from GuestBook g where ";

        // 페이징 정렬 방식
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<GuestBook> result = null;

        // 검색어가 있을 경우의 페이징 --------------------------------------------------------
        if (StringUtils.hasText(pageRequestDTO.getSearchType()) && StringUtils.hasText(pageRequestDTO.getSearchValue())) {
            switch (pageRequestDTO.getSearchType()) {
                case "title" :
                    jpql += "g.title like ";
                    break;
                case "writer" :
                    jpql += "g.writer like ";
                    break;
                case "content" :
                    jpql += "g.content like ";
                    break;
            }
            jpql += "concat('%', :searchValue, '%') order by g.gno desc";



            System.out.println("JPQL : " + jpql);

            TypedQuery<GuestBook> query = em.createQuery(jpql, GuestBook.class); //문자열로 된 jpql 쿼리문을 쿼리문 객체로 생성

            // searchValue 값을 parameter에 대입
            query = query.setParameter("searchValue",pageRequestDTO.getSearchValue());

            // 쿼리문 실행 후 결과를 가져옴 (페이징 되지 않은 결과)
            List<GuestBook> list = query.getResultList();
            int start = (int)pageable.getOffset();
            int end = Math.min(list.size(), start + pageRequestDTO.getSize());

            List<GuestBook> pageContent = list.subList(start, end);

            result = new PageImpl<GuestBook>(pageContent, pageable, list.size());

        } else {
            // 검색어가 없을 경우 페이징
            // Pageable(페이징에 필요한 매개변수(PageRequestDTO) + 정렬방식)을 넘겨주며 페이징하여 결과를 가져온다.
            result = guestBookRepository.findAll(pageable);

        }



        // 람다식을 이용한 방법
        // Entity를 DTO로 변환하는 함수를 전달하기 위해 준비
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDTO(entity));
        
        // Page<GuestBook을 함수에 전달하여, Entity 각각을 DTO로 변환한 후 List로 만들고, 그 List를 가지고 있는 PageResultDTO 객체를 만들어 반환>
        return new PageResultDTO<>(result, fn);

        // // 람다식을 이용하지 않으면???
        // List<GuestBook> guestBookList = result.getContent();

        // List<GuestBookDTO> guestBookDTOList = new ArrayList<>();

        // for(GuestBook g : guestBookList) {
        //     GuestBookDTO dto = entityToDTO(g);
        //     guestBookDTOList.add(dto);
        // };

        // return new PageResultDTO<>(guestBookDTOList);
    }

    @Override
    public GuestBookDTO read(Long gno) {
        GuestBookDTO guestBookDTO = null;

        Optional<GuestBook> result = guestBookRepository.findById(gno);

        if(result.isPresent()) {
            GuestBook guestBook = result.get();
            return
            
            guestBookDTO = entityToDTO(guestBook);
            
        }
        return guestBookDTO;
        
    }

}

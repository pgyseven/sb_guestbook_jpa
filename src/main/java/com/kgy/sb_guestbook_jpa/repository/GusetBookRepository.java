package com.kgy.sb_guestbook_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgy.sb_guestbook_jpa.entity.GuestBook;

public interface GusetBookRepository extends JpaRepository<GuestBook, Long> {
    
}

package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.BookStatusCommEntity;

public interface BookStatusCommRepository extends JpaRepository<BookStatusCommEntity,Long> {

}

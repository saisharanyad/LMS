package com.lms.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.entity.BooksEntity;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity,Long> {

	public static final String SEARCH_BOOKS = "SELECT b FROM BooksEntity b WHERE " +
	           "(:q IS NULL OR (LOWER(b.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
	           "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :q, '%')))) " +
	           "AND (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +      
	           "AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) " +
	           "AND (:year IS NULL OR b.publishedYear = :year) ";
	
	
	@Query(SEARCH_BOOKS)
	Page<BooksEntity> searchBooks(@Param("q") String q, 
            @Param("title") String title, 
            @Param("author") String author, 
            @Param("year") Integer year, 
            Pageable pageable);
	
	
}

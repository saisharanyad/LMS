package com.lms.service;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import com.lms.dto.BookResponseDTO;
import com.lms.dto.BooksDTO;

@Service
public interface BooksService {

	void createBook(BooksDTO booksDto);
	boolean deleteBook(long bookId);
	boolean updateBookAvailabilityStatus(long bookId,String availabilityStatus);
	BookResponseDTO searchBooks(String partialSearchText,String title,
			String author,Integer publishedYear,
			Integer page, Integer size);
	
}

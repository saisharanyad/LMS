package com.lms.service.impl;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lms.constants.BooksConstants;
import com.lms.dto.BookResponseDTO;
import com.lms.dto.BooksDTO;
import com.lms.dto.PaginationDTO;
import com.lms.entity.BookStatusCommEntity;
import com.lms.entity.BooksEntity;
import com.lms.entity.WishlistUsersEntity;
import com.lms.exception.ResourceNotFoundException;
import com.lms.repository.BookStatusCommRepository;
import com.lms.repository.BooksRepository;
import com.lms.repository.WishlistUsersRepository;
import com.lms.service.BooksService;

import jakarta.transaction.Transactional;

@Service
public class BooksServiceImpl implements BooksService{
	
	private static final Logger log = LoggerFactory.getLogger(BooksServiceImpl.class);
	
	private BooksRepository booksRepository;
	
	private BookStatusCommRepository bookStatusCommRepository;
	
	private WishlistUsersRepository wishlistUsersRepository;
	
	@Autowired
	public BooksServiceImpl(BooksRepository booksRepository,
			BookStatusCommRepository bookStatusCommRepository,
			WishlistUsersRepository wishlistUsersRepository) {
		this.booksRepository = booksRepository;
		this.bookStatusCommRepository = bookStatusCommRepository;
		this.wishlistUsersRepository = wishlistUsersRepository;
	}

	@Override
	public void createBook(BooksDTO booksDto) {
		
				BooksEntity book = new BooksEntity();
				
				book.setAuthor(booksDto.getAuthor());
				book.setTitle(booksDto.getTitle());
				book.setIsbn(booksDto.getIsbn());
				book.setPublishedYear(booksDto.getPublishedYear());
				book.setAvailabilityStatus(booksDto.getAvailabilityStatus());
				book.setCreated(new Timestamp(System.currentTimeMillis()));
				book.setUpdated(new Timestamp(System.currentTimeMillis()));
				
				LocalDateTime endOfTime = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
				Timestamp timestamp = Timestamp.valueOf(endOfTime);
				book.setDeleted(timestamp);
				
				book = booksRepository.save(book);
				
				log.info("Book is saved with bookId :::"+book.getBookId());
				
	}

	@Override
	public boolean deleteBook(long bookId) {
		
		BooksEntity book = booksRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "Book_Id", String.valueOf(bookId))
                );
			
			book.setUpdated(new Timestamp(System.currentTimeMillis()));
			book.setDeleted(new Timestamp(System.currentTimeMillis()));
			booksRepository.save(book);
			return true;
			
	}

	@Override
	@Transactional
	public boolean updateBookAvailabilityStatus(long bookId, String availabilityStatus) {
		
		BooksEntity book = booksRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", "Book_Id", String.valueOf(bookId))
                );
		
		//update status in Books table
			if(availabilityStatus.equals("Y")) {
				book.setAvailabilityStatus(BooksConstants.AVAILABILITY_STATUS_YES);
				book.setUpdated(new Timestamp(System.currentTimeMillis()));
			}	
			else {
				log.info("invalid availability status for bookid ::: "+availabilityStatus);
				throw new IllegalArgumentException("invalid availability status for bookid ::: "+availabilityStatus);
			}
			
			//update book status
			booksRepository.save(book);
			
			//fetch all wishlist users for bookId
			List<Long> userIds = wishlistUsersRepository.findUserIdsByBookId(bookId);
			
			if(userIds.isEmpty()) {
				log.info("No wishlists for bookId ");
			}
			
			
			for(Long wishlistuserId : userIds) {
			//insert in outbox table
				BookStatusCommEntity statusComm = new BookStatusCommEntity();
				statusComm.setBookId(bookId);
				statusComm.setUserId(wishlistuserId);
				statusComm.setAvailabilityStatus(BooksConstants.AVAILABILITY_STATUS_YES);
				statusComm.setNotificationStatus("COMM_AWAIT");
				statusComm.setNotificationDesc("Availability awaiting communication");
				statusComm.setCreated(new Timestamp(System.currentTimeMillis()));
				
				bookStatusCommRepository.save(statusComm);
		
		}
		return true;
	}

	@Override
	public BookResponseDTO searchBooks(String partialSearchText, String title,
			String author, Integer publishedYear,
			Integer page, Integer size) {
		
		String query = StringUtils.hasText(partialSearchText) ? partialSearchText : null;
	    String t = StringUtils.hasText(title) ? title : null;
	    String a = StringUtils.hasText(author) ? author : null;
	    
	    Integer y = (publishedYear != null && publishedYear != -1) ? publishedYear : null;
	    
		Pageable pageable = PageRequest.of(page,size);
		
		Page<BooksEntity> bookresults = booksRepository.searchBooks(query,
				t,a,y,pageable);
		
		 List<BooksDTO> bookList = bookresults.getContent().stream().map(entity -> {
		        BooksDTO dto = new BooksDTO();
		        dto.setBookId(entity.getBookId());
		        dto.setTitle(entity.getTitle());
		        dto.setAuthor(entity.getAuthor());
		        dto.setIsbn(entity.getIsbn());
		        dto.setPublishedYear(entity.getPublishedYear());
		        dto.setAvailabilityStatus(entity.getAvailabilityStatus());
		        dto.setCreated(entity.getCreated());
		        dto.setUpdated(entity.getUpdated());
		        dto.setDeleted(entity.getDeleted());
		        return dto;
		    }).collect(Collectors.toList());
		
		log.info("bookresults getTotalPages ::: "+bookresults.getTotalPages());
		log.info("bookresults getTotalElements ::: "+bookresults.getTotalElements());
		log.info("bookresults getContent ::: "+bookresults.getNumber());
		
		 	PaginationDTO pagination = new PaginationDTO();
		    pagination.setTotalItems(bookresults.getTotalElements());
		    pagination.setTotalPages(bookresults.getTotalPages());
		    pagination.setCurrentPage(bookresults.getNumber()); // 0-indexed
		    pagination.setPageSize(bookresults.getSize());
		    
		    
		    BookResponseDTO response = new BookResponseDTO();
		    response.setBookDtoList(bookList);
		    response.setPaginationDto(pagination);
		    
		    
		return response;
	}

}

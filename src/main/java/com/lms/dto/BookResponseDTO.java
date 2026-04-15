package com.lms.dto;

import java.util.List;

public class BookResponseDTO {
	
	List<BooksDTO> bookDtoList;
	PaginationDTO paginationDto;
	
	public List<BooksDTO> getBookDtoList() {
		return bookDtoList;
	}
	public void setBookDtoList(List<BooksDTO> bookDtoList) {
		this.bookDtoList = bookDtoList;
	}
	public PaginationDTO getPaginationDto() {
		return paginationDto;
	}
	public void setPaginationDto(PaginationDTO paginationDto) {
		this.paginationDto = paginationDto;
	}
	
	
	

}

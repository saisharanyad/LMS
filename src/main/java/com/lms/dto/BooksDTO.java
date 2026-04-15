package com.lms.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BooksDTO {
	
		private Long bookId;
		
		@NotEmpty(message = "author can not be a null or empty")
	    @Size(min = 1, max = 100, message = "The length of the author should be between 5 and 30")
	    private String author;
		
		@NotEmpty(message = "title can not be a null or empty")
	    @Size(min = 1, max = 100, message = "The length of the title should be between 1 and 100")
	    private String title;
		
		@NotEmpty(message = "isbn can not be a null or empty")
	    @Size(min = 1, max = 20, message = "The length of the isbn should be between 1 and 20")
	    private String isbn;
		
		@NotEmpty(message = "publishedYear can not be a null or empty")
	    @Size(min = 4, max = 10, message = "The length of the publishedYear should be between 4 and 10")
	    private String publishedYear;
		
		@NotEmpty(message = "availabilityStatus can not be a null or empty")
		@Size(min = 1, max = 20, message = "The length of availabilityStatus should be between 1 and 20")
	    private String availabilityStatus;
		
	    private Timestamp created;
		private Timestamp updated;
		private Timestamp deleted;
	    
	    
	    
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		public String getPublishedYear() {
			return publishedYear;
		}
		public void setPublishedYear(String publishedYear) {
			this.publishedYear = publishedYear;
		}
		public String getAvailabilityStatus() {
			return availabilityStatus;
		}
		public void setAvailabilityStatus(String availabilityStatus) {
			this.availabilityStatus = availabilityStatus;
		}
		public Timestamp getCreated() {
			return created;
		}
		public void setCreated(Timestamp created) {
			this.created = created;
		}
		public Timestamp getUpdated() {
			return updated;
		}
		public void setUpdated(Timestamp updated) {
			this.updated = updated;
		}
		public Timestamp getDeleted() {
			return deleted;
		}
		public void setDeleted(Timestamp deleted) {
			this.deleted = deleted;
		}
	    
	    
	    
}

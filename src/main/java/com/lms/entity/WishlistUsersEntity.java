package com.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Table(name="wishlist_users")
@Entity
@IdClass(WishlistId.class)
public class WishlistUsersEntity {

	@Id
	@Column(name="user_id")
    private Long userId;

	@Id
    @Column(name="book_id")
    private String bookId;
	
	@Column(name="user_status")
	private String userStatus;
	
	@Column(name="book_status")
	private String bookStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	
    
	

}

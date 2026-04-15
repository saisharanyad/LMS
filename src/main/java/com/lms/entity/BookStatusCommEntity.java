package com.lms.entity;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="book_status_comm_outbox")
@Entity
public class BookStatusCommEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comm_id")
    private Long commId;
	
	@Column(name="book_id")
    private Long bookId;
	
	@Column(name="user_id")
	private Long userId;

    @Column(name="availability_status")
    private String availabilityStatus;

    @Column(name="notification_status")
    private String notificationStatus;

    @Column(name="notification_desc")
    private String notificationDesc;
    
    @Column(name="created")
    private Timestamp created;
    
    @Column(name="updated")
    private Timestamp updated;

	public Long getCommId() {
		return commId;
	}

	public void setCommId(Long commId) {
		this.commId = commId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
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

	@Override
	public String toString() {
		return "BookStatusCommEntity [commId=" + commId + ", bookId=" + bookId + ", userId=" + userId
				+ ", availabilityStatus=" + availabilityStatus + ", notificationStatus=" + notificationStatus
				+ ", notificationDesc=" + notificationDesc + ", created=" + created + ", updated=" + updated + "]";
	}
    
    
    
    
}

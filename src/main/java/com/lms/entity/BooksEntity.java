package com.lms.entity;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Table(name="books")
@Entity
public class BooksEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
    private Long bookId;

    @Column(name="author")
    private String author;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;
    
    @Column(name="published_year")
    private String publishedYear;
    
    @Column(name="availability_status")
    private String availabilityStatus;
    
    @Column(name="created")
    private Timestamp created;
    
    @Column(name="updated")
    private Timestamp updated;
    
    @Column(name="deleted")
    private Timestamp deleted;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    		name="wishlist_users",
    		joinColumns = @JoinColumn(name = "book_id"),
    		inverseJoinColumns = @JoinColumn(name = "user_id")
    		)
    private Set<UsersEntity> users = new LinkedHashSet<>();

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

	public Set<UsersEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UsersEntity> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "BooksEntity [bookId=" + bookId + ", author=" + author + ", title=" + title + ", isbn=" + isbn
				+ ", publishedYear=" + publishedYear + ", availabilityStatus=" + availabilityStatus + ", created="
				+ created + ", updated=" + updated + ", deleted=" + deleted + ", users=" + users + "]";
	}

	
    
    
    
}

package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.entity.WishlistId;
import com.lms.entity.WishlistUsersEntity;

@Repository
public interface WishlistUsersRepository extends JpaRepository<WishlistUsersEntity,WishlistId> {
	
	
	    @Query("SELECT w.userId FROM WishlistUsersEntity w WHERE w.bookId = :bookId")
	    List<Long> findUserIdsByBookId(@Param("bookId") Long bookId);
	
}

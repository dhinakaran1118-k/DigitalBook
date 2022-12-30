package com.books.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_READER")
public class Reader {

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="reader_id")	
	private long readerId;
	
	@Column(name="author_id")
	private long authorId;
	
	@Column(name="book_id")
	private long bookId;
	
	
	@Column(name="subcription_date")
	private LocalDateTime subcriptionDate;
	
	@Column(name="subscriber_name")
	private String subscriberName;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
	public Reader() {
		super();
	}
	
	public Reader(long readerId, long authorId, long bookId, LocalDateTime subcriptionDate, String subscriberName,
			LocalDateTime updatedOn) {
		super();
		this.readerId = readerId;
		this.authorId = authorId;
		this.bookId = bookId;
		this.subcriptionDate = subcriptionDate;
		this.subscriberName = subscriberName;
		this.updatedOn = updatedOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getReaderId() {
		return readerId;
	}

	public void setReaderId(long readerId) {
		this.readerId = readerId;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public LocalDateTime getSubcriptionDate() {
		return subcriptionDate;
	}

	public void setSubcriptionDate(LocalDateTime subcriptionDate) {
		this.subcriptionDate = subcriptionDate;
	}

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

}

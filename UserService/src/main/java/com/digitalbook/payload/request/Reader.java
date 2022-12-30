package com.digitalbook.payload.request;

import java.time.LocalDateTime;

public class Reader {

	private long id;
	private long readerId;
	private long authorId;
	private long bookId;
	private LocalDateTime subcriptionDate;
	private String subscriberName;
	private LocalDateTime updatedOn;

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

	@Override
	public String toString() {
		return "Reader [id=" + id + ", readerId=" + readerId + ", authorId=" + authorId + ", bookId=" + bookId
				+ ", subcriptionDate=" + subcriptionDate + ", subscriberName=" + subscriberName + ", updatedOn="
				+ updatedOn + "]";
	}

}

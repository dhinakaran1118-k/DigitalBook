package com.books.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BOOK")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "code")
	private int code;
	
	@Column(name = "author_id")
	private int authorId;
	
	@Column(name = "author_name")
	private String authorName;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "is_active")
	private int isActive;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", code=" + code + ", authorId=" + authorId
				+ ", authorName=" + authorName + ", price=" + price + ", category=" + category + ", publisher="
				+ publisher + ", isActive=" + isActive + "]";
	}
		
}

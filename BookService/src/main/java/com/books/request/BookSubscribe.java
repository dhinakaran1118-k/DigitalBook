package com.books.request;

public class BookSubscribe {
	
	private long authorId;
	private long bookId;	
	private String subscriberName;
	
	public BookSubscribe(long authorId, long bookId, String subscriberName) {
		super();
		this.authorId = authorId;
		this.bookId = bookId;
		this.subscriberName = subscriberName;
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

	public String getSubscriberName() {
		return subscriberName;
	}

	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	
}

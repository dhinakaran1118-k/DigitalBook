package com.books.response;

import java.time.LocalDateTime;

public class BookSubscribeResponse {

	private long bookId;	
	private String subscriberName;	
	private LocalDateTime subscriptionDate;	
	private long subscriptionId;
	
	public BookSubscribeResponse(long bookId, String subscriberName, LocalDateTime subscriptionDate,
			long subscriptionId) {
		super();
		this.bookId = bookId;
		this.subscriberName = subscriberName;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionId = subscriptionId;
	}
	public BookSubscribeResponse() {
		super();
		// TODO Auto-generated constructor stub
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
	public LocalDateTime getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(LocalDateTime subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

}

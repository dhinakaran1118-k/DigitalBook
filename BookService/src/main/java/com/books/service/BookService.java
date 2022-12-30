package com.books.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.books.dao.Book;
import com.books.dao.Reader;
import com.books.repository.BookRepository;
import com.books.repository.ReaderRepository;
import com.books.request.BookSubscribe;
import com.books.response.BookSubscribeResponse;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ReaderRepository readerRepository;
	
	public boolean existsByTitle(String title) {
		return bookRepository.existsByTitle(title);
	}
	
	public long createBook(Book book){
		 bookRepository.save(book);
		 return book.getBookId();
	}
	
	public List<Book> searchBooks
	(String title,String authorName,String category,String publisher) {
		List<Book> books = new ArrayList<>();
	    if(!ObjectUtils.isEmpty(title) && !ObjectUtils.isEmpty(authorName)
	     && !ObjectUtils.isEmpty(category)
	     &&  !ObjectUtils.isEmpty(publisher))
		books = bookRepository.findBooksByCategoryOrTitleOrAuthor(title,authorName,category,publisher);
		//books=bookRepository.findAll();
		return books;
	}
	
	public Book updateBook(Book book, long bookId) throws Exception{
		if(bookRepository.existsById(bookId)) {
			book.setBookId(bookId);
			return bookRepository.save(book);
		} else {
			throw new Exception("Can not update Book with id: "+bookId);
		}
	}
	
	public Optional<Book> getBook(long bookId) {
		return bookRepository.findById(bookId);
	}
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public BookSubscribeResponse subscribBook(BookSubscribe bookSubscribe,int readerId) {
			
		List<Reader> readers=readerRepository.findAll();
		List<Reader> reader=readers.stream()
		.filter(s->s.getBookId()==bookSubscribe.getBookId() && s.getReaderId()==readerId && 
		s.getAuthorId()==bookSubscribe.getAuthorId())
		.collect(Collectors.toList());
		if(!reader.isEmpty()) {
			return null;
		}
		
		Reader newReader1=new Reader(bookSubscribe.getAuthorId(),readerId,bookSubscribe.getBookId(),LocalDateTime.now(),
				bookSubscribe.getSubscriberName(),LocalDateTime.now());
		
		
		Reader savedReader=readerRepository.save(newReader1);
		
		BookSubscribeResponse response=new BookSubscribeResponse();
		response.setSubscriberName(savedReader.getSubscriberName());
		response.setBookId(savedReader.getBookId());
		response.setSubscriptionId(savedReader.getId());
		response.setSubscriptionDate(LocalDateTime.now());
		
		return response;
	}
	
    public Boolean cancelSubscribeBook(long readerId, long subscriptionId) {		
		Optional<Reader> reader=readerRepository.findById(subscriptionId);			
		if(reader.isPresent()) {
			int duration=reader.get().getSubcriptionDate().getHour();
			if(duration>24) {
				return false;
			}			
			if(reader.get().getId()==subscriptionId) {
				readerRepository.deleteById(reader.get().getId());
				return true;
			}
		}		
		return false;
		}
    
    public List<Reader> getSubscribedDetails(String subscriberName) {
    	System.out.println("reader "+subscriberName);
		List<Reader> list = readerRepository.findAllByReaderName(subscriberName);
		//logger.info("books of author: {}",books);
		System.out.println("got it");
		return list;
	}
    
    public List<Book> getAllAuthorBooks(String authorName) {
    	System.out.println("authorid"+authorName);
		List<Book> books = bookRepository.findAllByAuthorName(authorName);
		//logger.info("books of author: {}",books);
		System.out.println("got it");
		return books;
	}

}

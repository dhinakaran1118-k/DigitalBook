package com.books.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.dao.Book;
import com.books.dao.Reader;
import com.books.request.BookSubscribe;
import com.books.response.BookSubscribeResponse;
import com.books.response.MessageResponse;
import com.books.service.BookService;

@RestController
@RequestMapping("api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;	
	
	/*@PostMapping("/author/createbook")
	public Book createBook(@RequestBody Book book){
		return bookService.createBook(book);
	}*/
	
	@PostMapping("/author/createbook")
	public ResponseEntity<?> createBook(@RequestBody Book book){
		bookService.createBook(book);
		return ResponseEntity.ok(book.getBookId());
	}
	
	@GetMapping("/searchbooks")
	public List<Book> searchBooks
	(
	 @Nullable @RequestParam("title") String title,	
	 @Nullable @RequestParam("authorName") String authorName,
	 //@Nullable @RequestParam("price") int price, 
	 @Nullable @RequestParam("category") String category,
	 @Nullable @RequestParam("publisher") String publisher )
	{
		System.out.println(category+" "+title);
		List<Book> books = bookService.searchBooks(title,authorName,category,publisher);
		return books;
	}
	
	@PutMapping("/author/updatebook/{bookid}")
	public Book updateBook(@RequestBody Book book, @PathVariable long bookid) 
			throws Exception{
		return bookService.updateBook(book, bookid);
	}
	
	@GetMapping("/user/{user_id}/getbook/{bookid}")
	public Optional<Book> getBook(@PathVariable long bookId) {
		return bookService.getBook(bookId);
	}
	
	@GetMapping("/reader/getallbooks")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	
	 @PostMapping("/{reader-id}/subscribe")
	 public ResponseEntity<?> subscribBook( @PathVariable("reader-id") int readerId, @RequestBody BookSubscribe bookSubscribeReq){
		System.out.println("Book Subscribe Started "+LocalDateTime.now());
		//log.info("Book Subscribe Started "+LocalDateTime.now());
		BookSubscribeResponse subscribebookResp=bookService.subscribBook(bookSubscribeReq,readerId);
		
		if(subscribebookResp==null) {
			System.out.println("Book Subscribe fail "+LocalDateTime.now());
			//log.info("Book Subscribe fail "+LocalDateTime.now());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("Book Subscribe successfull "+LocalDateTime.now());
		//log.info("Book Subscribe successfull "+LocalDateTime.now());
		return new ResponseEntity<>(subscribebookResp,HttpStatus.CREATED); 
	}
	 
	
	@PostMapping("/readers/{reader-id}/books/{subscription-id}/unsubscribe")
	public ResponseEntity<?> cancelSubscribeBook(@PathVariable("reader-id") long readerId
	,@PathVariable("subscription-id") long subscriptionId){
		
		boolean status=bookService.cancelSubscribeBook(readerId, subscriptionId);
		
		if(status) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/reader/{reader}/getsubscribeddetails")
	public List<Reader> getSubscribedDetails(@PathVariable("reader") String subscriberName) {
		List<Reader> List = new ArrayList<>();
		if(Objects.isNull(subscriberName))
			return List;
		//logger.info("retrieving books of author: {}", authorId);
		System.out.println("Get books of reader");
		List = bookService.getSubscribedDetails(subscriberName);
		return List;
		//return bookService.getSubscribedDetails();
	}
	
	@GetMapping("/author/{author}/getAuthorBooks")
	public List<Book> getAllAuthorBooks(@PathVariable("author") String authorName) {
		
		List<Book> booksList = new ArrayList<>();
		if(Objects.isNull(authorName))
			return booksList;
		//logger.info("retrieving books of author: {}", authorId);
		System.out.println("Get books of author");
		booksList = bookService.getAllAuthorBooks(authorName);
		return booksList;
	}
	 
	 
}

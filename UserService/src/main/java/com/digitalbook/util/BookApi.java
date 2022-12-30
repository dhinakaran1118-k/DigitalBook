package com.digitalbook.util;

import org.springframework.beans.factory.annotation.Value;

public class BookApi {
	
	@Value("${book.port}")
	static String bookServicePort ="8092";
	public static final String CREATE_BOOK = "http://localhost:"+bookServicePort+"/api/book/author/createbook";
    //http://localhost:8092/api/book/author/1/createbook
}

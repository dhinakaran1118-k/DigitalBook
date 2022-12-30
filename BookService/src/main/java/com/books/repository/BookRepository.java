package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.books.dao.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    //@Query(value="select * from TBL_BOOK where title=:title")
	boolean existsByTitle(@Param("title") String title);
	
	@Query("select books from Book books where books.title LIKE %:title% and books.authorName LIKE %:authorName%  and books.category LIKE %:category% and books.publisher LIKE %:publisher%")
	List<Book> findBooksByCategoryOrTitleOrAuthor(String title,String authorName,String category,String publisher);
	
	@Query("select books from Book books where books.authorName LIKE %:authorName%")
	List<Book> findAllByAuthorName(String authorName);
}

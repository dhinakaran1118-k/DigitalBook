package com.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.books.dao.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

	@Query("select readers from Reader readers where readers.subscriberName LIKE %:subscriberName%")
	List<Reader> findAllByReaderName(String subscriberName);
}

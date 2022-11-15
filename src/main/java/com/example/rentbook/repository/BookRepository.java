package com.example.rentbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rentbook.pojo.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	public Page<Book> findAll(Pageable pageable);

	public Page<Book> findByBookName(String bookName, Pageable pageable);

	public Page<Book> findByAuthorName(String authorName, Pageable pageable);

	public Book findBySlNo(Integer slNo);
}

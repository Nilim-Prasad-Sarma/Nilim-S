package com.example.rentbook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rentbook.pojo.Book;

public interface BookService {

	public Page<Book> getAllBookDetails(Pageable pageable);

	public Page<Book> searchBooksByBookName(String bookName, Pageable pageable);

	public Page<Book> findByAuthorName(String authorName, Pageable pageable);

	public Book findBySlNo(Integer slNo);

}

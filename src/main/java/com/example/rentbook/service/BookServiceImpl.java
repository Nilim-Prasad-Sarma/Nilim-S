package com.example.rentbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.rentbook.pojo.Book;
import com.example.rentbook.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> getAllBookDetails(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Page<Book> searchBooksByBookName(String bookName, Pageable pageable) {
		return bookRepository.findByBookName(bookName, pageable);
	}

	@Override
	public Page<Book> findByAuthorName(String authorName, Pageable pageable) {
		return bookRepository.findByAuthorName(authorName, pageable);
	}

	@Override
	public Book findBySlNo(Integer slNo) {
		return bookRepository.findBySlNo(slNo);
	}

}

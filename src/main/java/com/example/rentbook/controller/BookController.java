package com.example.rentbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.rentbook.dto.BookDTO;
import com.example.rentbook.pojo.Book;
import com.example.rentbook.service.BookService;

@Controller
@RequestMapping("/rent-book")
public class BookController {

	private String bookName = "";

	@Autowired
	private BookService bookService;

	@GetMapping("/getallbooks/{page}")
	public String getAllBookDetails(@PathVariable Integer page, ModelMap map) {

		Pageable pageable = PageRequest.of(page, 5);

		Page<Book> allBookDetails = bookService.getAllBookDetails(pageable);
		if (!allBookDetails.isEmpty()) {
			map.addAttribute("listOfBooks", allBookDetails);
			map.addAttribute("currentPage", page);
			map.addAttribute("totalPages", allBookDetails.getTotalPages());
			return "bookLibrary";
		} else {
			return "errorPage";
		}
	}

	@PostMapping("/searchBooks")
	public String searchBooks(BookDTO bookDTO, ModelMap map) {

		bookName = bookDTO.getBookName();

		Pageable pageable = PageRequest.of(bookDTO.getPageNumber(), 5);

		Page<Book> searchBooksByBookName = bookService.searchBooksByBookName(bookDTO.getBookName(), pageable);
		if (!searchBooksByBookName.isEmpty()) {
			map.addAttribute("showBooks", searchBooksByBookName);
			map.addAttribute("currentPage", bookDTO.getPageNumber());
			map.addAttribute("totalPages", searchBooksByBookName.getTotalPages());
			return "searchedbooks";
		} else {
			Page<Book> authorName = bookService.findByAuthorName(bookDTO.getBookName(), pageable);
			if (!authorName.isEmpty()) {
				map.addAttribute("currentPage", bookDTO.getPageNumber());
				map.addAttribute("totalPages", authorName.getTotalPages());
				map.addAttribute("showBooks", authorName);
				return "searchedbooks";
			}
		}
		return null;
	}

	@GetMapping("/searchBooks/{pagenumber}")
	public String searchBookByPageNumber(@PathVariable int pagenumber, ModelMap map) {
		return searchBooks(new BookDTO(bookName, pagenumber), map);
	}

	@GetMapping("/getBookDetails/{slNo}")
	public String bookDetails(@PathVariable Integer slNo, ModelMap map) {
		Book book = bookService.findBySlNo(slNo);
		if (book != null) {
			map.addAttribute("bookName", book.getBookName());
			map.addAttribute("bookPrice", book.getBookPrice());
			return "bookDetails";
		}
		return null;
	}

	@PostMapping("/rentBooks/{noOfHours}/{bookPrice}")
	public String rentedBooks(@PathVariable Integer noOfHours, @PathVariable Integer bookPrice, ModelMap map) {
		Integer totalAmount = noOfHours * bookPrice;
		map.addAttribute("totalPrice", totalAmount);
		return "bookDetails";
	}
}

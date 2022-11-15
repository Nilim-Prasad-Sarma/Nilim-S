package com.example.rentbook.pojo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_info")
public class Book {
	@Id
	@Column(name = "sl_no")
	private Integer slNo;
	@Column(name = "book_name")
	private String bookName;
	@Column(name = "author_name")
	private String authorName;
	@Column(name = "published_by")
	private String publishedBy;
	@Column(name = "published_date")
	private LocalDate publishedDate;
	@Column(length = 1000, name = "book_description")
	private String bookDescription;
	@Column(name = "book_price")
	private Integer bookPrice;
}

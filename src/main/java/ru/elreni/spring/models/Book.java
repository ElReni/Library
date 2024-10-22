package ru.elreni.spring.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {	
	
	int bookId;
	
	@NotEmpty(message = "Title should be not empty")
	@Size(min = 1, max = 100, message="Title length should be in range 1-100 symbols")
	String title;
	
	@NotEmpty(message ="Author name should be not empty")
	String author;
	
	@Min(value = 1900, message = "Year of book publication should be greater or equal 1900")
	int year;
	
	public Book(int bookId, String title, String author, int year) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}

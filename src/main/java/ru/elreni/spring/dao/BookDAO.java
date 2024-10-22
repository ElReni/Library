package ru.elreni.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.elreni.spring.models.Book;
import ru.elreni.spring.models.Person;

@Component
public class BookDAO {
	JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));	
	}
	
	public void add(Book book) {
		jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());	
	}

	public Book get(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[] {id}, new int[] {java.sql.Types.INTEGER}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);	
	}

	public void update(int id, Book book) {
		jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE book_id=?", book.getTitle(), book.getAuthor(), book.getYear(), id);		
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);		
	}

	public void assign(int id, Person person) {
		jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPersonId(), id);		
	}
	
	public void release(int id) {
		jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", id);		
	}

	public List<Book> getBooksByPersonId(int id) {
		return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[] {id}, new int[] {java.sql.Types.INTEGER}, new BeanPropertyRowMapper<>(Book.class));
	}
}

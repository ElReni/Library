package ru.elreni.spring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import ru.elreni.spring.models.Person;

@Component
public class PersonDAO {
	
	JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Person> index () {
		return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));		
	}

	public void add(Person person) {
		jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth, email) VALUES (?, ?, ?)", person.getFullName(), person.getYearOfBirth(), person.getEmail());		
	}

	public Person get(int id) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[] {id}, new int[] {java.sql.Types.INTEGER}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);			
	}
	
	public Optional<Person> get(String email) {
		return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[] {email}, new int[] {java.sql.Types.VARCHAR}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();		
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);		
	}

	public void update(int id, Person person) {
		jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=?, email=? WHERE person_id=?", person.getFullName(), person.getYearOfBirth(), person.getEmail(), id);			
	}

	public Optional<Person> getBookOwner(int id) {
		return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id=Person.person_id WHERE book_id=?", new Object[] {id}, new int[] {java.sql.Types.INTEGER}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();	
	}
	

}

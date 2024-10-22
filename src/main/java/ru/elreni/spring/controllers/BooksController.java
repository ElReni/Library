package ru.elreni.spring.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.elreni.spring.dao.BookDAO;
import ru.elreni.spring.dao.PersonDAO;
import ru.elreni.spring.models.Book;
import ru.elreni.spring.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {	
	
	private final BookDAO bookDAO;
	private final PersonDAO personDAO;	
	
	@Autowired
	public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
		super();
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("books", bookDAO.index());
		return "/books/index";
	}
	
	@GetMapping("/new")
	public String toAddBookPage(Model model) {
		model.addAttribute("book", new Book());
		return "/books/new";
	}
	
	@PostMapping()
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/people/edit";	
		}
		
		bookDAO.add(book);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookDAO.get(id));
		
		Optional<Person> bookOwner = personDAO.getBookOwner(id);
		
		if (bookOwner.isPresent()) {
			model.addAttribute("owner", bookOwner.get());
		} else {
			model.addAttribute("people", personDAO.index());
		}			
		return "/books/show";
	}
	
	@GetMapping("/{id}/edit")
	public String toEditPage(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookDAO.get(id));
		return "/books/edit";
	}
	
	@PatchMapping("/{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "/people/edit";	
		}
		
		bookDAO.update(id, book);
		return "redirect:/books";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		bookDAO.delete(id);
		return("redirect:/books");
	}
	
	@PatchMapping("/{id}/assign")
	public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) { 
		bookDAO.assign(id, person);
		return "redirect:/books/" + id;		
	}
	
	@PatchMapping("/{id}/release")
	public String release(@PathVariable("id") int id) {
		bookDAO.release(id);
		return "redirect:/books/" + id;
	}
}

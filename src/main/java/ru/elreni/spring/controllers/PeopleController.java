package ru.elreni.spring.controllers;

import jakarta.validation.Valid;

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

import ru.elreni.spring.dao.BookDAO;
import ru.elreni.spring.dao.PersonDAO;
import ru.elreni.spring.models.Person;
import ru.elreni.spring.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {
	private PersonDAO personDAO;
	private BookDAO bookDAO;
	private PersonValidator personValidator;

	@Autowired
	public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
		super();
		this.personDAO = personDAO;
		this.bookDAO = bookDAO;
		this.personValidator = personValidator;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("people", personDAO.index());
		return "/people/index";
	}
	
	@GetMapping("/new")
	public String toAddPersonPage(Model model) {
		model.addAttribute("person", new Person());
		return "/people/new";
	}
	
	@PostMapping()
	public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		
		personValidator.validate(person, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/people/new";
		}
		
		personDAO.add(person);
		
		return "redirect:/people";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {		
		model.addAttribute("books" ,bookDAO.getBooksByPersonId(id));
		model.addAttribute("person", personDAO.get(id));
		return "/people/show";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		personDAO.delete(id);
		return "redirect:/people";
	}
	
	@GetMapping("/{id}/edit")
	public String toEditPage(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", personDAO.get(id));
		return "/people/edit";		
	}
	
	@PatchMapping("/{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		
		person.setPersonId(id);		
		personValidator.validate(person, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "/people/edit";	
		}
		
		personDAO.update(id, person);
		
		return "redirect:/people";		
	}
	
	
	
}

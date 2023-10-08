package com.example.VictorBookStores.web;

import com.example.VictorBookStores.model.Book;
import com.example.VictorBookStores.model.BookRepository;
import com.example.VictorBookStores.model.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;

	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/booklist", method= RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/books", method= RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return(List<Book>) brepository.findAll();
	}
	
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBooksRest(@PathVariable("id") Long bookid){
		return brepository.findById(bookid);
	}
	
	@RequestMapping(value="/addbook")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
		brepository.save(book);
		return "redirect:booklist";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	@RequestMapping(value="/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editbook";
	}

}

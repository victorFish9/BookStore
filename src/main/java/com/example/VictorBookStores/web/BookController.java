package com.example.VictorBookStores.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.VictorBookStores.model.Book;
import com.example.VictorBookStores.model.BookRepository;
import com.example.VictorBookStores.model.CategoryRepository;

import org.springframework.ui.Model;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/index", method= RequestMethod.GET)
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
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
		brepository.save(book);
		return "redirect:index";
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../index";
	}
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", brepository.findById(bookId));
		model.addAttribute("categorys", crepository.findAll());
		return "editbook";
	}

}

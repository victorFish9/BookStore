package com.example.VictorBookStores;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.VictorBookStores.model.Book;
import com.example.VictorBookStores.model.BookRepository;
import com.example.VictorBookStores.model.CategoryRepository;
import com.example.VictorBookStores.model.Category;

@SpringBootApplication
public class VictorBookStoresApplication {
	private static final Logger log = LoggerFactory.getLogger(VictorBookStoresApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VictorBookStoresApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository srepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Romance"));
			
			srepository.save(new Book("Gatsby", "Michael Corny", 1991, "123abc", 20, crepository.findByName("Drama").get(0)));
			srepository.save(new Book("Barbie", "Barbara Ruth", 2003, "456def", 40, crepository.findByName("Fiction").get(0)));
			
			log.info("fetch all books");
			for (Book book : srepository.findAll()) {
				log.info(book.toString());
			}
		};
		
	}

}

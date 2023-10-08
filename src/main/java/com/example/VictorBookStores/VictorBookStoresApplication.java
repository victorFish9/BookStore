package com.example.VictorBookStores;

import com.example.VictorBookStores.model.*;
import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VictorBookStoresApplication {
	private static final Logger log = LoggerFactory.getLogger(VictorBookStoresApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VictorBookStoresApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository srepository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Romance"));
			
			srepository.save(new Book("Gatsby", "Michael Corny", 1991, "123abc", 20, crepository.findByName("Drama").get(0)));
			srepository.save(new Book("Barbie", "Barbara Ruth", 2003, "456def", 40, crepository.findByName("Fiction").get(0)));

			//Create user
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : srepository.findAll()) {
				log.info(book.toString());
			}
		};
		
	}

}

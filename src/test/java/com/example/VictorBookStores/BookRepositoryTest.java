package com.example.VictorBookStores;

import com.example.VictorBookStores.model.Book;
import com.example.VictorBookStores.model.BookRepository;
import com.example.VictorBookStores.model.Category;
import com.example.VictorBookStores.model.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = VictorBookStoresApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository brepository;

    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByTitleShouldReturnIsbn(){
        List<Book> books = brepository.findByTitle("Barbie");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Barbara Ruth");
    }

    @Test
    public void createNewBook(){
        Category category = new Category("Scifi");
        crepository.save(category);
        Book book = new Book("Story of Mickey Mouse", "Victor", 2003, "SOMM854", 25, category);
        brepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook(){
        List<Book> books = brepository.findByTitle("Barbie");
        Book book = books.get(0);
        brepository.delete(book);
        List<Book> newBook = brepository.findByTitle("Barbie");
        assertThat(newBook).hasSize(0);
    }


}

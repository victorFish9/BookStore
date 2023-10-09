package com.example.VictorBookStores;

import com.example.VictorBookStores.web.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VictorBookStoresApplicationTests {

	@Autowired
	private BookController controller;
	@Test
	public void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}

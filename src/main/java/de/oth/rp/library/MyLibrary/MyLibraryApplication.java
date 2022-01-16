package de.oth.rp.library.MyLibrary;

import de.oth.rp.library.MyLibrary.books.Scrape;
import de.oth.rp.library.MyLibrary.entity.Book;
import de.oth.rp.library.MyLibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyLibraryApplication implements ApplicationRunner{

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(MyLibraryApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Scrape scrape = new Scrape();
//		scrape.setup();
		Book book = new Book();
		book.setIsbn("1234");
		bookService.addBook(book);
	}
}

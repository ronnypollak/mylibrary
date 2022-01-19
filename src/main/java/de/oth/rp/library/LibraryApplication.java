package de.oth.rp.library;

import de.oth.rp.library.books.Scrape;
import de.oth.rp.library.service.AuthorService;
import de.oth.rp.library.service.BookService;
import de.oth.rp.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication implements ApplicationRunner{

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args){
		Scrape scrape = new Scrape(bookService, authorService, userService);
		scrape.setup();
//		Book book = new Book();
//		book.setIsbn("1234");
//		bookService.addBook(book);
	}
}

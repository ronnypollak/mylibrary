package de.oth.rp.library.web;

import de.oth.rp.library.entity.Book;
import de.oth.rp.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books")
    public String getBooksByName(Model model, @RequestParam("book_name") String name){
        System.out.println(name);
        return "books";
    }

    @GetMapping(value = "/book/{isbn}")
    public String getBookByIsbn(Model model, @PathVariable("isbn") String isbn){
        System.out.println(isbn);
        Book book = bookService.findBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "bookdetails";
    }
}

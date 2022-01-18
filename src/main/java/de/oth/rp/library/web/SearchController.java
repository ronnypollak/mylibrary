package de.oth.rp.library.web;

import de.oth.rp.library.entity.Book;
import de.oth.rp.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class SearchController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/search") // /search
    public String searchBooks(Model model) {
//        model.addAttribute("user", new User());
        return "search";
    }

    @PostMapping(value = "/search") // /search
    public String doSearchBooks(Model model, @RequestParam("book_name") String name) {
//        model.addAttribute("user", new User());
        System.out.println(name);
        List<Book> books;
        books = bookService.findBooksByName(name);
        model.addAttribute("books", books);
        return "books";
    }
}

package de.oth.rp.library.web;

import de.oth.rp.library.entity.Book;
import de.oth.rp.library.entity.User;
import de.oth.rp.library.service.BookService;
import de.oth.rp.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping(value = "/books")
    public String getBooksByName(Model model, Principal principal
                                 ){
        User user = userService.getUserByUsername(principal.getName());
        List<Book> books = user.getOwnedBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping(value = "/book/{isbn}")
    public String getBookByIsbn(Model model, Principal principal,
                                @PathVariable("isbn") String isbn){
        System.out.println(isbn);
        Book book = bookService.findBookByIsbn(isbn);
        model.addAttribute("book", book);
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);

        // hilfsmethode
//        book.createFile();
        //

        return "bookdetails";
    }

    @PostMapping(value = "/book/{isbn}")
    public String claimBookByIsbn(Model model, Principal principal,
                                  @PathVariable("isbn") String isbn){
        User user = userService.getUserByUsername(principal.getName());
        Book book = bookService.findBookByIsbn(isbn);
        userService.addBookToUser(user, book);
        model.addAttribute("book", book);
        model.addAttribute("user", user);
        return "bookdetails";
    }
}

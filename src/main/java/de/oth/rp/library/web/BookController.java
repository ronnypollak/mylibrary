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

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

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

    @PostMapping(value = "/book/{isbn}")
    public String claimBookByIsbn(Model model, @PathVariable("isbn") String isbn
            , Principal principal, @ModelAttribute("book") Book book){
        System.out.println(isbn);
        User user = userService.getUserByUsername(principal.getName());
        if(!user.getOwnedBooks().contains(book)){
            user.getOwnedBooks().add(book);
            userService.addUser(user);
        }
        model.addAttribute("book", book);
        return "bookdetails";
    }
}

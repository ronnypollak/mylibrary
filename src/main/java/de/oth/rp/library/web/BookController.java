package de.oth.rp.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @GetMapping(value = "/books")
    public String getBooksByName(Model model, @RequestParam("name") String name){

        return "books";
    }
}
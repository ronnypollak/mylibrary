package de.oth.rp.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SearchController {

    @GetMapping(value = "/search") // /search
    public String searchBooks(Model model) {
//        model.addAttribute("user", new User());
        return "search";
    }

    @PostMapping(value = "/search") // /search
    public String doSearchBooks(Model model) {
//        model.addAttribute("user", new User());
        return "search";
    }
}

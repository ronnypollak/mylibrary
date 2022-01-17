package de.oth.rp.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET) // /search
    public String searchBooks(Model model) {
//        model.addAttribute("user", new User());
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST) // /search
    public String doSearchBooks(Model model) {
//        model.addAttribute("user", new User());
        return "search";
    }
}

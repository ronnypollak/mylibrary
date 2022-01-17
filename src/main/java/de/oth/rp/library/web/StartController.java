package de.oth.rp.library.web;

import de.oth.rp.library.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StartController {

    @RequestMapping("/")
    public String setup(){
        return "start";
    }


    @GetMapping(value = "/login") // /login
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(value = "/login") // /login
    public String doLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


}

package de.oth.rp.library.web;

import de.oth.rp.library.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {

    @RequestMapping("/")
    public String setup(){
        return "start";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET) // /login
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST) // /login
    public String doLogin(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


}

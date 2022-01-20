package de.oth.rp.library.web;

import de.oth.rp.library.entity.User;
import de.oth.rp.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class StartController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String setup(){
        return "start";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) // /login
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST) // th:action="@{/login}"
    public String doLogin() {
        return "index";
    }



//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String doLogin(Model model, @ModelAttribute("username") String username
//                                    ,@ModelAttribute("password") String password) {
//
//        Optional<User> optionalUser = userService.findById(username);
//        if (optionalUser.isPresent()){
//            if(!(optionalUser.get().getPassword() == passwordEncoder.encode(password))){
//                return "search";
//            }else {
////                TODO: Add messages for failed login and register
//                System.out.println("wrong pw");
//                return "login";
//            }
//        }else {
//            System.out.println("user doesn't exist");
//            return "login";
//        }
//    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String doLogin() {
//        return "search";
//    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(Model model, @ModelAttribute("username") String username
            ,@ModelAttribute("password") String password) {
        if (userService.findById(username).isPresent()) return "register";
        else {
            User user = new User(username, password);
            userService.addUser(user);
            model.addAttribute("user", user);
            return "login";
        }
    }




}

package de.oth.rp.library.service;

import de.oth.rp.library.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

//    void addUser(User user);

    Optional<User> findById(String name);

    User getUserByUsername(String username);
    User registerUser(User newUser);

    void save(User user);
}

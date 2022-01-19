package de.oth.rp.library.service;

import de.oth.rp.library.entity.User;

import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> findById(String username);
}

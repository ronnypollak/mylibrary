package de.oth.rp.library.service.impl;

import de.oth.rp.library.entity.User;
import de.oth.rp.library.repository.UserRepo;
import de.oth.rp.library.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public Optional<User> findById(String username){
        return userRepo.findById(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findById(username).orElseThrow(
                () -> new ServiceException("User with email " + username + " not found")
        );
    }

    @Override
    public User registerUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("User with email " + username + " not found")
        );
    }
}

package de.oth.rp.library.service.impl;

import de.oth.rp.library.entity.Book;
import de.oth.rp.library.entity.User;
import de.oth.rp.library.repository.BookRepo;
import de.oth.rp.library.repository.UserRepo;
import de.oth.rp.library.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    public void addUser(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//    }

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

    public void save(User user){
        userRepo.save(user);
    }

    public User addBookToUser(User user, Book book){
        if(!user.getOwnedBooks().contains(book)){
            List<Book> books = user.getOwnedBooks();
            books.add(book);
            user.setOwnedBooks(books);
            List<User> users = book.getOwnedBy();
            users.add(user);
            book.setOwnedBy(users);
            book.setClaims(book.getClaims()+1);
            bookRepo.save(book);
            userRepo.save(user);
        }
        return user;
    }
}

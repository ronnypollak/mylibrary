package de.oth.rp.library.MyLibrary.service;


import de.oth.rp.library.MyLibrary.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
}

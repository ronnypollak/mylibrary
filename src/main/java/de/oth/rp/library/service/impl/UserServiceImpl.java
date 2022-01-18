package de.oth.rp.library.service.impl;

import de.oth.rp.library.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepo userRepo;
}

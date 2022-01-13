package de.oth.rp.library.service;

import de.oth.rp.library.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;
}

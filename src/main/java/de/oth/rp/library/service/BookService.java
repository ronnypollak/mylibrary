package de.oth.rp.library.service;

import de.oth.rp.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
}

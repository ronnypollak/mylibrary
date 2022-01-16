package de.oth.rp.library.MyLibrary.service.impl;


import de.oth.rp.library.MyLibrary.entity.Author;
import de.oth.rp.library.MyLibrary.repository.AuthorRepo;
import de.oth.rp.library.MyLibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public Author addAuthor(Author author){
        return authorRepo.save(author);
    }

    public List<Author> addAuthors(ArrayList<Author> authors){
        return (List<Author>) authorRepo.saveAll(authors);
    }

}

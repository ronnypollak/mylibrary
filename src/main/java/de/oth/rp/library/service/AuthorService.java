package de.oth.rp.library.service;

import de.oth.rp.library.entity.Author;

import java.util.ArrayList;
import java.util.List;

public interface AuthorService {

    public Author addAuthor(Author author);

    public List<Author> addAuthors(List<Author> authors);


}

package de.oth.rp.library.service;

import de.oth.rp.library.entity.Author;

import java.util.ArrayList;
import java.util.List;

public interface AuthorService {

    Author addAuthor(Author author);

    List<Author> addAuthors(List<Author> authors);


}

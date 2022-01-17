package de.oth.rp.library.service;



import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookService {

    public List<Book> addBooks(List<Book> newBooks);

    public Book addBook(Book book, Author author);

    public Book addBook(Book book);
}

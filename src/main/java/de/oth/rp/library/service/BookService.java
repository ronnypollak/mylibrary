package de.oth.rp.library.service;



import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> addBooks(List<Book> newBooks);

    Book addBook(Book book, Author author);

    Book addBook(Book book);

    List<Book> findBooksByName(String name);
}

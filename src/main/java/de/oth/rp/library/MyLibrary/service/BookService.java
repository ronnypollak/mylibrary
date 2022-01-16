package de.oth.rp.library.MyLibrary.service;



import de.oth.rp.library.MyLibrary.entity.Author;
import de.oth.rp.library.MyLibrary.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookService {

    public List<Book> addBooks(ArrayList<Book> newBooks);

    public Book addBook(Book book, Author author);

    public Book addBook(Book book);
}

package de.oth.rp.library.service.impl;


import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;
import de.oth.rp.library.repository.BookRepo;
import de.oth.rp.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    public List<Book> addBooks(List<Book> newBooks){
        return (List<Book>) bookRepo.saveAll(newBooks);
    }

    public Book addBook(Book book, Author author){
        ArrayList<Author> bookAuthors = (ArrayList<Author>) book.getAuthors();
        if (!bookAuthors.contains(author)){
            bookAuthors.add(author);
            book.setAuthors(bookAuthors);
        }
        ArrayList<Book> authorBooks = (ArrayList<Book>) author.getBooks();
        if(!authorBooks.contains(book)){
            authorBooks.add(book);
            author.setBooks(authorBooks);
        }
        return bookRepo.save(book);
    }

    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    public List<Book> findBooksByName(String name){
        return bookRepo.findBookByNameContaining(name);
    }

    public Book findBookByIsbn(String isbn){
        return bookRepo.findBookByIsbn(isbn);
    }
}

package de.oth.rp.library.repository;

import de.oth.rp.library.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepo extends PagingAndSortingRepository<Book, String> {

    List<Book> findBookByNameContaining(String name);

    Book findBookByIsbn(String isbn);



}

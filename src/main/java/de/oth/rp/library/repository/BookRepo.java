package de.oth.rp.library.repository;

import de.oth.rp.library.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepo extends PagingAndSortingRepository<Book, String> {


}

package de.oth.rp.library.MyLibrary.repository;

import de.oth.rp.library.MyLibrary.entity.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepo extends PagingAndSortingRepository<Book, String> {


}

package de.oth.rp.library.MyLibrary.repository;


import de.oth.rp.library.MyLibrary.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
}

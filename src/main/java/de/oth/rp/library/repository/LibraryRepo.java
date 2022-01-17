package de.oth.rp.library.repository;


import de.oth.rp.library.entity.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepo extends CrudRepository<Library, Long> {
}

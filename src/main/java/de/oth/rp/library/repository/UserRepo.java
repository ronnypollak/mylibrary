package de.oth.rp.library.repository;


import de.oth.rp.library.entity.Book;
import de.oth.rp.library.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, String> {

}

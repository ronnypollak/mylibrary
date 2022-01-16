package de.oth.rp.library.MyLibrary.repository;


import de.oth.rp.library.MyLibrary.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
}

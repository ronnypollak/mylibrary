package de.oth.rp.library.repository;


import de.oth.rp.library.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends CrudRepository<User, String> {


}

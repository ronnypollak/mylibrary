package de.oth.rp.library.entity;


import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends SingleIdEntity<Long> {

    @Id
    private long userId;
    private String username;
    @ElementCollection
    private List<String> ranks;
    private int bookCount;
    private int downloadCount;
    @OneToMany
    private List<Book> ownedBooks;

    @Override
    public Long getID() {
        return this.userId;
    }
}

package de.oth.rp.library.entity;

import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
public class Book extends SingleIdEntity<Long> {

    @Id
    private long bookId;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private float rating;
    private int claims;
    private File content;

    @Override
    public Long getID() {
        return this.bookId;
    }
}

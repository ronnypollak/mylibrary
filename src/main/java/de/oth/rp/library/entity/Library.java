package de.oth.rp.library.entity;


import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library extends SingleIdEntity<Long> {

    @Id @GeneratedValue
    private long libraryId;

    @Override
    public Long getID() {
        return this.libraryId;
    }
}

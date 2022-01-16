package de.oth.rp.library.MyLibrary.entity;


import de.oth.rp.library.MyLibrary.entity.util.SingleIdEntity;

import javax.persistence.*;

@Entity
public class Library extends SingleIdEntity<Long> {

    @Id
    private long libraryId;

    @Override
    public Long getID() {
        return this.libraryId;
    }
}

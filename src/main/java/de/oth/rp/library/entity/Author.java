package de.oth.rp.library.entity;

import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Author extends SingleIdEntity<Long>{

    @Id
    private long authorId;
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date bornDate;


    @Override
    public Long getID() {
        return this.authorId;
    }
}

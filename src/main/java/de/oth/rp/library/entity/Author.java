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
    private String name;
    //changed variables bc only name is available in the openlib api
//    private String lastName;
    @Temporal(TemporalType.DATE)
    //Changed variable name
    private Date birthDate;
    //added variable
    private int workCount;


    public Author(String name, Date birthDate, int workCount) {
        this.name = name;
        this.birthDate = birthDate;
        this.workCount = workCount;
    }

    public Author() {

    }

    @Override
    public Long getID() {
        return this.authorId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", workCount=" + workCount +
                '}';
    }
}

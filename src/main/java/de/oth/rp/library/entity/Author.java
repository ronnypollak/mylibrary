package de.oth.rp.library.entity;



import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Author extends SingleIdEntity<Long> {

    @Id @GeneratedValue
    private long authorId;
    private String name;
    //changed variables bc only name is available in the openlib api
//    private String lastName;
    @Temporal(TemporalType.DATE)
    //Changed variable name
    private Date birthDate;
    //added variable
    private int workCount;
    // TODO: Liste an büchern hinzufügen und beim erstellen das buch zur liste hinzufügen
    @ManyToMany
    private List<Book> books;


    public Author(String name, Date birthDate, int workCount) {
        this.name = name;
        this.birthDate = birthDate;
        this.workCount = workCount;
        this.books = new ArrayList<>();
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

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        if (!this.books.contains(book)) {
            this.books.add(book);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return authorId == author.authorId && workCount == author.workCount && Objects.equals(name, author.name) && Objects.equals(birthDate, author.birthDate) && Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authorId, name, birthDate, workCount, books);
    }
}

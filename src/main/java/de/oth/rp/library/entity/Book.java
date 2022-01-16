package de.oth.rp.library.entity;

import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private String description;
//    TODO: Make enum
    private String language;
//    TODO: @ElementCollection untersuchen oder evtl eigene Klasse machen
    @ElementCollection
    private ArrayList<String> categories;
    @ManyToMany
    private ArrayList<Author> authors;
    //added
    private int pageCount;
    private String isbn;

    public Book() {
    }

    public Book(String isbn, String title, int pageCount, String publishedDate, String description, String language, List<String> categories, ArrayList<Author> authors) throws ParseException {
        this.isbn = isbn;
        this.name = title;
        this.description = description;
        this.pageCount = pageCount;
        this.language = language;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        this.releaseDate = formatter.parse(publishedDate);

        this.categories = new ArrayList(categories);
        this.authors = authors;
    }


    @Override
    public Long getID() {
        return this.bookId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", claims=" + claims +
                ", content=" + content +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", categories=" + categories +
                ", authors=" + authors +
                ", pageCount=" + pageCount +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

package de.oth.rp.library.entity;



import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Book extends SingleIdEntity<String> {

    @Id
    private String isbn;
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
    private List<String> categories;
    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
    //added
    private int pageCount;


    public Book() {
    }

    public Book(String isbn, String title, int pageCount, String publishedDate, String description, String language, List<String> categories, List<Author> authors) throws ParseException {
        this.isbn = isbn;
        this.name = title;
        this.description = description;
        this.pageCount = pageCount;
        this.language = language;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        this.releaseDate = formatter.parse(publishedDate);

        this.categories = categories;
        this.authors = authors;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("isbn='").append(isbn).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", rating=").append(rating);
        sb.append(", claims=").append(claims);
        sb.append(", content=").append(content);
        sb.append(", description='").append(description).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", categories=").append(categories);
        sb.append(", authors=").append(authors);
        sb.append(", pageCount=").append(pageCount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getID() {
        return isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getClaims() {
        return claims;
    }

    public void setClaims(int claims) {
        this.claims = claims;
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Float.compare(book.rating, rating) == 0 && claims == book.claims && pageCount == book.pageCount && Objects.equals(isbn, book.isbn) && Objects.equals(name, book.name) && Objects.equals(releaseDate, book.releaseDate) && Objects.equals(content, book.content) && Objects.equals(description, book.description) && Objects.equals(language, book.language) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn, name, releaseDate, rating, claims, content, description, language, categories, pageCount);
    }
}

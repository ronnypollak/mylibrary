package de.oth.rp.library.entity;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import de.oth.rp.library.entity.util.SingleIdEntity;

import javax.persistence.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Entity
public class Book extends SingleIdEntity<String> {

    @Id
    private String isbn;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private float rating;
    private int claims;
    @Column(columnDefinition="LONGTEXT")
    private String description;
//    TODO: Make enum
    private String language;
//    TODO: @ElementCollection untersuchen oder evtl eigene Klasse machen
    @ElementCollection
    private List<String> categories;
    @ManyToMany(mappedBy = "books", cascade = CascadeType.REMOVE)
    private List<Author> authors;
    private int pageCount;
    private String thumbnail;
    private String maturityRating;
    @ManyToMany(mappedBy = "ownedBooks")
    private List<User> ownedBy;


    public Book() {
    }

    public Book(String isbn, String title, int pageCount, String publishedDate, String description, String language,
                List<String> categories, List<Author> authors, String thumbnail, String maturityRating) throws ParseException {
        this.isbn = isbn;
        this.name = title;
        this.description = description;
        this.pageCount = pageCount;
        this.language = language;
        this.thumbnail = thumbnail;
        this.maturityRating = maturityRating;

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

//    public String getContent() {
//        String path = "src/main/resources/static/files/" + isbn + ".pdf";
//        File f = new File(path);
//            if (!f.exists()) {
//            Document document = new Document();
//
//            try {
//
//                PdfWriter.getInstance(document, new FileOutputStream(path));
//            } catch (DocumentException | FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            document.open();
////            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
////            Chunk chunk = new Chunk("test", font);
//
//            try {
//                document.add(new Paragraph(description));
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            }
//            document.close();
//        }
//        return isbn;
//    }

    public void createFile() {
        String path = "src/main/resources/static/files/" + isbn + ".pdf";
        File f = new File(path);
        File directory = new File("src/main/resources/static/files/");
        if (! directory.exists()){
            directory.mkdir();
        }

        if (!f.exists()) {
            Document document = new Document();

            try {

                PdfWriter.getInstance(document, new FileOutputStream(path));
            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }

            document.open();

            try {
                document.add(new Paragraph(description));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();
        }
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public List<User> getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(List<User> ownedBy) {
        this.ownedBy = ownedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Float.compare(book.rating, rating) == 0 && claims == book.claims && pageCount == book.pageCount && Objects.equals(isbn, book.isbn) && Objects.equals(name, book.name) && Objects.equals(releaseDate, book.releaseDate) && Objects.equals(description, book.description) && Objects.equals(language, book.language) && Objects.equals(categories, book.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn, name, releaseDate, rating, claims, description, language, categories, pageCount);
    }
}

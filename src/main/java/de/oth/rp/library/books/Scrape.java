package de.oth.rp.library.books;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;
import de.oth.rp.library.openlib.OLAuthor;
import de.oth.rp.library.openlib.OpenLibrary;
import de.oth.rp.library.service.AuthorService;
import de.oth.rp.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//TODO: changes Date to gregorian Calendar? to only get year
//TODO: avoid duplicates in saving

@Component
public class Scrape {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    public Scrape(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void setup(){

        try {
            ObjectMapper mapper = new ObjectMapper();

//            System.out.println(System.getProperty("user.dir"));

            FileInputStream fileInputStream = new FileInputStream("src/main/resources/static/isbn_numbers/isbn.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

            String isbn;
            String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
            ArrayList<Item> items = new ArrayList<>();
            int amountBooks = 20;
            int i = 0;
            while (((isbn = br.readLine()) != null) && i < amountBooks) {
                Thread.sleep(2000);
                GoogleLib googleLib = mapper.readValue(new URL(url + isbn), GoogleLib.class);
                if (googleLib.getTotalItems() != 0) {
                    googleLib.getItems().get(0).setIsbn((isbn));
                    items.add(googleLib.getItems().get(0));
                    System.out.println(isbn + " added");
                    i++;
                }
            }

            fileInputStream.close();
            System.out.println(items);
            System.out.println(items.size());
            ArrayList<Book> books = new ArrayList<>();
//            ArrayList<Author> authors = new ArrayList<>();

            items.forEach(item -> {
                        try {
                            mapBook(item, books);
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
            System.out.println(books);
//            books.get(0).getAuthors().get(0).addBook(books.get(0));
            bookService.addBooks(books);

//            authorService.addAuthor(books.get(0).getAuthors().get(0));
//            bookService.addBook(books.get(0));
            // Always add book first before auhtor?
            books.forEach(book ->
                authorService.addAuthors(book.getAuthors())
            );

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //map googlebook to book and get authors
    private static void mapBook(Item item, ArrayList<Book> books) throws IOException, ParseException {
        VolumeInfo volumeInfo = item.getVolumeInfo();
        ArrayList<Author> authors = new ArrayList<>();

        getAuthors(volumeInfo.getAuthors(), authors);
        Book book = new Book(item.getIsbn(), volumeInfo.getTitle(), volumeInfo.getPageCount(), volumeInfo.getPublishedDate(), volumeInfo.getDescription(), volumeInfo.getLanguage(),
                            volumeInfo.getCategories(), authors, volumeInfo.getImageLinks().getThumbnail());

        books.add(book);

        //add book to authors list of books
        authors.forEach( author -> {
            ArrayList<Book> authorBooks = (ArrayList<Book>) author.getBooks();
            if(!authorBooks.contains(book)){
                authorBooks.add(book);
                author.setBooks(authorBooks);
            }
        });
    }

    // get authors for 1 specific book
    private static ArrayList<Author> getAuthors(List<String> authorList, ArrayList<Author> authors) throws IOException {
        // openLib api
        String url = "https://openlibrary.org/search/authors.json?q=";
        ArrayList<OLAuthor> olAuthors = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        for (String s : authorList) {
            String[] names = s.split("\\s+");
            OpenLibrary openLibrary = mapper.readValue(new URL(url + names[0] + "+" + names[1]), OpenLibrary.class);
            olAuthors.add(openLibrary.getOLAuthors().get(0));
        }

        olAuthors.forEach(olAuthor -> {
            String dateString = olAuthor.getBirthDate();
            Date date = null;
            if ((dateString != null)) {
                dateString = dateString.substring(dateString.length() - 4);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.ENGLISH);
                try {
                    date = formatter.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            Author author = new Author(olAuthor.getName(), date, olAuthor.getWorkCount());
            authors.add(author);
        });

        return authors;
    }
}

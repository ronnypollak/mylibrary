package de.oth.rp.library.books;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.oth.rp.library.entity.AccountType;
import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;
import de.oth.rp.library.entity.User;
import de.oth.rp.library.openlib.OLAuthor;
import de.oth.rp.library.openlib.OpenLibrary;
import de.oth.rp.library.service.AuthorService;
import de.oth.rp.library.service.BookService;
import de.oth.rp.library.service.UserService;
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
    @Autowired
    private UserService userService;

    public Scrape(BookService bookService, AuthorService authorService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.userService = userService;
    }

    public Scrape() {
    }

    public void setup(){
        if (!bookService.findBooks().iterator().hasNext()){
            try {
                userService.addUser(new User("admin", "admin", AccountType.ADMIN));
                userService.addUser(new User("standard", "standard", AccountType.STANDARD));

                ObjectMapper mapper = new ObjectMapper();

                FileInputStream fileInputStream = new FileInputStream("src/main/resources/static/isbn_numbers/isbn.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

                String isbn;
                String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
                ArrayList<Item> items = new ArrayList<>();
                int amountBooks = 20;
                int i = 0;
                while (((isbn = br.readLine()) != null) && i < amountBooks) {
                    Thread.sleep(1500);
                    GoogleLib googleLib = mapper.readValue(new URL(url + isbn), GoogleLib.class);
                    if (googleLib.getTotalItems() != 0) {
                        googleLib.getItems().get(0).setIsbn((isbn));
                        items.add(googleLib.getItems().get(0));
                        System.out.println(isbn + " added to list");
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
                            } catch (IOException | ParseException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                );
                System.out.println(books);
                bookService.addBooks(books);

                books.forEach(book ->
                        authorService.addAuthors(book.getAuthors())
                );

            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.out.println("Repo already setup");
        }


        System.out.println("setup done");
    }

    //map googlebook to book and get authors
    private static void mapBook(Item item, ArrayList<Book> books) throws IOException, ParseException, InterruptedException {
        VolumeInfo volumeInfo = item.getVolumeInfo();
        ArrayList<Author> authors = new ArrayList<>();

        getAuthors(volumeInfo.getAuthors(), authors);
        Book book = new Book(item.getIsbn(), volumeInfo.getTitle(), volumeInfo.getPageCount(), volumeInfo.getPublishedDate(),
                            volumeInfo.getDescription(), volumeInfo.getLanguage(),
                            volumeInfo.getCategories(), authors, volumeInfo.getImageLinks().getThumbnail(),
                            volumeInfo.getMaturityRating());

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
    private static ArrayList<Author> getAuthors(List<String> authorList, ArrayList<Author> authors) throws IOException, InterruptedException {
        // openLib api
        String url = "https://openlibrary.org/search/authors.json?q=";
        ArrayList<OLAuthor> olAuthors = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        for (String s : authorList) {
            String[] names = s.split("\\s+");
            Thread.sleep(1000);
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

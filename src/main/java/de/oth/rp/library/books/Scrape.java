package de.oth.rp.library.books;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.oth.rp.library.entity.Author;
import de.oth.rp.library.entity.Book;
import de.oth.rp.library.openlib.OLAuthor;
import de.oth.rp.library.openlib.OpenLibrary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//TODO: changes Date to gregorian Calendar? to only get year

public class Scrape {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        /*GoogleLib googleLib = mapper.readValue(new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:0735619670"), GoogleLib.class);

        System.out.println(googleLib.getItems().get(0).getVolumeInfo().getTitle());
        System.out.println(googleLib.getItems());*/

        FileInputStream fileInputStream = new FileInputStream("C:\\Uni\\Softwareentwicklung\\Projekt\\library\\src\\main\\resources\\static\\isbn_numbers\\isbn_small.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

        String isbn;
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
        ArrayList<Item> items = new ArrayList<>();

        while ((isbn = br.readLine()) != null)   {
            GoogleLib googleLib = mapper.readValue(new URL(url + isbn), GoogleLib.class);
            if (googleLib.getTotalItems() != 0) {
                googleLib.getItems().get(0).setIsbn((isbn));
                items.add(googleLib.getItems().get(0));
                System.out.println(isbn + " added");
            }
        }

        fileInputStream.close();
        System.out.println(items);
        System.out.println(items.size());
        ArrayList<Book> books = new ArrayList<>();
        Book book;
        items.forEach((item) -> {
                    try {
                        mapBook(item, books);
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
                );
        System.out.println(books);

    }

    private static void mapBook(Item item, ArrayList<Book> books) throws IOException, ParseException {
        VolumeInfo volumeInfo = item.getVolumeInfo();
        //TODO: authoren scrapen von openLIbrary
        ArrayList<Author> authors = new ArrayList<>();
        getAuthors(volumeInfo.getAuthors(), authors);
        Book book = new Book(item.getIsbn(), volumeInfo.getTitle(), volumeInfo.getPageCount(), volumeInfo.getPublishedDate(), volumeInfo.getDescription(), volumeInfo.getLanguage(),
                            volumeInfo.getCategories(), authors);
        books.add(book);
    }

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
            if (!(dateString == null)) {
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

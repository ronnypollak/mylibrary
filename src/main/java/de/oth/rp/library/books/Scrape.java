package de.oth.rp.library.books;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Scrape {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        GoogleLib googleLib = mapper.readValue(new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:0735619670"), GoogleLib.class);

        System.out.println(googleLib.getItems().get(0).getVolumeInfo().getTitle());
    }
}

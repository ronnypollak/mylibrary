package de.oth.rp.library.service;



import de.oth.rp.library.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepo libraryRepo;
}

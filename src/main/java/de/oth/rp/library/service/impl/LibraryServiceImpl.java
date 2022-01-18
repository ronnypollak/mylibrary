package de.oth.rp.library.service.impl;

import de.oth.rp.library.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl {

    @Autowired
    private LibraryRepo libraryRepo;

    //TODO: if repo is empty do setup?
}


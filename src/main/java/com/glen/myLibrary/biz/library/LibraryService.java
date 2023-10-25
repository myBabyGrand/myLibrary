package com.glen.myLibrary.biz.library;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    public Library getLibrary(Long id){
        return new Library();
    }
}

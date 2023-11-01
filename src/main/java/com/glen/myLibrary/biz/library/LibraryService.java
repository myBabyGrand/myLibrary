package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    public Library getLibrary(Long id){
        return libraryRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }
}

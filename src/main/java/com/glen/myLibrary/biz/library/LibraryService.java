package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    public Library getLibrary(Long id){
        return libraryRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    public Library makeTestLibrary(){
        Library library = Library
                .builder()
                .approvedAt(LocalDateTime.now())
                .introduce("test Library")
                .libraryStatus(LibraryStatus.OPERATING)
                .description("Library for Test")
                .name("TEST Library")
                .build();
        return libraryRepository.save(library);
    }
}

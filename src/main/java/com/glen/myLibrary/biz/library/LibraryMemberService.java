package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryMemberService {

    private final LibraryMemberRepository libraryMemberRepository;


    public LibraryMember getLibraryMember(Long libraryMemberId){
        return libraryMemberRepository
                .findById(libraryMemberId).orElseThrow(DataNotFoundException::new);
    }

}

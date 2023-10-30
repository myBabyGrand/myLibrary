package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.library.dto.LibraryMemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryMemberService {

    private final LibraryMemberRepository libraryMemberRepository;


    public LibraryMember getLibraryMember(Long libraryMemberId){
        return libraryMemberRepository
                .findById(libraryMemberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
    }

}

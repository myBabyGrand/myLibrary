package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.library.dto.LibraryMemberDTO;
import org.springframework.stereotype.Service;

@Service
public class LibraryMemberService {


    public LibraryMemberDTO getLibraryMember(Long libraryMemberId){
        return new LibraryMemberDTO();
    }

}

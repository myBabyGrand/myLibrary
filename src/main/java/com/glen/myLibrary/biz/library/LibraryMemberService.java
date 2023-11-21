package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LibraryMemberService {

    private final LibraryMemberRepository libraryMemberRepository;

    public LibraryMember makeTestLibraryMember(Account account, Library library){
        LibraryMember libraryMember = LibraryMember
                .builder()
                .account(account)
                .library(library)
                .libraryMemberType(LibraryMemberType.USER)
                .libraryMemberStatus(LibraryMemberStatus.APPROVED)
                .joinedAt(LocalDateTime.now())
                .build();
        return libraryMemberRepository.save(libraryMember);
    }

    public LibraryMember getLibraryMember(Long libraryMemberId){
        return libraryMemberRepository
                .findById(libraryMemberId).orElseThrow(DataNotFoundException::new);
    }

}

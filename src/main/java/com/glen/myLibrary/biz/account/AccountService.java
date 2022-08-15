package com.glen.myLibrary.biz.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void save(AccountDTO accountDTO){
        Account account = Account.builder()
                .email(accountDTO.getEmail())
                .nickname(accountDTO.getNickname())
                .password(accountDTO.getPassword())
                .accountType(accountDTO.getAccountType())
                .description(accountDTO.getDescription())
                .emailVerified(false)
                .joinedAt(LocalDateTime.now())
                .build();
        accountRepository.save(account);
    }

    public AccountResponse get(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .password(account.getPassword())
                .accountType(account.getAccountType())
                .description(account.getDescription())
                .emailVerified(account.isEmailVerified())
                .joinedAt(account.getJoinedAt())
                .build();
    }

    public List<AccountResponse> getAccountList(AccountSearch accountSearch) {
//        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
//        return accountRepository.findAll(pageable).stream()
//                .map(AccountResponse::new)
//                .collect(Collectors.toList());
        return accountRepository.getPageList(accountSearch).stream()
                .map(AccountResponse::new)
                .collect(Collectors.toList());
    }
}

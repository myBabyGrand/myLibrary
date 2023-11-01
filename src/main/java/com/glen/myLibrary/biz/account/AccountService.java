package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.account.dto.AccountDTO;
import com.glen.myLibrary.biz.account.dto.AccountUpdateDTO;
import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Account account = accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .password(account.getPassword())//TODO : password 암호화
                .accountType(account.getAccountType())
                .description(account.getDescription())
                .emailVerified(account.isEmailVerified())
                .joinedAt(account.getJoinedAt())
                .build();
    }

    public AccountDTO getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        return AccountDTO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .password(account.getPassword())//TODO : password 암호화
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

    @Transactional
    public void updateAccount(long id, AccountUpdateDTO updateRequest){
        Account account = accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        account.from(updateRequest);
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(DataNotFoundException::new);
        accountRepository.delete(account);
    }
}

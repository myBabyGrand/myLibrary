package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.account.dto.AccountDTO;
import com.glen.myLibrary.biz.account.dto.AccountUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/account/save")
    public Map<String, String> createAccount(@RequestBody @Valid AccountDTO request){
        accountService.save(request);
        return Map.of();
    }

    @GetMapping("/account/{accountId}")
    public AccountResponse get(@PathVariable(name = "accountId") Long id){
        AccountResponse accountResponse = accountService.get(id);
        return accountResponse;
    }
    @GetMapping("/accounts")
    public List<AccountResponse> getAccountList(@ModelAttribute AccountSearch accountSearch){
        return accountService.getAccountList(accountSearch);
    }

    @PatchMapping("/account/{accountId}")
    public void updateAccount(@PathVariable Long accountId, @RequestBody @Valid AccountUpdateDTO request){
        accountService.updateAccount(accountId, request);
    }

    @DeleteMapping("/account/{accountId}")
    public void deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
    }
}

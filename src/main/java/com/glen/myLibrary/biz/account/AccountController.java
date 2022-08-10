package com.glen.myLibrary.biz.account;

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
    public Map<String, String> save(@RequestBody @Valid AccountDTO request){
        accountService.save(request);
        return Map.of();
    }

    @GetMapping("/account/{accountId}")
    public AccountResponse get(@PathVariable(name = "accountId") Long id){
        AccountResponse accountResponse = accountService.get(id);
        return accountResponse;
    }
    @GetMapping("/accounts")
    public List<AccountResponse> getAccountList(){
        return accountService.getAccountList();
    }

}

package com.glen.myLibrary.biz.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Account get(@PathVariable(name = "accountId") Long id){
        Account account = accountService.get(id);
        return account;
    }
}